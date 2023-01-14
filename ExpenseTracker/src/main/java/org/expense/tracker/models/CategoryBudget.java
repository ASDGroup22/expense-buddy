package org.expense.tracker.models;

public class CategoryBudget {

    int categoryId;

    Category category;
    double budgetVal;

    public CategoryBudget(int categoryId, Category category, double budgetVal) {
        this.categoryId = categoryId;
        this.category = category;
        this.budgetVal = budgetVal;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getBudgetVal() {
        return budgetVal;
    }

    public void setBudgetVal(double budgetVal) {
        this.budgetVal = budgetVal;
    }
}
