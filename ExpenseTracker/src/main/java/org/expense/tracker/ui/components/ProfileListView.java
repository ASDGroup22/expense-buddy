package org.expense.tracker.ui.components;

import org.expense.tracker.models.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class ProfileListView{
    
    private ListView<User> profileListView;
    private ObservableList<User> userObservableList;
    private EventHandler<MouseEvent> profileItemClickHandler;

    public ProfileListView() {
        this.profileListView = new ListView<>();
        this.userObservableList = FXCollections.observableArrayList();
        this.profileListView.setOnMouseClicked(this.profileItemClickHandler);
        this.profileListView.getStyleClass().add("label-black-bold");
    }

    public void initialize() {
        profileListView.setItems(userObservableList);
        // profileListView.setCellFactory(transactionListView -> new CategoryListViewCell());
        profileListView.setOnMouseClicked(profileItemClickHandler);
    }

    public ListView<User> getProfileListView() {
        initialize();
        return profileListView;
    }

    public void setProfileListView(ListView<User> profileListView) {
        this.profileListView = profileListView;
    }

    public ObservableList<User> getProfileObservableList() {
        return userObservableList;
    }

    public void setProfileObservableList(ObservableList<User> userObservableList) {
        this.userObservableList = userObservableList;
    }

    public EventHandler<MouseEvent> getProfileItemClickHandler() {
        return profileItemClickHandler;
    }

    public void setProfileItemClickHandler(EventHandler<MouseEvent> profileItemClickHandler) {
        this.profileItemClickHandler = profileItemClickHandler;
    }


    
    
}
