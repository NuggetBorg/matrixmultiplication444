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
		
		// Test variables
		final int NUMBER_OF_THREADS =     12;
		final int STARTING_MATRIX_SIZE =  1100;
		final int ENDING_MATRIX_SIZE =    1600;
		final int INTERVAL =              25;
		
		// On my PC, 12 seems to be the optimal number of threads
		// Warm-up to allow JVM to configure 
		System.out.println("                                JVM Warmup data");
		runTest(100, 12);
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("            Comparing Parallel and Sequential Matrix Multiplication\n");
		System.out.print("   _________________________________________________________________________________________\n");
		System.out.printf("%16s%25s%25s%24s\n", "Matrix Size", "Parallel Time (s)", "Sequential Time (s)", "Parallel Time Saved");
		System.out.print("   _________________________________________________________________________________________\n");
		
		
		
		for(int i = STARTING_MATRIX_SIZE; i <= ENDING_MATRIX_SIZE; i += INTERVAL) {
			System.out.printf("%12d%8s",i,"|");
			runTest(i, NUMBER_OF_THREADS);
		}
		
	}
	
	
	public static void runTest(int mtxSize, int threadCt) {
		// Object declarations and initializations
		SequentialMultiplier sequMul = new SequentialMultiplier();
		MatrixUtils util = new MatrixUtils();
		
		// Create matrices
		int[][] matrix1 = util.populateMatrix(mtxSize);
		int[][] matrix2 = util.populateMatrix(mtxSize);
		
		// Perform multiplication
		double paraResult = ParallelMultiplier.multiply(matrix1, matrix2, threadCt);
		System.out.printf("%16.4f",paraResult);
		
		double sequResult = sequMul.multiply(matrix1, matrix2);
		System.out.printf("        |%16.4f",sequResult);
		System.out.printf("        |%16.4f\n",sequResult - paraResult);
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
