package org.expense.tracker.managers;

import org.expense.tracker.models.Budget;

import java.util.ArrayList;
import java.util.List;

public class BudgetManager {
    private List<Budget> budgetList;

    public BudgetManager() {
        budgetList = new ArrayList<>();
    }

    public List<Budget> getBudgetList() {
        return budgetList;
    }

    public void setBudgetList(List<Budget> budgetList) {
        this.budgetList = budgetList;
    }

    public void addBudget(Budget budget) {
        budgetList.add(budget);
    }

    public Budget getBudgetForCategory(int category) {
        for (Budget budget : budgetList) {
            if (budget.getCategoryId() == category) {
                return budget;
            }
        }
        return null;
    }

    public void updateBudget(int categoryId, double budget) {
        Budget categoryBudget = budgetList.get(categoryId);
        categoryBudget.setBudgetVal(budget);
        budgetList.set(getBudgetPosition(categoryId), categoryBudget);
    }

    private int getBudgetPosition(int categoryId) {
        for (Budget item : budgetList) {
            if (item.getCategoryId() == categoryId) {
                return budgetList.indexOf(item);
            }
        }
        return -1;
    }

    public double getTotalBudget() {
        double total = 0;
        for (Budget budget : budgetList) {
            total += budget.getBudgetVal();
        }
        return total;
    }
}


