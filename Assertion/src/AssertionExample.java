public class AssertionExample {	//reminder to enable assertions so assert statements are not ignored java -ea AssertionExample//
    public static void main(String[] args) {
        int i = 11;
        int sum = 0;
        int month = 13; // Set to an invalid month to trigger assertion
        String s = "";

        // Calculate the sum of the first i natural numbers
        for (int j = 1; j <= i; j++) {
            sum += j;
        }

        try { //try catch block 
            // Assertion for i
            assert i == 1 : s = "Assertion failed: i is " + i;	//note assert syntax can display custom error message 

            // Assertion for sum range
            assert (sum > 10 && sum < 4 * 10) : s = "Assertion failed: sum is " + sum;

            // Combined assertion
            assert (i == 11) && (sum > 10 && sum <= 45) : 
                s = "Assertion failed: i = " + i + ", sum = " + sum;

            // Assertion for month range
            assert (month >= 1 && month <= 12) : 
                s = "Assertion failed: month = " + month;

            // If all assertions pass the following will print i, sum, month
            System.out.println("i = " + i);
            System.out.println("sum = " + sum);
            System.out.println("month = " + month);
            //This block catches any AssertionError that is thrown inside the corresponding try block
        } catch (AssertionError e) {	
            System.out.println("=== Assertion error occurred ===");
            System.out.println(s + "\n=== Assertion error occurred ===");
            System.out.println("===  Assertion error occurred ===");
        }

        System.out.println("\nProgram finished.");
    }
}
