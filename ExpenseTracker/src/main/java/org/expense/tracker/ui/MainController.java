package org.expense.tracker.ui;

import java.util.Date;
import java.util.ResourceBundle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZoneId;

import org.expense.tracker.controllers.Controller;
import org.expense.tracker.models.Category;
import org.expense.tracker.models.User;
import org.expense.tracker.models.Transaction;
import org.expense.tracker.ui.components.CategoryForm;
import org.expense.tracker.ui.components.CategoryListView;
import org.expense.tracker.ui.components.CategoryViewHeader;
import org.expense.tracker.ui.components.HeadBarView;
import org.expense.tracker.ui.components.HeadLabel;
import org.expense.tracker.ui.components.HomeView;
import org.expense.tracker.ui.components.ProfileForm;
import org.expense.tracker.ui.components.ProfileListView;
import org.expense.tracker.ui.components.SettingsView;
import org.expense.tracker.ui.components.TransactionForm;
import org.expense.tracker.ui.components.TransactionListView;
import org.expense.tracker.ui.components.TransactionViewHeader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainController implements Initializable{

    @FXML
    BorderPane innerBorder;

    @FXML
    HBox headBar;

    @FXML
    ImageView homeIcon;

    @FXML
    ImageView transactionIcon;

    @FXML
    ImageView budgetIcon;

    @FXML
    ImageView categoryIcon;

    @FXML 
    ImageView settingsIcon;

    @FXML
    Label labelHome;

    @FXML
    Label labelTransaction;

    @FXML
    Label labelBudget;

    @FXML
    Label labelCategory;

    @FXML
    Label labelSettings;

    FileInputStream imageHome;
    FileInputStream imageTransaction;
    FileInputStream imageBudget;
    FileInputStream imageCategory;
    FileInputStream imageSettings;

    private HeadBarView headBarView;
    private ObservableList<User> userObservableList;

    private HomeView homeView;
    private SettingsView settingsView;
    private HeadLabel headLabel;

    private ProfileListView profileListView;

    private TransactionListView transactionListView;
    private TransactionViewHeader transactionViewHeader;
    private ObservableList<Transaction> transactionObservableList;

    private CategoryListView categoryListView;
    private CategoryViewHeader categoryViewHeader;
    private ObservableList<Category> incomeCategoryObservableList;
    private ObservableList<Category> expenseCategoryObservableList;

    private ProfileForm profileForm;
    private TransactionForm transactionForm;
    private CategoryForm categoryForm;

    private Controller controller;

    private SimpleDateFormat dateFormatter;

    private int selectedProfileId;
    private String selectedTab;

    public MainController() {

        this.controller = new Controller();

        this.headLabel = new HeadLabel();

        this.dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        this.homeView = new HomeView();
        this.transactionListView = new TransactionListView();
        this.categoryListView = new CategoryListView();
        this.settingsView = new SettingsView();
        this.profileListView = new ProfileListView();


        this.profileForm = new ProfileForm();
        this.transactionForm = new TransactionForm();
        this.categoryForm = new CategoryForm();

        this.transactionObservableList = FXCollections.observableArrayList();
        this.incomeCategoryObservableList = FXCollections.observableArrayList();
        this.expenseCategoryObservableList = FXCollections.observableArrayList();
        this.userObservableList =  FXCollections.observableArrayList();

        this.headBarView = new HeadBarView();
        this.headBar = new HBox();

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Label initialLabel = new Label("Expense Buddy");
        initialLabel.getStyleClass().add("label-head");
        innerBorder.setCenter(initialLabel);

        try {
            imageHome = new FileInputStream(new File(this.getClass().getResource("../images/icons-home-page.png").getFile()));
            imageTransaction = new FileInputStream(new File(this.getClass().getResource("../images/icons-transaction-img1.png").getFile()));
            imageBudget = new FileInputStream(new File(this.getClass().getResource("../images/icon-budget-img1.png").getFile()));
            imageCategory = new FileInputStream(new File(this.getClass().getResource("../images/icon-categories.png").getFile()));
            imageSettings = new FileInputStream(new File(this.getClass().getResource("../images/icons-settings-img1.png").getFile()));

            homeIcon.setImage(new Image(imageHome));
            transactionIcon.setImage(new Image(imageTransaction));
            budgetIcon.setImage(new Image(imageBudget));
            categoryIcon.setImage(new Image(imageCategory));
            settingsIcon.setImage(new Image(imageSettings));

            labelHome.getStyleClass().add("label-black");
            labelTransaction.getStyleClass().add("label-black");
            labelBudget.getStyleClass().add("label-black");
            labelCategory.getStyleClass().add("label-black");
            labelSettings.getStyleClass().add("label-black");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void resetObservableLists(){

        userObservableList = FXCollections.observableArrayList();

        for (User user : controller.getProfiles()) {
            userObservableList.add(user);
        }

        incomeCategoryObservableList = FXCollections.observableArrayList();
        

        for (Category category : controller.getIncomeCategories(selectedProfileId)) {
            incomeCategoryObservableList.add(category);
        }

        expenseCategoryObservableList = FXCollections.observableArrayList();
        
        for (Category category : controller.getExpenseCategories(selectedProfileId)) {
            expenseCategoryObservableList.add(category);
        }

        transactionObservableList = FXCollections.observableArrayList();

        for (Transaction transaction : controller.getTransactions(selectedProfileId)) {
            transactionObservableList.add(transaction);
        }

    }

    private void resetView(){

        if (selectedTab.equalsIgnoreCase("home")){
            homeTabClicked();
        } else if (selectedTab.equalsIgnoreCase("transaction")){
            transactionTabClicked();
        } else if (selectedTab.equalsIgnoreCase("category")){
            categoryTabClicked();
        } else if (selectedTab.equalsIgnoreCase("settings")){
            settingsTabClicked();
        }
    }

// Head Bar View

    public void setHeadBarView(){

        resetObservableLists();

        headBarView = new HeadBarView();
        headBarView.getProfile().getSelectionModel().select(controller.getProfile(selectedProfileId));

        headBarView.setAddProfileClickHandler(addProfileClickHandler);
        headBarView.setProfileClickHandler(profileClickHandler);
        headBarView.setProfileObservableList(userObservableList);
        headBar.getChildren().setAll(headBarView.getHeadBar());
    }

// Main View tabs

    public void homeTabClicked(){

        resetObservableLists();

        homeView = new HomeView();

        try {
            Float budgetSummary = controller.getProfile(selectedProfileId).getBudget();
            Float incomeSummary = controller.getIncomeSummary(selectedProfileId);
            Float expenseSummary = controller.getExpenseSummary(selectedProfileId);
            Float balanceSummary = budgetSummary + incomeSummary - expenseSummary;

            String budgetTotal = Float.toString(budgetSummary);
            String incomeTotal = Float.toString(incomeSummary);
            String expenseTotal = Float.toString(expenseSummary);
            String balanceTotal = Float.toString(balanceSummary);
            
            homeView.setIncomeCategoryObservableList(incomeCategoryObservableList);
            homeView.setExpenseCategoryObservableList(expenseCategoryObservableList);

            homeView.getProfileBudgetLabel().setText(budgetTotal);
            homeView.getIncomeTotalLabel().setText(incomeTotal);
            homeView.getExpenseTotalLabel().setText(expenseTotal);
            homeView.getBalanceTotalLabel().setText(balanceTotal);
            homeView.setSelectcategoryEventhandler(selectcategoryEventhandler);

            homeView.setMainColorOnValue();

        } catch (NullPointerException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        innerBorder.setTop(headLabel.getHeadLabel("Home"));
        innerBorder.setCenter(homeView.getHomeView());

        setHeadBarView();

        selectedTab = "home";
    }

    public void transactionTabClicked(){

        resetObservableLists();

        transactionListView = new TransactionListView();

        transactionListView.setTransactionObservableList(transactionObservableList);
        transactionListView.setTransactionItemClickHandler(transactionItemClickHandler);

        transactionViewHeader = new TransactionViewHeader("Transactions", Float.toString(controller.getIncomeSummary(selectedProfileId)),
                                            Float.toString(controller.getExpenseSummary(selectedProfileId)));

        transactionViewHeader.setAddTransactionItemClickHandler(addTransactionItemClickHandler);

        innerBorder.setTop(transactionViewHeader.getTransactionViewHeader());
        innerBorder.setCenter(transactionListView.getTransactionListView());

        setHeadBarView();

        selectedTab = "transaction";
    }

    public void categoryTabClicked(){

        resetObservableLists();

        categoryListView = new CategoryListView();

        categoryListView.setCategoryObservableList(incomeCategoryObservableList);
        categoryListView.setCategoryItemClickHandler(categoryItemClickHandler);

        categoryViewHeader = new CategoryViewHeader("Categories");
        categoryViewHeader.setIncomeToggleButtonClickHandler(incomeToggleButtonClickHandler);
        categoryViewHeader.setExpenseToggleButtonClickHandler(expenseToggleButtonClickHandler);
        categoryViewHeader.setAddCategoryItemClickHandler(addCategoryItemClickHandler);

        innerBorder.setTop(categoryViewHeader.getCategoryViewHeader());
        categoryViewHeader.getIncomeToggleButton().fire();
        innerBorder.setCenter(categoryListView.getCategoryListView());

        setHeadBarView();

        selectedTab = "category";
    }

    public void settingsTabClicked(){

        settingsView = new SettingsView();

        settingsView.setManageProfileButtonClickHandler(manageProfileButtonClickHandler);
        innerBorder.setTop(headLabel.getHeadLabel("Settings"));
        innerBorder.setCenter(settingsView.getSettingsView());

        setHeadBarView();

        selectedTab = "settings";
    }

// Set scenes

    public void setProfileListView(){
        resetObservableLists();
        profileListView = new ProfileListView();
        profileListView.setProfileObservableList(userObservableList);
        profileListView.setProfileItemClickHandler(profileItemClickHandler);
        innerBorder.setCenter(profileListView.getProfileListView());
    }

    public void setProfileFormView(){
        resetObservableLists();
        profileForm = new ProfileForm();
        profileForm.setActionButtonClickHandler(profileActionButtonClickHandler);
        profileForm.setDeleteButtonClickHandler(profileDeleteButtonClickHandler);
        profileForm.setCancelButtonClickHandler(profileCancelButtonClickHandler);
    }

    public void setTransactionFormView(){
        resetObservableLists();
        transactionForm = new TransactionForm();
        transactionForm.setIncomeCategoryObservableList(incomeCategoryObservableList);
        transactionForm.setExpenseCategoryObservableList(expenseCategoryObservableList);
        transactionForm.setActionButtonClickHandler(transactionActionButtonClickHandler);
        transactionForm.setDeleteButtonClickHandler(transactionDeleteButtonClickHandler);
        transactionForm.setCancelButtonClickHandler(transactionCancelButtonClickHandler);
    }

    public void setCategorFormView(){
        categoryForm = new CategoryForm();
        categoryForm.setActionButtonClickHandler(categoryActionButtonClickHandler);
        categoryForm.setDeleteButtonClickHandler(categoryDeleteButtonClickHandler);
        categoryForm.setCancelButtonClickHandler(categoryCancelButtonClickHandler);
    }

// Action Handlers

// Action Handlers - HeaderBar

    EventHandler<ActionEvent> profileClickHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent arg0) {

            int profileId = headBarView.getProfile().getSelectionModel().getSelectedItem().getUserId();
            selectedProfileId = profileId;

            resetView();
            
        }};

// Action Handlers - Profile

    EventHandler<MouseEvent> addProfileClickHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            
            setProfileFormView();
            profileForm.setFormMode("INSERT");
            
            innerBorder.setCenter(profileForm.getProfileForm()); 
            innerBorder.setTop(headLabel.getHeadLabel("Add Profile"));
            
        }
    };

    EventHandler<MouseEvent> profileActionButtonClickHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            
            try {
                int profileId = profileForm.getId();
                String profileName = profileForm.getName().getText();
                String budgetString = profileForm.getBudget().getText();

                Float budget = Float.valueOf(0);

                if (budgetString.isEmpty()){

                } else {
                    budget = Float.parseFloat(budgetString);
                }
                
                if (profileForm.getFormMode().equalsIgnoreCase("INSERT")){

                    controller.createProfile(profileName, budget);

                    resetView();

                } else if (profileForm.getFormMode().equalsIgnoreCase("UPDATE")){

                    User user = new User(profileId, profileName, budget);
                    
                    controller.updateProfile(user);

                    setProfileListView();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    };

    EventHandler<MouseEvent> profileDeleteButtonClickHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent arg0) {
         
            try {

                int profileId = profileForm.getId();

                if (profileForm.getFormMode().equalsIgnoreCase("INSERT")){

                } else if (profileForm.getFormMode().equalsIgnoreCase("UPDATE")){

                    controller.deleteProfile(profileId);;

                    setProfileListView();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    };

    EventHandler<MouseEvent> profileCancelButtonClickHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent arg0) {

            try {
                if (profileForm.getFormMode().equalsIgnoreCase("INSERT")){
                    
                    resetView();

                } else if (profileForm.getFormMode().equalsIgnoreCase("UPDATE")){

                    innerBorder.setCenter(profileListView.getProfileListView());
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    };

    EventHandler<MouseEvent> manageProfileButtonClickHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent arg0) {

            try {
                setProfileListView();
                innerBorder.setTop(headLabel.getHeadLabel("Profiles"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    EventHandler<MouseEvent> profileItemClickHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent arg0) {

            try {
                setProfileFormView();
                profileForm.setFormMode("Update");

                int profileId = profileListView.getProfileListView().getSelectionModel().getSelectedItem().getUserId();
                String name = profileListView.getProfileListView().getSelectionModel().getSelectedItem().getUserName();
                
                profileForm.setId(profileId);
                profileForm.getName().setText(name);

                innerBorder.setCenter(profileForm.getProfileForm()); 
                innerBorder.setTop(headLabel.getHeadLabel("Update Profile"));
            } catch (NullPointerException e){

            } catch (Exception e){
                e.printStackTrace();
            }


        }
    };

// Action handlers - Home

    EventHandler<ActionEvent> selectcategoryEventhandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent arg0) {

            Category category = homeView.getSelectCategoryComboBox().getSelectionModel().getSelectedItem();

            try {

                Float catgeorySummary = controller.getCategorySummary(selectedProfileId, category.getId());

                String catSummary = Float.toString(catgeorySummary);

                if(!category.isExpenseCategory()){

                    homeView.getEarningLabel().setText(catSummary);
                    
                } else if(category.isExpenseCategory()){

                    //String budget = Float.toString(expense.getBudget());
                    //String balance = Float.toString(expense.getBudget() - catgeorySummary);

                    homeView.getBudgetLabel().setText("0.00");
                    homeView.getExpenseLabel().setText(catSummary);
                    homeView.getBalanceLabel().setText("0.00");
                    homeView.setSubColorOnValue();
                }    
            } catch (Exception e) {

            }
                    
            
        }};

// Action Handlers - Transaction

    EventHandler<MouseEvent> addTransactionItemClickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            setTransactionFormView();
            transactionForm.setFormMode("INSERT");

            innerBorder.setCenter(transactionForm.getTransactionForm(true)); 
            innerBorder.setTop(headLabel.getHeadLabel("Add transaction"));

        }
    };

    EventHandler<MouseEvent> transactionItemClickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            try {

                setTransactionFormView();
                transactionForm.setFormMode("Update");

                // transactionForm.setAmount(event.getPickResult().getClass().);
                int transactionId = transactionListView.getTransactionListView().getSelectionModel().getSelectedItem().getTransactionId();
                String amount = Float.toString(transactionListView.getTransactionListView().getSelectionModel().getSelectedItem().getAmount());
                Category category = transactionListView.getTransactionListView().getSelectionModel().getSelectedItem().getCategory();
                String note = transactionListView.getTransactionListView().getSelectionModel().getSelectedItem().getNote();
                Boolean recurring = transactionListView.getTransactionListView().getSelectionModel().getSelectedItem().isRecurring();
                String date = transactionListView.getTransactionListView().getSelectionModel().getSelectedItem().getTransactionDate().toString();
                Date date1 = dateFormatter.parse(date);
                
                transactionForm.setId(transactionId);
                transactionForm.getAmount().setText(amount);
                transactionForm.getCategory().getSelectionModel().select(category);
                transactionForm.getNote().setText(note);
                transactionForm.getDate().setValue(date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

                if(recurring){
                    transactionForm.getRecurring().fire();
                }
                innerBorder.setCenter(transactionForm.getTransactionForm(!category.isExpenseCategory()));
                innerBorder.setTop(headLabel.getHeadLabel("Update transaction"));
            } catch (NullPointerException e){
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };

    EventHandler<MouseEvent> transactionActionButtonClickHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent arg0) {
            
            try {

                int transactionId = transactionForm.getId();
                float amount = Float.parseFloat(transactionForm.getAmount().getText());
                Boolean recurring = transactionForm.getRecurring().isPressed();
                String note = transactionForm.getNote().getText();
                Category category = transactionForm.getCategory().getSelectionModel().getSelectedItem();
                Date date = dateFormatter.parse(transactionForm.getDate().getValue().toString());
                boolean isExpense = transactionForm.getExpenseOption().isSelected();

                if (transactionForm.getFormMode().equalsIgnoreCase("INSERT")){
                    controller.createTransaction(selectedProfileId, amount, recurring, note, category,  date, isExpense);

                } else if (transactionForm.getFormMode().equalsIgnoreCase("UPDATE")){
                    Transaction transaction = new Transaction(transactionId, amount, recurring, note, category, date, isExpense);
                    controller.updateTransaction(selectedProfileId, transaction);
                }

                transactionTabClicked();

            } catch (NumberFormatException e) {
                transactionForm.getErrorLabel().setText("Error: Please validate inputs.");
            } catch (NullPointerException e) {
                transactionForm.getErrorLabel().setText("Error: Please validate inputs.");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    };

    EventHandler<MouseEvent> transactionDeleteButtonClickHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent arg0) {
            
            try {

                int transactionId = transactionForm.getId();


                if (transactionForm.getFormMode().equalsIgnoreCase("INSERT")){
                    
                } else if (transactionForm.getFormMode().equalsIgnoreCase("UPDATE")){

                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(transactionId);
                    controller.deleteTransaction(selectedProfileId, transaction);
                }

                transactionTabClicked();

            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    };

    EventHandler<MouseEvent> transactionCancelButtonClickHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent arg0) {

            try {
                transactionTabClicked();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    };

// Action Handlers - Category

    EventHandler<MouseEvent> addCategoryItemClickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            setCategorFormView();
            categoryForm.setFormMode("INSERT");
            
            innerBorder.setCenter(categoryForm.getCategoryForm(true)); 
            innerBorder.setTop(headLabel.getHeadLabel("Add category"));

        }
    };

    EventHandler<MouseEvent> categoryItemClickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            try {

                Category category = categoryListView.getCategoryListView().getSelectionModel().getSelectedItem();

                if (category.equals(null)){

                } else {
                    setCategorFormView();
                    categoryForm.setFormMode("Update");

                    int categoryId = category.getId();
                    String name = category.getName().toString();
                    categoryForm.setId(categoryId);
                    categoryForm.getName().setText(name);

                    if (category.isExpenseCategory()) {
                        categoryForm.getBudget().setText(Float.toString(0.0f));
                    }
                    innerBorder.setCenter(categoryForm.getCategoryForm(!category.isExpenseCategory()));
                    innerBorder.setTop(headLabel.getHeadLabel("Update Category"));
                }
            } catch (NullPointerException e){
                
            } catch (Exception e) {
                e.printStackTrace();
            }
    

        }
    };


    EventHandler<MouseEvent> incomeToggleButtonClickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            categoryListView.setCategoryObservableList(incomeCategoryObservableList);
            innerBorder.setCenter(categoryListView.getCategoryListView());

        }
    };


    EventHandler<MouseEvent> expenseToggleButtonClickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
           
            categoryListView.setCategoryObservableList(expenseCategoryObservableList);
            innerBorder.setCenter(categoryListView.getCategoryListView());

        }
    };


    EventHandler<MouseEvent> categoryActionButtonClickHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent arg0) {

            try {

                int categoryId = categoryForm.getId();
                String name = categoryForm.getName().getText();

                String budgetString = categoryForm.getBudget().getText();
                Float budget = Float.valueOf(0);

                if (budgetString.isEmpty()){

                } else {
                    budget = Float.parseFloat(budgetString);
                }
                
                Boolean isExpense = categoryForm.getBudget().isVisible();

                if (categoryForm.getFormMode().equalsIgnoreCase("INSERT")){

                    if (isExpense){
                        controller.createCategory(selectedProfileId, name, true);
                    } else {
                        controller.createCategory(selectedProfileId, name, false);
                    }

                } else if (categoryForm.getFormMode().equalsIgnoreCase("UPDATE")){

                    if (isExpense){
                        controller.updateCategory(selectedProfileId, new Category(categoryId, name, true));;
                    } else {
                        controller.updateCategory(selectedProfileId, new Category(categoryId, name, false));;
                    }

                }

                categoryTabClicked();

            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    };

    EventHandler<MouseEvent> categoryDeleteButtonClickHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent arg0) {

            Category category = categoryListView.getCategoryListView().getSelectionModel().getSelectedItem();

            try {

                if (categoryForm.getFormMode().equalsIgnoreCase("INSERT")){

                } else if (categoryForm.getFormMode().equalsIgnoreCase("UPDATE")){
                    controller.deleteCategory(selectedProfileId, category);
                }
                categoryTabClicked();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    EventHandler<MouseEvent> categoryCancelButtonClickHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent arg0) {

            try {
                categoryTabClicked();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    };

    
}
