
public class PrintNum implements Runnable{

	private int lastNumber = 50;

	public PrintNum(int lastNumber) {
	
		this.lastNumber = lastNumber;
	}
	
	public void run() {
		
		Thread.currentThread().setPriority(10);
		
	for (int i=1; i< lastNumber; i++)
		System.out.println(i+"  ");
	
	try {
		Thread.sleep(100);
	}catch (Exception e) {}
	}
}
