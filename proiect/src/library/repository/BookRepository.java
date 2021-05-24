package library.repository;

import library.Author;
import library.books.*;
import library.config.DatabaseConfiguration;

import java.sql.*;

/*
pozitie in baza de date - variablia - pozitie in constructor
1 - title - 1
2 - type - #
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
                "(id int PRIMARY KEY AUTO_INCREMENT, title varchar(50), type varchar(20)" +
                "author varchar(30), section varchar(30)" +
                "nr_of_pages int, publication_year int," +
                "informations varchar(200), explicit_content boolean," +
                "novel_narrator varchar(30), novel_main_characters varchar(200)" +
                "comicbook_black_and_white boolean, comicbook_backwards_reading boolean," +
                "biography_person varchar(30)," +
                "encyclopedia_subject varchar(30)" +
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
                        "(title, type, author, section, nr_of_pages, publication_year," +
                        "informations, explicit_content, novel_narrator, novel_main_characters) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                break;
            case "ComicBook":
                insertBookSql = "INSERT INTO books" +
                        "(title, type, author, section, nr_of_pages, publication_year," +
                        "informations, explicit_content, comicbook_black_and_white, comicbook_backwards_reading) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                break;
            case "Biography":
                insertBookSql = "INSERT INTO books" +
                        "(title, type, author, section, nr_of_pages, publication_year," +
                        "informations, explicit_content, biography_person) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
                break;
            case "Encyclopedia":
                insertBookSql = "INSERT INTO books" +
                        "(title, type, author, section, nr_of_pages, publication_year," +
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

            switch (resultSet.getString(2)) {
                case "Novel":
                    return new Novel(resultSet.getString(1), resultSet.getInt(5), resultSet.getInt(6),
                            resultSet.getString(7), resultSet.getBoolean(8), resultSet.getString(3),
                            resultSet.getString(4), resultSet.getString(9), resultSet.getString(10));
                case "ComicBook":
                    return new ComicBook(resultSet.getString(1), resultSet.getInt(5), resultSet.getInt(6),
                            resultSet.getString(7), resultSet.getBoolean(8), resultSet.getString(3),
                            resultSet.getString(4), resultSet.getBoolean(11), resultSet.getBoolean(12));
                case "Biography":
                    return new Biography(resultSet.getString(1), resultSet.getInt(5), resultSet.getInt(6),
                            resultSet.getString(7), resultSet.getBoolean(8), resultSet.getString(3),
                            resultSet.getString(4), resultSet.getString(13));
                case "Encyclopedia":
                    return new Encyclopedia(resultSet.getString(1), resultSet.getInt(5), resultSet.getInt(6),
                            resultSet.getString(7), resultSet.getBoolean(8), resultSet.getString(3),
                            resultSet.getString(4), resultSet.getString(14));
            }
        }
        return null;
    }

    public void deleteBook(Book b) {
        String deleteAuthorSql = "DELETE FROM books  WHERE title=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteAuthorSql);
            preparedStatement.setString(1, b.getTitle());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //TODO: DE IMPLEMENTAT ____________________________________________________________
    public void displayBooks() {
        String selectSql = "SELECT * FROM persons";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getString(1));
                System.out.println("Name:" + resultSet.getString(2));
                System.out.println("Age:" + resultSet.getDouble(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
