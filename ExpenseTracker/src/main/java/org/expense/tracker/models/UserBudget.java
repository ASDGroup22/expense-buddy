package org.expense.tracker.models;

import java.util.Map;
import java.util.HashMap;

public class UserBudget {
    private Map<Integer, Budget> budget;

    public UserBudget() {
        budget = new HashMap<>();
    }

    public void addCategory(int categoryId, Category category, double amount) {
        if (budget.containsKey(categoryId)) {
            budget.remove(categoryId);
        }
        budget.put(categoryId, new Budget(categoryId, category, amount));
    }

    public void setBudgetForCategory(int categoryId, Category category, double amount) {
        budget.put(categoryId, new Budget(categoryId, category, amount));
    }

    public double getBudgetForCategory(int category) {
        if (budget.containsKey(category)) {
            return budget.get(category).getCategoryId();
        }
        return 0;
    }

    public void removeCategory(int category) {
        budget.remove(category);
    }

    public double getTotalBudget() {
        double total = 0.0f;
        for (int i = 0; i < budget.size(); i++) {
            total += budget.get(i).getBudgetVal();
        }
        return total;
    }
}
