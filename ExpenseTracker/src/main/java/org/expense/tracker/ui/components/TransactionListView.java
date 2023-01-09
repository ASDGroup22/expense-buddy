package org.expense.tracker.ui.components;

import org.expense.tracker.models.Transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class TransactionListView{
    
    private ListView<Transaction> transactionListView;
    private ObservableList<Transaction> transactionObservableList;
    private EventHandler<MouseEvent> transactionItemClickHandler;

    public TransactionListView() {
        this.transactionListView = new ListView<>();
        this.transactionObservableList = FXCollections.observableArrayList();
        this.transactionListView.setOnMouseClicked(this.transactionItemClickHandler);
    }

    public ListView<Transaction> getTransactionListView() {
        transactionListView.setItems(transactionObservableList);
        transactionListView.setCellFactory(transactionListView -> new TransactionListViewCell());
        transactionListView.setPrefHeight(500);
        transactionListView.setOnMouseClicked(transactionItemClickHandler);
        return transactionListView;
    }

    public void setTransactionListView(ListView<Transaction> transactionListView) {
        this.transactionListView = transactionListView;
    }

    public ObservableList<Transaction> getTransactionObservableList() {
        return transactionObservableList;
    }

    public void setTransactionObservableList(ObservableList<Transaction> transactionObservableList) {
        this.transactionObservableList = transactionObservableList;
    }

    public EventHandler<MouseEvent> getTransactionItemClickHandler() {
        return transactionItemClickHandler;
    }

    public void setTransactionItemClickHandler(EventHandler<MouseEvent> transactionItemClickHandler) {
        this.transactionItemClickHandler = transactionItemClickHandler;
    }
    
}
