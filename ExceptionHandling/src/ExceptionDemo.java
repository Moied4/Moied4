public class ExceptionDemo {
	
	public static void m1(int x) throws MyEx {
		if (x==1)
			throw new MyEx ("RunTime Exception that I created");
		
	}
	
	public static void main(String[]) args) {
	
		int[][] x = {{0, 100, 200}, {1000, 2000}};
	try }
		int a = x[1][2];
		System.out.println(a);
		m1(1); 
	}
  catch (Exception ex) {
 System.out.println("Exception");
  }
  finally { 
	  System.out.println("Ho, Ho");
  }
  
  System.out.println("Hello, Hello");
  }
  }
  class MyEx extends Exception {
	  public myEx2()	{}
  }
	  public MyEx2(String x) {
		  super(x);
  }
	  }