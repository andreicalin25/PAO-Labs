package library.readers;

import library.books.Book;

import java.util.ArrayList;

public class AdultReader extends Reader {
    private String employment;

    public Boolean isUnderAged() {
        return false;
    }

    public AdultReader(String name, Integer age, String phone_number, String email_address, ArrayList<Book> books, String employment) {
        super(name, age, phone_number, email_address, books);
        this.employment = employment;
    }

    public AdultReader(String name, Integer age, String phone_number, String email_address, String employment) {
        super(name, age, phone_number, email_address);
        this.employment = employment;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    @Override
    public String toWrite() {

        String string = name + ',' + age.toString() + ',' + phone_number + ',' + email_address + ',' + employment;

        if(!borrowed_books.isEmpty()) {
            string += ',';

            for (Book b : borrowed_books) {
                if(b.equals(borrowed_books.get(borrowed_books.size() - 1))) {
                    string += b.getTitle();
                } else {
                    string += b.getTitle() + '-';
                }
            }
        }

        return string;
    }
}
