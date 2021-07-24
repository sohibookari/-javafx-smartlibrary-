
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kiteki.smartlibrary.dao.BookDaoImpl;
import org.kiteki.smartlibrary.domain.book.Books;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class BookDaoTest {
    private BookDaoImpl bookDao;

    public BookDaoTest() {
        // init dao.
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        bookDao = context.getBean("bookDaoImpl", BookDaoImpl.class);
    }

    @Before
    public void insertBookTest() {
        Books books = new Books();
        books.setName("测试书籍");
        books.setIsbn("9787544280907");
        books.setAuthor("测试作者");
        books.setPress("测试出版社");
        books.setStatus(0);
        bookDao.insertBook(books);
    }

    @Test
    public void selectBookByNameTest() {
        bookDao.selectBookByName("测试书籍").get(0).getIsbn();
    }

    @Test
    public void selectBookTest() {
        bookDao.selectBook(
                bookDao.selectBookByName("测试书籍").get(0).getId()
        );
    }

    @Test
    public void selectBooksAsListTest() {
        List<Books> booksList = bookDao.selectBooksAsList();
        for (Books book : booksList) {
            book.getId();
        }
    }

    @Test
    public void updateBookTest() {
        Books book = bookDao.selectBookByName("测试书籍").get(0);
        book.setName("测试书籍状态");
        bookDao.updateBook(book);
        bookDao.selectBookByName("测试书籍状态").get(0).getId();
        book.setName("测试书籍");
        bookDao.updateBook(book);
    }

    @After
    public void deleteBookTest() {
        bookDao.deleteBook(bookDao.selectBookByName("测试书籍").get(0).getId());
    }
}
