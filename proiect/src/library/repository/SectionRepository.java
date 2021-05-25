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
        String insertSectionSql = "INSERT INTO sections(name) VALUES(?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSectionSql);
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

            //also update section_name in Books
            String updateBooksSql = "UPDATE books SET section=? WHERE section=?";

            try {
                PreparedStatement preparedStatement2 = connection.prepareStatement(updateBooksSql);
                preparedStatement2.setString(1, name);
                preparedStatement2.setString(2, s.getName());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSection(Section s) {
        String deleteSectionSql = "DELETE FROM sections WHERE name=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(deleteSectionSql);
            preparedStatement1.setString(1, s.getName());

            preparedStatement1.executeUpdate();

            // Sterge cartile din sectiunea data
            String deleteBooksSql = "DELETE FROM books WHERE section=?";
            try {
                PreparedStatement preparedStatement2 = connection.prepareStatement(deleteBooksSql);
                preparedStatement2.setString(1, s.getName());
                preparedStatement2.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Section mapToSection(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Section(resultSet.getString(2));
        }
        return null;
    }

    public void displaySection(Section s) {
        String selectSql = "SELECT * FROM sections WHERE name=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

            System.out.println("Name: " + s.getName());

            // afisare carti
            String selectBooksSql = "SELECT * FROM books WHERE section=?";
            try {
                PreparedStatement preparedStatement2 = connection.prepareStatement(selectBooksSql);
                preparedStatement2.setString(1, s.getName());
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                while (resultSet2.next()) {
                    System.out.println("Title: " + resultSet2.getString(2));
                    System.out.println("Type: " + resultSet2.getString(3));
                    System.out.println("Author: " + resultSet2.getString(4));
                    System.out.println("Number of pages: " + resultSet2.getString(6));
                    System.out.println("Year of publication: " + resultSet2.getString(7));
                    System.out.println("");
                }
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
                System.out.println("Name: " + resultSet.getString(2));

                // Afisare titluri
                String selectBooksSql = "SELECT * FROM books WHERE section=?";
                try {
                    PreparedStatement preparedStatement2 = connection.prepareStatement(selectBooksSql);
                    preparedStatement2.setString(1, resultSet.getString(2));
                    ResultSet resultSet2 = preparedStatement2.executeQuery();
                    while (resultSet2.next()) {
                        System.out.println(" - " + resultSet2.getString(2));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
