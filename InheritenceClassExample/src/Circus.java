public class Circus extends GeometricObject {
    private double radius = 5;

    public Circle() {}

    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle(double radius, String colour, boolean filled) {
        super(colour, filled, new java.util.Date()); // Provide a date or modify superclass
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle radius = " + radius + ", " + super.toString();
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public double getDiameter() {
        return 2 * radius;
    }
    public void printCircle() {
        System.out.println("Circle:");
        System.out.println("Radius = " + radius);
        System.out.println("Area = " + getArea());
        System.out.println("Perimeter = " + getPerimeter());
        System.out.println("Diameter = " + getDiameter());
    }
}
