package org.kiteki.smartlibrary.domain.book;

public class BookViews {
    Integer id;
    String name;
    String isbn;
    String author;
    String press;
    String status;

    @Override
    public String toString() {
        return "BookViews{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookViews(Integer id, String name, String isbn, String author, String press, String status) {
        this.id = id;
        this.name = name;
        this.isbn = isbn;
        this.author = author;
        this.press = press;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
