	
	import java.util.Scanner;

public class Grade	{
	
	public static void main(String[] args)	{
		
		double grade; 
		Scanner s = new Scanner(System.in);
		
	System.out.print("Please enter a grade value (0 to 100): ");
	grade = s.nextDouble();
	if (grade < 0 || grade > 100)
	 {
	System.out.println("Wrong grade, Terminated");
	System.exit(1);
	 }
	if (grade >= 90) System.out.println("A");
	else if (grade >=80) System.out.println("B");
	else if (grade >=70) System.out.println("C");
	else if (grade >=60) System.out.println("Pass");
	else System.out.println("Fail");
	}

}