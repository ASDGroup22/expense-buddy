package org.expense.tracker.ui.components;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;

public class SettingsView {

    private GridPane gridPane;

    private Label profileSettingsNameLabel;

    private Button manageProfileButton;
    

    private EventHandler<MouseEvent> manageProfileButtonClickHandler;


    public SettingsView() {

        this.gridPane = new GridPane();
        this.gridPane.setPadding(new Insets(12,12,12,12));
        this.gridPane.setHgap(8);
        this.gridPane.setVgap(8);

        this.profileSettingsNameLabel = new Label("Profile Settings");
        this.profileSettingsNameLabel.getStyleClass().add("label-sub-head");
        this.manageProfileButton = new Button("Manage profiles");

        ColumnConstraints columnConstraint = new ColumnConstraints();
        columnConstraint.setPercentWidth(100d);
        gridPane.getColumnConstraints().add(columnConstraint);

        
        this.manageProfileButton.setOnMouseClicked(this.manageProfileButtonClickHandler);
        
        this.gridPane.add(this.profileSettingsNameLabel, 0, 2);
        this.gridPane.add(this.manageProfileButton, 0, 3);

    }

    public GridPane getSettingsView(){
        manageProfileButton.setOnMouseClicked(manageProfileButtonClickHandler);
        return gridPane;
    }

    public EventHandler<MouseEvent> getManageProfileButtonClickHandler() {
        return manageProfileButtonClickHandler;
    }

    public void setManageProfileButtonClickHandler(EventHandler<MouseEvent> manageProfileButtonClickHandler) {
        this.manageProfileButtonClickHandler = manageProfileButtonClickHandler;
    }

}