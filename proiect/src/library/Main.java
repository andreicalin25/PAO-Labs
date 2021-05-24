package library;

import library.books.*;
import library.readers.AdultReader;
import library.readers.Reader;
import library.readers.YoungReader;
import library.repository.AuthorRepository;
import library.repository.BookRepository;
import library.repository.ReaderRepository;
import library.repository.SectionRepository;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        Reader_Writer reader_writer = new Reader_Writer();
        UserOperations user_operations = new UserOperations();
        Library my_library = new Library();

        BookRepository bookRepository = new BookRepository();

//        reader_writer.readAuthors(my_library);
//        reader_writer.readSections(my_library);
//        reader_writer.readBooks(my_library);
        reader_writer.readReaders(my_library, bookRepository);

//        System.out.println("##### Autori #####");
//        my_library.showAuthors();
//        System.out.println("\n#### Sectiuni ####");
//        my_library.showSections();
//        System.out.println("\n#### Cititori ####");
//        my_library.showReaders();




        //TEST

        AuthorRepository authorRepository = new AuthorRepository();
        SectionRepository sectionRepository = new SectionRepository();
        ReaderRepository readerRepository = new ReaderRepository();

        authorRepository.createTable();
        sectionRepository.createTable();
        bookRepository.createTable();
        readerRepository.createTable();

        Reader r = readerRepository.getReaderByName("Calin Andrei");
        Book b = bookRepository.getBookByTitle("Avengers #1");
        bookRepository.getBorrowed(b, r.getName());

//        for (Section s : my_library.getSections()) {
//            for(Book b : s.getBooks()) {
//                bookRepository.addBook(b);
//            }
//        }
//        for(Section s: my_library.getSections()) {
//            sectionRepository.addSection(s);
//        }
//        for(Author a : my_library.getAuthors()) {
//            authorRepository.insertAuthor(a.getName(), a.getDate_of_birth(), a.getDate_of_death());
//        }

//        authorRepository.displayAuthors();
//        sectionRepository.displaySections();
        bookRepository.displayBooks();
        System.out.println("");
        System.out.println("");
        readerRepository.displayReaders();

        Scanner scanner = new Scanner(System.in);
        int o1 = 10;

        while (o1 != 0) {
            System.out.println("Bine ai venit in biblioteca noastra!\nVa rugam sa selectati una din cele trei optiuni:\n1 - admin\n2 - user\n0 - iesi");

            o1 = scanner.nextInt();

            if (o1 == 1) {
                int o2 = 10;
                System.out.println("Buna ziua domnul/doamna administrator!");

                while (o2 != 0) {
                    System.out.println("Ce operatie doriti sa efectuati?" +
                            "\n11 - adauga autor" +
                            "\n12 - sterge autor" +
                            "\n13 - afiseaza toti autorii" +
                            "\n21 - adauga sectiune" +
                            "\n22 - sterge sectiune" +
                            "\n23 - afiseaza toate sectiunile in mare" +
                            "\n24 - afiseaza toate cartile unei sectiuni in detaliu" +
                            "\n31 - adauga carte" +
                            "\n32 - sterge carte" +
                            "\n0 - log out");

                    o2 = scanner.nextInt();

                    switch (o2) {
                        case 11: {
                            user_operations.addAuthor(authorRepository,scanner);
                            break;
                        }
                        case 12: {
                            user_operations.deleteAuthor(authorRepository, scanner);
                            break;
                        }
                        case 13: {
                            user_operations.showAuthors(authorRepository);
                            break;
                        }
                        case 21: {
                            user_operations.addSection(sectionRepository, scanner);
                            break;
                        }
                        case 22: {
                            user_operations.deleteSection(sectionRepository, scanner);
                            break;
                        }
                        case 23: {
                            user_operations.showSections(sectionRepository);
                            break;
                        }
                        case 24: {
                            user_operations.showSectionBooks(sectionRepository, scanner);
                            break;
                        }
                        case 31: {
                            user_operations.addBook(bookRepository, sectionRepository, authorRepository, scanner);
                            break;
                        }
                        case 32: {
                            user_operations.deleteBook(bookRepository, scanner);
                            break;
                        }
                        default:
                            System.out.println("");
                    }
                }
            } else if (o1 == 2) {
                int o2 = 10;

                Reader reader = user_operations.login(my_library, scanner);

                System.out.println("Buna ziua " + reader.getName() + "!");

                while (o2 != 0) {
                    System.out.println("Ce operatie doriti sa efectuati?" +
                            "\n11 - afiseaza toate sectiunile in mare" +
                            "\n12 - afiseaza toate cartile unei sectiuni in detaliu" +
                            "\n13 - afiseaza toti autorii" +
                            "\n21 - imprumuta carte" +
                            "\n22 - returneaza carte" +
                            "\n23 - afiseaza cartile imprumutate" +
                            "\n0 - log out");

                    o2 = scanner.nextInt();

                    switch (o2) {
                        case 11: {
                            user_operations.showSections(sectionRepository);
                            break;
                        }
                        case 12: {
                            user_operations.showSectionBooks(sectionRepository, scanner);
                            break;
                        }
                        case 13: {
                            user_operations.showAuthors(authorRepository);
                            break;
                        }
                        case 21: {
                            user_operations.borrowBook(bookRepository, scanner, reader);
                            break;
                        }
                        case 22: {
                            user_operations.returnBook(bookRepository, scanner, reader);
                            break;
                        }
                        case 23: {
                            user_operations.showBorrowedBooks(readerRepository, reader);
                            break;
                        }
                        default:
                            System.out.println("");
                    }
                }
            }
//            else {
//                reader_writer.writeAuthors(my_library);
//                reader_writer.writeBooks(my_library);
//                reader_writer.writeSections(my_library);
//                reader_writer.writeReaders(my_library);
//            }

        }

    }
}
