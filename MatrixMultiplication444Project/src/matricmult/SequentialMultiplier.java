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
	public int[][] multiply(int[][] a, int[][] b) {
		System.out.println("");
		int rowsA = a.length;
		int colsA = a[0].length;
		int rowsB = b.length;
		int colsB = b[0].length;
		
		if(colsA != rowsB) {
			System.out.println("The matrices cannot be multiplied: Incorrect Dimensions!");
			return new int[0][0];
		} else {
			int[][] output = new int[rowsA][colsB];
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
	        
	        // Stop timer
	        long endTime = System.nanoTime();
	        double seconds = (endTime - startTime) / 1_000_000_000.0;
	        System.out.println("Sequential Time: " + seconds);
	        
	        return output;
		}
    }
}
