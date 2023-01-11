package org.expense.tracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.expense.tracker.ui.MainController;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class MainUI extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Start the Java Debug Wire Protocol (JDWP)
        startJDWP();
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

    private void startJDWP() throws IOException {
        int jdwpPort = 5005;
        InetAddress jdwpAddress = InetAddress.getByName("localhost");
        ServerSocket jdwpServer = new ServerSocket(jdwpPort, 0, jdwpAddress);
    }

}
