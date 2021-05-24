package library;

import library.books.*;
import library.readers.AdultReader;
import library.readers.Reader;
import library.readers.YoungReader;
import library.repository.AuthorRepository;
import library.repository.BookRepository;
import library.repository.SectionRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;
import java.nio.file.Path;

public class UserOperations {

//    static Reader_Writer reader_writer = new Reader_Writer();
    static String path = Path.of("proiect").toAbsolutePath() + "/src/library/files/audit.csv";
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    LocalDateTime now;

    /// AUTHORS
    public void addAuthor(AuthorRepository authorRepository, Scanner scanner) {
        System.out.println("Cum se numeste autorul?");
        String s1 = scanner.next();
        System.out.println("Pe ce data s-a nascut autorul?");
        String s2 = scanner.next();
        System.out.println("Pe ce data a murit autorul? (daca acesta inca traieste, se va trece 0)");
        String s3 = scanner.next();

        if (s3.equals("0")) {
            Author a = new Author(s1, s2, null);
            authorRepository.addAuthor(a);
        } else {
            Author a = new Author(s1, s2, s3);
            authorRepository.addAuthor(a);
        }
 }

    public void deleteAuthor(AuthorRepository authorRepository, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Cum se numeste autorul?");
        String s1 = scanner.nextLine();
        Author a = authorRepository.getAuthorByName(s1);
        if (Objects.isNull(a)) {
            System.out.println("Nu am gasit acest autor");
        } else {
            authorRepository.deleteAuthor(a);
        }
 }

    public void showAuthors(AuthorRepository authorRepository) {
        authorRepository.displayAuthors();
   }


    ///SECTIONS
    public void addSection(SectionRepository sectionRepository, Scanner scanner) {
        System.out.println("Cum se va numi noua sectiune?");
        String s1 = scanner.next();
        Section s = new Section(s1);
        sectionRepository.addSection(s);
  }

    public void deleteSection(SectionRepository sectionRepository, Scanner scanner) {
        System.out.println("Cum se numeste sectiunea?");
        String s1 = scanner.next();
        Section s = sectionRepository.getSectionByName(s1);
        if (Objects.isNull(s)) {
            System.out.println("Nu am gasit aceasta sectiune");
        } else {
            sectionRepository.deleteSection(s);
        }
   }

    public void showSections(SectionRepository sectionRepository) {
        sectionRepository.displaySections();
    }

    public void showSectionBooks(SectionRepository sectionRepository, Scanner scanner) {
        System.out.println("Cum se numeste sectiunea?");
        String s1 = scanner.next();
        Section s = sectionRepository.getSectionByName(s1);
        if (Objects.isNull(s)) {
            System.out.println("Nu am gasit aceasta sectiune");
        } else {
            sectionRepository.displaySection(s);
        }
    }

    ///BOOKS
    public void addBook(BookRepository bookRepository, SectionRepository sectionRepository, AuthorRepository authorRepository, Scanner scanner) {

        System.out.println("Din ce sectiune va face parte cartea?");
        String s1 = scanner.next();
        scanner.nextLine();
        Section s = sectionRepository.getSectionByName(s1);
        if (Objects.isNull(s)) {
            System.out.println("Nu am gasit aceasta sectiune");
        } else {
            System.out.println("Cum se numeste cartea?");
            String e1 = scanner.nextLine();
            System.out.println("Cate pagini are?");
            Integer e2 = scanner.nextInt();
            System.out.println("In ce an a fost scrisa?");
            Integer e3 = scanner.nextInt();
            scanner.nextLine();
            System.out.println("O scurta descriere a continutului?");
            String e4 = scanner.nextLine();
            System.out.println("Are continut explicit? true/false");
            Boolean e5 = scanner.nextBoolean();
            scanner.nextLine();
            System.out.println("Cine a scris cartea?");
            String e6 = scanner.nextLine().trim();

            System.out.println("Ce tip de carte este? 1 - roman/nuvela, 2 - biografie, 3 - banda desenata, 4 - enciclopedie");
            Integer option = scanner.nextInt();

            if (option == 1) {
                scanner.nextLine();
                System.out.println("Ce tip de narator are?");
                String e7 = scanner.nextLine();
                System.out.println("Cine sunt personajele principale? (vor fi separate prin ele folosind -)");
                String e8 = scanner.nextLine();

                Book b = new Novel(e1, e2, e3, e4, e5, e6, s1, e7, e8);
                bookRepository.addBook(b);

            } else if (option == 2) {
                System.out.println("Despre cine este vorba in biografie?");
                String e7 = scanner.nextLine();

                Book b = new Biography(e1, e2, e3, e4, e5, e6, s1, e7);
                bookRepository.addBook(b);

            } else if (option == 3) {
                System.out.println("Este in alb-negru? true/false");
                Boolean e7 = scanner.nextBoolean();
                System.out.println("Se citeste invers? true/false");
                Boolean e8 = scanner.nextBoolean();

                Book b = new ComicBook(e1, e2, e3, e4, e5, e6, s1, e7, e8);
                bookRepository.addBook(b);

            } else if (option == 4) {
                System.out.println("Ce subiect aprofundeaza?");
                String e7 = scanner.next();

                Book b = new Encyclopedia(e1, e2, e3, e4, e5, e6, s1, e7);
                bookRepository.addBook(b);
            }
        }
    }

