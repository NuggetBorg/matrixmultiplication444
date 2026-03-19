package matricmult;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Implements parallelism
 * 
 */
public class ParallelMultiplier {

	final static int THREAD_COUNT = 4;
	
	static int rowsA;
	static int colsA;
	static int rowsB;
	static int colsB;
	static int[][] a;
	static int[][] b;
	static int[][] output;

	public static int[][] multiply(int[][] matA, int[][] matB) {
		
		//List<Thread> threads = new ArrayList<>();
		Thread[] threads = new Thread[THREAD_COUNT];
		a = matA;
		b = matB;
		rowsA = a.length;
		colsA = a[0].length;
		rowsB = b.length;
		colsB = b[0].length;
		output = new int[rowsA][colsB];
		// Start timer
        long startTime = System.nanoTime();
		if(colsA != rowsB) {
			System.out.println("The matrices cannot be multiplied: Incorrect Dimensions!");
			return new int[0][0];
		} else {
			
			// Create a thread for each row of the result matrix
	        for (int i = 0; i < THREAD_COUNT; i++) {
	            // Use a final variable for the row index for use within the Runnable
	            final int row = i; 
	            Runnable task = new MatrixRowMultiplier(row);
	            Thread thread = new Thread(task);
	            threads[i] = thread;
	            thread.start();
	        }

	        // Wait for all threads to complete using join()
	        for (Thread thread : threads) {
	            try {
					thread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		}
		// Stop timer
        long endTime = System.nanoTime();
        double seconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("Parallel Time: " + seconds);
		return output;
    }
	
	
	static class MatrixRowMultiplier implements Runnable {
        private final int rowToCompute;

        MatrixRowMultiplier(int rowToCompute) {
            this.rowToCompute = rowToCompute;
        }

        @Override
        public void run() {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    output[rowToCompute][j] += a[rowToCompute][k] * b[k][j];
                }
            }
        }
    }
	
	
}

