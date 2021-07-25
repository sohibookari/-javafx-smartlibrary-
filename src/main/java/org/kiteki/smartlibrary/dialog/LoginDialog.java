package org.kiteki.smartlibrary.dialog;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class LoginDialog extends Dialog<Pair<String, String>> {
    public LoginDialog() {
        setTitle("登陆");
        setHeaderText("请输入你的登陆信息");

        Label lbUser = new Label("用户名");
        Label lbPasswd = new Label("密码");
        TextField tfUser = new TextField();
        TextField tfPasswd = new TextField();
        tfUser.setPromptText("此处输入用户名");
        tfPasswd.setPromptText("此处输入密码");

        // init button.
        ButtonType loginButtonType = new ButtonType("登陆", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(loginButtonType, cancelButtonType);

        // init input pane.
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(30, 150, 10, 10));

        gridPane.add(lbUser, 0, 0);
        gridPane.add(tfUser, 1, 0);
        gridPane.add(lbPasswd, 0, 1);
        gridPane.add(tfPasswd, 1, 1);

        // make bt disable when info invalid.
        Node loginButton = getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);
        tfUser.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        getDialogPane().setContent(gridPane);

        // handle login bt was actioned.
        setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(tfUser.getText(), tfPasswd.getText());
            }
            return null;
        });
    }
}
