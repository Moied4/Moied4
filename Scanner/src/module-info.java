public class Scanner {
	
			public static void main(String[] args) { 
				
			int min = Integer. parseInt(args[0]);
			int max = Integer. parseInt(args[1]);
		
		System.out.println( min +	(int)	(Math.random()	*(max - min + 1))	);
	}
}