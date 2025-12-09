public class Driver {
    public static void main(String[] args) {
        Thread tA = new Thread(new PrintChar(20, 'A'));
        Thread tB = new Thread(new PrintChar(20, 'B'));
        Thread tN = new Thread(new PrintNum(20));

        tA.start();
        try {
            Thread.sleep(100); 
        } catch (Exception e) {
           System.out.println("Sleep interrupted");
        }
        tB.start();
        tN.start();

      
        Thread t1 = new Thread(new Summation(4, new Sum()));
        Thread t2 = new Thread(new Summation(5, new Sum()));
        Thread t3 = new Thread(new Summation(6, new Sum()));

        t1.start();
        t2.start();
        t3.start();
    }
}

class PrintChar implements Runnable {
    private int times;
    private char charToPrint;

    public PrintChar(int times, char charToPrint) {
        this.times = times;
        this.charToPrint = charToPrint;
    }

    public void run() {
        System.out.println("\nInitial Priority = " + Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(7);
        System.out.println("New Priority = " + Thread.currentThread().getPriority());

        for (int i = 1; i <= times; i++) {
            System.out.print(charToPrint);
            Thread.yield();
            try {
                Thread.sleep(50);
            } catch (Exception e) {
             System.out.println("sleep interrupted");
            }
        }
        System.out.println();
    }
}

class PrintNum implements Runnable {
    private int lastNumber;

    public PrintNum(int lastNumber) {
        this.lastNumber = lastNumber;
    }

    public void run() {
        Thread.currentThread().setPriority(10);
        for (int i = 1; i <= lastNumber; i++) {
            System.out.print(i + " ");
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                System.out.println("sleep interrupted");
            }
        }
        System.out.println();
    }
}

class Sum {
    private int sum;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}

class Summation implements Runnable {
    private int upper;
    private Sum sumVal;

    public Summation(int upper, Sum sumVal) {
        this.upper = upper;
        this.sumVal = sumVal;
    }

    public void run() {
        int sum = 0;
        for (int i = 0; i <= upper; i++) {
            sum += i;
        }

        sumVal.setSum(sum);
        System.out.println("Sum of 0 to " + upper + " = " + sumVal.getSum());
    }
}
