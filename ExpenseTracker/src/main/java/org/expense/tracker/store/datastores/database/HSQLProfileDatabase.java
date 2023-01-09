package org.expense.tracker.store.datastores.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.expense.tracker.models.User;
import org.expense.tracker.store.datastores.DataManagerDao;

public class HSQLProfileDatabase implements DataManagerDao<User> {

    private Connection connection;

    public HSQLProfileDatabase() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            this.connection = DriverManager.getConnection("jdbc:hsqldb:file:data/testdb", "username", "pw");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
 
    @Override
    public void save(int profileId, User user) {

        try {
            String profileName = user.getUserName();
            float budget = 0;
            if (user.getBudget() != 0) {
                budget = user.getBudget();
            }

            String sqlStringInsertProfile =
                    "INSERT INTO PROFILES VALUES(" + profileId + ",'" + profileName + "',"+ budget+");";

            connection.createStatement().executeQuery(sqlStringInsertProfile);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void delete(int profileId, User object) {
        try {
            String sqlStringDeleteProfile = "DELETE FROM PROFILES WHERE profile_id = " + profileId;
            connection.createStatement().executeQuery(sqlStringDeleteProfile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<User> query(int profileId) {
        List<User> objects = new ArrayList<>();
        try {
            String sqlStringSelectProfile = "SELECT * FROM PROFILES";
            ResultSet resultSet = connection.createStatement().executeQuery(sqlStringSelectProfile);

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("profile_id"));
                user.setUserName(resultSet.getString("profile_name"));
                user.setBudget(resultSet.getFloat("budget"));
                objects.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objects;
    }
    @Override
    public void update(int profileId, User user) {

        try {
            String profileName = user.getUserName();
            float budget = user.getBudget();

            String sqlStringUpdateProfile =
                    "UPDATE PROFILES SET profile_name = '" + profileName + "' , budget = " + budget + " WHERE profile_id = " + profileId;

            connection.createStatement().executeQuery(sqlStringUpdateProfile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
