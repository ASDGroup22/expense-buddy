package org.expense.tracker.store.datastores;

import org.expense.tracker.models.Category;
import org.expense.tracker.models.CategoryBudget;
import org.expense.tracker.models.User;
import org.expense.tracker.models.Transaction;

import java.util.Date;
import java.util.List;

public interface DataRepository {
    int addProfile(String profileName, float budget);
    List<User> getProfiles();
    User getProfile(int profileId);
    void updateProfile(User user);
    void deleteProfile(int profileId);
    
    int addTransaction(int profileId, float amount, boolean recurring, String note, Category category,
                       Date transactionDate, boolean isExpense);
    List<Transaction> getTransactions(int profileId);
    List<CategoryBudget> getCategoryBudgets(int profileId);
    List<Transaction> getExpenses(int profileId);
    List<Transaction> getIncomes(int profileId);
    void updateTransaction(int profileId, Transaction transaction);
    void updateBudget(int profileId, int categoryId, double budget);
    void deleteTransaction(int profileId, Transaction transaction);
    int addCategory(int profileId, String categoryName, boolean isExpenseCategory);
    List<Category> getCategories(int profileId);
    List<Category> getExpenseCategories(int profileId);
    List<Category> getIncomeCategories(int profileId);
    void updateCategory(int profileId, Category category);
    void deleteCategory(int profileId, Category category);
}
