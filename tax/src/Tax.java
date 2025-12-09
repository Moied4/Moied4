	
	import java.util.Scanner;

public class Tax	{
	
	public static void main(String[] args)	{
		
		System.out.print ("Welcome to Tax Calculator! Press any key to continue...");
		Scanner s = new Scanner(System.in);
		s.next();
		
		double income; 
		
		System.out.print("Please enter your annual income : ");
		income = s.nextDouble();
	
		boolean married;
		
		System.out.print ("Are you married? Please enter true if married or false if unmarrried: ");
		married = s.nextBoolean();
	
	if
	
	((income >= 100000) && (married))
	
	{
		
		System.out.println("Your Income Tax is :");
		System.out.print(income*0.28);
		
	} 
	
	else if
	
	((income >= 100000) && (!married))
		
	{
		
		System.out.println("Your Income Tax is :");
		System.out.print(income*0.35);
	
	}
	
	else if
	
	((income < 100000) && (income >= 50000) && (married))
	
	{
		
		System.out.println("Your Income Tax is :");
		System.out.print(income*0.20);
		
	}
	
	else if
	
	((income < 100000) && (income >= 50000) && (!married))
		
	{
		
		System.out.println("Your Income Tax is :");
		System.out.print(income*0.25);
		
	}
	
	else if
	
	(income < 20000)
		 
	{
		
		System.out.println("No Income Tax");
		System.exit(0);

	}
	
	else if
	
	((income < 50000) && (income >= 30000) && (married))
	
	{	
		
		System.out.println("Your Income Tax is :");
		System.out.print(income*0.08);
	
	}
	
	else if
	
	((income < 50000) && (income >= 30000) && (!married))
		
	{
		
		System.out.println("Your Income Tax is :");
		System.out.print(income*0.10);
	
	}
	
	else if
	
	((income < 30000) && (income >= 20000) && (married))
	
	{
		
		System.out.println("Your Income Tax is :");
		System.out.print(income*0.04);
	
	}
	
	else if
	
	((income < 30000) && (income >= 20000) && (!married))
	
	{
		
		System.out.println("Your Income Tax is :");
		System.out.print(income*0.05);
	
	}	s.close();
					}
											}
	
				
	