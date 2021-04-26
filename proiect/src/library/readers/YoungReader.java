package library.readers;

import library.books.Book;

import java.util.ArrayList;

public class YoungReader extends Reader {
    private String parents_phone;
    private String parents_email;

    public YoungReader(String name, Integer age, String phone_number, String email_address, String parents_phone, String parents_email) {
        super(name, age, phone_number, email_address);
        this.parents_phone = parents_phone;
        this.parents_email = parents_email;
    }

    public YoungReader(String name, Integer age, String phone_number, String email_address, ArrayList<Book> books, String parents_phone, String parents_email) {
        super(name, age, phone_number, email_address, books);
        this.parents_phone = parents_phone;
        this.parents_email = parents_email;
    }

    public Boolean isUnderAged() {
        return true;
    }

    public String getParents_phone() {
        return parents_phone;
    }

    public void setParents_phone(String parents_phone) {
        this.parents_phone = parents_phone;
    }

    public String getParents_email() {
        return parents_email;
    }

    public void setParents_email(String parents_email) {
        this.parents_email = parents_email;
    }
}
