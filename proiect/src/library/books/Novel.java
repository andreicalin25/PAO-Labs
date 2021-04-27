package library.books;

import library.Author;

import java.util.Arrays;
import java.util.Date;

public class Novel extends Book{
    private String narrator;
    private String []main_characters;

    public Novel(String title, Integer nr_of_pages, Integer publication_year, String informations, Boolean explicit_content, Author author, String narrator, String[] main_characters) {
        super(title, nr_of_pages, publication_year, informations, explicit_content, author);
        this.narrator = narrator;
        this.main_characters = main_characters;
    }

    public String getNarrator() {
        return narrator;
    }

    public void setNarrator(String narrator) {
        this.narrator = narrator;
    }

    public String[] getMain_characters() {
        return main_characters;
    }

    public void setMain_characters(String[] main_characters) {
        this.main_characters = main_characters;
    }

    @Override
    public String toWrite() {

        String characters = "";
        for (String ch : main_characters) {
            characters += ch + '-';
        }

        String string = "novel," + getTitle() + ',' + getNr_of_pages().toString() + ',' + getPublication_year().toString()
                + ',' + getInformations() + ',' + getExplicit_content().toString() + ',' + getAuthor().getName()
                + ',' + narrator + ',' + characters;

        return string;
    }
}
