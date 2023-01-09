package org.expense.tracker.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.expense.tracker.managers.CategoryManager;
import org.expense.tracker.managers.TransactionManager;


@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE,
        creatorVisibility = JsonAutoDetect.Visibility.NONE
)
public class User {
    @JsonProperty
    private int userId;
    @JsonProperty
    private String userName;
    private TransactionManager transactionManager;
    private CategoryManager categoryManager;
    private float budget;

    // Represents total budget
    public User() {
    }

    public User(int userId, String userName, float budget) {
        this.userId = userId;
        this.userName = userName;
        this.transactionManager = new TransactionManager();
        this.categoryManager = new CategoryManager();
    }

    public User(String userName, float budget) {
        this.userName = userName;
        this.transactionManager = new TransactionManager();
        this.categoryManager = new CategoryManager();
        this.budget = budget;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public CategoryManager getCategoryManager() {
        return categoryManager;
    }

    public void setCategoryManager(CategoryManager categoryManager) {
        this.categoryManager = categoryManager;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return userName;
    }
}
