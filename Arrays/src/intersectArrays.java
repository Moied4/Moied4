import java.util.Arrays;

public class intersectArrays {
    
																// Method to find intersection of two arrays
    public static int[] findIntersection(int[] x, int[] y) {
        int[] temp = new int[Math.min(x.length, y.length)];
        int index = 0;

        																// Loop through the first array
        for (int a = 0; a < x.length; a++) {
        																// Loop through the second array
            for (int b = 0; b < y.length; b++) {
            															// Check if the elements match
                if (x[a] == y[b]) {
                    temp[index++] = y[b]; 								// Add the matching elements to the intersection array
                    break; 												// Break loop once  match of a and b is found
                }
            }
        }

        																// Return array containing the matching elements (intersection array)
        return Arrays.copyOf(temp, index);
    }

    public static void main(String[] args) {
        int[] x = {0, 1, 3, 4};
        int[] y = {3, 1, 2, 4};
        
        																// Find the intersection of the two arrays
        int[] intersection = findIntersection(x, y);
        
        																// Print intersection
        System.out.println("Intersection: " + Arrays.toString(intersection));
    }
}


    
    


		

	

