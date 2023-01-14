package org.expense.tracker.store.datastores.repos;

import org.expense.tracker.managers.CategoryManager;
import org.expense.tracker.managers.UserManager;
import org.expense.tracker.managers.TransactionManager;
import org.expense.tracker.models.Category;
import org.expense.tracker.models.Budget;
import org.expense.tracker.models.User;
import org.expense.tracker.models.Transaction;
import org.expense.tracker.store.datastores.DataRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InMemoryRepository implements DataRepository {
    UserManager userManager = new UserManager();

    @Override
    public int addUser(String userName) {
        return userManager.createProfile(userName);
    }

    @Override
    public List<User> addUsers() {
        return userManager.getProfileList();
    }

    @Override
    public User getUser(int userId) {
        return userManager.getProfile(userId);
    }

    @Override
    public int addTransaction(int userId, float amount, boolean recurring, String note, Category category,
                              Date transactionDate, boolean isExpense) {
        TransactionManager transactionManager = userManager.getProfile(userId).getTransactionManager();
        return transactionManager.createTransaction(amount, recurring, note, category, transactionDate, isExpense);
    }

    @Override
    public int addCategory(int userId, String categoryName, boolean isExpenseCategory) {
        CategoryManager transactionManager = userManager.getProfile(userId).getCategoryManager();
        return transactionManager.createCategory(categoryName, isExpenseCategory);
    }

    @Override
    public List<Transaction> getTransactions(int userId) {
        return userManager.getProfile(userId).getTransactionManager().getTransactionList();
    }

    @Override
    public List<Budget> getCategoryBudgets(int userId) {
        return userManager.getProfile(userId).getBudgetManager().getBudgetList();
    }

    @Override
    public double getCategoryBudget(int userId, int categoryId) {
        return userManager.getProfile(userId).getBudgetManager().getBudgetForCategory(categoryId).getBudgetVal();
    }

    @Override
    public List<Transaction> getExpenses(int userId) {
        List<Transaction> transactionList =
                userManager.getProfile(userId).getTransactionManager().getTransactionList();
        List<Transaction> expenses = new ArrayList<>();
        for (Transaction transaction: transactionList) {
            if (transaction.getCategory().isExpenseCategory()) {
                expenses.add(transaction);
            }
        }
        return expenses;
    }

    @Override
    public List<Transaction> getIncomes(int userId) {
        List<Transaction> transactionList =
                userManager.getProfile(userId).getTransactionManager().getTransactionList();
        List<Transaction> incomes = new ArrayList<>();
        for (Transaction transaction: transactionList) {
            if (!transaction.getCategory().isExpenseCategory()) {
                incomes.add(transaction);
            }
        }
        return incomes;
    }

    @Override
    public List<Category> getCategories(int userId) {
        return userManager.getProfile(userId).getCategoryManager().getCategoryList();
    }

    @Override
    public List<Category> getExpenseCategories(int userId) {
        return userManager.getProfile(userId).getCategoryManager().getExpenseCategories();
    }

    @Override
    public List<Category> getIncomeCategories(int userId) {
        return userManager.getProfile(userId).getCategoryManager().getIncomeCategories();
    }

    @Override
    public void updateUser(User user) {
        userManager.updateProfile(user);
    }

    @Override
    public void deleteUser(int userId) {
        userManager.deleteProfile(userId);
    }

    @Override
    public void updateTransaction(int userId, Transaction transaction) {
        userManager.getProfile(userId).getTransactionManager().updateTransaction(transaction);
    }

    @Override
    public void updateBudget(int userId, int categoryId, double budget) {
        userManager.getProfile(userId).getBudgetManager().updateBudget(categoryId, budget);
    }

    @Override
    public void deleteTransaction(int userId, Transaction transaction) {
        userManager.getProfile(userId).getTransactionManager().deleteTransaction(transaction.getTransactionId());
    }

    @Override
    public void updateCategory(int userId, Category category) {
        userManager.getProfile(userId).getCategoryManager().updateCategory(category);
    }

    @Override
    public void deleteCategory(int userId, Category category) {
        userManager.getProfile(userId).getCategoryManager().deleteCategory(category);
    }
}
