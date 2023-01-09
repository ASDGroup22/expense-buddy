package org.expense.tracker.ui.components;

import org.expense.tracker.models.Category;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class CategoryListView{
    
    private ListView<Category> categoryListView;
    private ObservableList<Category> categoryObservableList;
    private EventHandler<MouseEvent> categoryItemClickHandler;

    public CategoryListView() {
        this.categoryListView = new ListView<>();
        this.categoryObservableList = FXCollections.observableArrayList();
        this.categoryListView.setOnMouseClicked(this.categoryItemClickHandler);
    }

    public ListView<Category> getCategoryListView() {
        categoryListView.setItems(categoryObservableList);
        categoryListView.setCellFactory(transactionListView -> new CategoryListViewCell());
        categoryListView.setOnMouseClicked(categoryItemClickHandler);
        return categoryListView;
    }

    public void setCategoryListView(ListView<Category> categoryListView) {
        this.categoryListView = categoryListView;
    }

    public ObservableList<Category> getCategoryObservableList() {
        return categoryObservableList;
    }

    public void setCategoryObservableList(ObservableList<Category> categoryObservableList) {
        this.categoryObservableList = categoryObservableList;
    }

    public EventHandler<MouseEvent> getCategoryItemClickHandler() {
        return categoryItemClickHandler;
    }

    public void setCategoryItemClickHandler(EventHandler<MouseEvent> categoryItemClickHandler) {
        this.categoryItemClickHandler = categoryItemClickHandler;
    }
    
}
