package org.expense.tracker.ui.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import org.expense.tracker.models.CategoryBudget;
import org.expense.tracker.models.Transaction;

public class BudgetListView {

    private ListView<CategoryBudget> categoryBudgetListView;
    private ObservableList<CategoryBudget> categoryBudgetObservableList;
    private EventHandler<MouseEvent> budgetItemClickHandler;

    public BudgetListView() {
        this.categoryBudgetListView = new ListView<>();
        this.categoryBudgetObservableList = FXCollections.observableArrayList();
        this.categoryBudgetListView.setOnMouseClicked(this.budgetItemClickHandler);
    }

    public ListView<CategoryBudget> getCategoryBudgetListView() {
        categoryBudgetListView.setItems(categoryBudgetObservableList);
        categoryBudgetListView.setCellFactory(transactionListView -> new BudgetListViewCell());
        categoryBudgetListView.setPrefHeight(500);
        categoryBudgetListView.setOnMouseClicked(budgetItemClickHandler);
        return categoryBudgetListView;
    }

    public void setCategoryBudgetListView(ListView<CategoryBudget> categoryBudgetListView) {
        this.categoryBudgetListView = categoryBudgetListView;
    }

    public ObservableList<CategoryBudget> getCategoryBudgetObservableList() {
        return categoryBudgetObservableList;
    }

    public void setCategoryBudgetObservableList(ObservableList<CategoryBudget> categoryBudgetObservableList) {
        this.categoryBudgetObservableList = categoryBudgetObservableList;
    }

    public EventHandler<MouseEvent> getBudgetItemClickHandler() {
        return budgetItemClickHandler;
    }

    public void setBudgetItemClickHandler(EventHandler<MouseEvent> budgetItemClickHandler) {
        this.budgetItemClickHandler = budgetItemClickHandler;
    }
    
}
