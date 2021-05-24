package library.books;

import library.Author;
import library.Section;

import java.util.Date;

public class Encyclopedia extends Book{
    private String subject;

    public Encyclopedia(String title, Integer nr_of_pages, Integer publication_year, String informations, Boolean explicit_content, String author, String section, String subject) {
        super(title, nr_of_pages, publication_year, informations, explicit_content, author, section);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toWrite() {

        String string = "encyclopedia," + getSection() + ',' + getTitle() + ',' + getNr_of_pages().toString() + ',' + getPublication_year().toString()
                + ',' + getInformations().toString() + ',' + getExplicit_content().toString() + ',' + getAuthor()
                + ',' + subject;

        return string;
    }
}
