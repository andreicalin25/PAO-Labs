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
    private Author author;
    private Section section;

    public Book(String title, Integer nr_of_pages, Integer publication_year, String informations, Boolean explicit_content, Author author, Section section) {
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getNr_of_pages(), book.getNr_of_pages()) && Objects.equals(getPublication_year(), book.getPublication_year()) && Objects.equals(getInformations(), book.getInformations()) && Objects.equals(getExplicit_content(), book.getExplicit_content()) && Objects.equals(getAuthor(), book.getAuthor()) && Objects.equals(getSection(), book.getSection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getNr_of_pages(), getPublication_year(), getInformations(), getExplicit_content(), getAuthor(), getSection());
    }

    @Override
    public int compareTo(Book b) {
        return this.title.compareTo(b.title);
    }

    @Override
    public String toString() {
        return  '\n' + title + '\n' +
                '-' + author.getName() +
                '\n' + nr_of_pages +
                ", " + publication_year +
                ", " + informations;
    }

    public String toWrite() {
        String string = section.getName() + ',' + title + ',' + nr_of_pages.toString() + ',' + publication_year.toString() + ','
                + informations + ',' + explicit_content.toString() + ',' + author.getName();

        return string;
    }
}
