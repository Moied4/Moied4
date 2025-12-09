public class CircleDriver {
    public static void main(String[] args) {
        // Creating three Circle objects
        Circle c1 = new Circle(); // default radius = 1.0
        Circle c2 = new Circle(2.5);
        Circle c3 = new Circle(4.0);

        // Array of Circle objects
        Circle[] circles = {c1, c2, c3};
        
        //Output in console is based on radius and in circle 1 it is default 1.0
        //For loop run body and increment until we exceed amount of circles
        System.out.println("Using regular for loop:");
        for (int i = 0; i < circles.length; i++) {
            System.out.println("Circle " + (i + 1));
            System.out.println("Radius: " + circles[i].getRadius());
            System.out.println("Area: " + circles[i].getArea());
            System.out.println("Perimeter: " + circles[i].getPerimeter());
            System.out.println();
        }
        //For-each loop executes body for each c and increments count keeping track of Circle objects 
        System.out.println("Using for-each loop:");
        int count = 1;
        for (Circle c : circles) {
            System.out.println("Circle " + count);
            System.out.println("Radius: " + c.getRadius());
            System.out.println("Area: " + c.getArea());
            System.out.println("Perimeter: " + c.getPerimeter());
            System.out.println();
            count++;
        }

        System.out.println("Total number of circles created: " + Circle.getNumberOfCircles());
    }
}

		//Finally we can use get method and print out total number of circles created 
		