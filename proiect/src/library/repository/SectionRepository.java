package library.repository;

import library.Section;
import library.config.DatabaseConfiguration;

import java.sql.*;


public class SectionRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS sections" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(30))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSection(Section s) {
        String insertAuthorSql = "INSERT INTO sections(name) VALUES(?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertAuthorSql);
            preparedStatement.setString(1, s.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Section getSectionByName(String name) {
        String selectSql = "SELECT * FROM sections WHERE name=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToSection(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateSectionName(String name, Section s) {
        String updateNameSql = "UPDATE sections SET name=? WHERE name=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, s.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSection(Section s) {
        String updateNameSql = "DELETE FROM sections WHERE name=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, s.getName());

            preparedStatement.executeUpdate();


            //stergere carti

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Section mapToSection(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Section(resultSet.getString(1));
        }
        return null;
    }

    public void displaySection(Section s) {
        String selectSql = "SELECT * FROM sections WHERE name=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, s.getName());

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Name: " + resultSet.getString(1));

            // afisare carti


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displaySections() {
        String selectSql = "SELECT * FROM sections";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.print("Id: " + resultSet.getString(1));
                System.out.println(", Name: " + resultSet.getString(2));

                //afisare titluri

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
