package org.kiteki.smartlibrary.domain.session;

public class BorrowInfo {
    Integer id;
    String user_id;
    Integer book_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Integer getId() {
        return id;
    }

    public BorrowInfo() {

    }

    public BorrowInfo(String user_id, Integer book_id) {
        this.user_id = user_id;
        this.book_id = book_id;
    }
}
