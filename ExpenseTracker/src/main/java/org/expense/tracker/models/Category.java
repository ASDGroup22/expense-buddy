package org.expense.tracker.models;
public class Category {

    int id;
    String name;

    String description;
    boolean isExpenseCategory;

    public Category(int id, String name, String description, boolean isExpenseCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isExpenseCategory = isExpenseCategory;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExpenseCategory() {
        return isExpenseCategory;
    }

    public void setExpenseCategory(boolean expenseCategory) {
        isExpenseCategory = expenseCategory;
    }


}
