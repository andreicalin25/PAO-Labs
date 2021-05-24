package library.repository;

import library.books.*;
import library.config.DatabaseConfiguration;
import library.readers.AdultReader;
import library.readers.Reader;
import library.readers.YoungReader;

import java.sql.*;


public class ReaderRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS readers" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(30), age int, " +
                "phone_number varchar(30), email_address varchar(30), " +
                "adult_employment varchar(30), " +
                "young_parents_phone varchar(30), young_parents_email varchar(30))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addReader(Reader r) {

        String type = r.getClass().getSimpleName();
        String insertReaderSql = "";

        switch (type) {
            case "AdultReader":
                insertReaderSql = "INSERT INTO readers" +
                        "(name, age, phone_number, email_address, adult_employment) " +
                        "VALUES(?, ?, ?, ?, ?)";
                break;
            case "YoungReader":
                insertReaderSql = "INSERT INTO readers" +
                        "(name, age, phone_number, email_address, young_parents_phone, young_parents_email) " +
                        "VALUES(?, ?, ?, ?, ?, ?)";
                break;
        }

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertReaderSql);

            preparedStatement.setString(1, r.getName());
            preparedStatement.setInt(2, r.getAge());
            preparedStatement.setString(3, r.getPhone_number());
            preparedStatement.setString(4, r.getEmail_address());

            switch (type) {
                case "AdultReader":
                    AdultReader a = (AdultReader) r;
                    preparedStatement.setString(5, a.getEmployment());
                    break;

                case "YoungReader":
                    YoungReader y = (YoungReader) r;
                    preparedStatement.setString(5, y.getParents_phone());
                    preparedStatement.setString(6, y.getParents_email());
                    break;
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Reader getReaderByName(String name) {
        String selectSql = "SELECT * FROM readers WHERE name=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToReader(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Reader mapToReader(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            if (resultSet.getInt(3) >= 16)
            {
                return new AdultReader(resultSet.getString(2), resultSet.getInt(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
            }
            else
            {
                return new YoungReader(resultSet.getString(1), resultSet.getInt(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6));
            }
        }
        return null;
    }

    public void deleteReader(Reader r) {
        String deleteReaderSql = "DELETE FROM readers  WHERE name=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteReaderSql);
            preparedStatement.setString(1, r.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayReaders() {
        String selectSql = "SELECT * FROM readers";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("Id: " + resultSet.getString(1));
                System.out.println("Name: " + resultSet.getString(2));
                System.out.println("Age: " + resultSet.getInt(3));
                System.out.println("Phone: " + resultSet.getString(4));
                System.out.println("Email: " + resultSet.getString(5));

                int age = resultSet.getInt(3);

                if (age >= 16) {
                    System.out.println("Employment: " + resultSet.getString(6));
                } else {
                    System.out.println("Parent's phone: " + resultSet.getString(6));
                    System.out.println("Parent's email: " + resultSet.getString(7));
                }

                // Afisare carti imprumutate
                System.out.println("Borrowed books:");
                String selectBooksSql = "SELECT * FROM books WHERE borrowed_by=?";
                try {
                    PreparedStatement preparedStatement2 = connection.prepareStatement(selectBooksSql);
                    preparedStatement2.setString(1, resultSet.getString(2));
                    ResultSet resultSet2 = preparedStatement2.executeQuery();
                    while (resultSet2.next()) {
                        System.out.println("- " + resultSet2.getString(2));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
    }

    public void displayReader(Reader r) {

        System.out.println("Name: " + r.getName());
        System.out.println("Age: " + r.getAge());
        System.out.println("Borrowed books:");

        // afisare carti
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String selectBooksSql = "SELECT * FROM books WHERE borrowed_by=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectBooksSql);
            preparedStatement.setString(1, r.getName());
            ResultSet resultSet2 = preparedStatement.executeQuery();
            while (resultSet2.next()) {
                System.out.println("- " + resultSet2.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("");
    }

}
