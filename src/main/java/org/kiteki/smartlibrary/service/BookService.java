package org.kiteki.smartlibrary.service;

import org.kiteki.smartlibrary.dao.BookDaoImpl;
import org.kiteki.smartlibrary.dialog.InfoAlerter;
import org.kiteki.smartlibrary.domain.book.Books;
import org.kiteki.smartlibrary.domain.session.BorrowInfo;
import org.kiteki.smartlibrary.domain.session.UserSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class BookService {
    private final UserSession userSession;
    private List<Books> booksList;
    private final BookDaoImpl bookDao;

    public BookService(UserSession userSession) {
        // init user session.
        this.userSession = userSession;
        // init book dao.
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        bookDao = context.getBean("bookDaoImpl", BookDaoImpl.class);
        // init books list.
        refreshBookList();
    }

    public Books getBook(int id) {
        return bookDao.selectBook(id);
    }

    public List<Books> getBookByName(String name) {return bookDao.selectBookByName(name); }

    public List<Books> getBooksList() {
        return booksList;
    }

    public void refreshBookList() {
        booksList = bookDao.selectBooksAsList();
    }

    public void insertBook(Books books) {
        if (checkPermission())
            bookDao.insertBook(books);
    }

    public void updateBook(int id, Books books) {
        if (checkPermission()) {
            if(books.getId() == id) {
                bookDao.updateBook(books);
            }
            else {
                throw new UnsupportedOperationException("Can not update a book with invalid id.");
            }
        }
    }

    public void deleteBook(int id) {
        if (checkPermission())
            bookDao.deleteBook(id);
    }

    public void borrowBook(int id) {
        Books book = getBook(id);
        bookDao.insertBorrowBookInfo(new BorrowInfo(userSession.getUserId(), book.getId()));
        book.setStatus(1);
        bookDao.updateBook(book);
    }

    public void returnBook(int id) {
        Books book = getBook(id);
        bookDao.deleteBorrowBookInfo(new BorrowInfo(userSession.getUserId(), book.getId()));
        book.setStatus(0);
        bookDao.updateBook(book);
    }

    boolean checkPermission() {
        if (userSession.getRoleName().equals("admin") ||
                userSession.getRoleName().equals("test_role")) {
            return true;
        }
        else {
            new InfoAlerter().permissionFailed(userSession);
            throw new UnsupportedOperationException("User have no permission to operate.");
        }
    }

    public boolean isBookBorrowByCurUser(int id) {
        BorrowInfo borrowInfo = bookDao.selectBorrowBookInfo(new BorrowInfo(userSession.getUserId(), id));
        try {
            borrowInfo.getId();
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
