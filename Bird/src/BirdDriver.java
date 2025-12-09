// BirdDriver creates an object of Bird and calls its method

//driver class is to execute a method containing an Object

public class BirdDriver {
	public static void main(String[] args) {
		Bird myBird = new Bird();	//object created 
		myBird.name = " Tucan Sam ";
		myBird.age = 3;
		myBird.fly();	//method being called
	}
}

//basically prints "bird is flying:' when fly() is called