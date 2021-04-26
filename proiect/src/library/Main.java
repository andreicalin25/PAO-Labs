package library;

import library.books.*;
import library.readers.AdultReader;
import library.readers.Reader;
import library.readers.YoungReader;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        Reader_Writer reader_writer = new Reader_Writer();

        Library my_library = new Library();


        reader_writer.readAuthors(my_library);
        reader_writer.readSections(my_library);
        reader_writer.readBooks(my_library);
        reader_writer.readReaders(my_library);

        System.out.println("##### Autori #####");
        my_library.showAuthors();
        System.out.println("\n#### Sectiuni ####");
        my_library.showSections();
        System.out.println("\n#### Cititori ####");
        my_library.showReaders();

//        ArrayList<ArrayList<String>> obiecte = new ArrayList<>();
//        obiecte = file_reader("/Users/andreicalin/Desktop/PAO/PAO-Labs/proiect/src/library/input_files/authors.csv");
//        for(ArrayList<String> obiect : obiecte) {
//            for(String data : obiect) {
//                System.out.printf(data + " ");
//            }
//            System.out.printf("\n");
//        }

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

                            break;
                        }
                        case 12: {
                            scanner.nextLine();
                            System.out.println("Cum se numeste autorul?");
                            String s1 = scanner.nextLine();
                            Author a = my_library.getAuthorByName(s1);
                            if (a == null) {
                                System.out.println("Nu am gasit acest autor");
                            } else {
                                my_library.deleteAuthor(a);
                            }
                            break;
                        }
                        case 13: {
                            my_library.showAuthors();
                            break;
                        }
                        case 21: {
                            System.out.println("Cum se va numi noua sectiune?");
                            String s1 = scanner.next();
                            Section s = new Section(s1);
                            my_library.addSection(s);
                            break;
                        }
                        case 22: {
                            System.out.println("Cum se numeste sectiunea?");
                            String s1 = scanner.next();
                            Section s = my_library.getSectionByName(s1);
                            if (s == null) {
                                System.out.println("Nu am gasit aceasta sectiune");
                            } else {
                                my_library.deleteSection(s);
                            }
                            break;
                        }
                        case 23: {
                            my_library.showSections();
                            break;
                        }
                        case 24: {
                            System.out.println("Cum se numeste sectiunea?");
                            String s1 = scanner.next();
                            Section s = my_library.getSectionByName(s1);
                            if (s == null) {
                                System.out.println("Nu am gasit aceasta sectiune");
                            } else {
                                my_library.showSection(s);
                            }
                            break;
                        }
                        case 31: {
                            System.out.println("Din ce sectiune va face parte cartea?");
                            String s1 = scanner.next();
                            scanner.nextLine();
                            Section s = my_library.getSectionByName(s1);
                            if (s == null) {
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

                                    Book b = new Novel(e1, e2, e3, e4, e5, e6, e7, e8);
                                    s.addBook(b);

                                } else if (option == 2) {
                                    System.out.println("Despre cine este vorba in biografie?");
                                    String e7 = scanner.nextLine();

                                    Book b = new Biography(e1, e2, e3, e4, e5, e6, e7);
                                    s.addBook(b);

                                } else if (option == 3) {
                                    System.out.println("Este in alb-negru? true/false");
                                    Boolean e7 = scanner.nextBoolean();
                                    System.out.println("Se citeste invers? true/false");
                                    Boolean e8 = scanner.nextBoolean();

                                    Book b = new ComicBook(e1, e2, e3, e4, e5, e6, e7, e8);
                                    s.addBook(b);

                                } else if (option == 4) {
                                    System.out.println("Ce subiect aprofundeaza?");
                                    String e7 = scanner.next();

                                    Book b = new Encyclopedia(e1, e2, e3, e4, e5, e6, e7);
                                    s.addBook(b);
                                }

                            }
                            break;
                        }
                        case 32: {
                            System.out.println("Din ce sectiune este cartea pe care vreti sa o stergeti?");
                            String s1 = scanner.next();
                            scanner.nextLine();
                            Section s = my_library.getSectionByName(s1);
                            if (s == null) {
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
                            break;
                        }
                        default:
                            System.out.println("");
                    }
                }
            } else if (o1 == 2) {
                int o2 = 10;

                scanner.nextLine();
                System.out.println("Introduceti numele dumneavoastra:");

                String user_name = scanner.nextLine();
                Reader reader = my_library.getReaderByName(user_name);

                if (reader == null) {
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

                System.out.println("Buna ziua " + user_name + "!");

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
                            my_library.showSections();
                            break;
                        }
                        case 12: {
                            System.out.println("Cum se numeste sectiunea?");
                            String s1 = scanner.next();
                            Section s = my_library.getSectionByName(s1);
                            if (s == null) {
                                System.out.println("Nu am gasit aceasta sectiune");
                            } else {
                                my_library.showSection(s);
                            }
                            break;
                        }
                        case 13: {
                            my_library.showAuthors();
                            break;
                        }
                        case 21: {
                            System.out.println("Din ce sectiune doriti sa imprumutati?");
                            String s1 = scanner.next();
                            Section s = my_library.getSectionByName(s1);
                            System.out.println(s.getName());
                            if (s == null) {
                                System.out.println("Nu am gasit aceasta sectiune");
                            } else {
                                scanner.nextLine();
                                System.out.println("Cum se numeste cartea pe care doriti sa o imprumutati?");
                                String aux = scanner.nextLine();
                                Book b = s.getBookByTitle(aux);
                                System.out.println(b.getTitle());
                                if (b == null) {
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
                            break;
                        }
                        case 22: {
                            System.out.println("Din ce sectiune este cartea pe care doriti sa o returnati?");
                            String s1 = scanner.next();
                            Section s = my_library.getSectionByName(s1);
                            if (s == null) {
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
                            break;
                        }
                        case 23: {
                            for(String s : reader.getBorrowed_books()) {
                                System.out.println(s);
                            }
                            break;
                        }
                        default:
                            System.out.println("");
                    }
                }
            }

        }

    }
}
