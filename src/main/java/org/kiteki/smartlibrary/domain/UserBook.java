package org.kiteki.smartlibrary.domain;

public class UserBook {
    Integer id;
    String user_id;
    Integer book_id;

    @Override
    public String toString() {
        return "UserBook{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", book_id=" + book_id +
                '}';
    }
}
