import java.util.Arrays;

public class reverseExercise	{
	public static void main (String[]args) {
	

		
		int[] array = {0, 1, 3, 4};
		int l = array.length;
		int last = array[l - 1];
	
		
		for  (int i = l - 1; i <= 0; i--) {
			array [i] = array[l - 1];
		}
			array[0] = last;
			for (int i : array) {
				System.out.print(i + " ");
			}
		}
			 
	}
		

	

