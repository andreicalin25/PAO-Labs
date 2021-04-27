package library.books;

import library.Author;
import library.Section;

import java.util.Date;

public class ComicBook extends Book{
    private Boolean black_and_white;
    private Boolean backwards_reading;

    public ComicBook(String title, Integer nr_of_pages, Integer publication_year, String informations, Boolean explicit_content, Author author, Section section, Boolean black_and_white, Boolean backwards_reading) {
        super(title, nr_of_pages, publication_year, informations, explicit_content, author, section);
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

    @Override
    public String toWrite() {

        String string = "comicbook," + getSection().getName() + ',' +getTitle() + ',' + getNr_of_pages().toString() + ',' + getPublication_year().toString()
                + ',' + getInformations() + ',' + getExplicit_content().toString() + ',' + getAuthor().getName()
                + ',' + black_and_white.toString() + ',' + backwards_reading.toString();

        return string;
    }
}
