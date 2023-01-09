package org.expense.tracker.models;
public class Category {

    int id;
    String name;
    boolean isExpenseCategory;

    public Category(int id, String name, boolean isExpenseCategory) {
        this.id = id;
        this.name = name;
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
