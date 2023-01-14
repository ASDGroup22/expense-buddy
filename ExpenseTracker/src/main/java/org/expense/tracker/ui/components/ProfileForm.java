package org.expense.tracker.ui.components;


import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ProfileForm {

    private int id;

    private GridPane gridPane;

    private Label nameLabel;
    private TextField name;

    private Button actionButton;
    private Button deleteButton;
    private Button cancelButton;

    private EventHandler<MouseEvent> actionButtonClickHandler;
    private EventHandler<MouseEvent> deleteButtonClickHandler;
    private EventHandler<MouseEvent> cancelButtonClickHandler;

    private String formMode;

    public ProfileForm() {

        gridPane = new GridPane();
        gridPane.setPadding(new Insets(12,12,12,12));
        gridPane.setHgap(8);
        gridPane.setVgap(8);

        nameLabel = new Label("Name");
        name = new TextField();

        ColumnConstraints columnConstraint = new ColumnConstraints();
        columnConstraint.setPercentWidth(100d/2);
        gridPane.getColumnConstraints().add(columnConstraint);

        
        actionButton = new Button();
        deleteButton = new Button("Delete");
        cancelButton = new Button("Cancel");

        deleteButton.getStyleClass().add("button-delete");
        cancelButton.getStyleClass().add("button-cancel");


        gridPane.add(nameLabel, 0, 0);
        gridPane.add(name, 1, 0);

        gridPane.add(actionButton, 0, 8);
        gridPane.add(deleteButton, 1, 8);
        gridPane.add(cancelButton, 1, 8);

        gridPane.setHalignment(actionButton, HPos.CENTER);
        gridPane.setHalignment(deleteButton, HPos.LEFT);
        gridPane.setHalignment(cancelButton, HPos.RIGHT);
  

    }

    public GridPane getProfileForm(){

        actionButton.setText(formMode);

        actionButton.setOnMouseClicked(actionButtonClickHandler);
        deleteButton.setOnMouseClicked(deleteButtonClickHandler);
        cancelButton.setOnMouseClicked(cancelButtonClickHandler);

        if (formMode.equalsIgnoreCase("INSERT")){
            deleteButton.setVisible(false);
            actionButton.getStyleClass().add("button-create");
        } else {
            actionButton.getStyleClass().add("button-update");
        }

        return gridPane;
    }

    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
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

    public String getFormMode() {
        return formMode;
    }

    public void setFormMode(String formMode) {
        this.formMode = formMode;
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
    
    
}