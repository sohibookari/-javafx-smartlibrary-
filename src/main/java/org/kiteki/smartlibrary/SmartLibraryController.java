package org.kiteki.smartlibrary;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.log4j.Logger;
import org.kiteki.smartlibrary.dialog.InfoAlerter;
import org.kiteki.smartlibrary.domain.book.BookViews;
import org.kiteki.smartlibrary.domain.book.Books;
import org.kiteki.smartlibrary.domain.session.UserSession;
import org.kiteki.smartlibrary.service.BookService;
import org.kiteki.smartlibrary.service.SessionService;

import java.net.URL;
import java.util.ResourceBundle;

public class SmartLibraryController implements Initializable {
    Logger slcLogger = Logger.getLogger(SmartLibraryController.class);
    InfoAlerter infoAlerter;
    private SmartLibrary smartLibrary;
    private SessionService sessionService;
    private BookService bookService;
    private UserSession userSession;

    private BookViews selectedBook = null;
    private boolean ifBookSelected = false;

    public void setSmartLibrary(SmartLibrary smartLibrary) {
        this.smartLibrary = smartLibrary;
    }

    @FXML
    private Menu userMenu;

    @FXML
    private Button bookInfoQueryBt;

    @FXML
    private Button bookInfoAddBt;

    @FXML
    private Button bookInfoUpdateBt;

    @FXML
    private Button bookInfoDeleteBt;

    @FXML
    private Button bookInfoSubmitBt;

    @FXML
    private Button bookInfoBorrowBt;

    @FXML
    private TableView<BookViews> booksTable;

    @FXML
    private TableColumn<BookViews, String> bookNameColumn;

    @FXML
    private TableColumn<BookViews, String> bookISBNColumn;

    @FXML
    private TableColumn<BookViews, String> bookAuthorColumn;

    @FXML
    private TableColumn<BookViews, String> bookStatusColumn;

    @FXML
    private TableColumn<BookViews, String> bookPressColumn;

    @FXML
    private TextField bookNameField;

    @FXML
    private TextField bookAuthorField;

    @FXML
    private TextField bookPressField;

    @FXML
    private TextField bookISBNField;

    @FXML
    private TextField bookStatusField;

    @FXML
    public void switchBookInfoQuery(ActionEvent event) {
        bookService.refreshBookList();
        booksTable.getItems().clear();
        for (Books book : bookService.getBooksList()) {
            booksTable.getItems().add(new BookViews(
                    book.getId(),
                    book.getName(),
                    book.getIsbn(),
                    book.getAuthor(),
                    book.getPress(),
                    statusToString(book.getStatus())
            ));
        }
    }

    @FXML
    public void switchBookInfoUpdate(ActionEvent actionEvent) {
        ifBookSelected = true;
        setBookEdit(true);
    }

    @FXML
    public void switchBookInfoAdd(ActionEvent actionEvent) {
        setBookEdit(true);
        bookNameField.clear();
        bookAuthorField.clear();
        bookPressField.clear();
        bookISBNField.clear();
        bookStatusField.setText("未出借");

        ifBookSelected = false;
    }

    @FXML
    public void switchBookInfoDelete(ActionEvent actionEvent) {
        if (infoAlerter.deleteConfirm(selectedBook)) {
            bookService.deleteBook(selectedBook.getId());
            switchBookInfoQuery(actionEvent);
        } else {
            return;
        }
    }

    @FXML
    public void handleBookInfoSubmit(ActionEvent actionEvent) {
        Books book = new Books();
        book.setName(bookNameField.getText());
        book.setAuthor(bookAuthorField.getText());
        book.setPress(bookPressField.getText());
        book.setIsbn(bookISBNField.getText());
        book.setStatus(statusToInteger(bookStatusField.getText()));

        if (ifBookSelected) {
            book.setId(selectedBook.getId());
            bookService.updateBook(book.getId(), book);
            if (bookService.getBook(book.getId()).equals(book)) {
                infoAlerter.updateSucceed(book);
            } else {
                infoAlerter.updateFailed();
            }
        } else {
            bookService.insertBook(book);
            if (bookService.getBookByName(book.getName()).get(0) != null) {
                infoAlerter.insertSucceed(book);
            } else {
                infoAlerter.insertFailed();
            }
        }

        setBookEdit(false);
        ifBookSelected = false;
        switchBookInfoQuery(actionEvent);
    }

