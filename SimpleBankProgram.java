
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

class Account {
    static String bank_name = "Bank Of India";
    String branch_Name;
    int acc_no;
    String holder_name;
    double balance;
    String holder_address;

    // Constructor to initialize account details
    Account(int acc_no, String branch_Name, String holder_name, double balance, String holder_address) {
        this.branch_Name = branch_Name;
        this.acc_no = acc_no;
        this.holder_name = holder_name;
        this.balance = balance;
        this.holder_address = holder_address;
    }

    // Method to display account details
    public void showAccount() {
        System.out.println("Bank Name: " + bank_name);
        System.out.println("Branch Name: " + branch_Name);
        System.out.println("Account Number: " + acc_no);
        System.out.println("Account Holder name: " + holder_name);
        System.out.println("Account Holder Address: " + holder_address);
        System.out.println("Available Balance: " + balance);
    }

    LocalDateTime cDateTime = LocalDateTime.now();

    // Method to deposit amount
    public void depositAmount(int amount) {
        balance += amount;
        System.out.println("Rs." + amount + " Credited in your Account no.:- " + acc_no + " on " + cDateTime + ":");
    }

    // Method to withdraw amount
    public void withdrawAmount(int amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out
                    .println("Rs." + amount + " Debited from your Account no.:- " + acc_no + " on " + cDateTime + ":");
        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    // Method to check balance
    public void checkBalance() {
        System.out.println("Account no.: " + acc_no + "; Available Balance: " + balance);
    }

}

public class SimpleBankProgram {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, Account> accounts = new HashMap<>();

        while (true) {
            try {
                System.out.println("===============*************===============");
                System.out.println("Welcome to 'Bank Of India' services...");
                System.out.println("1. Create an account");
                System.out.println("2. Show my account");
                System.out.println("3. Check your balance");
                System.out.println("4. Deposit amount");
                System.out.println("5. Withdraw amount");
                System.out.println("6. Exit!");

                System.out.println("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline character
                switch (choice) {
                    case 1:
                        System.out.println("Enter account number: ");
                        int acc_no = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter account holder name: ");
                        String holder_name = sc.nextLine();
                        System.out.println("Enter initial balance: ");
                        int balance = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter account holder address: ");
                        String holder_address = sc.nextLine();
                        System.out.println("Enter your branch name: ");
                        String branch_Name = sc.nextLine();
                        accounts.put(acc_no, new Account(acc_no, branch_Name, holder_name, balance, holder_address));
                        System.out.println("Account created successfully!!!");
                        break;
                    case 2:
                        // Showing account details
                        System.out.println("Enter your account number: ");
                        acc_no = sc.nextInt();
                        if (accounts.containsKey(acc_no)) {
                            accounts.get(acc_no).showAccount();
                        } else {
                            System.out.println("Account not found!");
                        }
                        break;
                    case 3:
                        // Checking account balance
                        System.out.println("Enter your account number: ");
                        acc_no = sc.nextInt();
                        if (accounts.containsKey(acc_no)) {
                            accounts.get(acc_no).checkBalance();
                        } else {
                            System.out.println("Account not found!");
                        }
                        break;
                    case 4:
                        // Depositing amount
                        System.out.println("Enter your account number: ");
                        acc_no = sc.nextInt();
                        if (accounts.containsKey(acc_no)) {
                            System.out.println("Enter deposit amount: ");
                            int amount = sc.nextInt();
                            accounts.get(acc_no).depositAmount(amount);
                        } else {
                            System.out.println("Account not found!");
                        }
                        break;
                    case 5:
                        // Withdrawing amount
                        System.out.println("Enter your account number: ");
                        acc_no = sc.nextInt();
                        if (accounts.containsKey(acc_no)) {
                            System.out.println("Enter withdraw amount: ");
                            int amount = sc.nextInt();
                            accounts.get(acc_no).withdrawAmount(amount);
                        } else {
                            System.out.println("Account not found!");
                        }
                        break;
                    case 6:
                        // Exiting the program
                        System.out.println("Thanks for using our services!!!");
                        sc.close();
                        System.exit(0);
                    default:
                        // Handling invalid choice
                        System.out.println("Invalid choice!!!");
                        break;
                }
            } catch (InputMismatchException e) {
                // Handling input mismatch exceptions
                System.out.println("Error occurred: Please Enter valid inputs!");
                sc.nextLine();// Consume invalid input
            }
        }
    }
}
