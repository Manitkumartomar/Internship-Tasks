import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class JDBCDataValidation {

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_DEPARTMENT = "department";

    public static void main(String[] args) {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);

                // Prompting user to enter JDBC URL
                System.out.print("Enter JDBC_URL (or 'Exit' to quit): ");
                String URL = scanner.nextLine();
                if (URL.equalsIgnoreCase("Exit")) {
                    System.out.println("Exiting!!!");
                    break;
                }

                // Prompting user to enter username and password
                System.out.print("Enter USERNAME: ");
                String USERNAME = scanner.nextLine();
                System.out.print("Enter PASSWORD: ");
                String PASSWORD = scanner.nextLine();
                System.out.println("=========================");

                // Establishing database connection
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Connected to the database.");
                System.out.println("-------------------------");

                // Menu for CRUD operations
                while (true) {
                    System.out.println("1. Insert new record");
                    System.out.println("2. Update existing record");
                    System.out.println("3. Delete existing record");
                    System.out.println("4. Exit");
                    System.out.print("Enter your choice: ");
                    String choice = scanner.nextLine();

                    switch (choice) {
                        case "1":
                            insertRecord(connection, scanner);
                            break;
                        case "2":
                            updateRecord(connection, scanner);
                            break;
                        case "3":
                            deleteRecord(connection, scanner);
                            break;
                        case "4":
                            System.out.println("Exiting...");
                            connection.close(); // Closing the database connection
                            scanner.close(); // Closing the scanner
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } catch (SQLException e) {
                // Handle SQL exceptions
                System.out.println("SQL Exception occurred: " + e.getMessage());
            } catch (InputMismatchException e) {
                // Handle other exceptions
                System.out.println("Exception occurred: Invalid input!");
            }
        }
    }

    // Method to insert a new record
    private static void insertRecord(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter table name: ");
        String TABLE_NAME = scanner.nextLine();
        System.out.println("Inserting new record...");
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        // Perform data validation
        if (name.isEmpty() || department.isEmpty()) {
            System.out.println("Name and department cannot be empty.");
            return;
        }

        // Prepare SQL statement
        String sql = "INSERT INTO " + TABLE_NAME + "(" + COLUMN_ID + ", " + COLUMN_NAME + "," + COLUMN_AGE + ","
                + COLUMN_DEPARTMENT + ") VALUES (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.setString(2, name);
        statement.setInt(3, age);
        statement.setString(4, department);

        // Execute the statement
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("New record inserted successfully.");
        } else {
            System.out.println("Failed to insert new record.");
        }
    }

    // Method to update an existing record
    private static void updateRecord(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter table name: ");
        String TABLE_NAME = scanner.nextLine();
        System.out.println("Updating existing record...");
        System.out.print("Enter ID of the record to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new department: ");
        String department = scanner.nextLine();

        // Prepare SQL statement
        String sql = "UPDATE " + TABLE_NAME + " SET " + COLUMN_NAME + "=?, " + COLUMN_DEPARTMENT + "=? WHERE "
                + COLUMN_ID + "=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, department);
        statement.setInt(3, id);

        // Execute the statement
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Record updated successfully.");
        } else {
            System.out.println("Failed to update record. Record with ID " + id + " not found.");
        }
    }

    // Method to delete an existing record
    private static void deleteRecord(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter table name: ");
        String TABLE_NAME = scanner.nextLine();
        System.out.println("Deleting existing record...");
        System.out.print("Enter ID of the record to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Prepare SQL statement
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        // Execute the statement
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Record deleted successfully.");
        } else {
            System.out.println("Failed to delete record. Record with ID " + id + " not found.");
        }
    }
}
