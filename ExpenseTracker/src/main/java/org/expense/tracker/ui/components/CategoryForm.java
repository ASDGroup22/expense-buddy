package org.expense.tracker.ui.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class CategoryForm {

    private int id;

    private GridPane gridPane;

    private Label nameLabel;
    private TextField name;

    private Label categoryOption;
    private ToggleGroup toggleGroup;
    private RadioButton incomeOption;
    private RadioButton expenseOption;
    private HBox types;

    private Label budgetLabel;
    private TextField budget;

    private Button actionButton;
    private Button deleteButton;
    private Button cancelButton;

    private EventHandler<MouseEvent> actionButtonClickHandler;
    private EventHandler<MouseEvent> deleteButtonClickHandler;
    private EventHandler<MouseEvent> cancelButtonClickHandler;

    private String formMode;

    public CategoryForm() {

        gridPane = new GridPane();
        gridPane.setPadding(new Insets(12,12,12,12));
        gridPane.setHgap(8);
        gridPane.setVgap(8);

        nameLabel = new Label("Name");
        categoryOption = new Label("Type");
        budgetLabel = new Label("Budget");

        name = new TextField();
        budget = new TextField();

        toggleGroup = new ToggleGroup();
        incomeOption =  new RadioButton("Income");
        incomeOption.getStyleClass().add("label");
        expenseOption = new RadioButton("Expense");
        expenseOption.getStyleClass().add("label");
        incomeOption.setToggleGroup(toggleGroup);
        expenseOption.setToggleGroup(toggleGroup);
        types = new HBox();
        types.getChildren().addAll(incomeOption,expenseOption);


        ColumnConstraints columnConstraint = new ColumnConstraints();
        columnConstraint.setPercentWidth(100d/2);
        gridPane.getColumnConstraints().add(columnConstraint);

        
        incomeOption.setOnAction(incomeOptionEventHandler);
        expenseOption.setOnAction(expenseOptionEventHandler);

        actionButton = new Button();
        deleteButton = new Button("Delete");
        cancelButton = new Button("Cancel");

        deleteButton.getStyleClass().add("button-delete");
        cancelButton.getStyleClass().add("button-cancel");


        gridPane.add(nameLabel, 0, 0);
        gridPane.add(name, 1, 0);

        gridPane.add(categoryOption, 0, 2);
        gridPane.add(types, 1, 2);

        gridPane.add(budgetLabel, 0, 3);
        gridPane.add(budget, 1, 3);

        
        gridPane.add(actionButton, 0, 8);
        gridPane.add(deleteButton, 1, 8);
        gridPane.add(cancelButton, 1, 8);

        gridPane.setHalignment(actionButton, HPos.CENTER);
        gridPane.setHalignment(deleteButton, HPos.LEFT);
        gridPane.setHalignment(cancelButton, HPos.RIGHT);
  
    }

    public GridPane getCategoryForm(Boolean isIncome){

        actionButton.setText(formMode);

        actionButton.setOnMouseClicked(actionButtonClickHandler);
        deleteButton.setOnMouseClicked(deleteButtonClickHandler);
        cancelButton.setOnMouseClicked(cancelButtonClickHandler);

        if (isIncome){
            incomeOption.fire();
        } else {
            expenseOption.fire();
        }

        if (formMode.equalsIgnoreCase("INSERT")){
            deleteButton.setVisible(false);
            actionButton.getStyleClass().add("button-create");
        } else {
            actionButton.getStyleClass().add("button-update");
        }


        return gridPane;
    }
    

    

    EventHandler<ActionEvent> incomeOptionEventHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent arg0) {
            budgetLabel.setVisible(false);
            budget.setVisible(false);
        }
        
    };

    EventHandler<ActionEvent> expenseOptionEventHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent arg0) {
            budgetLabel.setVisible(true);
            budget.setVisible(true);
        }
        
    };

    public String getFormMode() {
        return formMode;
    }

    public void setFormMode(String formMode) {
        this.formMode = formMode;
    }

    public EventHandler<MouseEvent> getActionButtonClickHandler() {
        return actionButtonClickHandler;
    }

    public void setActionButtonClickHandler(EventHandler<MouseEvent> actionButtonClickHandler) {
        this.actionButtonClickHandler = actionButtonClickHandler;
    }

    public EventHandler<MouseEvent> getCancelButtonClickHandler() {
        return cancelButtonClickHandler;
    }

    public void setCancelButtonClickHandler(EventHandler<MouseEvent> cancelButtonClickHandler) {
        this.cancelButtonClickHandler = cancelButtonClickHandler;
    }

    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }

    public TextField getBudget() {
        return budget;
    }

    public void setBudget(TextField budget) {
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RadioButton getIncomeOption() {
        return incomeOption;
    }

    public void setIncomeOption(RadioButton incomeOption) {
        this.incomeOption = incomeOption;
    }

    public RadioButton getExpenseOption() {
        return expenseOption;
    }

    public void setExpenseOption(RadioButton expenseOption) {
        this.expenseOption = expenseOption;
    }

    public EventHandler<MouseEvent> getDeleteButtonClickHandler() {
        return deleteButtonClickHandler;
    }

    public void setDeleteButtonClickHandler(EventHandler<MouseEvent> deleteButtonClickHandler) {
        this.deleteButtonClickHandler = deleteButtonClickHandler;
    }

    
}