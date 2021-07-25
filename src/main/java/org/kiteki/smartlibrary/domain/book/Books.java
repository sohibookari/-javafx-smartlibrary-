package org.kiteki.smartlibrary.domain.book;

import java.util.Objects;

public class Books {
    Integer id;
    String isbn;
    String name;
    String author;
    String press;
    Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getStatus() {
        return status;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return getId().equals(books.getId()) && getIsbn().equals(books.getIsbn()) && getName().equals(books.getName()) && getAuthor().equals(books.getAuthor()) && getPress().equals(books.getPress()) && getStatus().equals(books.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIsbn(), getName(), getAuthor(), getPress(), getStatus());
    }
}
