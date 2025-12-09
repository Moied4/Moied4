//a variable is like a bag for storing data, assigns certain amount of memory depending on if int byte short long float etc...//
//this program declares variable assigns it value and adds 1 to it with "++" which is operator...//

public class Declare {
		public static void main(String[] args)	{
		
		
		{
			int a = 1;                 	//logic is to declare variable so if int = a and a = a + 1
			a = a + 1;					// then "++" operator will increment value of a = a+1 by 1 which will = 3
			a++;
			System.out.println(a); 
		}
	
		{	
			byte a = 2;					// byte a = (byte) (a+1)
			a = (byte) (a + 1);			// 2+1 incremented "++" operator = 4
			a++;
			System.out.println(a);
		}
			
		{
			short a = 3;
			a = (short) (a + 1);        // 3+1 incremented "++" = 5
			a++;
			System.out.println(a);
		
		}
		
		{	
			long a = 4;
			a = (long) (a + 1);			// 4+1 incremented "++" = 6
			a++;
			System.out.println(a);
		}

		{	float a = 1;
			a = (float) (a + 1);		// a = (float) (1+1) "++" = 3.0 
			a++;						// float is a way to store floating point number (decimal) as opposed to int 
			System.out.println(a);
		
		}
		
		{
			boolean a = true;			// boolean is either true or false 
			System.out.println(a);
		}
		
		{
			char a = 'd';				// stores single character so a=d+1 = e "++" = f
			a = (char) (a + 1);
			a++;
			System.out.println(a);
		}
		
		{	double a = 3.0;				// double data type is like float but can represent much larger numbers but takes up more data 
			a = (double) (a + 2.3);
			a++;
			System.out.println(a);
		}
		
		{	
			String str = "Moied";		//string variable or data type stores text
			System.out.println(str);
		}
		
				}											
												}	
		
			
