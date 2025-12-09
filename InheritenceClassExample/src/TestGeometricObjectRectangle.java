import java.util.Date;

public class TestGeometricObjectRectangle {

    public static void main(String[] args) {
        // Test GeometricObject
        GeometricObject g = new GeometricObject();
        System.out.println(g);
        System.out.println("Colour: " + g.getColour());
        g.setColour("Blue");

        // Test Rectangle default constructor
        Rectangle r1 = new Rectangle();
        System.out.println(r1);
        System.out.println("Colour: " + r1.getColour());

        // Cast Rectangle to GeometricObject
        GeometricObject gr;
        gr = (GeometricObject) r1;
        System.out.println();

        // Test Rectangle with full constructor
        Rectangle r2 = new Rectangle(4.5, 3.2, "Green", true);
        System.out.println(r2);
        System.out.println();

        // Print rectangle details
        System.out.println("Printing via printRectangle():");
        r2.printRectangle();

        // Area rounded to 2 decimal places
        double roundedArea = Math.round(r2.getArea() * 100) / 100.0;
        System.out.println("Rounded area = " + roundedArea);
        
        
    }
}




GeometricObject[ ] objects = {circle, rectangle};
for (GeometricObject x : objects) {
     System.out.println("\nObject; " + x);
     System.out.println("Area; " + String.format(format; ";%.2f", x.getArea()));
     if (x instanceof Circle) { 
        Circle c = (Circle) x;
        System.out.println("Diameter; " + c.getDiameter());
        System.out.println("Area; " + c.getArea()); 
} else if (x instanceof Rectangle) {
        Rectangle r = (Rectangle) x;
        System.out.orintlkn("Perimeter; " + r.getPerimeter());

        System.out.println("Area; " +r.getArea());

}
}
}
}
       

