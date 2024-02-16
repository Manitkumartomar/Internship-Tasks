import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ResultSetMetaData {

    public static void main(String[] args) {
        // Creating a scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Starts an infinite loop to continuously prompt the user for input
        while (true) {
            try {
                // Prompt the user to enter the url or Exit to close the program
                System.out.print("Enter JDBC_URL(or 'Exit' to quit): ");
                String JDBC_URL = scanner.nextLine();
                if (JDBC_URL.equalsIgnoreCase("Exit")) {
                    // If user enter Exit, exit the program
                    System.out.println("Exiting the program...!!");
                    break;
                }

                // Prompt the user to enter the username and password
                System.out.print("Enter Username: ");
                String USER_NAME = scanner.nextLine();
                System.out.print("Enter Password: ");
                String PASSWORD = scanner.nextLine();

                // Prompt the user to write a query
                System.out.print("Write query(SLECET * FROM table_name){Case-Insensitive}: ");
                String query = scanner.nextLine();

                // Establish connection to the database using JDBC
                Connection connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);

                // Create a statement object to execut the SQL query
                Statement statement = connection.createStatement();

                // Execute the query and set the result to the resultSet
                ResultSet resultSet = statement.executeQuery(query);

                // Get metaData of the resultSet
                java.sql.ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

                int columnCount = resultSetMetaData.getColumnCount();

                System.out.println("Column Count: " + columnCount);

                // Print column details
                System.out.println("Column Details...");

                // Iterate over each column and print its name and data type
                for (int i = 1; i <= columnCount; i++) {
                    System.out.println("Column Name: " + resultSetMetaData.getColumnName(i));
                    System.out.println("Data Type: " + resultSetMetaData.getColumnTypeName(i));
                    System.out.println("----------------------");
                }

                // Close the result set, statement, and connection to release resources
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                // Handle SQL exceptions
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();// Scanner closed
    }
}