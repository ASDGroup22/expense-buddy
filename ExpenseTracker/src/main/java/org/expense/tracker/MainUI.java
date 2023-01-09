package org.expense.tracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.expense.tracker.ui.MainController;

import java.io.IOException;

public class MainUI extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        MainController mainController = new MainController();
        scene = new Scene(loadFXML("fxml/main_ui", mainController));
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Expense Tracker Buddy");
        stage.show();
    }

    public static Parent loadFXML(String fxml, Object controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainUI.class.getResource(fxml + ".fxml"));
        fxmlLoader.setController(controller);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
