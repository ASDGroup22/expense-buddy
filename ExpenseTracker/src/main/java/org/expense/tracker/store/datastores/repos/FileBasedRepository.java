package org.expense.tracker.store.datastores.repos;

import org.expense.tracker.models.Category;
import org.expense.tracker.models.CategoryBudget;
import org.expense.tracker.models.User;
import org.expense.tracker.models.Transaction;
import org.expense.tracker.store.datastores.DataRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileBasedRepository implements DataRepository {
    private Connection connection;

    public FileBasedRepository() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:data/testdb", "username", "pw");
            String sqlStringCreateTable = "CREATE TABLE IF NOT EXISTS PROFILES (profile_id int primary key , " +
                    "profile_name varchar(200), budget float);" +
                    "CREATE TABLE IF NOT EXISTS TRANSACTIONS (profile_id int, transaction_id int, " +
                    "category_id int, amount float, recurring boolean, transaction_note varchar(200), transaction_date Date, is_expense boolean);"
                    +
                    "CREATE TABLE IF NOT EXISTS CATEGORIES (profile_id int, category_id int, " +
                    "is_expense_category boolean,category_name varchar(200));";
            connection.createStatement().executeQuery(sqlStringCreateTable);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addProfile(String profileName) {
        int id = getProfiles().size();
        try {
            String sqlStringInsertProfile =
                    "INSERT INTO PROFILES VALUES(" + id + ",'" + profileName + "');";
            connection.createStatement().executeQuery(sqlStringInsertProfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<User> getProfiles() {
        List<User> objects = new ArrayList<>();
        try {
            String sqlStringSelectProfile = "SELECT * FROM PROFILES";
            ResultSet resultSet = connection.createStatement().executeQuery(sqlStringSelectProfile);

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("profile_id"));
                user.setUserName(resultSet.getString("profile_name"));
                objects.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    @Override
    public User getProfile(int id) {

        try {
            String sqlStringSelectProfile = "SELECT * FROM PROFILES";
            ResultSet resultSet = connection.createStatement().executeQuery(sqlStringSelectProfile);

            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("profile_id"));
                user.setUserName(resultSet.getString("profile_name"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateProfile(User user) {
        try {
            String profileName = user.getUserName();

            String sqlStringUpdateProfile =
                    "UPDATE PROFILES SET profile_name = '" + profileName + "' WHERE profile_id = " + user.getUserId();

            connection.createStatement().executeQuery(sqlStringUpdateProfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProfile(int profileId) {
        try {
            String sqlStringDeleteProfile = "DELETE FROM PROFILES WHERE profile_id = " + profileId;
            connection.createStatement().executeQuery(sqlStringDeleteProfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addTransaction(int profileId, float amount, boolean recurring, String note, Category category,
                              Date transactionDate, boolean isExpense) {
        int id = getTransactions(profileId).size();
        try {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO TRANSACTIONS VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
            statement.setInt(1, profileId);
            statement.setInt(2, id);
            statement.setInt(3, category.getId());
            statement.setFloat(4, amount);
            statement.setBoolean(5, recurring);
            statement.setString(6, note);
            statement.setDate(7, new java.sql.Date(transactionDate.getTime()));
            statement.setBoolean(8, isExpense);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    Category getCategory(int profileId, int categoryId) throws SQLException {
        String sqlStringSelectProfile = "SELECT * FROM CATEGORIES WHERE category_id = " + categoryId +
                " AND profile_id = " + profileId;
        ResultSet resultSet = connection.createStatement().executeQuery(sqlStringSelectProfile);

        while (resultSet.next()) {

            if (resultSet.getBoolean("is_expense_category")) {
                return new Category(resultSet.getInt("category_id"),
                        resultSet.getString("category_name"), true);
            } else {
                return new Category(resultSet.getInt("category_id"),
                        resultSet.getString("category_name"), false);
            }

        }
        return null;
    }

    private List<Transaction> getTransactionList(int profileId, boolean isExpense) {
        List<Transaction> expenses = new ArrayList<>();
        try {
            String sqlStringSelectTransaction = "SELECT * FROM TRANSACTIONS WHERE profile_id = " + profileId +
                    " AND is_expense = " + isExpense;
            ResultSet resultSet = connection.createStatement().executeQuery(sqlStringSelectTransaction);

            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(resultSet.getInt("transaction_id"));
                transaction.setAmount(resultSet.getFloat("amount"));
                transaction.setCategory(getCategory(profileId, resultSet.getInt("category_id")));
                transaction.setRecurring(resultSet.getBoolean("recurring"));
                transaction.setNote(resultSet.getString("transaction_note"));
                transaction.setTransactionDate(resultSet.getDate("transaction_date"));
                transaction.setIsExpense(resultSet.getBoolean("is_expense"));

                expenses.add(transaction);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenses;
    }

    @Override
    public List<Transaction> getExpenses(int profileId) {
        return getTransactionList(profileId, true);
    }


    @Override
    public List<Transaction> getIncomes(int profileId) {
        return getTransactionList(profileId, false);
    }

    @Override
    public int addCategory(int profileId, String categoryName, boolean isExpenseCategory) {
        int id = 0;
        try {
            String sqlStringSelectProfile = "SELECT COUNT(*) FROM CATEGORIES WHERE profile_id = " + profileId;
            ResultSet resultSet = connection.createStatement().executeQuery(sqlStringSelectProfile);

            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String sqlStringInsertCategory =
                    "INSERT INTO CATEGORIES VALUES(" + profileId + "," + id + "," + isExpenseCategory + ",'" + categoryName + "', 0.0E0);";
            connection.createStatement().executeQuery(sqlStringInsertCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<Category> getCategories(int profileId) {
        List<Category> expenses = new ArrayList<>();
        try {
            String sqlStringSelectProfile = "SELECT * FROM CATEGORIES WHERE profile_id = " + profileId;
            ResultSet resultSet = connection.createStatement().executeQuery(sqlStringSelectProfile);

            while (resultSet.next()) {
                expenses.add(new Category(resultSet.getInt("category_id"), resultSet.getString("category_name")
                        , resultSet.getBoolean("is_expense_category")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenses;
    }

    private List<Category> getCategories(int profileId, boolean isExpenseCategory) {
        List<Category> expenses = new ArrayList<>();
        try {
            String sqlStringSelectProfile = "SELECT * FROM CATEGORIES WHERE profile_id = " + profileId +
                    " AND is_expense_category = " + isExpenseCategory;
            ResultSet resultSet = connection.createStatement().executeQuery(sqlStringSelectProfile);

            while (resultSet.next()) {
                expenses.add(new Category(resultSet.getInt("category_id"), resultSet.getString("category_name")
                        , true));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenses;
    }

    @Override
    public List<Category> getExpenseCategories(int profileId) {
        return getCategories(profileId, true);
    }

    @Override
    public List<Transaction> getTransactions(int profileId) {
        List<Transaction> objects = new ArrayList<>();
        try {
            String sqlStringSelectTransaction = "SELECT * FROM TRANSACTIONS WHERE profile_id = " + profileId;
            ResultSet resultSet = connection.createStatement().executeQuery(sqlStringSelectTransaction);

            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(resultSet.getInt("transaction_id"));
                transaction.setAmount(resultSet.getFloat("amount"));
                transaction.setCategory(getCategory(profileId, resultSet.getInt("category_id")));
                transaction.setRecurring(resultSet.getBoolean("recurring"));
                transaction.setNote(resultSet.getString("transaction_note"));
                transaction.setTransactionDate(resultSet.getDate("transaction_date"));
                transaction.setIsExpense(resultSet.getBoolean("is_expense"));

                objects.add(transaction);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objects;
    }

    @Override
    public List<CategoryBudget> getCategoryBudgets(int profileId) {
        List<CategoryBudget> budgetList = new ArrayList<>();
        try {
            String sqlStringSelectProfile = "SELECT * FROM CATEGORIES WHERE profile_id = " + profileId + " AND is_expense_category = true";
            ResultSet resultSet = connection.createStatement().executeQuery(sqlStringSelectProfile);

            while (resultSet.next()) {
                budgetList.add(new CategoryBudget(resultSet.getInt("category_id"),
                        new Category(resultSet.getInt("category_id"), resultSet.getString("category_name")
                        , resultSet.getBoolean("is_expense_category")), resultSet.getDouble("budget")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return budgetList;
    }

    @Override
    public void updateBudget(int profileId, int categoryId, double budget) {
        try {

            String sqlStringUpdateCategory =
                    "UPDATE CATEGORIES SET budget = " + budget
                            + " WHERE profile_id = " + profileId + " AND category_id = " +
                            categoryId;
            connection.createStatement().executeQuery(sqlStringUpdateCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Category> getIncomeCategories(int profileId) {
        return getCategories(profileId, false);
    }

    @Override
    public void updateTransaction(int profileId, Transaction transaction) {
        try {
            int transactionId = transaction.getTransactionId();
            float transactionAmount = transaction.getAmount();
            int categoryId = transaction.getCategory().getId();
            Boolean recurring = transaction.isRecurring();
            String transactionNote = transaction.getNote();
            Boolean isExpense = transaction.isExpense();
            java.sql.Date transactionDate = new java.sql.Date(transaction.getTransactionDate().getTime());

            PreparedStatement statement =
                    connection.prepareStatement("UPDATE TRANSACTIONS SET category_id = ? , amount = ? , recurring = " +
                            "? , transaction_note = ? , transaction_date = ?, is_expense = ? WHERE profile_id = ? AND transaction_id = ?");
            statement.setInt(1, categoryId);
            statement.setFloat(2, transactionAmount);
            statement.setBoolean(3, recurring);
            statement.setString(4, transactionNote);
            statement.setDate(5, transactionDate);
            statement.setBoolean(6, isExpense);
            statement.setInt(7, profileId);
            statement.setInt(8, transactionId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTransaction(int profileId, Transaction transaction) {
        try {
            int transactionId = transaction.getTransactionId();
            String sqlStringDeleteTransaction = "DELETE FROM TRANSACTIONS WHERE transaction_id = " + transactionId;
            connection.createStatement().executeQuery(sqlStringDeleteTransaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCategory(int profileId, Category category) {
        try {
            int categoryId = category.getId();
            String categoryName = category.getName();

            String sqlStringUpdateCategory =
                    "UPDATE CATEGORIES SET is_expense_category = '" + category.isExpenseCategory() + "', category_name = '" + categoryName
                            + "' WHERE profile_id = " + profileId + " AND category_id = " +
                            categoryId;

            connection.createStatement().executeQuery(sqlStringUpdateCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(int profileId, Category category) {
        try {
            int categoryId = category.getId();

            String sqlStringDeleteCategory = "DELETE FROM CATEGORIES WHERE category_id = " + categoryId;
            connection.createStatement().executeQuery(sqlStringDeleteCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