    @FXML
    public void handleLoginAction(ActionEvent actionEvent) {
        smartLibrary.showLoginDialog(actionEvent);
        slcLogger.info("User input login info: " + smartLibrary.getLogInfo());
        sessionService.login(
                smartLibrary.getLogInfo().getKey(),
                smartLibrary.getLogInfo().getValue()
        );
        if (sessionService.checkSessionStatus()) {
            userSession = sessionService.getUserSession();
            slcLogger.info("User login as: " + userSession.getRoleName());
            initializeBookService();
            infoAlerter.loginSucceed(userSession);
            userMenu.setText(userSession.getUserName()+"(已登录)");
        } else {
            slcLogger.error("User login information can' t resolve.");
            infoAlerter.loginFailed();
        }
    }

    @FXML
    public void handleLogoutAction(ActionEvent actionEvent) {
        userSession = null;
        bookInfoQueryBt.setDisable(true);
        bookInfoAddBt.setDisable(true);
        bookInfoDeleteBt.setDisable(true);
        bookInfoUpdateBt.setDisable(true);
        bookInfoSubmitBt.setDisable(true);
        booksTable.getItems().clear();
        userMenu.setText("用户(未登录)");
        infoAlerter.logoutSucceed();
    }

    @FXML
    public void handleExitAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    public void handleAboutAction(ActionEvent actionEvent) {
        infoAlerter.about();
    }

    @FXML
    public void switchBookInfoBorrow(ActionEvent actionEvent) {
        switch (handleSelectedBookStatus()) {
            case 0:
                bookService.borrowBook(selectedBook.getId());
                bookInfoBorrowBt.setText("归还当前书目");
                break;
            case 1:
                bookService.returnBook(selectedBook.getId());
                bookInfoBorrowBt.setText("借阅当前书目");
                break;
            default:
                break;
        }
        switchBookInfoQuery(actionEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sessionService = new SessionService();
        infoAlerter = new InfoAlerter();
        userMenu.setText("用户(未登录)");

        bookNameColumn.setCellValueFactory( new PropertyValueFactory<>("name"));
        bookISBNColumn.setCellValueFactory( new PropertyValueFactory<>("isbn"));
        bookAuthorColumn.setCellValueFactory( new PropertyValueFactory<>("author") );
        bookPressColumn.setCellValueFactory( new PropertyValueFactory<>("press") );
        bookStatusColumn.setCellValueFactory( new PropertyValueFactory<>("status") );

        // set table view select event.
        booksTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedBook = newSelection;
                bookInfoUpdateBt.setDisable(false);
                bookInfoDeleteBt.setDisable(false);
                slcLogger.info("User select " + newSelection);
                setBookInfo();
                handleSelectedBookStatus();
            } else {
                bookInfoUpdateBt.setDisable(true);
                bookInfoDeleteBt.setDisable(true);
            }
        });
    }

    public void initializeBookService() {
        bookService = new BookService(userSession);
        bookInfoQueryBt.setDisable(false);
        bookInfoAddBt.setDisable(false);
    }

    public String statusToString(Integer status) {
        if (status.equals(0)) {
            return "未出借";
        } else {
            return "已出借";
        }
    }

    public Integer statusToInteger(String status) {
        if (status.equals("未出借")) {
            return 0;
        } else {
            return 1;
        }
    }

    public void setBookEdit(boolean b) {
        bookInfoSubmitBt.setDisable(!b);
        bookNameField.setEditable(b);
        bookAuthorField.setEditable(b);
        bookPressField.setEditable(b);
        bookISBNField.setEditable(b);
    }

    public void setBookInfo() {
        Books book = bookService.getBook(selectedBook.getId());
        bookNameField.setText(book.getName());
        bookAuthorField.setText(book.getAuthor());
        bookPressField.setText(book.getPress());
        bookISBNField.setText(book.getIsbn());
        bookStatusField.setText(statusToString(book.getStatus()));
    }

    public int handleSelectedBookStatus() {
        if (bookService.getBook(selectedBook.getId()).getStatus() == 0 ) {
            bookInfoBorrowBt.setText("借阅当前书目");
            bookInfoBorrowBt.setDisable(false);
            return 0;
        } else if (bookService.isBookBorrowByCurUser(selectedBook.getId())) {
            bookInfoBorrowBt.setText("归还当前书目");
            bookInfoBorrowBt.setDisable(false);
            return 1;
        } else {
            bookInfoBorrowBt.setText("借阅当前书目");
            bookInfoBorrowBt.setDisable(true);
            return 2;
        }
    }
}
