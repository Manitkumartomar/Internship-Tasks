
// Importing all necessary packages here explicitly
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ParameterizedQuery {
    // Initializing variables for opening, closing and managing
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static void main(String[] args) {
        // Taking input from the user
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                // Taking jdbc url from the user
                System.out.print("Enter jDBC url: ");
                String JDBC_URL = scanner.next();

                // Taking username
                System.out.print("Enter username: ");
                String USERNAME = scanner.next();

                // Taking password
                System.out.print("Enter Password: ");
                String PASSWORD = scanner.next();

                connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

                try {
                    // Taking the id of the employee which information user wants
                    System.out.print("Enter the employee ID: ");
                    int employeeId = scanner.nextInt();

                    String sql = "SELECT * FROM employees WHERE id = ?";
                    // Create a PreparedStatement with a parameterized query
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, employeeId);

                    resultSet = preparedStatement.executeQuery();

                    // Check if the employee details exist
                    if (resultSet.next()) {
                        System.out.println("Employee's Details: ");
                        System.out.println("ID: " + resultSet.getInt("id"));
                        System.out.println("Name: " + resultSet.getString("name"));
                        System.out.println("Age: " + resultSet.getDouble("age"));
                        System.out.println("Department: " + resultSet.getString("department"));
                    } else {
                        System.out.println("Employee with ID " + employeeId + " not found.");
                    }
                    break; // Break the loop if execution is successful

                } catch (InputMismatchException e) {
                    System.out.println("Error occurred: Please enter a numerical value for employee ID.");
                    scanner.nextLine(); // Consume the invalid input
                }
            }

            catch (SQLException e) {// Handling Exception regarding JDBC and SQL credentials
                System.out.println("Error occurred: " + e.getMessage());
            } finally {
                try {// closing all resources if they are not in use now
                    if (connection != null)
                        connection.close();
                    if (preparedStatement != null)
                        preparedStatement.close();
                    if (resultSet != null)
                        resultSet.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        scanner.close();// Scanner closed
    }
}
