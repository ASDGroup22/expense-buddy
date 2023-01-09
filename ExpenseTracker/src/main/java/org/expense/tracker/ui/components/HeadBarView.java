package org.expense.tracker.ui.components;

import org.expense.tracker.models.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class HeadBarView {
    
    private HBox headBar;
    
    private Region region;
    private ComboBox<User> profile;
    private Button addProfile;

    private ObservableList<User> userObservableList;
    private EventHandler<MouseEvent> addProfileClickHandler;
    private EventHandler<ActionEvent> profileClickHandler;


    public HeadBarView() {

        this.headBar = new HBox();   
        
        this.region = new Region();
        this.profile = new ComboBox<User>();
        this.addProfile = new Button("+");
        this.addProfile.getStyleClass().add("button-add-profile");

        this.userObservableList = FXCollections.observableArrayList();

        this.addProfile.setOnMouseClicked(this.addProfileClickHandler);
        
        this.profile.setOnAction(this.profileClickHandler);
        this.profile.getStyleClass().add("combo-box-profile");

        HBox.setHgrow(region, Priority.ALWAYS);
        HBox.setHgrow(headBar, Priority.ALWAYS);

        this.headBar.setPadding(new Insets(12,12,12,12));
        this.headBar.getChildren().addAll(region, profile, addProfile);
    }


    public HBox getHeadBar(){
               
        profile.setItems(userObservableList);
        profile.setOnAction(profileClickHandler);
        addProfile.setOnMouseClicked(addProfileClickHandler);
        headBar.getChildren().setAll(region, profile, addProfile);
        
        return headBar;

    }

    public ComboBox<User> getProfile() {
        return profile;
    }

    public void setProfile(ComboBox<User> profile) {
        this.profile = profile;
    }

    public ObservableList<User> getProfileObservableList() {
        return userObservableList;
    }

    public void setProfileObservableList(ObservableList<User> userObservableList) {
        this.userObservableList = userObservableList;
    }

    public EventHandler<MouseEvent> getAddProfileClickHandler() {
        return addProfileClickHandler;
    }

    public void setAddProfileClickHandler(EventHandler<MouseEvent> addProfileClickHandler) {
        this.addProfileClickHandler = addProfileClickHandler;
    }


    public EventHandler<ActionEvent> getProfileClickHandler() {
        return profileClickHandler;
    }


    public void setProfileClickHandler(EventHandler<ActionEvent> profileClickHandler) {
        this.profileClickHandler = profileClickHandler;
    }

}
