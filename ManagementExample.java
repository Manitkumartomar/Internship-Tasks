import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Class representing a product
class Product {
    private String name; // Name of the product
    private double price; // Price of the product
    private int quantity; // Quantity of the product in inventory

    // Getter and setter methods for name, price, and quantity
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Constructor to initialize the product with name, price, and quantity
    Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // toString method to represent the product as a string
    public String toString() {
        return "Product: " + name + ", Price: $" + price + ", Quantity: " + quantity;
    }
}

// Class representing inventory management
class Inventory {
    private Map<String, Product> products; // Map to store products by name

    // Constructor to initialize the inventory with an empty product map
    Inventory() {
        this.products = new HashMap<>();
    }

    // Method to add a product to the inventory
    public void addProduct(Product product) {
        products.put(product.getName(), product);
    }

    // Method to remove a product from the inventory by name
    public void removeProduct(String productName) {
        Product product = products.get(productName);
        if (product != null) {
            products.remove(productName);
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Product not found in inventory.");
        }
    }

    // Method to get a product from the inventory by name
    public Product getProduct(String productName) {
        return products.get(productName);
    }

    // Method to update the quantity of a product in the inventory
    public void updateQuantity(String productName, int newQuantity) {
        Product product = products.get(productName);
        if (product != null) {
            product.setQuantity(newQuantity);
        } else {
            System.out.println("Product not found in inventory.");
        }
    }

    // Method to update the price of a product in the inventory
    public void updatePrice(String productName, double newPrice) {
        Product product = products.get(productName);
        if (product != null) {
            product.setPrice(newPrice);
        } else {
            System.out.println("Product not found in inventory.");
        }
    }

    // Method to display the current inventory
    public void displayInventory() {
        System.out.println("Current Inventory:");
        for (Product product : products.values()) {
            System.out.println(product);
        }
    }
}

// Class demonstrating inventory management
public class ManagementExample {
    public static void main(String[] args) {
        Inventory inventory = new Inventory(); // Create an inventory object

        // Add some initial products to the inventory
        inventory.addProduct(new Product("Laptop", 999.99, 10));
        inventory.addProduct(new Product("Mouse", 19.99, 15));
        inventory.addProduct(new Product("Keyboard", 29.99, 12));

        Scanner sc = new Scanner(System.in); // Scanner object for user input
        while (true) { // Infinite loop for menu-driven program
            try {
                // Display menu options
                System.out.println("--------------------------------");
                System.out.println("----Welcome to an inventory----");
                System.out.println("--------------------------------");
                System.out.println("1. Display inventory");
                System.out.println("2. Update Quantity");
                System.out.println("3. Update Price");
                System.out.println("4. Add new product");
                System.out.println("5. Remove a product");
                System.out.println("6. Exit");

                System.out.println("Enter your choice: "); // Prompt for user choice
                int choice = sc.nextInt(); // Read user choice
                switch (choice) {
                    case 1: // Option to display inventory
                        inventory.displayInventory();
                        break;
                    case 2: // Option to update quantity
                        System.out.println("Enter product name: ");
                        String productName = sc.next();
                        System.out.println("Enter new Quantity: ");
                        int newQuantity = sc.nextInt();
                        inventory.updateQuantity(productName, newQuantity);
                        System.out.println("Quantity updated successfully!");
                        break;
                    case 3: // Option to update price
                        System.out.println("Enter your product name: ");
                        productName = sc.next();
                        System.out.println("Enter new price: ");
                        double newPrice = sc.nextDouble();
                        inventory.updatePrice(productName, newPrice);
                        System.out.println("Price updated successfully!");
                        break;
                    case 4: // Option to add new product
                        System.out.println("Enter new product name: "); 
                        String newProduct = sc.next();
                        System.out.println("Enter new price: ");
                        double newprice = sc.nextDouble();
                        System.out.println("Enter new quantity");
                        int newquantity = sc.nextInt();
                        inventory.addProduct(new Product(newProduct, newprice, newquantity));
                        System.out.println("Product added successfully!");
                        break;
                    case 5: // Option to remove product
                        System.out.println("Enter product name to remove: ");
                        String removeName = sc.next();
                        inventory.removeProduct(removeName);
                        break;
                    case 6: // Option to exit program
                        System.out.println("Exiting program, Goodbye!");
                        sc.close(); // Close scanner
                        System.exit(0); // Exit program
                        break;
                    default: // Handling invalid choice
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            } catch (Exception e) { // Exception handling for input errors
                System.out.println("Error occurred: \n"+
                                  "1. Choice must be an Integer!\n"+
                                  "2. Product name must be String type!\n"+
                                  "3. Quantity and Price must be double or integer type!");
                sc.nextLine(); // Clear scanner buffer
            }
        }
    }
}
