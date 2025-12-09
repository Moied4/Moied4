public class reverseStrings	{

	public static String reverseString (String a) {
		
		String r = "";
		
		for  (int j= a.length() - 1; j >= 0; j--)r=r + a.charAt(j);
			
		return r;
			 
		
		
		}	

	

	public static void main(String[] args)	{
		
		System.out.println(reverseString("Moied"));
		
	}
}