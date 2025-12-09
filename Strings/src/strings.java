public class strings {

	public static String smallerFirstLetter(String a, String b) {
		if  ( a.charAt(0) > b.charAt(0)) 
				return b;
		 else if ( a. charAt(0) == b.charAt(0))  
			 if (a.length() > b.length())
			return b;
			 
			return a;
		
			}	

	

	public static void main(String[] args)	{
	
		System.out.println(smallerFirstLetter("moied" , "Moieds"));
	}
}