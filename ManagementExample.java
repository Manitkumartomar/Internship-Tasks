import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Product {
    private String name;
    private double price;
    private int quantity;

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

    Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String toString() {
        return "Product: " + name + ", Price: $" + price + ", Quantity: " + quantity;
    }
}

class Inventory {
    private Map<String, Product> products;

    Inventory() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        products.put(product.getName(), product);
    }

    public void removeProduct(String productName) {
        products.remove(productName);
    }

    public Product getProduct(String productName) {
        return products.get(productName);
    }

    public void updateQuantity(String productName, int newQuantity) {
        Product product = products.get(productName);
        if (product != null) {
            product.setQuantity(newQuantity);
        } else {
            System.out.println("Product not found in inventory.");
        }
    }

    public void updatePrice(String productName, int newPrice) {
        Product product = products.get(productName);
        if (product != null) {
            product.setPrice(newPrice);
        } else {
            System.out.println("Product not found in inventory.");
        }
    }

    public void displayInventory() {
        System.out.println("Current Inventory:");
        for (Product product : products.values()) {
            System.out.println(product);
        }
    }
}

public class ManagementExample {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        inventory.addProduct(new Product("Laptop", 999.99, 10));
        inventory.addProduct(new Product("Mouse", 19.99, 15));
        inventory.addProduct(new Product("Keyboard", 29.99, 12));

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Display inventory");
            System.out.println("2. Update Quantity");
            System.out.println("3. Update Price");
            System.out.println("4. Add new product");
            System.out.println("5. Remove a product");
            System.out.println("6. Exit");

            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    inventory.displayInventory();
                    break;
                case 2:
                    System.out.println("Enter product name: ");
                    String productName = sc.next();
                    System.out.println("Enter new Quantity: ");
                    int newQuantity = sc.nextInt();
                    inventory.updateQuantity(productName, newQuantity);
                    break;
                case 3: 
                    System.out.println("Enter your product name: ");
                    String productname = sc.next();
                    System.out.println("Enter new price: ");
                    int newPrice = sc.nextInt();
                    inventory.updatePrice(productname, newPrice);
                    break;
                case 4:
                    System.out.println("Enter new product name: "); 
                    String newProduct = sc.next();
                    System.out.println("Enter new price: ");
                    double newprice = sc.nextInt();
                    System.out.println("Enter new quantity");
                    int newquantity = sc.nextInt();
                    inventory.addProduct(new Product(newProduct, newprice, newquantity));
                    System.out.println("Product added successfully!");
                    break;
                case 5:
                    System.out.println("Enter product name to remove: ");
                    String removeName = sc.next();
                    inventory.removeProduct(removeName);
                    System.out.println("Product removed successfully.");
                    break;
                case 6:
                    System.out.println("Exiting program, Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    sc.close();
                    break;
            }
        }
    }
}
