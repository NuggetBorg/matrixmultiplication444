package matricmult;
/**
 * 
 * Code to fill arrays
 * generateMatrix(int rows, int cols) method
 * Need method to verify correctness (Ensure parallel version works
 * 
 */
public class MatrixUtils {
	// Create different dimension matrices
	public int[][] populateMatrix(int rows, int columns) {
        // Create 2 n x n matrices
        int[][] a = new int[rows][columns];

        // Populate matrices
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                a[i][j] = (int)(Math.round(Math.random() * 100));
            }
        }
        System.out.println("Matrix Populated");
        return a;       
	}
	// Create square matrices
	public int[][] populateMatrix(int n) {
        // Create 2 n x n matrices
        int[][] a = new int[n][n];

        // Populate matrices
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = (int)(Math.round(Math.random() * 100));
            }
        }
        System.out.println("Matrix Populated");
        return a;       
	}
	
	//Print matrix with spacing specified
	public void printMatrix(int[][] matrix, int spacing) {
		// Outer loop for rows
		for (int i = 0; i < matrix.length; i++) {
		    // Inner loop for columns
		    for (int j = 0; j < matrix[i].length; j++) {
		        // Use custom spacing instead of default
		        System.out.printf("%"+spacing+"d", matrix[i][j]);
		    }
		    // New line after each row
		    System.out.println();
		}
	}
	//Print matrix with default spacing
		public void printMatrix(int[][] matrix) {
			// Outer loop for rows
			for (int i = 0; i < matrix.length; i++) {
			    // Inner loop for columns
			    for (int j = 0; j < matrix[i].length; j++) {
			        System.out.printf("%4d", matrix[i][j]);
			    }
			    // New line after each row
			    System.out.println();
			}
		}
	
}
