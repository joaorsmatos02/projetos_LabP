package Projeto2LabP;

/**
 * 
 * @author João Matos nº 56292
 *
 * On Linux: Compile - javac MatrixOperation.java
 */

public class MatrixOperation {

	/**
	 * Determines if all lines of a matrix are of the same length and if the matrix
	 * is square
	 * 
	 * @param m - the matrix to be tested
	 * @return true if m is square or false if m is not square
	 */

	public static boolean isSquare(int[][] m) {
		boolean result = true;
		for (int i = 0; i < m.length; i++) {
			if (m.length != m[i].length)
				result = false;
		}
		return result;
	}

	/**
	 * Determines if the given matrix is the identity matrix (if the main diagonal
	 * is composed of 1's and all other positions contain 0)
	 * 
	 * @param m - the matrix to be tested
	 * @return true if m is an identity matrix, false if it is not
	 * @requires m to be a square matrix (isSquare(m) == true)
	 */

	public static boolean isIdentity(int[][] m) {
		boolean diagonal = true;
		for (int i = 0; i < m.length; i++) {
			for (int l = 0; l < m[i].length; l++) {
				if (l == i && m[i][i] != 1) {
					diagonal = false;
				} else if (l != i && m[i][l] != 0) {
					diagonal = false;
				}
			}
		}
		return diagonal;
	}

	/**
	 * Calculates a given matrix's diagonal matrix (contains 0 in every position
	 * except in the main diagonal, which is identical to the original matrix)
	 * 
	 * @param m - the matrix to be used
	 * @return m's diagonal matrix
	 * @requires m to be a square matrix (isSquare(m) == true)
	 */

	public static int[][] diagonal(int[][] m) {
		int[][] diagonal = new int[m.length][m[0].length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				if (i == j) {
					diagonal[i][j] = m[i][j];
				} else {
					diagonal[i][j] = 0;
				}
			}
		}
		return diagonal;
	}

	/**
	 * Copies the given matrix's main diagonal to a one dimensional array
	 * 
	 * @param m - the matrix to be used
	 * @return an array containing m's diagonal
	 * @requires m to be a square matrix (isSquare(m) == true)
	 */

	public static int[] diagonalVector(int[][] m) {
		int[] diagonal = new int[m.length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				if (i == j) {
					diagonal[i] = m[i][j];
				}
			}
		}
		return diagonal;
	}

	/**
	 * Calculates m's transpose matrix (swaps lines with columns)
	 * 
	 * @param m - the matrix to be used
	 * @return m's transpose matrix
	 * @requires m to be a square matrix (isSquare(m) == true)
	 */

	public static int[][] transpose(int[][] m) {
		int[][] transpose = new int[m.length][m.length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				transpose[i][j] = m[j][i];
			}
		}
		return transpose;
	}

	/**
	 * Calculates the sum of m's index line, column or diagonal, depending on the
	 * chosen type
	 * 
	 * @param m     - the matrix to be used
	 * @param index - which line or column to be used
	 * @param type  - the type of operation to perform
	 * @requires m to be a square matrix (isSquare(m) = true)
	 * @requires type to be between 1 and 3
	 * @requires index to be at least 0 and less than m's number of lines if type ==
	 *           1 or less than m's number of columns if type == 2
	 * @return the sum of m's index line if type == 1
	 * @return the sum of m's index column if type == 2
	 * @return the sum of m's diagonal if type == 3
	 */

	public static int sumCalculation(int[][] m, int index, int type) {
		int sum = 0;
		if (type == 1) {
			for (int i = 0; i < m[index].length; i++) {
				sum += m[index][i];
			}
		} else if (type == 2) {
			for (int i = 0; i < m.length; i++) {
				sum += m[i][index];
			}
		} else if (type == 3) {
			int i = 0;
			int j = 0;
			while (i < m.length) {
				sum += m[i][j];
				i++;
				j++;
			}
		}
		return sum;
	}

	/**
	 * Copies the m matrix but every element of the line number index is multiplied
	 * by scalar
	 * 
	 * @param m      - the matrix to be used
	 * @param scalar - the number to multiply
	 * @param index  - the line to be used
	 * @return a matrix identical to m except line number index, which is the
	 *         multiplication of m's index line with scalar
	 * @requires m to be a square matrix (isSquare(m) == true)
	 * @requires index to be at least 0 and less than m's number of lines
	 */

	public static int[][] multiplyLine(int[][] m, int scalar, int index) {
		int[][] multiplied = new int[m.length][m[0].length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				if (i == index) {
					multiplied[i][j] = scalar * m[i][j];
				} else {
					multiplied[i][j] = m[i][j];
				}
			}
		}
		return multiplied;
	}

	/**
	 * Copies the m matrix and subtracts the line number index from all other lines
	 * 
	 * @param m     - the matrix to be used
	 * @param index - the line to subtract
	 * @return a matrix obtained by subtracting m's index line from all other lines
	 * @requires m to be a square matrix (isSquare(m) == true)
	 * @requires index to be at least 0 and less than m's number of lines
	 */

	public static int[][] subtractLine(int[][] m, int index) {
		int[][] subtracted = new int[m.length][m[0].length];
		int[] toSubtract = m[index];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				if (i == index) {
					subtracted[i][j] = m[i][j];
				} else {
					subtracted[i][j] = m[i][j] - toSubtract[j];
				}
			}
		}
		return subtracted;
	}
}
