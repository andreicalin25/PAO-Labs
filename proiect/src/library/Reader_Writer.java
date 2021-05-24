package library;

import library.books.*;
import library.readers.AdultReader;
import library.readers.Reader;
import library.readers.YoungReader;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;


public class Reader_Writer {

    public ArrayList<ArrayList<String>> fileReader(String path) {

        ArrayList<ArrayList<String>> objects = new ArrayList<>();

        try {
            File file = new File(path);
            Scanner init = new Scanner(file);

            String k;
            k = init.nextLine();

            while (k != null) {
                ArrayList<String> current_obj = new ArrayList<>();
                current_obj.addAll(Arrays.asList(k.split(",")));
                objects.add(current_obj);

                if (init.hasNextLine()) {
                    k = init.nextLine();
                } else {
                    k = null;
                }

            }
        }catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
        }

        return objects;
    }

    public void fileWriter(String path, String string) {

        try {
            FileWriter file = new FileWriter(path);
            file.write(string);
            file.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

//    public void readAuthors(Library my_library) {
//        String path = String.valueOf(Path.of("proiect").toAbsolutePath());
//        path += "/src/library/files/authors.csv";
//
//        for(ArrayList<String> author_string : fileReader(path)) {
//            String name = author_string.get(0);
//            String birth = author_string.get(1);
//            String death = author_string.get(2);
//
//            if(death.equals("0")) {
//                Author a = new Author(name, birth);
//                my_library.addAuthor(a);
//            } else {
//                Author a = new Author(name, birth, death);
//                my_library.addAuthor(a);
//            }
//        }
//    }

    public void readSections(Library my_library) {
        String path = String.valueOf(Path.of("proiect").toAbsolutePath());
        path += "/src/library/files/sections.csv";

        for(ArrayList<String> section_string : fileReader(path)) {
            Section sec = new Section(section_string.get(0));
            my_library.addSection(sec);
        }
    }

    public void readBooks(Library my_library) {
        String path = String.valueOf(Path.of("proiect").toAbsolutePath());
        path += "/src/library/files/books.csv";

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
                Book b = new Novel(title, pages, year, info, explicit, aut, sec, narrator, characters);

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
                Book b = new Biography(title, pages, year, info, explicit, aut, sec, character);

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
                Book b = new ComicBook(title, pages, year, info, explicit, aut, sec, b_w, back);

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
                Book b = new Encyclopedia(title, pages, year, info, explicit, aut, sec, subject);

                sec.addBook(b);
            }
        }
    }

    public void readReaders(Library my_library) {
        String path = String.valueOf(Path.of("proiect").toAbsolutePath());
        path += "/src/library/files/readers.csv";

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

    public void writeAuthors(Library my_library) {
        String path = String.valueOf(Path.of("proiect").toAbsolutePath());
        path += "/src/library/files/authors.csv";

        String string = "";
        for (Author a : my_library.getAuthors()) {
            string += a.toWrite() + '\n';
        }

        //fac acest strip pentru a scoate \n care se afla in coada si care imi genereaza un rand in plus in fisier
        string = string.substring(0, string.length() - 1);

        fileWriter(path, string);
    }

    public void writeBooks(Library my_library) {
        String path = String.valueOf(Path.of("proiect").toAbsolutePath());
        path += "/src/library/files/books.csv";

        String string = "";
        for (Section s : my_library.getSections()) {
            for(Book b : s.getBooks()) {
                string += b.toWrite() + '\n';
            }
        }

        //fac acest strip pentru a scoate \n care se afla in coada si care imi genereaza un rand in plus in fisier
        string = string.substring(0, string.length() - 1);

        fileWriter(path, string);
    }

    public void writeSections(Library my_library) {
        String path = String.valueOf(Path.of("proiect").toAbsolutePath());
        path += "/src/library/files/sections.csv";

        String string = "";
        for (Section s : my_library.getSections()) {
            string += s.toWrite() + '\n';
        }

        //fac acest strip pentru a scoate \n care se afla in coada si care imi genereaza un rand in plus in fisier
        string = string.substring(0, string.length() - 1);

        fileWriter(path, string);
    }

    public void writeReaders(Library my_library) {
        String path = String.valueOf(Path.of("proiect").toAbsolutePath());
        path += "/src/library/files/readers.csv";

        String string = "";
        for (Reader r : my_library.getReaders()) {
            string += r.toWrite() + '\n';
        }

        //fac acest strip pentru a scoate ultimul \n care se afla in coada si care imi genereaza un rand in plus in fisier
        string = string.substring(0, string.length() - 1);

        fileWriter(path, string);
    }
}