    public void deleteBook(BookRepository bookRepository, Scanner scanner) {
        System.out.println("Cum se numeste cartea pe care vreti sa o stergeti?");
        String s1 = scanner.next();
        scanner.nextLine();
        Book b = bookRepository.getBookByTitle(s1);
        bookRepository.deleteBook(b);
    }

    public void borrowBook(Library my_library, Scanner scanner, Reader reader) {
        System.out.println("Din ce sectiune doriti sa imprumutati?");
        String s1 = scanner.next();
        Section s = my_library.getSectionByName(s1);

        if (Objects.isNull(s)) {
            System.out.println("Nu am gasit aceasta sectiune");
        } else {
            scanner.nextLine();
            System.out.println("Cum se numeste cartea pe care doriti sa o imprumutati?");
            String aux = scanner.nextLine();
            Book b = s.getBookByTitle(aux);
            if (Objects.isNull(b)) {
                System.out.println("Nu am gasit aceasta carte");
            }
            else {
                if (reader.isUnderAged() && b.getExplicit_content()) {
                    System.out.println("Un cititor tanar nu poate imprumuta aceasta carte!");
                }
                else {
                    reader.borrowBook(b);
                }
            }
        }
   }

    public void returnBook(Library my_library, Scanner scanner, Reader reader) {
        System.out.println("Din ce sectiune este cartea pe care doriti sa o returnati?");
        String s1 = scanner.next();
        Section s = my_library.getSectionByName(s1);
        if (Objects.isNull(s)) {
            System.out.println("Nu am gasit aceasta sectiune");
        } else {
            scanner.nextLine();
            System.out.println("Cum se numeste cartea pe care doriti sa o returnati?");
            String aux = scanner.nextLine();
            Book b = s.getBookByTitle(aux);
            if (b == null) {
                System.out.println("Nu am gasit aceasta carte");
            } else {
                reader.returnBook(b);
            }
        }
    }

    public void showBorrowedBooks(Reader reader) {
        for(String s : reader.getBorrowed_books()) {
            System.out.println(s);
        }
   }

    public Reader login(Library my_library, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Introduceti numele dumneavoastra:");

        String user_name = scanner.nextLine();
        Reader reader = my_library.getReaderByName(user_name);

        if (Objects.isNull(reader)) {
            System.out.println("Nu sunteti inregistrat inca!");

            System.out.println("Introduceti varsta:");
            Integer s2 = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Introduceti numarul de telefon:");
            String s3 = scanner.nextLine();

            System.out.println("Introduceti adresa de mail:");
            String s4 = scanner.nextLine();

            if (s2 >= 16) {
                System.out.println("Introduceti ocupatia curenta:");
                String s5 = scanner.nextLine();

                AdultReader r = new AdultReader(user_name, s2, s3, s4, s5);
                my_library.addReader(r);
            } else {
                System.out.println("Introduceti telefonul unui parinte:");
                String s5 = scanner.nextLine();
                System.out.println("Introduceti mailul unui parinte:");
                String s6 = scanner.nextLine();

                YoungReader r = new YoungReader(user_name, s2, s3, s4, s5, s6);
                my_library.addReader(r);
                reader = r;
            }
        }
        return reader;
    }
}
