package org.expense.tracker.store.datastores.repos;

import org.expense.tracker.models.Category;
import org.expense.tracker.models.User;
import org.expense.tracker.models.Transaction;
import org.expense.tracker.store.datastores.DataRepository;
import org.expense.tracker.store.datastores.DataManagerDao;
import org.expense.tracker.store.datastores.database.HSQLCategoryDatabase;
import org.expense.tracker.store.datastores.database.HSQLProfileDatabase;
import org.expense.tracker.store.datastores.database.HSQLTransactionDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataRepositoryImpl implements DataRepository {

    DataManagerDao<User> profileData = new HSQLProfileDatabase();
    DataManagerDao<Category> categoryData = new HSQLCategoryDatabase();
    DataManagerDao<Transaction> transData = new HSQLTransactionDatabase();

    public DataRepositoryImpl() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:data/testdb", "username", "pw");
            String sqlStringCreateTable = "CREATE TABLE IF NOT EXISTS PROFILES (profile_id int primary key , " +
                    "profile_name varchar(200), budget float);" +
                    "CREATE TABLE IF NOT EXISTS TRANSACTIONS (profile_id int, transaction_id int, " +
                    "category_id int, amount float, recurring boolean, transaction_note varchar(200), transaction_date Date);"
                    +
                    "CREATE TABLE IF NOT EXISTS CATEGORIES (profile_id int, category_id int, " +
                    "category_type varchar(200), category_name varchar(200), budget float);";
            connection.createStatement().executeQuery(sqlStringCreateTable);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addProfile(String profileName, float budget) {
        int id = getProfiles().size();
        profileData.save(id, new User(profileName, budget));
        return id;
    }

    @Override
    public List<User> getProfiles() {
        return profileData.query(0);
    }

    @Override
    public User getProfile(int id) {
        List<User> query = profileData.query(id);
        for (User user : query) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void updateProfile(User user) {
        profileData.update(user.getUserId(), user);
    }

    @Override
    public void deleteProfile(int profileId) {
        profileData.delete(profileId, getProfile(profileId));
    }

    @Override
    public int addTransaction(int profileId, float amount, boolean recurring, String note, Category category,
                              Date transactionDate, boolean isExpense) {
        int id = getTransactions(profileId).size();
        transData.save(profileId, new Transaction(id, amount, recurring, note, category, transactionDate, isExpense));
        return id;
    }

    @Override
    public List<Transaction> getExpenses(int profileId) {
        List<Transaction> query = transData.query(profileId);
        List<Transaction> expenses = new ArrayList<>();
        for (Transaction transaction : query) {
            if (transaction.getCategory().isExpenseCategory()) {
                expenses.add(transaction);
            }
        }
        return expenses;
    }

    @Override
    public List<Transaction> getIncomes(int profileId) {
        List<Transaction> query = transData.query(profileId);
        List<Transaction> incomes = new ArrayList<>();
        for (Transaction transaction : query) {
            if (!transaction.getCategory().isExpenseCategory()) {
                incomes.add(transaction);
            }
        }
        return incomes;
    }

    @Override
    public int addCategory(int profileId, String categoryName, boolean isExpenseCategory) {
        int id = this.categoryData.query(profileId).size();
        this.categoryData.save(profileId, new Category(id, categoryName, categoryName, isExpenseCategory));
        return id;
    }

    @Override
    public List<Category> getExpenseCategories(int profileId) {
        List<Category> query = categoryData.query(profileId);
        List<Category> expenses = new ArrayList<>();
        for (Category category : query) {
            if (category.isExpenseCategory()) {
                expenses.add(category);
            }
        }
        return expenses;
    }

    @Override
    public List<Transaction> getTransactions(int profileId) {
        return transData.query(profileId);
    }

    @Override
    public List<Category> getIncomeCategories(int profileId) {
        List<Category> query = categoryData.query(profileId);
        List<Category> incomes = new ArrayList<>();
        for (Category category : query) {
            if (!category.isExpenseCategory()) {
                incomes.add(category);
            }
        }
        return incomes;
    }

    @Override
    public List<Category> getCategories(int profileId) {
        return categoryData.query(profileId);
    }

    @Override
    public void updateTransaction(int profileId, Transaction transaction) {
        transData.update(profileId, transaction);
    }

    @Override
    public void deleteTransaction(int profileId, Transaction transaction) {
        transData.delete(profileId, transaction);
    }

    @Override
    public void updateCategory(int profileId, Category category) {
        categoryData.update(profileId, category);
    }

    @Override
    public void deleteCategory(int profileId, Category category) {
        categoryData.delete(profileId, category);
    }
}
