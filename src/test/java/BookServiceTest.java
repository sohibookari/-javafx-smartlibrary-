import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kiteki.smartlibrary.domain.book.Books;
import org.kiteki.smartlibrary.service.BookService;
import org.kiteki.smartlibrary.service.SessionService;

import java.util.List;

public class BookServiceTest {
    private SessionService sessionService;
    private BookService bookService;

    public BookServiceTest() {
        sessionService = new SessionService();
        sessionService.login("admin", "passwd");
        bookService = new BookService(sessionService.getUserSession());
    }

    @Before
    public void bookInsertTest() {
        Books books = new Books();
        books.setName("测试书籍");
        books.setIsbn("9787544280907");
        books.setAuthor("测试作者");
        books.setPress("测试出版社");
        books.setStatus(0);
        bookService.insertBook(books);
        bookService.refreshBookList();
    }

    @Test
    public void bookSelectAsListTest() {
        List<Books> booksList = bookService.getBooksList();
        for ( Books book : booksList ) {
            book.getId();
        }
    }

    @Test
    public void bookSelectTest() {
        List<Books> booksList = bookService.getBooksList();
        for ( Books book : booksList ) {
            bookService.getBook(book.getId()).getName();
        }
    }

    @Test
    public void bookSelectByNameTest() {
        Books books = bookService.getBookByName("测试书籍").get(0);
        if (!books.getIsbn().equals("9787544280907")) {
            throw new UnsupportedOperationException("May have insert error. @" + books.getIsbn());
        }
    }

    @Test
    public void bookUpdateTest() {
        Books books = bookService.getBookByName("测试书籍").get(0);
        books.setName("测试书籍状态");
        bookService.updateBook(books.getId(), books);
        if (!bookService.getBookByName("测试书籍状态").get(0).getIsbn().equals("9787544280907")) {
            throw new UnsupportedOperationException("May have update error. @" + books.getIsbn());
        }
        books.setName("测试书籍");
        bookService.updateBook(books.getId(), books);
    }

    @Test
    public void bookBorrowTest() {
        Books books = bookService.getBookByName("测试书籍").get(0);
        bookService.borrowBook(books.getId());
        if (bookService.getBookByName("测试书籍").get(0).getStatus() != 1) {
            throw new UnsupportedOperationException("May have update error. @" + books.getIsbn());
        }
        if (!bookService.isBookBorrowByCurUser(books.getId())) {
            throw new UnsupportedOperationException("May have borrow check error.");
        }
    }

    @Test
    public void bookReturnTest() {
        Books books = bookService.getBookByName("测试书籍").get(0);
        bookService.returnBook(books.getId());
        if (bookService.getBookByName("测试书籍").get(0).getStatus() != 0) {
            throw new UnsupportedOperationException("May have update error. @" + books.getIsbn());
        }
    }

    @After
    public void bookDeleteTest() {
        Books books = bookService.getBookByName("测试书籍").get(0);
        bookService.deleteBook(books.getId());
    }
}
