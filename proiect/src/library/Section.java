package library;

import library.books.Book;

import java.util.Arrays;

public class Section {
    private String name;
    private Book[]books;

    public Section(String name, Book[] books) {
        this.name = name;
        this.books = books;
    }

    public Section(String name) {
        this.name = name;
        this.books = new Book[0];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    @Override
    public String toString() {
        String s;
        s = name + ":\n";
        for (Book b : books) {
            s += b.getTitle();
            s += '\n';
        }

        return s;
    }

    public void addBook(Book new_b) {
        Book []new_books = new Book[books.length + 1];
        new_books[books.length] = new_b;
        System.arraycopy(books, 0, new_books, 0, books.length);
        Arrays.sort(new_books);
        books = new_books;
    }

    public void deleteBook(Book b) {
        int i;

        for(i=0; i < books.length; i++) {
            if(books[i].equals(b))
                break;
        }

        if (i != books.length) {
            Book []new_books = new Book[books.length - 1];
            System.arraycopy(books, 0, new_books, 0, i);

            for(i = i; i < books.length - 1; i++)
                new_books[i] = books[i+1];

            books = new_books;
            System.out.println("Cartea a fost eliminata cu succes");
        }
    }

    public String deleteBook(String b_title) {
        int i;

        for(i=0; i < books.length; i++) {
            if(books[i].getTitle().equals(b_title))
                break;
        }

        if(i == books.length) {
            return "Nu am gasit cartea ceruta. Cartea nu se afla in lista sau verificati din nou daca ati scris corect titlul\n";
        }
        else {
            Book []new_books = new Book[books.length - 1];
            System.arraycopy(books, 0, new_books, 0, i);

            for(i = i; i < books.length - 1; i++)
                new_books[i] = books[i+1];

            books = new_books;
            return "Cartea a fost eliminata cu succes";
        }
    }

    public void showBooks() {

        System.out.println('\n' + this.name + ':');

        for(Book b : books) {
            System.out.println(b);
        }
    }

    public Book getBookByTitle (String title) {
        for (Book b : books){
            if (b.getTitle().equals(title)) {
                return b;
            }
        }
        return null;
    }

    public void deleteBooksWrittenBy (Author a) {

        int count = 0;

        for(Book b : books) {
            if(b.getAuthor().equals(a)) {
                count++;
            }
        }

        Book []new_books = new Book[books.length - count];

        int i = 0;
        for(Book b : books) {
            if (! b.getAuthor().equals(a)) {
                new_books[i++] = b;
            }
        }

        books = new_books;
    }

}
