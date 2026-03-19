package matricmult;
/**
 * 
 * Implements runnable
 * Should take a range of rows and perform 
 * multiplication for just those rows
 * 
 * Needs to reference source matrices and the result matrix
 */
public class MultiplyTask implements Runnable {
	
	// Object declarations and initializations
	SequentialMultiplier sequMul = new SequentialMultiplier();
	ParallelMultiplier paraMul = new ParallelMultiplier();
	MatrixUtils util = new MatrixUtils();
	
	@Override
	public void run() {
		int[][] matrix1 = util.populateMatrix(100);
		int[][] matrix2 = util.populateMatrix(100);
		int[][] paraResult = ParallelMultiplier.multiply(matrix1, matrix2);
		System.out.println(paraResult[0][0]);
		int[][] sequResult = sequMul.multiply(matrix1, matrix2);
		System.out.println(sequResult[0][0]);
		
		//util.printMatrix(matrix1);
		System.out.println();
		//util.printMatrix(matrix2);
		System.out.println();
		//util.printMatrix(result, 8);
		
	}
	
}
