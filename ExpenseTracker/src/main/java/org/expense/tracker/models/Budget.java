package org.expense.tracker.models;

import java.util.Map;
import java.util.HashMap;

public class Budget {
    private Map<String, Double> budget;

    public Budget() {
        budget = new HashMap<>();
    }

    public void addCategory(String category, double amount) {
        budget.put(category, amount);
    }

    public void setBudgetForCategory(String category, double amount) {
        budget.put(category, amount);
    }

    public double getBudgetForCategory(String category) {
        return budget.get(category);
    }

    public void removeCategory(String category) {
        budget.remove(category);
    }

    public void getTotalBudget() {
        Double total = 0.0;
        for (int i = 0; i < budget.size(); i++) {
            total += budget.get(i);
        }
    }
}
