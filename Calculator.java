import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first number");
        double n1 = sc.nextDouble();
        System.out.println("Enter the second number");
        double n2 = sc.nextDouble();
        System.out.println("Choose an operation: +, -, *, /");
        char oper = sc.next().charAt(0);
        sc.close();

        double result = 0;

        switch (oper) {
            case '+':
                result = n1 + n2;
                break;
            case '-':
                result = n1 - n2;
                break;
            case '*':
                result = n1 * n2;
                break;
            case '/':
                if (n2 != 0) {
                    result = n1 / n2;
                } else {
                    System.out.println("Error: Division by zero is not allowed");
                    return;
                }
                break;

            default:
                System.out.println("Error: Invalid operation");
                break;
        }
                System.out.println("Result : " + result);
    }
}