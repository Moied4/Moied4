	import java.util.Scanner;

public class Program	{
	
	public static void main( String[] args ) {
	Scanner s = new Scanner (System.in); 
				
	}
							
	
	
			
			 public static double doTax (Scanner s) {
					double tax = 0,  taxRate, rebate, income;
					int maritalStatus;
					
	System.out.println (" tax : " + tax) ;
	
	System.out.print(" Enter your annual income: ");
	income = s.nextDouble(); 
	
	System.out.print(" Enter your marital status (1: Single, 2: Married): ");
	maritalStatus = s.nextInt(); 
	
	 
	

	taxRate = income < 20000 ? 0.0 : income < 30000 ? 0.05 : income < 50000 ? 0.1 :
			income < 100000 ? 0.25 : 0.35;

	
	rebate = maritalStatus == 1 ? 0.0 : income < 20000 ? 0.0 :
				income < 30000 ? 0.01 : income < 50000 ? 0.02 :
				income < 100000 ? 0.05 : 0.07;
	
 tax = income * (taxRate-rebate);

 return tax;
			 
					}
											}
	





		 
				
	 public static repeatMethod() {
	
	public static void main( String[] args ) {
		
		int i = 0;
		while (i < 3) {
			i++;
		}
			repeatMethod();
	
	
	}
	
	