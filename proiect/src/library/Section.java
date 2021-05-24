package library;

import library.books.Book;

import java.util.ArrayList;
import java.util.Arrays;

public class Section {
    private String name;

    public Section(String name, ArrayList<Book> books) {
        this.name = name;
    }

    public Section(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String s;
        s = name + ":\n";

        return s;
    }

    public String toWrite() {
        return name;
    }

}
