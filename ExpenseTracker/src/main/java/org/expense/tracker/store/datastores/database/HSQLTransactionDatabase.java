package org.expense.tracker.store.datastores.database;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.expense.tracker.models.Category;
import org.expense.tracker.models.Transaction;
import org.expense.tracker.store.datastores.DataManagerDao;

public class HSQLTransactionDatabase implements DataManagerDao<Transaction> {

    private Connection connection;

    public HSQLTransactionDatabase() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            this.connection = DriverManager.getConnection("jdbc:hsqldb:file:data/testdb", "username", "pw");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(int profileId, Transaction transaction) {

        try {
            int transactionId = transaction.getTransactionId();
            float transactionAmount = transaction.getAmount();
            int categoryId = transaction.getCategory().getId();
            boolean recurring = transaction.isRecurring();
            String transactionNote = transaction.getNote();
            Date transactionDate = new java.sql.Date(transaction.getTransactionDate().getTime());

            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO TRANSACTIONS VALUES(?, ?, ?, ?, ?, ?, ?);");
            statement.setInt(1, profileId);
            statement.setInt(2, transactionId);
            statement.setInt(3, categoryId);
            statement.setFloat(4, transactionAmount);
            statement.setBoolean(5, recurring);
            statement.setString(6, transactionNote);
            statement.setDate(7, transactionDate);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int profileId, Transaction transaction) {

        try {
            int transactionId = transaction.getTransactionId();

            String sqlStringDeleteTransaction = "DELETE FROM TRANSACTIONS WHERE transaction_id = " + transactionId;
            connection.createStatement().executeQuery(sqlStringDeleteTransaction);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public List<Transaction> query(int profileId) {

        List<Transaction> objects = new ArrayList<>();

        try {
            String sqlStringSelectTransaction = "SELECT * FROM TRANSACTIONS WHERE profile_id = " + profileId;
            ResultSet resultSet = connection.createStatement().executeQuery(sqlStringSelectTransaction);

            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(resultSet.getInt("transaction_id"));
                transaction.setAmount(resultSet.getFloat("amount"));
                transaction.setCategory(getCategory(resultSet.getInt("category_id")));
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
    
    private Category getCategory(int categoryId) throws SQLException {
        String sqlStringSelectProfile = "SELECT * FROM CATEGORIES WHERE category_id = " + categoryId;
        ResultSet resultSet = connection.createStatement().executeQuery(sqlStringSelectProfile);

        while (resultSet.next()) {

            if (resultSet.getString("category_type").equalsIgnoreCase("EXPENSE")) {
                Category expense = new Category(resultSet.getInt("category_id"),
                        resultSet.getString("category_name"),
                        resultSet.getString("category_name"), true);
                //set budget
                // resultSet.getFloat("budget"))
                return expense;
            } else if (resultSet.getString("category_type").equalsIgnoreCase("INCOME")) {
                Category income = new Category(resultSet.getInt("category_id"),
                        resultSet.getString("category_name"),
                        resultSet.getString("category_name"), false);
                return income;
            }

        }
        return null;
    }
    
    @Override
    public void update(int profileId, Transaction transaction) {

        try {
            int transactionId = transaction.getTransactionId();
            float transactionAmount = transaction.getAmount();
            int categoryId = transaction.getCategory().getId();
            Boolean recurring = transaction.isRecurring();
            String transactionNote = transaction.getNote();
            Date transactionDate = new java.sql.Date(transaction.getTransactionDate().getTime());

            PreparedStatement statement =
                    connection.prepareStatement("UPDATE TRANSACTIONS SET category_id = ? , amount = ? , recurring = " +
                            "? , transaction_note = ? , transaction_date = ? WHERE profile_id = ? AND transaction_id " +
                            "= ?");
                    connection.prepareStatement("INSERT INTO TRANSACTIONS VALUES(?, ?, ?, ?, ?, ?, ?);");
            statement.setInt(1, categoryId);
            statement.setFloat(2, transactionAmount);
            statement.setBoolean(3, recurring);
            statement.setString(4, transactionNote);
            statement.setDate(5, transactionDate);
            statement.setInt(6, profileId);
            statement.setInt(7, transactionId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
