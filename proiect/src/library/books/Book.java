package library.books;

import library.Author;
import library.Section;

import java.util.Objects;

public class Book implements Comparable<Book>{
    private String title;
    private Integer nr_of_pages;
    private Integer publication_year;
    private String informations;
    private Boolean explicit_content;
    private String author;
    private String section;

    public Book(String title, Integer nr_of_pages, Integer publication_year, String informations, Boolean explicit_content, String author, String section) {
        this.title = title;
        this.nr_of_pages = nr_of_pages;
        this.publication_year = publication_year;
        this.informations = informations;
        this.explicit_content = explicit_content;
        this.author = author;
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNr_of_pages() {
        return nr_of_pages;
    }

    public void setNr_of_pages(Integer nr_of_pages) {
        this.nr_of_pages = nr_of_pages;
    }

    public Integer getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(Integer publication_year) {
        this.publication_year = publication_year;
    }

    public String getInformations() {
        return informations;
    }

    public void setInformations(String informations) {
        this.informations = informations;
    }

    public Boolean getExplicit_content() {
        return explicit_content;
    }

    public void setExplicit_content(Boolean explicit_content) {
        this.explicit_content = explicit_content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", nr_of_pages=" + nr_of_pages +
                ", publication_year=" + publication_year +
                ", informations='" + informations + '\'' +
                ", explicit_content=" + explicit_content +
                ", author='" + author + '\'' +
                ", section='" + section + '\'' +
                '}';
    }

    @Override
    public int compareTo(Book b) {
        return this.title.compareTo(b.title);
    }

    public String toWrite() {
        String string = section + ',' + title + ',' + nr_of_pages.toString() + ',' + publication_year.toString() + ','
                + informations + ',' + explicit_content.toString() + ',' + author;

        return string;
    }
}
