
abstract class Shape {
    abstract double calculateArea();

    abstract double calculatePerimeter();
}

class Circle extends Shape {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    double calculateArea() {
        return Math.PI * radius * radius;
    }

    double calculatePerimeter() {
        return 2 * (Math.PI) * radius;
    }
}

class Rectangle extends Shape {
    private double length;
    private double breadth;

    Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    double calculateArea() {
        return length * breadth;
    }

    double calculatePerimeter() {
        return 2 * (length + breadth);
    }
}

class Triangle extends Shape {
    private double side1;
    private double side2;
    private double side3;

    Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    double calculateArea() {
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    double calculatePerimeter() {
        return (side1 + side2 + side3);
    }
}

public class GeometryExample {
    public static void main(String[] args) {
        Circle obj1 = new Circle(7);
        Rectangle obj2 = new Rectangle(2.5, 14);
        Triangle obj3 = new Triangle(10, 11, 12);

        System.out.println("Circle - Area: " + obj1.calculateArea() + ", Perimeter: " + obj1.calculatePerimeter());
        System.out.println("Rectangle - Area: " + obj2.calculateArea() + ", Perimeter: " + obj2.calculatePerimeter());
        System.out.println("Triangle - Area: " + obj3.calculateArea() + ", Perimeter: " + obj3.calculatePerimeter());
    }
}
