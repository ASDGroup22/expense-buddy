package org.expense.tracker.ui.components;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.expense.tracker.models.Category;
import org.expense.tracker.models.CategoryBudget;

import java.time.LocalDate;

public class BudgetForm {

    private int id;

    private GridPane gridPane;

    private Label errorLabel;

    private Label amountLabel;
    private TextField amount;

    private Label categoryLabel;
    private TextField category;

    private Button actionButton;
    private Button cancelButton;

    private EventHandler<MouseEvent> actionButtonClickHandler;
    private EventHandler<MouseEvent> cancelButtonClickHandler;

    public BudgetForm() {
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(12,12,12,12));
        gridPane.setHgap(8);
        gridPane.setVgap(8);

        amountLabel = new Label("Budget");
        categoryLabel = new Label("Category");

        amount = new TextField();
        category = new TextField();

        actionButton = new Button("Update");
        cancelButton = new Button("Cancel");

        cancelButton.getStyleClass().add("button-cancel");

        errorLabel = new Label();
        errorLabel.getStyleClass().add("label-red");


        gridPane.add(categoryLabel, 0, 0);
        gridPane.add(category, 1, 0);

        gridPane.add(amountLabel, 0, 3);
        gridPane.add(amount, 1, 3);

        gridPane.add(errorLabel, 1, 7, 2, 1);

        gridPane.add(actionButton, 0, 9);
        gridPane.add(cancelButton, 1, 9);

        gridPane.setHalignment(actionButton, HPos.CENTER);
        gridPane.setHalignment(cancelButton, HPos.RIGHT);

    }

    public GridPane getBudgetForm(){
        actionButton.setOnMouseClicked(actionButtonClickHandler);
        cancelButton.setOnMouseClicked(cancelButtonClickHandler);
        actionButton.getStyleClass().add("button-update");
        return gridPane;
    }
    

    public TextField getAmount() {
        return amount;
    }

    public void setAmount(TextField amount) {
        this.amount = amount;
    }

    public TextField getCategory() {
        return category;
    }

    public void setCategory(TextField category) {
        this.category = category;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(Label errorLabel) {
        this.errorLabel = errorLabel;
    }

}