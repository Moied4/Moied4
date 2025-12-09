package mathOperationsREST;

public class MathOp {
    private int x;
    private int y;
    private int z;
    private int sum;
    private int product;

    // Default constructor 
    public MathOp() {}

    // Constructor that calculates sum and product automatically
    public MathOp(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.sum = calculateSum();
        this.product = calculatePrd();
    }

    // Calculation methods
    public int calculateSum() {
        return x + 2 * y + 3 * z;
    }

    public int calculatePrd() {
        return x * 2 * y * 3 * z;
    }

    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }
    public int getSum() { return sum; }
    public int getProduct() { return product; }

    // Setters
    public void setX(int x) { 
        this.x = x; 
        updateDerivedValues();
    }

    public void setY(int y) { 
        this.y = y; 
        updateDerivedValues();
    }

    public void setZ(int z) { 
        this.z = z; 
        updateDerivedValues();
    }

    // recalc sum and product if x, y, z change
    private void updateDerivedValues() {
        this.sum = calculateSum();
        this.product = calculatePrd();
    }
}
