package library;

import library.books.*;
import library.readers.AdultReader;
import library.readers.Reader;
import library.readers.YoungReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Library my_library = new Library();

        //initialize library with some values
        try {
            File file = new File("/Users/andreicalin/Desktop/PAO/proiect/src/library/initializare.txt");
            Scanner init = new Scanner(file);

            //add authors
            for (int i = 0; i < 5; i++) {
                String s1, s2, s3;
                s1 = init.nextLine().trim();
                s2 = init.nextLine().trim();
                s3 = init.nextLine().trim();

                if (s3.equals("0")) {
                    Author a = new Author(s1, s2);
                    my_library.addAuthor(a);
                } else {
                    Author a = new Author(s1, s2, s3);
                    my_library.addAuthor(a);
                }

                String space = init.nextLine().trim();
            }

            //add sections
            for (int i = 0; i < 4; i++) {
                String s;
                s = init.nextLine().trim();
                Section sec = new Section(s);
                my_library.addSection(sec);

                String space = init.nextLine().trim();
            }

            //add books
            for (int i = 0; i < 5; i++) {
                String type = init.nextLine().trim();

                if (type.equals("novel")) {
                    String s0 = init.nextLine().trim();

                    String s1 = init.nextLine().trim();

                    String aux2 = init.nextLine();
                    Integer s2 = Integer.parseInt(aux2);

                    String aux3 = init.nextLine();
                    Integer s3 = Integer.parseInt(aux3);

                    String s4 = init.nextLine().trim();

                    String aux5 = init.nextLine().trim();
                    Boolean s5 = Boolean.parseBoolean(aux5);

                    String s6 = init.nextLine().trim();

                    String s7 = init.nextLine().trim();
                    String[] s8 = init.nextLine().trim().split("-");

                    Section sec = my_library.getSectionByName(s0);
                    Author a = my_library.getAuthorByName(s6);
                    Book b = new Novel(s1, s2, s3, s4, s5, a, s7, s8);

                    sec.addBook(b);
                }

                if (type.equals("biography")) {
                    String s0 = init.nextLine().trim();

                    String s1 = init.nextLine().trim();

                    String aux2 = init.nextLine();
                    Integer s2 = Integer.parseInt(aux2);

                    String aux3 = init.nextLine();
                    Integer s3 = Integer.parseInt(aux3);

                    String s4 = init.nextLine().trim();

                    String aux5 = init.nextLine().trim();
                    Boolean s5 = Boolean.parseBoolean(aux5);

                    String s6 = init.nextLine().trim();

                    String s7 = init.nextLine().trim();

                    Section sec = my_library.getSectionByName(s0);
                    Author a = my_library.getAuthorByName(s6);
                    Book b = new Biography(s1, s2, s3, s4, s5, a, s7);

                    sec.addBook(b);
                }

                if (type.equals("comicbook")) {
                    String s0 = init.nextLine().trim();

                    String s1 = init.nextLine().trim();

                    String aux2 = init.nextLine();
                    Integer s2 = Integer.parseInt(aux2);

                    String aux3 = init.nextLine();
                    Integer s3 = Integer.parseInt(aux3);

                    String s4 = init.nextLine().trim();

                    String aux5 = init.nextLine().trim();
                    Boolean s5 = Boolean.parseBoolean(aux5);

                    String s6 = init.nextLine().trim();

                    String aux7 = init.nextLine().trim();
                    Boolean s7 = Boolean.parseBoolean(aux7);

                    String aux8 = init.nextLine().trim();
                    Boolean s8 = Boolean.parseBoolean(aux8);

                    Section sec = my_library.getSectionByName(s0);
                    Author a = my_library.getAuthorByName(s6);
                    Book b = new ComicBook(s1, s2, s3, s4, s5, a, s7, s8);

                    sec.addBook(b);
                }

                String space = init.nextLine().trim();
            }

            //add readers
            for (int i = 0; i < 2; i++) {
                String s1 = init.nextLine().trim();

                String aux2 = init.nextLine().trim();
                Integer s2 = Integer.parseInt(aux2);

                String s3 = init.nextLine().trim();

                String s4 = init.nextLine().trim();

                if (s2 >= 16) {
                    String s5 = init.nextLine().trim();

                    AdultReader r = new AdultReader(s1, s2, s3, s4, s5);
                    my_library.addReader(r);
                } else {
                    String s5 = init.nextLine().trim();
                    String s6 = init.nextLine().trim();

                    YoungReader r = new YoungReader(s1, s2, s3, s4, s5, s6);
                    my_library.addReader(r);
                }

                String space = init.nextLine().trim();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        System.out.println("##### Autori #####");
//        my_library.showAuthors();
//        System.out.println("\n#### Sectiuni ####");
//        my_library.showSections();
//        System.out.println("\n#### Cititori ####");
//        my_library.showReaders();

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
                            if (s == null) {
                                System.out.println("Nu am gasit aceasta sectiune");
                            } else {
                                scanner.nextLine();
                                System.out.println("Cum se numeste cartea pe care doriti sa o imprumutati?");
                                String aux = scanner.nextLine();
                                Book b = s.getBookByTitle(aux);
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
