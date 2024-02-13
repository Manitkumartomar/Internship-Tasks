import java.sql.*;

public class Jdbc {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/company";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Manit@2024";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void main(String[] args) {
        try {
            // Establishing a connection to the database
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Creating a statement to execute SQL queries
            statement = connection.createStatement();

            // Defining the SQL query to fetch all records from the "employees" table
            String query = "SELECT * FROM employees";

            // Executing the query and storing the result in a ResultSet
            resultSet = statement.executeQuery(query);

            // Displaying the results
            System.out.println("Employee records:");
            System.out.println("ID | Name | age | department");
            System.out.println("-----------------------------------");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String department = resultSet.getString("department");
                System.out.println(id + " | " + name + " | " + age + " | " + department);
            }
        } catch (SQLException e) {
            // Handle database-related exceptions
            if (e.getSQLState().equals("42000")) {
                System.out.println("Error: Database not found. Please check if the database exists.");
            } else if (e.getSQLState().equals("28000")) {
                System.out.println("Error: Access denied. Please check your username or password.");
            } else {
                e.printStackTrace(); // Handle other SQL exceptions
            }
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
        } finally {
            // Closing all resources to prevent memory leaks
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error: Something went wrong while closing connections.");
            }
        }
    }
}
