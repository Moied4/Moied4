
public class PrintChar implements Runnable {
	private int times = 50;
	private char charToPrint;

	public PrintChar(int times, char charToPrint) {
	
		this.times=times;
		this.charToPrint = charToPrint;
	}
	
	public void run() {
	System.out.println("\nPriority = " + Thread.currentThread().getPriority());
		Thread.currentThread().setPriority(7);
	System.out.println("\nPriority = " + Thread.currentThread().getPriority());
		
	for (int i=1; i<times; i++)
		System.out.println(charToPrint);
		Thread.yield();
		
		try {
			Thread.sleep(100);
		}catch (Exception e) {}
		
	}
}
