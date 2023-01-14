package org.expense.tracker.store.datastores.repos;

import org.expense.tracker.managers.CategoryManager;
import org.expense.tracker.managers.UserManager;
import org.expense.tracker.managers.TransactionManager;
import org.expense.tracker.models.Category;
import org.expense.tracker.models.CategoryBudget;
import org.expense.tracker.models.User;
import org.expense.tracker.models.Transaction;
import org.expense.tracker.store.datastores.DataRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InMemoryRepository implements DataRepository {
    UserManager userManager = new UserManager();

    @Override
    public int addProfile(String profileName, float budget) {
        return userManager.createProfile(profileName, budget);
    }

    @Override
    public List<User> getProfiles() {
        return userManager.getProfileList();
    }

    @Override
    public User getProfile(int profileId) {
        return userManager.getProfile(profileId);
    }

    @Override
    public int addTransaction(int profileId, float amount, boolean recurring, String note, Category category,
                              Date transactionDate, boolean isExpense) {
        TransactionManager transactionManager = userManager.getProfile(profileId).getTransactionManager();
        return transactionManager.createTransaction(amount, recurring, note, category, transactionDate, isExpense);
    }

    @Override
    public int addCategory(int profileId, String categoryName, boolean isExpenseCategory) {
        CategoryManager transactionManager = userManager.getProfile(profileId).getCategoryManager();
        return transactionManager.createCategory(categoryName, isExpenseCategory);
    }

    @Override
    public List<Transaction> getTransactions(int profileId) {
        return userManager.getProfile(profileId).getTransactionManager().getTransactionList();
    }

    @Override
    public List<CategoryBudget> getCategoryBudgets(int profileId) {
        return userManager.getProfile(profileId).getBudgetManager().getBudgetList();
    }

    @Override
    public List<Transaction> getExpenses(int profileId) {
        List<Transaction> transactionList =
                userManager.getProfile(profileId).getTransactionManager().getTransactionList();
        List<Transaction> expenses = new ArrayList<>();
        for (Transaction transaction: transactionList) {
            if (transaction.getCategory().isExpenseCategory()) {
                expenses.add(transaction);
            }
        }
        return expenses;
    }

    @Override
    public List<Transaction> getIncomes(int profileId) {
        List<Transaction> transactionList =
                userManager.getProfile(profileId).getTransactionManager().getTransactionList();
        List<Transaction> incomes = new ArrayList<>();
        for (Transaction transaction: transactionList) {
            if (!transaction.getCategory().isExpenseCategory()) {
                incomes.add(transaction);
            }
        }
        return incomes;
    }

    @Override
    public List<Category> getCategories(int profileId) {
        return userManager.getProfile(profileId).getCategoryManager().getCategoryList();
    }

    @Override
    public List<Category> getExpenseCategories(int profileId) {
        return userManager.getProfile(profileId).getCategoryManager().getExpenseCategories();
    }

    @Override
    public List<Category> getIncomeCategories(int profileId) {
        return userManager.getProfile(profileId).getCategoryManager().getIncomeCategories();
    }

    @Override
    public void updateProfile(User user) {
        userManager.updateProfile(user);
    }

    @Override
    public void deleteProfile(int profileId) {
        userManager.deleteProfile(profileId);
    }

    @Override
    public void updateTransaction(int profileId, Transaction transaction) {
        userManager.getProfile(profileId).getTransactionManager().updateTransaction(transaction);
    }

    @Override
    public void updateBudget(int profileId, int categoryId, double budget) {
        userManager.getProfile(profileId).getBudgetManager().updateBudget(categoryId, budget);
    }

    @Override
    public void deleteTransaction(int profileId, Transaction transaction) {
        userManager.getProfile(profileId).getTransactionManager().deleteTransaction(transaction.getTransactionId());
    }

    @Override
    public void updateCategory(int profileId, Category category) {
        userManager.getProfile(profileId).getCategoryManager().updateCategory(category);
    }

    @Override
    public void deleteCategory(int profileId, Category category) {
        userManager.getProfile(profileId).getCategoryManager().deleteCategory(category);
    }
}
