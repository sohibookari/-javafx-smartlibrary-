package org.kiteki.smartlibrary.domain;

public class BookBooks {
    Integer id;
    String isbn;
    String name;
    String varname;
    String author;
    String translator;
    String press;
    String producer;
    String imprint_date;
    String pagination;
    String pricing;
    String bookshelf_id;

    @Override
    public String toString() {
        return "BookBooks{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", name='" + name + '\'' +
                ", varname='" + varname + '\'' +
                ", author='" + author + '\'' +
                ", translator='" + translator + '\'' +
                ", press='" + press + '\'' +
                ", producer='" + producer + '\'' +
                ", imprint_date='" + imprint_date + '\'' +
                ", pagination='" + pagination + '\'' +
                ", pricing='" + pricing + '\'' +
                ", bookshelf_id='" + bookshelf_id + '\'' +
                '}';
    }
}
