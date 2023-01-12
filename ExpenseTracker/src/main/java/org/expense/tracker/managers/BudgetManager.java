package org.expense.tracker.managers;

import org.expense.tracker.models.Budget;

import java.util.HashMap;

public class BudgetManager {
    private HashMap<Integer, Budget> budgetMap;

    public BudgetManager() {
        budgetMap = new HashMap<>();
    }

    public void addCategories(Integer profileId) {
        // TODO
        // Get categories for given profileId
        // Create budget and add
    }

    public void setBudgetForCategory(String category, double amount) {
        //TODO
    }

    public double getBudgetForCategory(String category) {
        //TODO
        return 0;
    }

    public void removeCategory(String category) {
        // TODO
    }

    public void getTotalBudget() {
        //TODO
    }
}


