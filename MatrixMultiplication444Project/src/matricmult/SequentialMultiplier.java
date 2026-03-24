package matricmult;
/**
 * 
 * Baseline class
 * Will use standard triple-nested loop algorithm
 * O(n^3) complexity
 * (Compared against parallel process
 * 
 */
public class SequentialMultiplier {
	// Store the result in a volatile variable to prevent dead code elimination
	static volatile int result;
	
	public double multiply(int[][] a, int[][] b) {
		int rowsA = a.length;
		int colsA = a[0].length;
		int rowsB = b.length;
		int colsB = b[0].length;
		int[][] output;
		
		
		if(colsA != rowsB) {
			System.out.println("The matrices cannot be multiplied: Incorrect Dimensions!");
			return 0;
		} else {
			output = new int[rowsA][colsB];
	        // Start timer
	        long startTime = System.nanoTime();
	        // Matrix Multiplication
	        for (int i = 0; i < rowsA; i++) {
	            for (int j = 0; j < colsB; j++) {
	                int sum = 0;
	                for (int k = 0; k < colsA; k++) {
	                    sum += a[i][k] * b[k][j];
	                }
	                output[i][j] = sum;
	            }
	        }
	        result = output[0][0];
	        // Stop timer
	        long endTime = System.nanoTime();
	        double seconds = (endTime - startTime) / 1_000_000_000.0;        
	        return seconds;
		}
    }
}
