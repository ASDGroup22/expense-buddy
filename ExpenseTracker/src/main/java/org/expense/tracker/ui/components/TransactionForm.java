package org.expense.tracker.ui.components;

import java.time.LocalDate;

import javafx.scene.control.ListCell;
import javafx.util.StringConverter;
import org.expense.tracker.models.Category;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class TransactionForm {

    private int id;

    private GridPane gridPane;

    private Label errorLabel;

    private Label amountLabel;
    private TextField amount;

    private Label dateLabel;
    private DatePicker date;

    private Label transactionOption;
    private ToggleGroup toggleGroup;
    private RadioButton incomeOption;
    private RadioButton expenseOption;
    private HBox types;
    private ObservableList<Category> incomeCategoryObservableList;
    private ObservableList<Category> expenseCategoryObservableList;

    private Label categoryLabel;
    private ComboBox<Category> category;

    private Label recurringLabel;
    private CheckBox recurring;

    private Label noteLabel;
    private TextArea note;

    private Button actionButton;
    private Button deleteButton;
    private Button cancelButton;

    private EventHandler<MouseEvent> actionButtonClickHandler;
    private EventHandler<MouseEvent> deleteButtonClickHandler;
    private EventHandler<MouseEvent> cancelButtonClickHandler;

    private String formMode;

    public TransactionForm() {

        gridPane = new GridPane();
        gridPane.setPadding(new Insets(12,12,12,12));
        gridPane.setHgap(8);
        gridPane.setVgap(8);

        amountLabel = new Label("Amount");
        dateLabel = new Label("Date");
        transactionOption = new Label("Type");
        categoryLabel = new Label("Category");
        recurringLabel = new Label("Recurring");
        noteLabel = new Label("Note");

        amount = new TextField();
        date = new DatePicker();
        category = new ComboBox<Category>();
        recurring = new CheckBox();
        note = new TextArea();

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

        errorLabel = new Label();
        errorLabel.getStyleClass().add("label-red");


        gridPane.add(amountLabel, 0, 0);
        gridPane.add(amount, 1, 0);

        gridPane.add(dateLabel, 0, 1);
        gridPane.add(date, 1, 1);

        gridPane.add(transactionOption, 0, 2);
        gridPane.add(types, 1, 2);

        gridPane.add(categoryLabel, 0, 3);
        gridPane.add(category, 1, 3);

        gridPane.add(recurringLabel, 0, 4);
        gridPane.add(recurring, 1, 4);

        gridPane.add(noteLabel, 0, 5);
        gridPane.add(note, 1, 5);

        gridPane.add(errorLabel, 1, 7, 2, 1);

        gridPane.add(actionButton, 0, 9);
        gridPane.add(deleteButton, 1, 9);
        gridPane.add(cancelButton, 1, 9);

        gridPane.setHalignment(actionButton, HPos.CENTER);
        gridPane.setHalignment(deleteButton, HPos.LEFT);
        gridPane.setHalignment(cancelButton, HPos.RIGHT);

    }

    public GridPane getTransactionForm(Boolean isIncome){

        actionButton.setText(formMode);

        actionButton.setOnMouseClicked(actionButtonClickHandler);
        deleteButton.setOnMouseClicked(deleteButtonClickHandler);
        cancelButton.setOnMouseClicked(cancelButtonClickHandler);

        if (isIncome){
            incomeOption.fire();
        } else {
            expenseOption.fire();
        }
        
        category.setItems(incomeCategoryObservableList);
        category.setCellFactory(listView -> new ListCell<Category>() {
            @Override
            protected void updateItem(Category category, boolean empty) {
                super.updateItem(category, empty);
                if (category != null) {
                    setText(category.getName());
                } else {
                    setText(null);
                }
            }
        });
        category.setConverter(new StringConverter<Category>() {
            @Override
            public String toString(Category object) {
                if (object == null){
                    return "";
                }
                return object.getName();
            }

            @Override
            public Category fromString(String string) {
                return null;
            }
        });

        if (formMode.equalsIgnoreCase("INSERT")){
            date.setValue(LocalDate.now());
            deleteButton.setVisible(false);
            actionButton.getStyleClass().add("button-create");
        } else {
            actionButton.getStyleClass().add("button-update");
        }


        return gridPane;
    }
    

    public TextField getAmount() {
        return amount;
    }

    public void setAmount(TextField amount) {
        this.amount = amount;
    }

    public DatePicker getDate() {
        return date;
    }

    public void setDate(DatePicker date) {
        this.date = date;
    }

    public ComboBox<Category> getCategory() {
        return category;
    }

    public void setCategory(ComboBox<Category> category) {
        this.category = category;
    }

    public CheckBox getRecurring() {
        return recurring;
    }

    public void setRecurring(CheckBox recurring) {
        this.recurring = recurring;
    }

    public TextArea getNote() {
        return note;
    }

    public void setNote(TextArea note) {
        this.note = note;
    }

    public ObservableList<Category> getIncomeCategoryObservableList() {
        return incomeCategoryObservableList;
    }

    public void setIncomeCategoryObservableList(ObservableList<Category> incomeCategoryObservableList) {
        this.incomeCategoryObservableList = incomeCategoryObservableList;
    }

    public ObservableList<Category> getExpenseCategoryObservableList() {
        return expenseCategoryObservableList;
    }

    public void setExpenseCategoryObservableList(ObservableList<Category> expenseCategoryObservableList) {
        this.expenseCategoryObservableList = expenseCategoryObservableList;
    }


    EventHandler<ActionEvent> incomeOptionEventHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent arg0) {
            category.setItems(incomeCategoryObservableList);
        }
        
    };

    EventHandler<ActionEvent> expenseOptionEventHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent arg0) {
            category.setItems(expenseCategoryObservableList);
        }
        
    };

    public EventHandler<MouseEvent> getActionButtonClickHandler() {
        return actionButtonClickHandler;
    }

    public void setActionButtonClickHandler(EventHandler<MouseEvent> actionButtonClickHandler) {
        this.actionButtonClickHandler = actionButtonClickHandler;
    }

    public String getFormMode() {
        return formMode;
    }

    public void setFormMode(String formMode) {
        this.formMode = formMode;
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

    public EventHandler<MouseEvent> getDeleteButtonClickHandler() {
        return deleteButtonClickHandler;
    }

    public void setDeleteButtonClickHandler(EventHandler<MouseEvent> deleteButtonClickHandler) {
        this.deleteButtonClickHandler = deleteButtonClickHandler;
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

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    public void setToggleGroup(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(Label errorLabel) {
        this.errorLabel = errorLabel;
    }
   
}