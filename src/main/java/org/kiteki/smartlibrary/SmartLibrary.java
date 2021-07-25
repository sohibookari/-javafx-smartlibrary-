package org.kiteki.smartlibrary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.kiteki.smartlibrary.dialog.LoginDialog;

import java.io.IOException;
import java.util.Optional;

public class SmartLibrary extends Application {
    Pair<String, String> logInfo;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader rootLoader = new FXMLLoader();
        rootLoader.setLocation(getClass().getResource("/SmartLibrary.fxml"));
        Parent rootNode = rootLoader.load();
        Scene rootScene = new Scene(rootNode);

        SmartLibraryController controller = rootLoader.getController();
        controller.setSmartLibrary(this);

        primaryStage.setTitle("智能图书管理系统");
        primaryStage.setScene(rootScene);
        primaryStage.show();
    }

    public Pair<String, String> getLogInfo() {
        return logInfo;
    }

    public void showLoginDialog(ActionEvent actionEvent) {
        LoginDialog loginDialog = new LoginDialog();
        Optional<Pair<String, String>> li = loginDialog.showAndWait();
        li.ifPresent( r -> logInfo= new Pair<>(r.getKey(), r.getValue())
        );

    }
}
