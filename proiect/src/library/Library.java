package library;

import library.books.Book;
import library.readers.Reader;

import java.util.ArrayList;

public class Library {
    private ArrayList<Section> sections;
    private ArrayList<Author> authors;
    private ArrayList<Reader> readers;

    //CONSTRUCTORS
    public Library(ArrayList<Section> sections, ArrayList<Author> authors, ArrayList<Reader> readers) {
        this.sections = sections;
        this.authors = authors;
        this.readers = readers;
    }

    public Library() {
        this.sections = new ArrayList<Section>();
        this.authors = new ArrayList<Author>();
        this.readers = new ArrayList<Reader>();
    }

    //GET AND SET
    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public ArrayList<Reader> getReaders() {
        return readers;
    }

    public void setReaders(ArrayList<Reader> readers) {
        this.readers = readers;
    }

    //SECTIONS ADD - SHOW - DELETE
    public void addSection(Section new_s) {
        this.sections.add(new_s);
    }

    public void deleteSection(Section deleted_s) {
        deleted_s.setName(null);
        deleted_s.setBooks(null);
    }

    public void showSection(Section s){
        s.showBooks();
    }

    public void showSections() {

        for (Section s : sections) {
            System.out.println(s.getName() + ':');

            for (Book b : s.getBooks()){
                System.out.println(b.getTitle());
            }

            System.out.println('\n');
        }
    }

    public Section getSectionByName(String name) {
        for (Section s : sections) {
            if (s.getName().equals(name))
                return s;
        }
        return null;
    }

    //AUTHORS ADD - SHOW - DELETE
    public void addAuthor(Author new_a) {
        this.authors.add(new_a);
    }

    public void deleteAuthor(Author deleted_a) {

        for(Section s : sections) {
            s.deleteBooksWrittenBy(deleted_a);
        }

        this.authors.remove(deleted_a);
    }

    public void showAuthors() {

        for (Author a : authors) {
            System.out.println(a);

            String titles = "";
            for (Section s : sections){
                for (Book b : s.getBooks()){
                    if(b.getAuthor().equals(a)) {
                        titles += b.getTitle() + "\n";
                    }
                }
            }

            System.out.println(titles);
        }
    }

    public Author getAuthorByName(String name) {
        for (Author a : authors) {
            if (a.getName().equals(name))
                return a;
        }
        return null;
    }

    //READERS ADD - SHOW
    public void addReader(Reader new_r) {
        this.readers.add(new_r);
    }

    public void showReaders() {
        for (Reader r : readers) {
            System.out.println(r.getName() + '\n');
        }
    }

    public Reader getReaderByName(String name) {
        for(Reader r : readers){
            if (r.getName().equals(name)) {
                return r;
            }
        }
        return null;
    }
}
