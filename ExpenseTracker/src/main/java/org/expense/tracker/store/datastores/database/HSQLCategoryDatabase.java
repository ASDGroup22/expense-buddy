package org.expense.tracker.store.datastores.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.expense.tracker.models.Category;
import org.expense.tracker.store.datastores.DataManagerDao;

public class HSQLCategoryDatabase implements DataManagerDao<Category> {

    private Connection connection;

    public HSQLCategoryDatabase() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            this.connection = DriverManager.getConnection("jdbc:hsqldb:file:data/testdb", "username", "pw");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(int profileId, Category category) {
        try {
            int categoryId = category.getId();
            float budget = 0;

            String categoryName = category.getName();


            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO CATEGORIES VALUES(?, ?, ?, ?);");
            statement.setInt(1, profileId);
            statement.setInt(2, categoryId);
            statement.setBoolean(3, category.isExpenseCategory());
            statement.setString(4, categoryName);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int profileId, Category category) {
        try {
            int categoryId = category.getId();

            String sqlStringDeleteCategory = "DELETE FROM CATEGORIES WHERE category_id = " + categoryId;
            connection.createStatement().executeQuery(sqlStringDeleteCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Category> query(int profileId) {
        List<Category> objects = new ArrayList<>();
        try {
            String sqlStringSelectProfile = "SELECT * FROM CATEGORIES WHERE profile_id = " + profileId;
            ResultSet resultSet = connection.createStatement().executeQuery(sqlStringSelectProfile);

            while (resultSet.next()) {

                if (resultSet.getBoolean("is_expense_category")) {
                    Category expense = new Category(resultSet.getInt("category_id"), resultSet.getString("category_name"),
                            resultSet.getString("category_name"), true);
                    //TODO: set budget
                    //resultSet.getFloat("budget")
                    objects.add(expense);
                } else if (!resultSet.getBoolean("is_expense_category")) {
                    Category income = new Category(resultSet.getInt("category_id"),
                            resultSet.getString("category_name"),
                            resultSet.getString("category_name"), false);
                    objects.add(income);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objects;
    }

    @Override
    public void update(int profileId, Category category) {

        try {
            int categoryId = category.getId();

            String categoryType = null;
            float budget = 0;

            if (category.isExpenseCategory()) {
                categoryType = "EXPENSE";
                // TODO: set budget
                // budget = expense.getBudget();
                budget = 0.0f;
            } else {
                categoryType = "INCOME";
            }

            String categoryName = category.getName();

            String sqlStringUpdateCategory =
                    "UPDATE CATEGORIES SET category_type = '" + categoryType + "', category_name = '" + categoryName
                            + "', budget = " + budget + " WHERE profile_id = " + profileId + " AND category_id = " +
                            categoryId;

            connection.createStatement().executeQuery(sqlStringUpdateCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
