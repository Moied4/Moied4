 import java.util.Arrays;

public class IntersectArrays {
    
																// declare Method to find intersection of two integer arrays
    public static int[] findIntersection(int[] x, int[] y) {
        int[] temp = new int[Math.min(x.length, y.length)];        // the intersection of x and y minimum of the length of the two arrays 
        int index = 0;                                             // initialize index integer variable store 0 

        																
        															// Loop through the first array start at var index 0 and increment until length of x 0 1 2 3
        for (int a = 0; a < x.length; a++) {
        																// Loop through the second array start at var index 0 and increment until length of y 0 1 2 3
            for (int b = 0; b < y.length; b++) {
            															// Check if x match y and break loop if and once condition true add value to a temporary array increment its size
                if (x[a] == y[b]) {
                    temp[index++] = x[a]; 								
                    break; 						
                }
            }
        }

        																// Return  array using copyOf utility, containing the matching element with pertinent index of x (intersection array)
        return Arrays.copyOf(temp, index);
    }
    																	 
    																	// initialize  arrays x and y arrays of integer variables
    public static void main(String[] args) {
        int[] x = {0, 1, 3, 4};
        int[] y = {3, 1, 2, 4};
        																
        																
        int[] intersection = findIntersection(x, y);						// take intersection of x and y integer array and turn to string to print out in console 
        
        																
        System.out.println("Intersection: " + Arrays.toString(intersection));
    }
}

