package org.kiteki.smartlibrary.domain;

public class BookBookshelf {
    Integer id;
    String name;
    String location;

    @Override
    public String toString() {
        return "BookBookshelf{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
