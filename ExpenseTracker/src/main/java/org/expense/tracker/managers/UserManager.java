package org.expense.tracker.managers;

import org.expense.tracker.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> userList;

    public UserManager() {
        userList = new ArrayList<>();
    }

    public int createProfile(String name) {
        int id = userList.size();
        User user = new User(id, name);
        userList.add(user);
        return id;
    }

    public List<User> getProfileList() {
        return userList;
    }

    public void updateProfile(User newUser) {
        User user = getProfile(newUser.getUserId());
        user.setUserName(newUser.getUserName());
    }

    public void deleteProfile(int profileId) {
        userList.remove(profileId);
    }

    public User getProfile(int id) {
        for (User userObj : userList) {
            if (id == userObj.getUserId()) {
                return userObj;
            }
        }
        return null;
    }
}
