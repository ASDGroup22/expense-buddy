package org.expense.tracker.controllers;

import org.expense.tracker.models.Category;
import org.expense.tracker.models.Budget;
import org.expense.tracker.models.User;
import org.expense.tracker.models.Transaction;
import org.expense.tracker.store.datastores.DataRepository;
import org.expense.tracker.store.datastores.repos.FileBasedRepository;
import org.expense.tracker.store.datastores.repos.InMemoryRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Controller {

    private final DataRepository dataRepository = selectDataRepository();

    public Controller() {
        createProfile("user1");
    }

    // CRUD for profiles
    public int createProfile(String profileName) {
        int profileId = dataRepository.addProfile(profileName);
        loadCategoryPresets(profileId);
        return profileId;
    }

    private DataRepository selectDataRepository() {
        switch (getDatabaseProp()) {
            case "DB":
                return new FileBasedRepository();
            default:
                return new InMemoryRepository();
        }
    }

    public List<User> getProfiles() {
        return dataRepository.getProfiles();
    }

    public User getProfile(int profileId) {
        return dataRepository.getProfile(profileId);
    }

    public void updateProfile(User newUser) {
        dataRepository.updateProfile(newUser);
    }

    public void deleteProfile(int profileId) {
        List<Category> categories = new ArrayList<>(dataRepository.getCategories(profileId));
        for (Category category : categories) {
            dataRepository.deleteCategory(profileId, category);
        }
        dataRepository.deleteProfile(profileId);
    }

    // CRUD for Transactions 
    public int createTransaction(int profileId, float amount, boolean recurring, String note, Category category,
                                 Date transactionDate, boolean isExpense) {
        return dataRepository.addTransaction(profileId, amount, recurring, note, category, transactionDate, isExpense);
    }

    public List<Transaction> getTransactions(int profileId) {
        return dataRepository.getTransactions(profileId);
    }

    public List<Transaction> getExpenseTransactions(int profileId) {
        return dataRepository.getExpenses(profileId);
    }

    public List<Transaction> getIncomeTransactions(int profileId) {
        return dataRepository.getIncomes(profileId);
    }

    public void updateTransaction(int profileId, Transaction transaction) {
        dataRepository.updateTransaction(profileId, transaction);
    }

    public void deleteTransaction(int profileId, Transaction transaction) {
        dataRepository.deleteTransaction(profileId, transaction);
    }


    // CRUD for budget

    public List<Budget> getCategoryBudgets(int profileId) {
        return dataRepository.getCategoryBudgets(profileId);
    }

    public void updateBudget(int profileId, int categoryId, double budget) {
        dataRepository.updateBudget(profileId, categoryId, budget);
    }

    public double getTotalBudget(int profileId) {
        List<Budget> budgetList = getCategoryBudgets(profileId);
        double total = 0;
        for (Budget budget : budgetList) {
            total += budget.getBudgetVal();
        }
        return total;
    }

    // CRUD for Categories

//    public int createIncomeCategory(int profileId, String categoryName){
//        return dataRepository.addCategory(profileId, categoryName, false);
//    }

    public int createCategory(int profileId, String categoryName, boolean isExpenseCategory) {
        return dataRepository.addCategory(profileId, categoryName, isExpenseCategory);
    }

    public List<Category> getCategories(int profileId) {
        return dataRepository.getCategories(profileId);
    }

    public List<Category> getExpenseCategories(int profileId) {
        return dataRepository.getExpenseCategories(profileId);
    }


    public List<Category> getIncomeCategories(int profileId) {
        return dataRepository.getIncomeCategories(profileId);
    }


    public void updateCategory(int profileId, Category category) {
        dataRepository.updateCategory(profileId, category);
    }


    public void deleteCategory(int profileId, Category category) {
        dataRepository.deleteCategory(profileId, category);
    }

    public float getIncomeSummary(int profileId) {
        List<Transaction> incomeTransactions = getIncomeTransactions(profileId);
        float summary = 0;
        for (Transaction transaction : incomeTransactions) {
            summary += transaction.getAmount();
        }
        return summary;
    }

    public float getExpenseSummary(int profileId) {
        List<Transaction> expenseTransactions = getExpenseTransactions(profileId);
        float summary = 0;
        for (Transaction transaction : expenseTransactions) {
            summary += transaction.getAmount();
        }
        return summary;
    }

    public float getCategorySummary(int profileId, int cataId) {
        float summary = 0;
        List<Transaction> transactions = getTransactions(profileId);
        for (Transaction transaction : transactions) {
            if (cataId == transaction.getCategory().getId()) {
                summary += transaction.getAmount();
            }
        }
        return summary;
    }

    public static String getDatabaseProp() {
        Properties prop = new Properties();
        String propFileName = System.getProperty("config.file", "config.properties");
        ;

        InputStream inputStream = Controller.class.getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            try {
                prop.load(inputStream);
                return prop.getProperty("datastore");
            } catch (IOException e) {
                return "";
            }
        }
        return "";
    }

    private void loadCategoryPresets(int profileId) {
        createCategory(profileId, "Salary", false);
        createCategory(profileId, "Interest", false);
        createCategory(profileId, "Rent", true);
        createCategory(profileId, "Entertainment", true);
        createCategory(profileId, "Food", true);
    }

    public DataRepository getBudgetRepository() {
        return dataRepository;
    }
}
