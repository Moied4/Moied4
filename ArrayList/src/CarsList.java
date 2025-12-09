
import java.util.*;

public class CarsList {
	
	public static void main(String [] args) {
		
		ArrayList<String> cars = new ArrayList<>();
		System.out.println(cars.size());
		cars.add("Volvo");
		cars.add("GM");
		cars.add(0, "BMW");
		System.out.println(cars.size());
		System.out.println(cars.contains("BMW"));
		System.out.println(cars.get(0));
		System.out.println(cars.indexOf("Volvo"));
		System.out.println(cars.isEmpty());
		System.out.println(cars.add("Volvo"));
		cars.remove(cars.lastIndexOf("Volvo"));
		
		cars.set(0, "Audi");
		
		for (String x: cars) System.out.println(x);
		System.out.println(cars.size());
		
		Collections.sort(cars);
		for(String x: cars) System.out.println
	}
}