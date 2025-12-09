import java.util.Arrays;

public class StoreArrays {
    
	 public static void main(String[] args) {
		 final int AMOUNT_STORES = 2;
		 final int AMOUNT_FRUITS = 3;
		 final int AMOUNT_VALUES = 2;
		 final double[][] DEMAND = { {100, 150, 200}, {120, 130, 180} };
			
		 double[][][] values = new double[AMOUNT_STORES][AMOUNT_FRUITS][AMOUNT_VALUES];
		 
		 initialize(values);
	 
		 double store_profit = 0, total_profit = 0;
	 
		   
		    for (int i = 0; i < values.length; i++) {
		    	store_profit = 0;
		    	for (int j = 0; j < values[i].length; j++) {
		    		store_profit += (values[i][j][1] - values[i][j][0]) * DEMAND[i][j];
		    	}
		    	
 				total_profit  += store_profit;
 				System.out.printf("Store" + (i+1) + " profit = " + "%.2f", store_profit);
 				System.out.println();
		    }

		    System.out.printf("Total profit = " + "$%.2f", total_profit);

	 }

	 public static void initialize(double[][][]values) {
		 final double MARK_UP = 0.2;
		 final double ORANGE_LB_COST = 2;
		 final double APPLE_LB_COST = 1;
		 final double GRAPES_LB_COST = 3;
		 
		 double[] orange_cost_price = {ORANGE_LB_COST, ORANGE_LB_COST*(1 + MARK_UP)};
		 double[] apple_cost_price = {APPLE_LB_COST, APPLE_LB_COST*(1 + MARK_UP)};
		 double[] grape_cost_price = {GRAPES_LB_COST, GRAPES_LB_COST*(1 + MARK_UP)};
		 double[][] fruits = {orange_cost_price, apple_cost_price, grape_cost_price};
		 for (int i = 0; i < values.length; i++) 
		 values[i] = fruits;
	}
}