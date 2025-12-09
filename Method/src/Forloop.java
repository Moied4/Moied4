	import java.util.Scanner;

public class Forloop	{
	
public static double doMethod (Scanner s)   {
		double tax, taxRate, rebate, income;
			int maritalStatus;
	
	System.out.print(" Please Enter your annual income: ");
	income = s.nextDouble(); 
	
	System.out.print(" Please Enter your marital status (1: Single, 2: Married): ");
	maritalStatus = s.nextInt(); 
	
	taxRate = income < 20000 ? 0.0 : income < 30000 ? 0.05 : income < 50000 ? 0.1 :
			income < 100000 ? 0.25 : 0.35;

	rebate = maritalStatus == 1 ? 0.0 : income < 20000 ? 0.0 :
				income < 30000 ? 0.01 : income < 50000 ? 0.02 :
				income < 100000 ? 0.05 : 0.07;
	
tax = income * (taxRate-rebate);
System.out.printf (" Your income tax is: " + tax) ;
return tax;
	}
 
 public static void repeatMethod(Scanner s) {
		
	for (int count = 0; count < 3; count++) {
		
		
	System.out.println( " Welcome to Tax Calculator Person : "  + (count + 1));
	doMethod (s);
	 System.out.println ("     Thank you Come Again ! ");
		
	}
												
		
		}
 
 public static void main(String[] args) {
	 Scanner s = new Scanner(System.in);
	 
	 repeatMethod(s);

	 s.close();				}
 
					}
			 
			 
			 
