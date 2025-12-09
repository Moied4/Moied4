
import java.util.*;

public class GenExample {

//declaring generic method meaning a method accepts and returns any type...
	
	public static <T> T get(T value) {
		return value;
	}


	
	public static void main(String[] args) {
		Myclassd d = new Myclassd(99.9);
		Myclassi i = new Myclassi(1);
		Myclassg<String> g = new Myclassg<>("Generics");

		Myclassg<Double> e = new Myclassg<>(2.2);
		
	
	 System.out.println("Double: " + d.getA());
     System.out.println("Integer: " + i.getA());
     System.out.println("Generic: " + g.get_a());
     
     System.out.println("Generic: " + e.get_a());

     // Calling the generic method 
     String result = get("Generic Method Call");
     System.out.println("Generic Method: " + result);
	}
}

//class 1 returns double

class Myclassd	{
		private double a;

		Myclassd(double a) {	
			this.a = a;
			
		}
	public double getA() {  
		
		return a;
	}
	
}


//class 2 returns integer

class Myclassi {
		private int a;
	
	Myclassi(int a) {
		this.a = a;
	}
	public int getA() {
		
		return a;
	}
	}

//class 3 generic

	 class Myclassg<T> {
		public T a;
		
		Myclassg(T x) {
			this.a = x;
		}
		
		public T get_a() {
			return a;
		}
	}