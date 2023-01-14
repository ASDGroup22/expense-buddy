package org.expense.tracker.store.datastores;

import org.expense.tracker.models.Category;
import org.expense.tracker.models.Budget;
import org.expense.tracker.models.User;
import org.expense.tracker.models.Transaction;

import java.util.Date;
import java.util.List;

public interface DataRepository {
    int addUser(String userName);
    List<User> addUsers();
    User getUser(int userId);
    void updateUser(User user);
    void deleteUser(int userId);
    
    int addTransaction(int userId, float amount, boolean recurring, String note, Category category,
                       Date transactionDate, boolean isExpense);
    List<Transaction> getTransactions(int userId);
    List<Budget> getCategoryBudgets(int userId);
    double getCategoryBudget(int userId, int categoryId);
    List<Transaction> getExpenses(int userId);
    List<Transaction> getIncomes(int userId);
    void updateTransaction(int userId, Transaction transaction);
    void updateBudget(int userId, int categoryId, double budget);
    void deleteTransaction(int userId, Transaction transaction);
    int addCategory(int userId, String categoryName, boolean isExpenseCategory);
    List<Category> getCategories(int userId);
    List<Category> getExpenseCategories(int userId);
    List<Category> getIncomeCategories(int userId);
    void updateCategory(int userId, Category category);
    void deleteCategory(int userId, Category category);
}
