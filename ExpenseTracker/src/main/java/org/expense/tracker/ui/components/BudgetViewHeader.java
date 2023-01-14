package org.expense.tracker.ui.components;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class BudgetViewHeader {

    private VBox outerBox;
    private HBox innerBoxHead;
    private HBox innerBoxItems;
    private HBox innerBoxSummary;
    private Region region1;
    private Region region2;
    private Region region4;
    private Region region6;
    Label innerBoxHeadLabel;
    Label totalBudget;

    private EventHandler<MouseEvent> addTransactionItemClickHandler;


    public BudgetViewHeader(String innerBoxHeadLabel, String income) {

        this.outerBox = new VBox();
        this.innerBoxHead = new HBox();
        this.innerBoxItems = new HBox();
        this.innerBoxSummary = new HBox();

        this.region1 = new Region();
        this.region2 = new Region();
        this.region4 = new Region();
        this.region6 = new Region();
        this.innerBoxHeadLabel = new Label(innerBoxHeadLabel);
        this.innerBoxHeadLabel.getStyleClass().add("label-head");

        this.totalBudget = new Label(income);
        this.totalBudget.getStyleClass().add("label-green-head");



        HBox.setHgrow(region1, Priority.ALWAYS);
        HBox.setHgrow(region2, Priority.ALWAYS);
        HBox.setHgrow(region4, Priority.ALWAYS);
        HBox.setHgrow(region6, Priority.ALWAYS);


        HBox.setHgrow(totalBudget, Priority.ALWAYS);
    }


    public BudgetViewHeader() {
    }


    public VBox getBudgetViewHeader(){
        
        innerBoxHead.getChildren().addAll(region1, innerBoxHeadLabel, region2);
        innerBoxSummary.getChildren().addAll(region4, totalBudget, region6);
        outerBox.getChildren().addAll(innerBoxHead, innerBoxItems, innerBoxSummary);
        return outerBox;

    }


    public Label getInnerBoxHeadLabel() {
        return innerBoxHeadLabel;
    }


    public void setInnerBoxHeadLabel(Label innerBoxHeadLabel) {
        this.innerBoxHeadLabel = innerBoxHeadLabel;
    }


    public Label getTotalBudget() {
        return totalBudget;
    }


    public void setTotalBudget(Label totalBudget) {
        this.totalBudget = totalBudget;
    }


    public EventHandler<MouseEvent> getAddTransactionItemClickHandler() {
        return addTransactionItemClickHandler;
    }


    public void setAddTransactionItemClickHandler(EventHandler<MouseEvent> addTransactionItemClickHandler) {
        this.addTransactionItemClickHandler = addTransactionItemClickHandler;
    }
    
}
