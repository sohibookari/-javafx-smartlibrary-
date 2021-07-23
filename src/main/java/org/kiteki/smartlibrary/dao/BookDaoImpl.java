package org.kiteki.smartlibrary.dao;

import org.apache.ibatis.session.SqlSession;
import org.kiteki.smartlibrary.domain.book.Books;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookDaoImpl implements BookDao {

    private SqlSession sqlSession;

    @Autowired
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Books selectBook(int id) {
        return sqlSession.selectOne("org.kiteki.smartlibrary.mappers.bookMapper.selectBook", id);
    }

    @Override
    public List<Books> selectBooksAsList() {
        return sqlSession.selectList("org.kiteki.smartlibrary.mappers.bookMapper.selectBooksAsList");
    }

    @Override
    public void insertBook(Books books) {
        sqlSession.insert("org.kiteki.smartlibrary.mappers.bookMapper.insertBook", books);
    }

    @Override
    public void updateBook(Books books) {
        sqlSession.update("org.kiteki.smartlibrary.mappers.bookMapper.updateBook", books);
    }

    @Override
    public void deleteBook(int id) {
        sqlSession.delete("org.kiteki.smartlibrary.mappers.bookMapper.deleteBook", id);
    }
}
