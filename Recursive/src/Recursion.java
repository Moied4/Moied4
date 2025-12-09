public class Recursion {
	
	public static long factorial(int n) 
	throws StackOverflowError {
		
		if (n==0)
			return 1;									// base condition 
		return n*factorial(n-1);						// self calls
		}
	
		public static void main (String[] args) {
			long result;								// use long in case long numbers 
			try {
	
				result = factorial(4);					// caller
				System.out.println(result);
	
				}
	
			catch (StackOverflowError e) {
			System.out.println("stack overflow");		// stack overflow exception handling in case number too large for stack memory
	
		}
	
	}

}