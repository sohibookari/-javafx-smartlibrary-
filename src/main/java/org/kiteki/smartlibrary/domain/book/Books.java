package org.kiteki.smartlibrary.domain.book;

public class Books {
    Integer id;
    String isbn;
    String name;
    String author;
    String press;
    Integer status;

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", status=" + status +
                '}';
    }
}
