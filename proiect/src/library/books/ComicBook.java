package library.books;

import library.Author;

import java.util.Date;

public class ComicBook extends Book{
    private Boolean black_and_white;
    private Boolean backwards_reading;

    public ComicBook(String title, Integer nr_of_pages, Integer publication_year, String informations, Boolean explicit_content, Author author, Boolean black_and_white, Boolean backwards_reading) {
        super(title, nr_of_pages, publication_year, informations, explicit_content, author);
        this.black_and_white = black_and_white;
        this.backwards_reading = backwards_reading;
    }

    public Boolean getBlack_and_white() {
        return black_and_white;
    }

    public void setBlack_and_white(Boolean black_and_white) {
        this.black_and_white = black_and_white;
    }

    public Boolean getBackwards_reading() {
        return backwards_reading;
    }

    public void setBackwards_reading(Boolean backwards_reading) {
        this.backwards_reading = backwards_reading;
    }
}