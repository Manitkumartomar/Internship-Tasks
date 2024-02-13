//Abstract class Shape representing a geometric shape
abstract class Shape {
    // Abstract method to calculate the Area of the shape
    abstract double calculateArea();

    // Abstract method to calculate the Primeter of the shape
    abstract double calculatePerimeter();
}

// Class Circle representing a circle shape, inheriting from Shape
class Circle extends Shape {
    private double radius; // Radius of the circle

    // Constructor to initialize the circle with a given radius
    Circle(double radius) {
        this.radius = radius;
    }

    // Method to calculate the area of the circle
    double calculateArea() {
        return Math.PI * radius * radius;// Area formula for a circle
    }

    // Method to calculate the perimeter of the circle
    double calculatePerimeter() {
        return 2 * (Math.PI) * radius;// Perimeter formula for a circle
    }
}

// Class Rectangle representing a rectangle shape, inheriting from Shape
class Rectangle extends Shape {
    private double length;// Length of the rectangle
    private double breadth;// Breadth of the rectangle

    // Constructor to initialize the rectangle with given length and breadth
    Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    // Method to calculate the area of the rectangle
    double calculateArea() {
        return length * breadth;// Area formula for rectangle
    }

    // Method to calculate the perimeter of the rectangle
    double calculatePerimeter() {
        return 2 * (length + breadth);// Perimeter formula for rectangle
    }
}

// Class Triangle representing a triangle shape, inheriting from Shape
class Triangle extends Shape {
    private double side1; // Length of side 1
    private double side2; // Length of side 2
    private double side3; // Length of side 3

    // Constructor to initialize the triangle with given side lengths
    Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    // Method to calculate the area of the triangle using Heron's formula
    double calculateArea() {
        double s = (side1 + side2 + side3) / 2;// Semi-perimeter
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));// Heron's formula
    }

    // Method to calculate the perimeter of the triangle
    double calculatePerimeter() {
        return (side1 + side2 + side3);// Perimeter formula for triangle
    }
}

// Class to demonstrate geometric shapes and their calculations
public class GeometryExample {
    public static void main(String[] args) {
        // Creating instances of Circle, Rectangle, and Triangle
        Circle obj1 = new Circle(7);
        Rectangle obj2 = new Rectangle(2.5, 14);
        Triangle obj3 = new Triangle(10, 11, 12);

        // Printing the area and perimeter of each shape
        System.out.println("Circle - Area: " + obj1.calculateArea() + ", Perimeter: " + obj1.calculatePerimeter());
        System.out.println("Rectangle - Area: " + obj2.calculateArea() + ", Perimeter: " + obj2.calculatePerimeter());
        System.out.println("Triangle - Area: " + obj3.calculateArea() + ", Perimeter: " + obj3.calculatePerimeter());
    }
}
