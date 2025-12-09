public class Circle {
    private double radius; // instance variable
    private static int numberOfCircles = 0; // static variable to track how many circles have been created

    // Default constructor
    public Circle() {
        this.radius = 1.0;
        numberOfCircles++;
    }

    // Parameterized constructor
    public Circle(double radius) {
        this.radius = radius;
        numberOfCircles++;
    }

    // Getter for radius
    public double getRadius() {
        return radius;
    }

    // Setter for radius
    public void setRadius(double radius) {
        this.radius = radius;
    }

    // Method to calculate area
    public double getArea() {
        return Math.PI * radius * radius;
    }

    // Method to calculate perimeter
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    // Static method to get number of circles created
    public static int getNumberOfCircles() {
        return numberOfCircles;
    }
}