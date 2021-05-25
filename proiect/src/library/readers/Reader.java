package library.readers;

import library.books.Book;

import java.util.ArrayList;

public abstract class Reader {
    protected String name;
    protected Integer age;
    protected String phone_number;
    protected String email_address;

    abstract public Boolean isUnderAged();

    public Reader(String name, Integer age, String phone_number, String email_address) {
        this.name = name;
        this.age = age;
        this.phone_number = phone_number;
        this.email_address = email_address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public abstract String toWrite();
}

