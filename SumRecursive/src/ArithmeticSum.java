
// Recursive Method which returns arithmetic series sum based on math formula 

		
public class ArithmeticSum {

															// Recursive method to compute arithmetic series sum
    public static int arithmeticSum(int a, int d, int n) {
        if (n == 1) {
            return a; 										// base case
        }
        return a + arithmeticSum(a + d, d, n - 1); 			// a is term, d is deviation from a term, n is total terms
    }

    public static void main(String[] args) {
        int a = 10;
        int d = 15;
        int n = 20;

        try {																	// method returns based on calling arithmeticSum (10, 15, 20)
            int result = arithmeticSum(a, d, n);								// try catch block for StackOverFlowError when we call method arithmeticSum
            System.out.println("Arithmetic sum : " + result);
        } catch (StackOverflowError e) {
            System.out.println("Stack overflow occurred during recursive sum calculation!");
        }
    }
}

