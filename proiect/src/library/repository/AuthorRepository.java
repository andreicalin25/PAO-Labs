package library.repository;

import library.Author;
import library.config.DatabaseConfiguration;

import java.sql.*;


public class AuthorRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS authors" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(30)," +
                "date_of_birth varchar(12), date_of_death varchar(12))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAuthor(String name, String date_of_birth, String date_of_death) {
        String insertAuthorSql = "INSERT INTO authors(name, date_of_birth, date_of_death) VALUES(?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertAuthorSql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, date_of_birth);
            preparedStatement.setString(3, date_of_death);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Author getAuthorByName(String name) {
        String selectSql = "SELECT * FROM authors WHERE name=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToAuthor(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAuthorName(String name, int id) {
        String updateNameSql = "UPDATE authors SET name=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAuthorById(int id) {
        String updateNameSql = "DELETE FROM authors  WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Author mapToAuthor(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Author(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
        }
        return null;
    }

    public void displayAuthors() {
        String selectSql = "SELECT * FROM authors";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getString(1));
                System.out.println("Name:" + resultSet.getString(2));
                System.out.println("Date of birth:" + resultSet.getString(3));
                System.out.println("Date of death:" + resultSet.getString(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
