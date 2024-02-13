public class FibonacciSeries {
  // Static variables to store the initial Fibonacci sequence values
  static int n1 = 0, n2 = 1, n3 = 0;

  // Method to print the Fibonacci series using recursion
  public static void printFibo(int count) {
    // Base case: If count is greater than zero
    if (count > 0) {
      // Calculate the next Fibonacci number
      n3 = n1 + n2;
      n1 = n2;
      n2 = n3;

      // Print the next Fibonacci number
      System.out.print(" " + n3);

      // Reucrsively call printFibo method with count decremented by 1
      printFibo(count - 1);
    }
  }

  // Main method to execute the program
  public static void main(String[] args) {
    // Number of Fibonacci numbers to print
    int count = 10;

    // Print the initial Fibonacci numbers
    System.out.print(n1 + " " + n2);
    // Call the printFibo method to print the remaining Fibonacci numbers
    printFibo(count - 2);// Subtracting 2 as the first two numbers are already printed

  }

}
