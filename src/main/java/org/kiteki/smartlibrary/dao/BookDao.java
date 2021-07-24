package org.kiteki.smartlibrary.dao;

import org.kiteki.smartlibrary.domain.book.Books;

import java.util.List;

public interface BookDao {
    Books selectBook(int id);
    List<Books> selectBooksAsList();
    void insertBook(Books books);
    void updateBook(Books books);
    void deleteBook(int id);
    List<Books> selectBookByName(String name);
}
