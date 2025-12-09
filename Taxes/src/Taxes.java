	
	import java.util.Scanner;

public class Taxes	{
	
	public static void main(String[] args)	{
		
		System.out.print (" Welcome to Tax Calculator! Press any key to continue...");
		Scanner s = new Scanner(System.in);
		s.next();
		
		double taxRate, rebate, income; 
		
		System.out.print(" Please enter your annual income : ");
		income = s.nextDouble();
	
		char maritalStatus;
		
		System.out.print (" Please enter your marital status (M/S): ");
		maritalStatus = s.next().charAt(0);
	
	taxRate = income < 20000 ? 0.0 : income < 30000 ? 0.05 : income < 50000 ? 0.1 :
				income < 100000 ? 0.25 : 0.35;
	
	rebate = maritalStatus == 's' || maritalStatus == 'S' ? 0.0 :( income < 20000 ? 0.0 :
					income < 30000 ? 0.01 : income < 50000 ? 0.02 :
					income < 100000 ? 0.05 : 0.07);
	
	double tax = income * (taxRate-rebate);
	System.out.printf(" Your income tax is: $%.2f%n", tax);

	
		s.close();
					}
											}
	
				