package matricmult;
/**
 * 
 * Initialize everything
 * create matrices
 * Run sequential and parallel multipliers
 * Ensure results are Identical
 * 
 */
public class Main {

	public static void main(String[] args) {
		// On my PC, 12 seems to be the optimal number of threads
		for(int i = 100; i < 1000; i += 100)
			runTest(i, 12);
		
	}
	
	
	public static void runTest(int mtxSize, int threadCt) {
		// Object declarations and initializations
		SequentialMultiplier sequMul = new SequentialMultiplier();
		MatrixUtils util = new MatrixUtils();
		
		// Create matrices
		int[][] matrix1 = util.populateMatrix(mtxSize);
		int[][] matrix2 = util.populateMatrix(mtxSize);
		
		// Perform multiplication
		int[][] paraResult = ParallelMultiplier.multiply(matrix1, matrix2, threadCt);
		int[][] sequResult = sequMul.multiply(matrix1, matrix2);
				
	/*****  Uncomment below to view matrices  *****/
		//util.printMatrix(matrix1);
		//util.printMatrix(matrix2);
		//System.out.println();
		//util.printMatrix(paraResult, 8);
		//System.out.println();
		//util.printMatrix(sequResult, 8);
	}
	
	public static void runTest(int mtxSize) {
		runTest(mtxSize, 4);
	}

}
