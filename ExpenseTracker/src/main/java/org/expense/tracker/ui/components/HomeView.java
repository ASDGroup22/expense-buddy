package org.expense.tracker.ui.components;

import org.expense.tracker.models.Category;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.ToggleGroup;
import org.expense.tracker.models.CategoryBudget;

public class HomeView {

    private GridPane gridPane;

    private Label summaryHeadLabel;
    private Label profileBudgetNameLabel;
    private Label profileBudgetLabel;
    private Label incomeTotalNameLabel;
    private Label incomeTotalLabel;
    private Label expenseTotalNameLabel;
    private Label expenseTotalLabel;
    private Label balanceTotalNameLabel;
    private Label balanceTotalLabel;

    private Label categorySummaryHeadLabel;
    private Label selectCategoryNameLabel;
    private ComboBox<Category> selectCategoryComboBox;
    private Label budgetNameLabel;
    private Label budgetLabel;
    private Label expenseNameLabel;
    private Label expenseLabel;
    private Label earningNameLabel;
    private Label earningLabel;
    private Label balanceNameLabel;
    private Label balanceLabel;


    private Label categoryOptionNameLabel;
    private ToggleGroup toggleGroup;
    private RadioButton incomeOption;
    private RadioButton expenseOption;
    private HBox types;

    EventHandler<ActionEvent> selectcategoryEventhandler;

    private ObservableList<Category> incomeCategoryObservableList;
    private ObservableList<Category> expenseCategoryObservableList;
    private ObservableList<CategoryBudget> budgetObservableList;

    public HomeView() {

        this.gridPane = new GridPane();
        this.gridPane.setPadding(new Insets(12,12,12,12));
        this.gridPane.setHgap(8);
        this.gridPane.setVgap(8);

        this.summaryHeadLabel = new Label("Finance Analysis");
        summaryHeadLabel.getStyleClass().add("label-sub-head");
        this.profileBudgetNameLabel = new Label("Overall Budget: ");
        this.profileBudgetLabel = new Label();
        profileBudgetLabel.getStyleClass().add("label-blue");

        this.incomeTotalNameLabel = new Label("Overall Income: ");
        this.incomeTotalLabel = new Label();
        incomeTotalLabel.getStyleClass().add("label-blue");

        this.expenseTotalNameLabel = new Label("Overall Expense: ");
        this.expenseTotalLabel = new Label();
        expenseTotalLabel.getStyleClass().add("label-red");

        this.balanceTotalNameLabel = new Label("Remaining Budget: ");
        this.balanceTotalLabel = new Label();

        this.categorySummaryHeadLabel = new Label("Category Overview");
        categorySummaryHeadLabel.getStyleClass().add("label-sub-head");

        this.selectCategoryNameLabel = new Label("Category: ");
        this.selectCategoryComboBox = new ComboBox<Category>();

        this.budgetNameLabel = new Label("Budget: ");
        this.budgetLabel = new Label();
        budgetLabel.getStyleClass().add("label-blue");

        this.expenseNameLabel = new Label("Spendings: ");
        this.expenseLabel = new Label();
        expenseLabel.getStyleClass().add("label-red");

        this.balanceNameLabel = new Label("Balance: ");
        this.balanceLabel = new Label();

        this.earningNameLabel = new Label("Earnings: ");
        this.earningLabel = new Label();
        earningLabel.getStyleClass().add("label-green");

        this.categoryOptionNameLabel = new Label("Type: ");
        this.toggleGroup = new ToggleGroup();

        this.incomeOption =  new RadioButton("Income");
        incomeOption.getStyleClass().add("label");

        this.expenseOption = new RadioButton("Expense");
        expenseOption.getStyleClass().add("label");

        this.incomeOption.setToggleGroup(toggleGroup);
        this.expenseOption.setToggleGroup(toggleGroup);
        this.types = new HBox();
        this.types.getChildren().addAll(incomeOption,expenseOption);


        ColumnConstraints columnConstraint = new ColumnConstraints();
        columnConstraint.setPercentWidth(100d/2);
        gridPane.getColumnConstraints().add(columnConstraint);

        
        incomeOption.setOnAction(incomeOptionEventHandler);
        expenseOption.setOnAction(expenseOptionEventHandler);
        selectCategoryComboBox.setOnAction(selectcategoryEventhandler);

        int i = 0;
        gridPane.add(summaryHeadLabel, 0, i);

        i+=2;
        gridPane.add(profileBudgetNameLabel, 0, i);
        gridPane.add(profileBudgetLabel, 1, i);

        i+=1;
        gridPane.add(incomeTotalNameLabel, 0, i);
        gridPane.add(incomeTotalLabel, 1, i);

        i+=1;
        gridPane.add(expenseTotalNameLabel, 0, i);
        gridPane.add(expenseTotalLabel, 1, i);

        i+=1;
        gridPane.add(balanceTotalNameLabel, 0, i);
        gridPane.add(balanceTotalLabel, 1, i);

        i+=3;
        gridPane.add(categorySummaryHeadLabel, 0, i);

        i+=2;
        gridPane.add(categoryOptionNameLabel, 0, i);
        gridPane.add(types, 1, i);

        i+=1;
        gridPane.add(selectCategoryNameLabel, 0, i);
        gridPane.add(selectCategoryComboBox, 1, i);

        i+=1;
        gridPane.add(budgetNameLabel, 0, i);
        gridPane.add(budgetLabel, 1, i);
        gridPane.add(earningNameLabel, 0, i);
        gridPane.add(earningLabel, 1, i);

        i+=1;
        gridPane.add(expenseNameLabel, 0, i);
        gridPane.add(expenseLabel, 1, i);

        i+=1;
        gridPane.add(balanceNameLabel, 0, i);
        gridPane.add(balanceLabel, 1, i);


    }

