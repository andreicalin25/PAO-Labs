package library;

import library.books.Book;

import java.util.ArrayList;
import java.util.Arrays;

public class Section {
    private String name;
    private ArrayList<Book> books;

    public Section(String name, ArrayList<Book> books) {
        this.name = name;
        this.books = books;
    }

    public Section(String name) {
        this.name = name;
        this.books = new ArrayList<Book>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        String s;
        s = name + ":\n";
        for (Book b : books) {
            s += b.getTitle();
            s += '\n';
        }

        return s;
    }

    public void addBook(Book new_b) {
        this.books.add(new_b);
    }

    public void deleteBook(Book b) {
        this.books.remove(b);
    }

    public void deleteBookByTitle(String b_title) {
        this.books.remove(getBookByTitle(b_title));
    }

    public void showBooks() {

        System.out.println('\n' + this.name + ':');

        for(Book b : books) {
            System.out.println(b);
        }
    }

    public Book getBookByTitle (String title) {
        for (Book b : books){
            if (b.getTitle().equals(title)) {
                return b;
            }
        }
        return null;
    }

    public void deleteBooksWrittenBy (Author a) {
        books.removeIf(b -> b.getAuthor().equals(a));
    }

    public String toWrite() {
        return name;
    }

}
