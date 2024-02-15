
// Importing all necessary packages explicitly
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

// Class for JDBC example
public class InsertEmployeeData {
	// Initializing variables for database connection and management
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;

	// Main method to execute the program
	public static void main(String[] args) {
		// Taking input from the user
		Scanner scanner = new Scanner(System.in);
		while (true) {
			try {
				// Prompt the user to enter the JDBC URL
				System.out.print("Enter JDBC URL (or 'Exit' to quit the program): ");
				String JDBC_URL = scanner.next();

				// Check if the user wants to exit the program
				if (JDBC_URL.equalsIgnoreCase("Exit")) {
					System.out.println("Exited!!!");
					break;
				}

				// Prompt the user to enter the username
				System.out.print("Enter username: ");
				String USERNAME = scanner.next();

				// Prompt the user to enter the password
				System.out.print("Enter Password: ");
				String PASSWORD = scanner.next();

				// Establish connection to the database
				connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

				// Loop to input employee details
				while (true) {
					try {
						System.out.println("Enter the employee details (or '-1' to go Back): ");
						System.out.print("ID: ");
						int id = scanner.nextInt();
						if (id == -1) {
							break;
						}
						System.out.print("Name (String): ");
						String name = scanner.next();
						System.out.print("Age: ");
						int age = scanner.nextInt();
						scanner.nextLine(); // Consume newline character
						System.out.print("Department (String): ");
						String department = scanner.nextLine();

						// SQL query to insert employee data into the database
						String sql = "INSERT INTO employees (id, name, age, department) VALUES (?, ?, ?, ?)";

						// Create a PreparedStatement with the parameterized query
						preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setInt(1, id);
						preparedStatement.setString(2, name);
						preparedStatement.setInt(3, age);
						preparedStatement.setString(4, department);

						// Execute the prepared statement to insert data into the database
						int rowsAffected = preparedStatement.executeUpdate();
						if (rowsAffected > 0) {
							System.out.println("Employee's record inserted successfully!");
						} else {
							System.out.println("Failed to insert employee record.");
						}

					} catch (InputMismatchException e) {
						System.out.println("Error occurred: Please enter numerical values for ID and age!");
						scanner.nextLine(); // Consume the invalid input
					}
				}
			} catch (SQLException e) { // Handling exceptions related to JDBC and SQL credentials
				System.out.println("Error occurred: " + e.getMessage());
			} finally {
				try { // Close all resources if they are not in use
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
		scanner.close(); // Close the Scanner
	}
}
