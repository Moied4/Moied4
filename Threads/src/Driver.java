 
public class Driver {
	public static void main(String [] args) {
		
		
		Thread tA = new Thread(new PrintChar (20, 'A'));
		Thread tB = new Thread(new PrintChar (20, 'B'));
		Thread tN = new Thread(new PrintNum(20));
		
		tA.start();
		tB.start();
		tN.start();
		
		tA.start();
		try {
			Thread.sleep(100);
		}catch(Exception e) {}
		tB.start();
		}
	
}
