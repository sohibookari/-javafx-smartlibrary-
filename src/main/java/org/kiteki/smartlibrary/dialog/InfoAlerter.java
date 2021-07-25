package org.kiteki.smartlibrary.dialog;

import com.sun.source.tree.LambdaExpressionTree;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.kiteki.smartlibrary.domain.book.BookViews;
import org.kiteki.smartlibrary.domain.book.Books;
import org.kiteki.smartlibrary.domain.session.UserSession;

import java.util.Optional;

public class InfoAlerter {
    public void loginSucceed(UserSession userSession){
        Alert loginSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
        loginSuccessAlert.setTitle("消息");
        loginSuccessAlert.setHeaderText("登陆状态");
        loginSuccessAlert.setContentText(
                userSession.getUserName() + "阁下, 欢迎你\n" +
                        "你现在以 " + userSession.getRoleName() + "的身份成功登陆");
        loginSuccessAlert.showAndWait();
    }

    public void logoutSucceed(){
        Alert loginSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
        loginSuccessAlert.setTitle("消息");
        loginSuccessAlert.setHeaderText("登陆状态");
        loginSuccessAlert.setContentText("您已经成功注销");
        loginSuccessAlert.showAndWait();
    }

    public void loginFailed() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("消息");
        alert.setHeaderText("登陆状态");
        alert.setContentText("您填写的登陆信息有误, 请核对");
        alert.showAndWait();
    }

    public void insertSucceed(Books book) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("消息");
        alert.setHeaderText("操作状态");
        alert.setContentText("您已经成功插入了书籍 " + book.getName());
        alert.showAndWait();
    }

    public void updateSucceed(Books book) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("消息");
        alert.setHeaderText("操作状态");
        alert.setContentText("您已经成功更新了书籍 " + book.getName());
        alert.showAndWait();
    }

    public void insertFailed() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("消息");
        alert.setHeaderText("操作状态");
        alert.setContentText("插入书籍失败, 请检查你的操作");
        alert.showAndWait();
    }

    public void updateFailed() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("消息");
        alert.setHeaderText("操作状态");
        alert.setContentText("更新书籍失败, 请检查你的操作");
        alert.showAndWait();
    }

    public boolean deleteConfirm(BookViews bookViews) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认");
        alert.setHeaderText("操作确认");
        alert.setContentText("您确认要删除 " + bookViews.getName() + " 这本书籍吗？");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    public void about() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("关于");
        alert.setHeaderText("关于智能图书管理系统");
        alert.setContentText("\t本软件由\n\tkitekira@outlook.com\n\t开发");
        alert.showAndWait();
    }

    public void permissionFailed(UserSession userSession) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("消息");
        alert.setHeaderText("操作状态");
        alert.setContentText("操作失败, 阁下( " + userSession.getRoleName() + " )没有权限这样做");
        alert.showAndWait();
    }
}
