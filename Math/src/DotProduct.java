import java.util.*;														// imported java package 

public class DotProduct	{												// public is access modifier, public class is accessible from any other class, file name must equal public class name 

	public static int  dotProduct(int[] x, int[]y) {					// main method, public static means the member belongs to the class rather than instances of the class,
		int result = 0; 												// dotProduct of arrays x and y of variable integer and initialize integer 0 for index of arrays
	
		if (x.length !=y.length) {										// if condition for array x and y lengths being unequal 
			System.out.println("Can't multiply");						// otherwise return result based on aforementioned index 0 
			return result;
		}
																		// for loop for running through array x starting from index 0 incremented until x length 
			for (int i=0; i < x.length; i++) {							// result += operator increments the value of result by the operation x[i] * y[i] 
			result += x[i] * y[i];										
			
			}		
			
			return result;												// method returns result 
		}

		public static int[][] multiplyMatrix(int[][]x, int[][]y) {		// integer array of arrays  matrix multiplication
			int[][] r = new int[x.length][y[0].length];					// r will store number of rows in matrix x multiply with number of columns in matrix y

			for (int i=0; i < x.length; i++) {							// increment through length of x start index 0,1,2,n
				for (int j = 0; j < y[0].length; j++) {					// increment int j through number of columns in first row index y[0]
					r[i][j] = dotProduct (x[i],  getColumn(y, j));		// dotProduct is i row of matrix x multiply with j column matrix y and store result in i row and j column of r 
				}
			
			}
		
			return r;													//  return r 
		}

		public static int[] getColumn (int[][] x, int c) {				//	method to get single column from matrix
			int [] col = new int [ x.length ];							//	matrix with one element per row of matrix x  values in 1 column are 1 per row 
	
			for (int i=0; i < x.length; i++) {							//	loop through every row in matrix x 
				col[i] = x[i][c]; 										//	go to row i of matrix x and get value in column c so col holds value of column c in matrix x
			}
			
			return col;													// return said columns as array

		}


		public static void main(String[] args) {							
			int[][] a = { {1, 3, 5}, {1, 4, 1} };					//	create 2 x 3 matrix called a
			int[][] b = { {2, 0, 1}, {0, 2, 0}, {3, 4, 1} };		// create 3 x 3 matrix called b
						
			int[][] result = multiplyMatrix(a, b);								//	call the method to multiply a and b and store in result 
			System.out.println(Arrays.deepToString(multiplyMatrix (a, b)) );	//  print the string representation of the deep array
				
		}

}