package org.expense.tracker.ui.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import org.expense.tracker.models.Budget;

public class BudgetListView {

    private ListView<Budget> categoryBudgetListView;
    private ObservableList<Budget> budgetObservableList;
    private EventHandler<MouseEvent> budgetItemClickHandler;

    public BudgetListView() {
        this.categoryBudgetListView = new ListView<>();
        this.budgetObservableList = FXCollections.observableArrayList();
        this.categoryBudgetListView.setOnMouseClicked(this.budgetItemClickHandler);
    }

    public ListView<Budget> getCategoryBudgetListView() {
        categoryBudgetListView.setItems(budgetObservableList);
        categoryBudgetListView.setCellFactory(transactionListView -> new BudgetListViewCell());
        categoryBudgetListView.setPrefHeight(500);
        categoryBudgetListView.setOnMouseClicked(budgetItemClickHandler);
        return categoryBudgetListView;
    }

    public void setCategoryBudgetListView(ListView<Budget> categoryBudgetListView) {
        this.categoryBudgetListView = categoryBudgetListView;
    }

    public ObservableList<Budget> getCategoryBudgetObservableList() {
        return budgetObservableList;
    }

    public void setCategoryBudgetObservableList(ObservableList<Budget> budgetObservableList) {
        this.budgetObservableList = budgetObservableList;
    }

    public EventHandler<MouseEvent> getBudgetItemClickHandler() {
        return budgetItemClickHandler;
    }

    public void setBudgetItemClickHandler(EventHandler<MouseEvent> budgetItemClickHandler) {
        this.budgetItemClickHandler = budgetItemClickHandler;
    }
    
}