    public void setMainColorOnValue(){

        if (Float.valueOf(balanceTotalLabel.getText())>0){
            balanceTotalLabel.getStyleClass().add("label-green");
        } else {
            balanceTotalLabel.getStyleClass().add("label-red");
        }
    }

    public void setSubColorOnValue(){

        if (Float.valueOf(balanceLabel.getText())>0){
            balanceLabel.getStyleClass().add("label-green");
        } else {
            balanceLabel.getStyleClass().add("label-red");
        }
    }

    public GridPane getHomeView(){

        incomeOption.fire();
        selectCategoryComboBox.setItems(incomeCategoryObservableList);
        selectCategoryComboBox.setOnAction(selectcategoryEventhandler);

        return gridPane;
    }
    

    

    EventHandler<ActionEvent> incomeOptionEventHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent arg0) {

            selectCategoryComboBox.setItems(incomeCategoryObservableList);

            earningNameLabel.setVisible(true);
            earningLabel.setVisible(true);

            budgetNameLabel.setVisible(false);
            budgetLabel.setVisible(false);
            budgetLabel.setText(null);

            expenseNameLabel.setVisible(false);
            expenseLabel.setVisible(false);
            expenseLabel.setText(null);

            balanceNameLabel.setVisible(false);
            balanceLabel.setVisible(false);
            balanceLabel.setText(null);

        }
        
    };

    EventHandler<ActionEvent> expenseOptionEventHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent arg0) {
            
            selectCategoryComboBox.setItems(expenseCategoryObservableList);

            earningNameLabel.setVisible(false);
            earningLabel.setVisible(false);
            earningLabel.setText(null);

            budgetNameLabel.setVisible(true);
            budgetLabel.setVisible(true);

            expenseNameLabel.setVisible(true);
            expenseLabel.setVisible(true);

            balanceNameLabel.setVisible(true);
            balanceLabel.setVisible(true);
        }
        
    };

    public Label getProfileBudgetLabel() {
        return profileBudgetLabel;
    }

    public void setProfileBudgetLabel(Label profileBudgetLabel) {
        this.profileBudgetLabel = profileBudgetLabel;
    }

    public Label getIncomeTotalLabel() {
        return incomeTotalLabel;
    }

    public void setIncomeTotalLabel(Label incomeTotalLabel) {
        this.incomeTotalLabel = incomeTotalLabel;
    }

    public Label getExpenseTotalLabel() {
        return expenseTotalLabel;
    }

    public void setExpenseTotalLabel(Label expenseTotalLabel) {
        this.expenseTotalLabel = expenseTotalLabel;
    }

    public Label getBalanceTotalLabel() {
        return balanceTotalLabel;
    }

    public void setBalanceTotalLabel(Label balanceTotalLabel) {
        this.balanceTotalLabel = balanceTotalLabel;
    }

    public ComboBox<Category> getSelectCategoryComboBox() {
        return selectCategoryComboBox;
    }

    public void setSelectCategoryComboBox(ComboBox<Category> selectCategoryComboBox) {
        this.selectCategoryComboBox = selectCategoryComboBox;
    }

    public Label getBudgetLabel() {
        return budgetLabel;
    }

    public void setBudgetLabel(Label budgetLabel) {
        this.budgetLabel = budgetLabel;
    }

    public Label getExpenseLabel() {
        return expenseLabel;
    }

    public void setExpenseLabel(Label expenseLabel) {
        this.expenseLabel = expenseLabel;
    }

    public Label getEarningLabel() {
        return earningLabel;
    }

    public void setEarningLabel(Label earningLabel) {
        this.earningLabel = earningLabel;
    }

    public Label getBalanceLabel() {
        return balanceLabel;
    }

    public void setBalanceLabel(Label balanceLabel) {
        this.balanceLabel = balanceLabel;
    }

    public EventHandler<ActionEvent> getIncomeOptionEventHandler() {
        return incomeOptionEventHandler;
    }

    public void setIncomeOptionEventHandler(EventHandler<ActionEvent> incomeOptionEventHandler) {
        this.incomeOptionEventHandler = incomeOptionEventHandler;
    }

    public EventHandler<ActionEvent> getExpenseOptionEventHandler() {
        return expenseOptionEventHandler;
    }

    public void setExpenseOptionEventHandler(EventHandler<ActionEvent> expenseOptionEventHandler) {
        this.expenseOptionEventHandler = expenseOptionEventHandler;
    }

    public EventHandler<ActionEvent> getSelectcategoryEventhandler() {
        return selectcategoryEventhandler;
    }

    public void setSelectcategoryEventhandler(EventHandler<ActionEvent> selectcategoryEventhandler) {
        this.selectcategoryEventhandler = selectcategoryEventhandler;
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

    public void setBudgetObservableList(ObservableList<CategoryBudget> budgetObservableList) {
        this.budgetObservableList = budgetObservableList;
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

    
   

    
}