package matricmult;

/**
 * 
 * Performs matrix multiplication across multiple threads
 * Static to allow concurrency within the class
 * 
 */
public class ParallelMultiplier {
	// Store the result in a volatile variable to prevent dead code elimination
	static volatile int result;
	static int THREAD_COUNT;
	
	static int rowsA;
	static int colsA;
	static int rowsB;
	static int colsB;
	static int[][] a;
	static int[][] b;
	static int[][] output;

	public static double multiply(int[][] matA, int[][] matB, int threadCount) {
		THREAD_COUNT = threadCount;
		//List of threads
		Thread[] threads = new Thread[THREAD_COUNT];
		// Matrices
		a = matA;
		b = matB;
		// Matrix dimensions
		rowsA = a.length;
		colsA = a[0].length;
		rowsB = b.length;
		colsB = b[0].length;
		//Output matrix
		output = new int[rowsA][colsB];
		
		// Start timer
        long startTime = System.nanoTime();
        
        // Ensure matrices are valid for multiplication
		if(colsA != rowsB) {
			System.out.println("The matrices cannot be multiplied: Incorrect Dimensions!");
			return 0;
		} else { // If the matrices are valid
			
			//Calculate # rows per thread
			int rowsPerThread = rowsA / THREAD_COUNT;
			int extraRows = rowsA % THREAD_COUNT;
			int startRow = 0;
			int endRow;
			
			// Distribute rows among threads
	        for (int i = 0; i < THREAD_COUNT; i++) {
	        	// Add 1 if there were extra rows
	        	int threadRowCount = rowsPerThread + (i < extraRows ? 1 : 0);
	        	// Set last row to an offset of the first row
	        	endRow = startRow + threadRowCount;
	        	// Start the thread
	            Runnable task = new MatrixRowMultiplier(startRow, endRow);
	            Thread thread = new Thread(task);
	            threads[i] = thread;
	            thread.start();
	            // Update start to end of previous block
	            startRow = endRow;
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
		result = output[0][0];
		// Stop timer
        long endTime = System.nanoTime();
        double seconds = (endTime - startTime) / 1_000_000_000.0;
		return seconds;
    }
	
	public static double multiply(int[][] matA, int[][] matB) {
		return multiply(matA, matB, 4);
	}
	
	static class MatrixRowMultiplier implements Runnable {
        private final int startRow;
        private final int endRow;

        MatrixRowMultiplier(int startRow, int endRow) {
            this.startRow = startRow;
            this.endRow = endRow;
        }

        @Override
        public void run() {
        	for(int i = startRow; i < endRow; i++) {
	            for (int j = 0; j < colsB; j++) {
	                for (int k = 0; k < colsA; k++) {
	                    output[i][j] += a[i][k] * b[k][j];
	                }
	            }
        	}
        }
    }
	
	
}

