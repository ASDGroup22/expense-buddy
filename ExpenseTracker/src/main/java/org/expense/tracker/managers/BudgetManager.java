package org.expense.tracker.managers;

import org.expense.tracker.models.CategoryBudget;
import org.expense.tracker.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BudgetManager {
    private List<CategoryBudget> budgetList;

    public BudgetManager() {
        budgetList = new ArrayList<>();
    }

    public List<CategoryBudget> getBudgetList() {
        return budgetList;
    }

    public void setBudgetList(List<CategoryBudget> budgetList) {
        this.budgetList = budgetList;
    }

    public void addBudget(CategoryBudget budget) {
        budgetList.add(budget);
    }

    public CategoryBudget getBudgetForCategory(int category) {
        for (CategoryBudget budget : budgetList) {
            if (budget.getCategoryId() == category) {
                return budget;
            }
        }
        return null;
    }

    public void updateBudget(int categoryId, double budget) {
        CategoryBudget categoryBudget = budgetList.get(categoryId);
        categoryBudget.setBudgetVal(budget);
        budgetList.set(getBudgetPosition(categoryId), categoryBudget);
    }

    private int getBudgetPosition(int categoryId) {
        for (CategoryBudget item : budgetList) {
            if (item.getCategoryId() == categoryId) {
                return budgetList.indexOf(item);
            }
        }
        return -1;
    }

    public double getTotalBudget() {
        double total = 0;
        for (CategoryBudget budget : budgetList) {
            total += budget.getBudgetVal();
        }
        return total;
    }
}


