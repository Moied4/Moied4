
import java.util.*;
import java.math.*;

public class StringTest {
	
	public static void main(String[] args) {
		
		StringBuilder stringBuilder = new StringBuilder("Welcome");
		
		System.out.println(stringBuilder.insert(1, "HaHa"));
		
		System.out.println(stringBuilder.append("HaHaHaHa"));
	}
}