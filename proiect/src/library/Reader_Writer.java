package library;

import library.books.*;
import library.readers.AdultReader;
import library.readers.Reader;
import library.readers.YoungReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Reader_Writer {

    public ArrayList<ArrayList<String>> fileReader(String path) {

        ArrayList<ArrayList<String>> objects = new ArrayList<>();

        try {
            File file = new File(path);
            Scanner init = new Scanner(file);

            String k;
            k = init.nextLine();

            while(k != null) {
                ArrayList<String> current_obj = new ArrayList<>();
                current_obj.addAll(Arrays.asList(k.split(",")));
                objects.add(current_obj);

                if(init.hasNextLine()){
                    k = init.nextLine();
                } else {
                    k = null;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return objects;
    }

    public void readAuthors(Library my_library) {
        String path = "/Users/andreicalin/Desktop/PAO/PAO-Labs/proiect/src/library/input_files/authors.csv";

        for(ArrayList<String> author_string : fileReader(path)) {
            String name = author_string.get(0);
            String birth = author_string.get(1);
            String death = author_string.get(2);

            if(death.equals("0")) {
                Author a = new Author(name, birth);
                my_library.addAuthor(a);
            } else {
                Author a = new Author(name, birth, death);
                my_library.addAuthor(a);
            }
        }
    }

    public void readSections(Library my_library) {
        String path = "/Users/andreicalin/Desktop/PAO/PAO-Labs/proiect/src/library/input_files/sections.csv";

        for(ArrayList<String> section_string : fileReader(path)) {
            Section sec = new Section(section_string.get(0));
            my_library.addSection(sec);
        }
    }

    public void readBooks(Library my_library) {
        String path = "/Users/andreicalin/Desktop/PAO/PAO-Labs/proiect/src/library/input_files/books.csv";

        for(ArrayList<String> book_string : fileReader(path)) {
            if (book_string.get(0).equals("novel")) {
                String section = book_string.get(1);
                String title = book_string.get(2);
                Integer pages = Integer.valueOf(book_string.get(3));
                Integer year = Integer.valueOf(book_string.get(4));
                String info = book_string.get(5);
                Boolean explicit = Boolean.valueOf(book_string.get(6));
                String author = book_string.get(7);
                String narrator = book_string.get(8);
                String[] characters = book_string.get(9).split("-");

                Section sec = my_library.getSectionByName(section);
                Author aut = my_library.getAuthorByName(author);
                Book b = new Novel(title, pages, year, info, explicit, aut, narrator, characters);

                sec.addBook(b);
            }
            else if (book_string.get(0).equals("biography")) {
                String section = book_string.get(1);
                String title = book_string.get(2);
                Integer pages = Integer.valueOf(book_string.get(3));
                Integer year = Integer.valueOf(book_string.get(4));
                String info = book_string.get(5);
                Boolean explicit = Boolean.valueOf(book_string.get(6));
                String author = book_string.get(7);
                String character = book_string.get(8);

                Section sec = my_library.getSectionByName(section);
                Author aut = my_library.getAuthorByName(author);
                Book b = new Biography(title, pages, year, info, explicit, aut, character);

                sec.addBook(b);
            }
            else if (book_string.get(0).equals("comicbook")) {
                String section = book_string.get(1);
                String title = book_string.get(2);
                Integer pages = Integer.valueOf(book_string.get(3));
                Integer year = Integer.valueOf(book_string.get(4));
                String info = book_string.get(5);
                Boolean explicit = Boolean.valueOf(book_string.get(6));
                String author = book_string.get(7);
                Boolean b_w = Boolean.valueOf(book_string.get(6));
                Boolean back = Boolean.valueOf(book_string.get(6));

                Section sec = my_library.getSectionByName(section);
                Author aut = my_library.getAuthorByName(author);
                Book b = new ComicBook(title, pages, year, info, explicit, aut, b_w, back);

                sec.addBook(b);
            }
            else if (book_string.get(0).equals("encyclopedia")) {
                String section = book_string.get(1);
                String title = book_string.get(2);
                Integer pages = Integer.valueOf(book_string.get(3));
                Integer year = Integer.valueOf(book_string.get(4));
                String info = book_string.get(5);
                Boolean explicit = Boolean.valueOf(book_string.get(6));
                String author = book_string.get(7);
                String subject = book_string.get(8);

                Section sec = my_library.getSectionByName(section);
                Author aut = my_library.getAuthorByName(author);
                Book b = new Encyclopedia(title, pages, year, info, explicit, aut, subject);

                sec.addBook(b);
            }
        }
    }

    public void readReaders(Library my_library) {
        String path = "/Users/andreicalin/Desktop/PAO/PAO-Labs/proiect/src/library/input_files/readers.csv";

        for(ArrayList<String> reader_string : fileReader(path)) {
            String name = reader_string.get(0);
            Integer age = Integer.valueOf(reader_string.get(1));
            String phone = reader_string.get(2);
            String email = reader_string.get(3);

            if(age >= 16) {
                String work = reader_string.get(4);

                if(reader_string.size() > 5) {

                    ArrayList<Book> books = new ArrayList<Book>();
                    for(String book_title : reader_string.get(5).split("-")) {
                        Book b = my_library.getBookByName(book_title);
                        books.add(b);
                    }

                    Reader r = new AdultReader(name, age, phone, email, books, work);
                    my_library.addReader(r);

                } else {
                    Reader r = new AdultReader(name, age, phone, email, work);
                    my_library.addReader(r);
                }
            }
            else {
                String parent_phone = reader_string.get(4);
                String parent_email = reader_string.get(5);


                if(reader_string.size() > 6) {

                    ArrayList<Book> books = new ArrayList<Book>();
                    for(String book_title : reader_string.get(6).split("-")) {
                        Book b = my_library.getBookByName(book_title);
                        books.add(b);
                    }

                    Reader r = new YoungReader(name, age, phone, email, books, parent_phone, parent_email);
                    my_library.addReader(r);

                } else {
                    Reader r = new YoungReader(name, age, phone, email, parent_phone, parent_email);
                    my_library.addReader(r);
                }
            }

        }
    }

}
