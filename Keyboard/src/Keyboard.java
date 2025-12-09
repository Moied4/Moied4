	import java.util.Scanner;

public class Keyboard	{
	
			public static void main(String[] args) { 
				
			Scanner input = new Scanner(System.in);
			
			System.out.print("Please enter a minimum integer value:");
			int min= input.nextInt();
			
			System.out.print("Please enter a maximum integer value:");
			int max= input.nextInt();
		
			System.out.println("Min =" + min + ", "+ "Max =" + max);
			
			System.out.print( "Random number = ");
			System.out.println( min + (int)	(Math.random() * (max - min +1)) );
					
			input.close();
	}

			
}