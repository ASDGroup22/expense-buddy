package org.expense.tracker.ui.components;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class CategoryViewHeader {

    private VBox outerBox;
    private HBox innerBoxHead;
    private ToggleGroup categoryToggleGroup;
    private ToggleButton incomeToggleButton;
    private ToggleButton expenseToggleButton;
    private HBox innerBoxItems;
    private Region region1;
    private Region region2;
    private Region region3;
    private Label innerBoxHeadLabel;
    private Button addCategory;

    private EventHandler<MouseEvent> incomeToggleButtonClickHandler;
    private EventHandler<MouseEvent> expenseToggleButtonClickHandler;
    private EventHandler<MouseEvent> addCategoryItemClickHandler;


    public CategoryViewHeader(String innerBoxHeadLabel) {

        this.outerBox = new VBox();
        this.innerBoxHead = new HBox();
        this.categoryToggleGroup = new ToggleGroup();
        this.incomeToggleButton = new ToggleButton("INCOME");
        this.expenseToggleButton = new ToggleButton("EXPENSE");

        incomeToggleButton.getStyleClass().add("button-income");
        expenseToggleButton.getStyleClass().add("button-expense");

        this.innerBoxItems = new HBox();
        this.region1 = new Region();
        this.region2 = new Region();
        this.region3 = new Region();

        this.innerBoxHeadLabel = new Label(innerBoxHeadLabel);
        this.innerBoxHeadLabel.getStyleClass().add("label-head");

        this.addCategory = new Button("+");
        this.addCategory.getStyleClass().add("button-add");

        this.incomeToggleButton.setOnMouseClicked(this.incomeToggleButtonClickHandler);
        this.expenseToggleButton.setOnMouseClicked(this.expenseToggleButtonClickHandler);

        this.addCategory.setOnMouseClicked(this.addCategoryItemClickHandler);

        HBox.setHgrow(region1, Priority.ALWAYS);
        HBox.setHgrow(region2, Priority.ALWAYS);
        HBox.setHgrow(region3, Priority.ALWAYS);
    }


    public CategoryViewHeader() {
    }


    public VBox getCategoryViewHeader(){
        
        addCategory.setOnMouseClicked(addCategoryItemClickHandler);
        incomeToggleButton.setOnMouseClicked(incomeToggleButtonClickHandler);
        expenseToggleButton.setOnMouseClicked(expenseToggleButtonClickHandler);
        innerBoxHead.getChildren().addAll(region1, innerBoxHeadLabel, region2);   
        incomeToggleButton.setToggleGroup(categoryToggleGroup);
        expenseToggleButton.setToggleGroup(categoryToggleGroup);

        innerBoxItems.getChildren().addAll(incomeToggleButton, expenseToggleButton, region3, addCategory);
        outerBox.getChildren().addAll(innerBoxHead, innerBoxItems);
        return outerBox;

    }


    public Label getInnerBoxHeadLabel() {
        return innerBoxHeadLabel;
    }


    public void setInnerBoxHeadLabel(Label innerBoxHeadLabel) {
        this.innerBoxHeadLabel = innerBoxHeadLabel;
    }


    public EventHandler<MouseEvent> getIncomeToggleButtonClickHandler() {
        return incomeToggleButtonClickHandler;
    }


    public void setIncomeToggleButtonClickHandler(EventHandler<MouseEvent> incomeToggleButtonClickHandler) {
        this.incomeToggleButtonClickHandler = incomeToggleButtonClickHandler;
    }


    public EventHandler<MouseEvent> getExpenseToggleButtonClickHandler() {
        return expenseToggleButtonClickHandler;
    }


    public void setExpenseToggleButtonClickHandler(EventHandler<MouseEvent> expenseToggleButtonClickHandler) {
        this.expenseToggleButtonClickHandler = expenseToggleButtonClickHandler;
    }


    public ToggleButton getIncomeToggleButton() {
        return incomeToggleButton;
    }


    public void setIncomeToggleButton(ToggleButton incomeToggleButton) {
        this.incomeToggleButton = incomeToggleButton;
    }


    public ToggleButton getExpenseToggleButton() {
        return expenseToggleButton;
    }


    public void setExpenseToggleButton(ToggleButton expenseToggleButton) {
        this.expenseToggleButton = expenseToggleButton;
    }


    public EventHandler<MouseEvent> getAddCategoryItemClickHandler() {
        return addCategoryItemClickHandler;
    }


    public void setAddCategoryItemClickHandler(EventHandler<MouseEvent> addCategoryItemClickHandler) {
        this.addCategoryItemClickHandler = addCategoryItemClickHandler;
    }


    
    
}
