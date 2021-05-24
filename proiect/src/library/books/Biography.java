package library.books;

import library.Author;
import library.Section;

import java.util.Date;

public class Biography extends Book{

    private String person;

    public Biography(String title, Integer nr_of_pages, Integer publication_year, String informations, Boolean explicit_content, String author, String section, String person) {
        super(title, nr_of_pages, publication_year, informations, explicit_content, author, section);
        this.person = person;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    @Override
    public String toWrite() {

        String string = "biography," + getSection() + ',' + getTitle() + ',' + getNr_of_pages().toString() + ',' + getPublication_year().toString()
                + ',' + getInformations().toString() + ',' + getExplicit_content().toString() + ',' + getAuthor()
                + ',' + person;

        return string;
    }
}
