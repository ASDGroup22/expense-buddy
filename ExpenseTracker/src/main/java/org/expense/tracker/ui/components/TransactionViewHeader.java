package org.expense.tracker.ui.components;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class TransactionViewHeader {

    private VBox outerBox;
    private HBox innerBoxHead;
    private HBox innerBoxItems;
    private HBox innerBoxSummary;
    private Region region1;
    private Region region2;
    private Region region3;
    private Region region4;
    private Region region5;
    private Region region6;
    Label innerBoxHeadLabel;
    Button addTransaction;
    Label incomeTotal;
    Label expenseTotal;

    private EventHandler<MouseEvent> addTransactionItemClickHandler;


    public TransactionViewHeader(String innerBoxHeadLabel, String income, String expense) {

        this.outerBox = new VBox();
        this.innerBoxHead = new HBox();
        this.innerBoxItems = new HBox();
        this.innerBoxSummary = new HBox();

        this.region1 = new Region();
        this.region2 = new Region();
        this.region3 = new Region();
        this.region4 = new Region();
        this.region5 = new Region();
        this.region6 = new Region();
        this.innerBoxHeadLabel = new Label(innerBoxHeadLabel);
        this.innerBoxHeadLabel.getStyleClass().add("label-head");
        this.addTransaction = new Button("+");
        this.addTransaction.getStyleClass().add("button-add");

        this.incomeTotal = new Label(income);
        this.incomeTotal.getStyleClass().add("label-green-head");
        this.expenseTotal = new Label(expense);
        this.expenseTotal.getStyleClass().add("label-red-head");


        this.addTransaction.setOnMouseClicked(this.addTransactionItemClickHandler);

        HBox.setHgrow(region1, Priority.ALWAYS);
        HBox.setHgrow(region2, Priority.ALWAYS);
        HBox.setHgrow(region3, Priority.ALWAYS);
        HBox.setHgrow(region4, Priority.ALWAYS);
        HBox.setHgrow(region5, Priority.ALWAYS);
        HBox.setHgrow(region6, Priority.ALWAYS);


        HBox.setHgrow(incomeTotal, Priority.ALWAYS);
        HBox.setHgrow(expenseTotal, Priority.ALWAYS);
    }


    public TransactionViewHeader() {
    }


    public VBox getTransactionViewHeader(){
        
        addTransaction.setOnMouseClicked(addTransactionItemClickHandler);
        innerBoxHead.getChildren().addAll(region1, innerBoxHeadLabel, region2);   
        innerBoxItems.getChildren().addAll(region3,addTransaction);
        innerBoxSummary.getChildren().addAll(region4, incomeTotal, region5, expenseTotal, region6);
        outerBox.getChildren().addAll(innerBoxHead, innerBoxItems, innerBoxSummary);
        return outerBox;

    }


    public Label getInnerBoxHeadLabel() {
        return innerBoxHeadLabel;
    }


    public void setInnerBoxHeadLabel(Label innerBoxHeadLabel) {
        this.innerBoxHeadLabel = innerBoxHeadLabel;
    }


    public Label getIncomeTotal() {
        return incomeTotal;
    }


    public void setIncomeTotal(Label incomeTotal) {
        this.incomeTotal = incomeTotal;
    }


    public Label getExpenseTotal() {
        return expenseTotal;
    }


    public void setExpenseTotal(Label expenseTotal) {
        this.expenseTotal = expenseTotal;
    }


    public EventHandler<MouseEvent> getAddTransactionItemClickHandler() {
        return addTransactionItemClickHandler;
    }


    public void setAddTransactionItemClickHandler(EventHandler<MouseEvent> addTransactionItemClickHandler) {
        this.addTransactionItemClickHandler = addTransactionItemClickHandler;
    }
    
}
