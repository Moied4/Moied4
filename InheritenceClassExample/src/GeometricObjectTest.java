import java.util.Date;

// Superclass
class GeometricObject {
    private String colour;
    private boolean filled;
    private Date dateCreated;

    public GeometricObject() {
        this.dateCreated = new Date();
    }

    public GeometricObject(String colour, boolean filled, Date dateCreated) {
        this.colour = colour;
        this.filled = filled;
        this.dateCreated = dateCreated;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public String toString() {
        return "colour = " + colour + ", filled = " + filled + ", dateCreated = " + dateCreated;
    }
}

// Rectangle subclass
class Rectangle extends GeometricObject {
    private double width = 1.0;
    private double height = 1.0;

    public Rectangle() {}

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle(double width, double height, String colour, boolean filled) {
        super(colour, filled, new Date());
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    public void printRectangle() {
        System.out.println(this);
        System.out.println("Area = " + getArea());
        System.out.println("Perimeter = " + getPerimeter());
    }

    @Override
    public String toString() {
        return "Rectangle width = " + width + ", height = " + height + ", " + super.toString();
    }
}

// Circle subclass
class Circle extends GeometricObject {
    private double radius = 1;

    public Circle() {}

    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle(double radius, String colour, boolean filled) {
        super(colour, filled, new Date());
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
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
        System.out.println(this);
        System.out.println("Area = " + getArea());
        System.out.println("Perimeter = " + getPerimeter());
        System.out.println("Diameter = " + getDiameter());
    }

    @Override
    public String toString() {
        return "Circle radius = " + radius + ", " + super.toString();
    }
}

// Driver class
public class GeometricObjectTest {
    public static void main(String[] args) {
        Circle circle = new Circle(5.0, "Red", true);
        Rectangle rectangle = new Rectangle(4.0, 6.0, "Blue", false);

        System.out.println("=== Circle ===");
        circle.printCircle();
        System.out.println();

        System.out.println("=== Rectangle ===");
        rectangle.printRectangle();
        System.out.println();

        System.out.println("=== Polymorphic Output ===");
        GeometricObject[] shapes = {circle, rectangle};
        for (GeometricObject shape : shapes) {
            System.out.println(shape.toString());
        }
    }
}
