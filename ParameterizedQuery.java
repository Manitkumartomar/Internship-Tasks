// Importing all necessary packages here explicitly
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ParameterizedQuery {
    // Initializing variables for opening, closing and managing 
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static void main(String[] args) {
         // Taking input from the user
            Scanner scanner = new Scanner(System.in);
            // Taking jdbc url from the user
            System.out.print("Enter jDBC url: ");
            String JDBC_URL = scanner.next();
            
            // Taking username
            System.out.print("Enter username: ");
            String USERNAME = scanner.next();

            // Taking password
            System.out.print("Enter Password: ");
            String PASSWORD = scanner.next();
            
            // Handling exception if any occurrs
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);) {
             
           // Taking the id of the employee which information user wants
            System.out.print("Enter the employee ID: ");
            int employeeId = scanner.nextInt();

            // Create a PreparedStatement with a parameterized query
            String sql = "SELECT * FROM employees WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, employeeId); // Set the parameter

                // Execute the query
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Check if the employee details exist
                    if (resultSet.next()) {
                        // Display employee details
                        System.out.println("ID: " + resultSet.getInt("id"));
                        System.out.println("Name: " + resultSet.getString("name"));
                        System.out.println("Age: " + resultSet.getDouble("age"));
                        System.out.println("Department: " + resultSet.getString("department"));
                    } else {
                        System.out.println("Employee with ID " + employeeId + " not found.");
                    }
                }
            }
        } catch (SQLException e) {

            if (e.getSQLState().equals("42000")) {// Will handle exception if database not found
                System.out.println("Error occurred: Database not found!");
            }
           else if (e.getSQLState().equals("28000")) {// Will handle exception if username or password is not correct
                System.out.println("Error: Access Denied, Please check your username or password!");
            }
           else if (e.getSQLState().equals("42S02")) {// Will handle exception if table not found
                System.out.println("Error: Table not found!");
            }
            else{
                e.printStackTrace();// It will handle other exceptions
            }
        }finally{
            try {// Closing all resources if they are not in use now
                if (connection != null) connection.close();
                if (statement != null) statement.close();
                if (preparedStatement != null) preparedStatement.close();
                if (resultSet != null) resultSet.close();
                scanner.close();// Scanner closed..
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
