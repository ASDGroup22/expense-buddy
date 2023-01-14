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
        createUser("user1");
    }

    // CRUD for users
    public int createUser(String userName) {
        int userId = dataRepository.addUser(userName);
        loadCategoryPresets(userId);
        return userId;
    }

    private DataRepository selectDataRepository() {
        switch (getDatabaseProp()) {
            case "DB":
                return new FileBasedRepository();
            default:
                return new InMemoryRepository();
        }
    }

    public List<User> getUsers() {
        return dataRepository.addUsers();
    }

    public User getUser(int userId) {
        return dataRepository.getUser(userId);
    }

    public void updateUser(User newUser) {
        dataRepository.updateUser(newUser);
    }

    public void deleteUser(int userId) {
        List<Category> categories = new ArrayList<>(dataRepository.getCategories(userId));
        for (Category category : categories) {
            dataRepository.deleteCategory(userId, category);
        }
        dataRepository.deleteUser(userId);
    }

    // CRUD for Transactions 
    public int createTransaction(int userId, float amount, boolean recurring, String note, Category category,
                                 Date transactionDate, boolean isExpense) {
        return dataRepository.addTransaction(userId, amount, recurring, note, category, transactionDate, isExpense);
    }

    public List<Transaction> getTransactions(int userId) {
        return dataRepository.getTransactions(userId);
    }

    public List<Transaction> getExpenseTransactions(int userId) {
        return dataRepository.getExpenses(userId);
    }

    public List<Transaction> getIncomeTransactions(int userId) {
        return dataRepository.getIncomes(userId);
    }

    public void updateTransaction(int userId, Transaction transaction) {
        dataRepository.updateTransaction(userId, transaction);
    }

    public void deleteTransaction(int userId, Transaction transaction) {
        dataRepository.deleteTransaction(userId, transaction);
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

    public int createCategory(int userId, String categoryName, boolean isExpenseCategory) {
        return dataRepository.addCategory(userId, categoryName, isExpenseCategory);
    }

    public List<Category> getCategories(int userId) {
        return dataRepository.getCategories(userId);
    }

    public List<Category> getExpenseCategories(int userId) {
        return dataRepository.getExpenseCategories(userId);
    }


    public List<Category> getIncomeCategories(int userId) {
        return dataRepository.getIncomeCategories(userId);
    }


    public void updateCategory(int userId, Category category) {
        dataRepository.updateCategory(userId, category);
    }


    public void deleteCategory(int userId, Category category) {
        dataRepository.deleteCategory(userId, category);
    }

    public float getIncomeSummary(int userId) {
        List<Transaction> incomeTransactions = getIncomeTransactions(userId);
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

    private void loadCategoryPresets(int userId) {
        createCategory(userId, "Salary", false);
        createCategory(userId, "Interest", false);
        createCategory(userId, "Rent", true);
        createCategory(userId, "Entertainment", true);
        createCategory(userId, "Food", true);
    }

    public DataRepository getBudgetRepository() {
        return dataRepository;
    }
}
