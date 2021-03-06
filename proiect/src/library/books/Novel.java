package library.books;

import library.Author;
import library.Section;

import java.util.Arrays;
import java.util.Date;

public class Novel extends Book{
    private String narrator;
    private String main_characters;

    public Novel(String title, Integer nr_of_pages, Integer publication_year, String informations, Boolean explicit_content, String author, String section, String narrator) {
        super(title, nr_of_pages, publication_year, informations, explicit_content, author, section);
        this.narrator = narrator;
    }

    public Novel(String title, Integer nr_of_pages, Integer publication_year, String informations, Boolean explicit_content, String author, String section, String narrator, String main_characters) {
        super(title, nr_of_pages, publication_year, informations, explicit_content, author, section);
        this.narrator = narrator;
        this.main_characters = main_characters;
    }

    public String getNarrator() {
        return narrator;
    }

    public void setNarrator(String narrator) {
        this.narrator = narrator;
    }

    public String getMain_characters() {
        return main_characters;
    }

    public void setMain_characters(String main_characters) {
        this.main_characters = main_characters;
    }

    @Override
    public String toWrite() {



        String string = "novel," + getSection() + ',' + getTitle() + ',' + getNr_of_pages().toString() + ',' + getPublication_year().toString()
                + ',' + getInformations() + ',' + getExplicit_content().toString() + ',' + getAuthor()
                + ',' + narrator + ',' + main_characters;

        return string;
    }
}
