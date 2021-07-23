package org.kiteki.smartlibrary.service;

import org.kiteki.smartlibrary.dao.BookDaoImpl;
import org.kiteki.smartlibrary.domain.book.Books;
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

    public List<Books> getBooksList() {
        return booksList;
    }

    public void refreshBookList() {
        booksList = bookDao.selectBooksAsList();
    }

    public void insertBook(Books books) {
        if (!checkIfAdmin()) return;
        bookDao.insertBook(books);
    }

    public void updateBook(Books books) {
        if (!checkIfAdmin()) return;
        bookDao.updateBook(books);
    }

    public void deleteBook(int id) {
        if (!checkIfAdmin()) return;
        bookDao.deleteBook(id);
    }

    public void borrowBook(int id) {
        Books book = getBook(id);
        book.setStatus(1);
        updateBook(book);
    }

    public void returnBook(int id) {
        Books book = getBook(id);
        book.setStatus(0);
        updateBook(book);
    }

    boolean checkIfAdmin() {
        return userSession.getRoleName().equals("admin");
    }
}
