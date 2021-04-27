package library;

import library.books.*;
import library.readers.AdultReader;
import library.readers.Reader;
import library.readers.YoungReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;
import java.nio.file.Path;

public class UserOperations {

    static Reader_Writer reader_writer = new Reader_Writer();
    static String path = Path.of("proiect").toAbsolutePath() + "/src/library/files/audit.csv";
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    LocalDateTime now;

    public void addAuthor(Library my_library, Scanner scanner) {
        System.out.println("Cum se numeste autorul?");
        String s1 = scanner.next();
        System.out.println("Pe ce data s-a nascut autorul?");
        String s2 = scanner.next();
        System.out.println("Pe ce data a murit autorul? (daca acesta inca traieste, se va trece 0)");
        String s3 = scanner.next();

        if (s3.equals("0")) {
            Author a = new Author(s1, s2);
            my_library.addAuthor(a);
        } else {
            Author a = new Author(s1, s2, s3);
            my_library.addAuthor(a);
        }

        now = LocalDateTime.now();
        reader_writer.fileWriter(path, "author added," + dtf.format(now) + '\n');
    }

    public void deleteAuthor(Library my_library, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Cum se numeste autorul?");
        String s1 = scanner.nextLine();
        Author a = my_library.getAuthorByName(s1);
        if (Objects.isNull(a)) {
            System.out.println("Nu am gasit acest autor");
        } else {
            my_library.deleteAuthor(a);
        }

        now = LocalDateTime.now();
        reader_writer.fileWriter(path, "author deleted," + dtf.format(now) + '\n');
    }

    public void showAuthors(Library my_library) {
        my_library.showAuthors();

        now = LocalDateTime.now();
        reader_writer.fileWriter(path, "authors shown," + dtf.format(now) + '\n');
    }

    public void addSection(Library my_library, Scanner scanner) {
        System.out.println("Cum se va numi noua sectiune?");
        String s1 = scanner.next();
        Section s = new Section(s1);
        my_library.addSection(s);

        now = LocalDateTime.now();
        reader_writer.fileWriter(path, "section added," + dtf.format(now) + '\n');
    }

    public void deleteSection(Library my_library, Scanner scanner) {
        System.out.println("Cum se numeste sectiunea?");
        String s1 = scanner.next();
        Section s = my_library.getSectionByName(s1);
        if (Objects.isNull(s)) {
            System.out.println("Nu am gasit aceasta sectiune");
        } else {
            my_library.deleteSection(s);
        }

        now = LocalDateTime.now();
        reader_writer.fileWriter(path, "section deleted," + dtf.format(now) + '\n');
    }

    public void showSections(Library my_library) {
        my_library.showSections();

        now = LocalDateTime.now();
        reader_writer.fileWriter(path, "all sections shown," + dtf.format(now) + '\n');
    }

    public void showSectionBooks(Library my_library, Scanner scanner) {
        System.out.println("Cum se numeste sectiunea?");
        String s1 = scanner.next();
        Section s = my_library.getSectionByName(s1);
        if (Objects.isNull(s)) {
            System.out.println("Nu am gasit aceasta sectiune");
        } else {
            my_library.showSection(s);
        }

        now = LocalDateTime.now();
        reader_writer.fileWriter(path, "section shown," + dtf.format(now) + '\n');
    }

    public void addBook(Library my_library, Scanner scanner) {

        System.out.println("Din ce sectiune va face parte cartea?");
        String s1 = scanner.next();
        scanner.nextLine();
        Section s = my_library.getSectionByName(s1);
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
            String aux6 = scanner.nextLine().trim();
            Author e6 = my_library.getAuthorByName(aux6);

            System.out.println("Ce tip de carte este? 1 - roman/nuvela, 2 - biografie, 3 - banda desenata, 4 - enciclopedie");
            Integer option = scanner.nextInt();

            if (option == 1) {
                scanner.nextLine();
                System.out.println("Ce tip de narator are?");
                String e7 = scanner.nextLine();
                System.out.println("Cine sunt personajele principale? (vor fi separate prin ele folosind -)");
                String[] e8 = scanner.nextLine().split("-");

                Book b = new Novel(e1, e2, e3, e4, e5, e6, s, e7, e8);
                s.addBook(b);

            } else if (option == 2) {
                System.out.println("Despre cine este vorba in biografie?");
                String e7 = scanner.nextLine();

                Book b = new Biography(e1, e2, e3, e4, e5, e6, s, e7);
                s.addBook(b);

            } else if (option == 3) {
                System.out.println("Este in alb-negru? true/false");
                Boolean e7 = scanner.nextBoolean();
                System.out.println("Se citeste invers? true/false");
                Boolean e8 = scanner.nextBoolean();

                Book b = new ComicBook(e1, e2, e3, e4, e5, e6, s, e7, e8);
                s.addBook(b);

            } else if (option == 4) {
                System.out.println("Ce subiect aprofundeaza?");
                String e7 = scanner.next();

                Book b = new Encyclopedia(e1, e2, e3, e4, e5, e6, s, e7);
                s.addBook(b);
            }
        }

        now = LocalDateTime.now();
        reader_writer.fileWriter(path, "book added," + dtf.format(now) + '\n');
    }

    public void deleteBook(Library my_library, Scanner scanner) {
        System.out.println("Din ce sectiune este cartea pe care vreti sa o stergeti?");
        String s1 = scanner.next();
        scanner.nextLine();
        Section s = my_library.getSectionByName(s1);
        if (Objects.isNull(s)) {
            System.out.println("Nu am gasit aceasta sectiune");
        } else {
            System.out.println("Cum se numeste cartea pe care vreti sa o stergeti?");
            String c1 = scanner.nextLine();
            Book b = s.getBookByTitle(c1);
            if (b == null) {
                System.out.println("Nu am gasit aceasta carte");
            } else {
                s.deleteBook(b);
            }
        }

        now = LocalDateTime.now();
        reader_writer.fileWriter(path, "book deleted," + dtf.format(now) + '\n');
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

        now = LocalDateTime.now();
        reader_writer.fileWriter(path, "book borrowed," + dtf.format(now) + '\n');
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

        now = LocalDateTime.now();
        reader_writer.fileWriter(path, "book returned," + dtf.format(now) + '\n');
    }

    public void showBorrowedBooks(Reader reader) {
        for(String s : reader.getBorrowed_books()) {
            System.out.println(s);
        }

        now = LocalDateTime.now();
        reader_writer.fileWriter(path, "borrowed books shown," + dtf.format(now) + '\n');
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

        now = LocalDateTime.now();
        reader_writer.fileWriter(path, "user logged in," + dtf.format(now) + '\n');

        return reader;
    }
}
