package library.repository;

import library.Author;
import library.books.*;
import library.config.DatabaseConfiguration;

import java.sql.*;

/*
pozitie in baza de date - variablia - pozitie in constructor
1 - title - 1
2 - book_type - #
3 - author - 6
4 - section - 7
5 - nr pag - 2
6 - year - 3
7 - info - 4
8 - explicit - 5
9 - narrator - 8
10 - charcters - 9
11 - B&W - 8
12 - backwards - 9
13 - person - 8
14 - subject - 8

 */
public class BookRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS books" +
                "(id int PRIMARY KEY AUTO_INCREMENT, title varchar(50), book_type varchar(20), " +
                "author varchar(30), section varchar(30), " +
                "nr_of_pages int, publication_year int, " +
                "informations varchar(200), explicit_content boolean, " +
                "novel_narrator varchar(30), novel_main_characters varchar(200), " +
                "comicbook_black_and_white boolean, comicbook_backwards_reading boolean, " +
                "biography_person varchar(30), " +
                "encyclopedia_subject varchar(30), " +
                "borrowed_by varchar(30))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBook(Book b) {

        String type = b.getClass().getSimpleName();
        String insertBookSql = "";

        switch (type) {
            case "Novel":
                insertBookSql = "INSERT INTO books" +
                        "(title, book_type, author, section, nr_of_pages, publication_year," +
                        "informations, explicit_content, novel_narrator, novel_main_characters) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                break;
            case "ComicBook":
                insertBookSql = "INSERT INTO books" +
                        "(title, book_type, author, section, nr_of_pages, publication_year," +
                        "informations, explicit_content, comicbook_black_and_white, comicbook_backwards_reading) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                break;
            case "Biography":
                insertBookSql = "INSERT INTO books" +
                        "(title, book_type, author, section, nr_of_pages, publication_year," +
                        "informations, explicit_content, biography_person) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
                break;
            case "Encyclopedia":
                insertBookSql = "INSERT INTO books" +
                        "(title, book_type, author, section, nr_of_pages, publication_year," +
                        "informations, explicit_content, encyclopedia_subject) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
                break;
        }

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertBookSql);

            preparedStatement.setString(1, b.getTitle());
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, b.getAuthor());
            preparedStatement.setString(4, b.getSection());
            preparedStatement.setInt(5, b.getNr_of_pages());
            preparedStatement.setInt(6, b.getPublication_year());
            preparedStatement.setString(7, b.getInformations());
            preparedStatement.setBoolean(8, b.getExplicit_content());

            switch (type) {
                case "Novel":
                    Novel n = (Novel) b;
                    preparedStatement.setString(9, n.getNarrator());
                    preparedStatement.setString(10, n.getMain_characters());
                    break;

                case "ComicBook":
                    ComicBook c = (ComicBook) b;
                    preparedStatement.setBoolean(9, c.getBlack_and_white());
                    preparedStatement.setBoolean(10, c.getBackwards_reading());
                    break;

                case "Biography":
                    Biography bg = (Biography) b;
                    preparedStatement.setString(9, bg.getPerson());
                    break;

                case "Encyclopedia":
                    Encyclopedia e = (Encyclopedia) b;
                    preparedStatement.setString(9, e.getSubject());
                    break;
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book getBookByTitle(String title) {
        String selectSql = "SELECT * FROM books WHERE title=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, title);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToBook(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Book mapToBook(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {

            switch (resultSet.getString(3)) {
                case "Novel":
                    return new Novel(resultSet.getString(2), resultSet.getInt(6), resultSet.getInt(7),
                            resultSet.getString(8), resultSet.getBoolean(9), resultSet.getString(4),
                            resultSet.getString(5), resultSet.getString(10), resultSet.getString(11));
                case "ComicBook":
                    return new ComicBook(resultSet.getString(2), resultSet.getInt(6), resultSet.getInt(7),
                            resultSet.getString(8), resultSet.getBoolean(9), resultSet.getString(4),
                            resultSet.getString(5), resultSet.getBoolean(12), resultSet.getBoolean(13));
                case "Biography":
                    return new Biography(resultSet.getString(2), resultSet.getInt(6), resultSet.getInt(7),
                            resultSet.getString(8), resultSet.getBoolean(9), resultSet.getString(4),
                            resultSet.getString(5), resultSet.getString(14));
                case "Encyclopedia":
                    return new Encyclopedia(resultSet.getString(2), resultSet.getInt(6), resultSet.getInt(7),
                            resultSet.getString(8), resultSet.getBoolean(9), resultSet.getString(4),
                            resultSet.getString(5), resultSet.getString(15));
            }
        }
        return null;
    }

    public void deleteBook(Book b) {
        String deleteBookSql = "DELETE FROM books  WHERE title=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteBookSql);
            preparedStatement.setString(1, b.getTitle());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayBooks() {
        String selectSql = "SELECT * FROM books";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("Id: " + resultSet.getString(1));
                System.out.println("Title: " + resultSet.getString(2));
                System.out.println("Type: " + resultSet.getString(3));
                System.out.println("Author: " + resultSet.getString(4));
                System.out.println("Section: " + resultSet.getString(5));
                System.out.println("Nr pages: " + resultSet.getInt(6));
                System.out.println("Borrowed by: " + resultSet.getString(16));
                System.out.println("");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean isBorrowed(Book b) {
        String selectSql = "SELECT * FROM books WHERE title=? AND borrowed_by IS NOT NULL";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, b.getTitle());

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return Boolean.TRUE;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Boolean.FALSE;
    }

    public void getBorrowed(Book b, String name) {
        String borrowBookSql = "UPDATE books SET borrowed_by=? WHERE title=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(borrowBookSql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, b.getTitle());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBorrowed(Book b) {
        String borrowBookSql = "UPDATE books SET borrowed_by=? WHERE title=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(borrowBookSql);
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, b.getTitle());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
