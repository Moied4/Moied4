public class Exercise {
	
			public static void main(String[] args) { 
				
			int min = Integer.parseInt(args[0]);	// configuration arg 100
			int max = Integer.parseInt(args[1]);	// configuration arg 200
		
		System.out.println( min +	(int)	(Math.random()	*(max - min + 1))	);
	}
}