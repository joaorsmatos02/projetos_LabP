package Projeto2LabP;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The RunMatrix class acts as a client of the MatrixOperation class, using all of the methods defined
 * there for many different purposes
 * 
 * @author João Matos nº 56292
 * 
 * On Linux:
 * Compile - javac RunMatrix.java
 */

public class RunMatrix {
	public static void main(String[] args) {
		// ----------
		// testes
		// ----------
    /*	TestsMatrixOperation.testIsSquare1();
		TestsMatrixOperation.testIsSquare2();
		TestsMatrixOperation.testIsSquareRagged();
		TestsMatrixOperation.testIsIdentity1();
		TestsMatrixOperation.testIsIdentity2();
		TestsMatrixOperation.testIsIdentity3();
		TestsMatrixOperation.testDiagonal();
		TestsMatrixOperation.testDiagonalVector();
		TestsMatrixOperation.testTranspose();
		TestsMatrixOperation.testSumCalculationType1();
		TestsMatrixOperation.testSumCalculationType2();
		TestsMatrixOperation.testSumCalculationType3();
		TestsMatrixOperation.testMultiplyLine();
		TestsMatrixOperation.testSubtractLine();   */
		// ----------
		// 1
		// ----------
		Scanner sc = null;
		try {
			sc = new Scanner(new File("inputMatrix1.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro não encontrado.");
			System.exit(0); //
		}
		String line = sc.nextLine();
		String[] size = line.split("\\s*[ ,.;]\\s*");
		int[][] matrix1 = new int[Integer.parseInt(size[0])][Integer.parseInt(size[1])];
		int lineIndex = 0;
		System.out.println("Matriz de entrada");
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			System.out.print(line);
			String[] contents = line.split("\\s*[ ,.;]\\s*");
			if(contents[0] == "") {
				line = line.substring(1, line.length());
				contents = line.split("\\s*[ ,.;]\\s*");
			}
			for (int j = 0; j < contents.length; j++) {
				try {
					matrix1[lineIndex][j] = Integer.parseInt(contents[j]);
				} catch (NumberFormatException r) {
					System.out.println("O ficheiro nao contem matriz quadrada.");
					System.exit(0);
				} catch (ArrayIndexOutOfBoundsException u) {
					System.out.println("O ficheiro nao contem matriz quadrada.");
					System.exit(0);
				}
			}
			lineIndex++;
			System.out.println();
		}

		boolean isSquare = MatrixOperation.isSquare(matrix1);
		if (!isSquare) {
			System.out.println("O ficheiro nao contem matriz quadrada.");
			System.exit(0);
		}
		// ----------
		// 2
		// ----------
		boolean isIdentity = MatrixOperation.isIdentity(matrix1);
		if(isIdentity) {
			System.out.println("É matriz identidade");
		}
		else {
			System.out.println("Nao eh matriz identidade");
		}
		// ----------
		// 3
		// ----------
		System.out.println("Matriz diagonal");
		int[][] diagonal = MatrixOperation.diagonal(matrix1);
		for (int k = 0; k < diagonal.length; k++) {
			for (int l = 0; l < diagonal[0].length; l++) {
				System.out.print(diagonal[k][l] + "  ");
			}
			System.out.println();
		}
		// ----------
		// 4
		// ----------
		System.out.println("Vector diagonal");
		int[] diagonalV = MatrixOperation.diagonalVector(matrix1);
		for (int m = 0; m < diagonalV.length; m++) {
			System.out.print(diagonalV[m] + " ");
		}
		System.out.println();
		// ----------
		// 5
		// ----------
		System.out.println("Matriz transposta");
		int[][] transpose = MatrixOperation.transpose(matrix1);
		for (int k = 0; k < transpose.length; k++) {
			for (int l = 0; l < transpose[0].length; l++) {
				System.out.print(transpose[k][l] + "  ");
			}
			System.out.println();
		}
		// ----------
		// 6
		// ----------
		System.out.print("Soma dos elementos de cada linha: ");
		for (int n = 0; n < matrix1.length; n++) {
			System.out.print(MatrixOperation.sumCalculation(matrix1, n, 1) + " ");
		}
		System.out.println();
		System.out.print("Soma dos elementos de cada coluna: ");
		for (int n = 0; n < matrix1[0].length; n++) {
			System.out.print(MatrixOperation.sumCalculation(matrix1, n, 2) + " ");
		}
		System.out.println();
		System.out.print("Soma dos elementos da diagonal: " + MatrixOperation.sumCalculation(matrix1, 0, 3));
		System.out.println();
		// ----------
		// 7
		// ----------
		boolean isValid = false;
		String index;
		int indexInt = 0;
		Scanner sc1 = new Scanner(System.in);
		while (!isValid) {
			System.out.println("Inserir um valor inteiro para multiplicar pela linha da matriz:");
			index = sc1.nextLine();
			try {
				indexInt = Integer.parseInt(index);
				if (Integer.parseInt(index) < matrix1.length && Integer.parseInt(index) >= 0) {
					isValid = true;
				} else {
					System.out.println("Indice invalido!");
				}
			} catch (NumberFormatException w) {
				System.out.println("Indice invalido!");
			}
		}
		int scalarInt = 0;
		isValid = false;
		while (!isValid) {
			System.out.println("Inserir o indice da linha da matriz a multiplicar:");
			index = sc1.nextLine();
			try {
				scalarInt = Integer.parseInt(index);
				isValid = true;
			} catch (NumberFormatException w) {
				System.out.println("Não é um número!");
			}
		}
		// ----------
		// 8
		// ----------
		System.out.println("Matriz com a linha de indice " + indexInt + " multiplicada por escalar " + scalarInt);
		int[][] multiplied = MatrixOperation.multiplyLine(matrix1, scalarInt, indexInt);
		for (int i = 0; i < multiplied.length; i++) {
			for (int l = 0; l < multiplied[0].length; l++) {
				System.out.print(multiplied[i][l] + "  ");
			}
			System.out.println();
		}
		// ----------
		// 9
		// ----------
		int maxSum = 0;
		int iMaxSum = 0;
		for (int n = 0; n < matrix1.length; n++) {
			int currentSum = MatrixOperation.sumCalculation(matrix1, n, 1);
			if (currentSum > maxSum) {
				maxSum = currentSum;
				iMaxSum = n;
			}
		}
		System.out.println("A linha com index " + iMaxSum + " tem o valor max da soma das linhas");
		// ----------
		// 10
		// ----------
		int max = 0;
		for (int n = 0; n < matrix1[iMaxSum].length; n++) {
			int current = matrix1[iMaxSum][n];
			if (current > max) {
				max = current;
			}
		}
		System.out.println("O max da linha com index " + iMaxSum + " eh " + max);
		// ----------
		// 11
		// ----------
		int scalar = 0;
		if (max == 0) {
			scalar = 1;
		} else if (max < 0) {
			while (scalar * max < 10) {
				scalar--;
			}
		} else {
			while (scalar * max < 10) {
				scalar++;
			}
		}
        int[][] result = MatrixOperation.multiplyLine(matrix1, scalar, iMaxSum);
		// ----------
		// 12
		// ----------
		int[][] resultFinal = MatrixOperation.subtractLine(result, iMaxSum);
		// ----------
		// 13
		// ----------
		System.out.println("Matriz apos multiplicacao da linha com indice " + iMaxSum + " por " + scalar + "\r\n" + "e restantes linhas a que foi subtraida a linha com indice " + iMaxSum);
		for (int i = 0; i < resultFinal.length; i++) {
			for (int j = 0; j < resultFinal[0].length; j++) {
				System.out.print(resultFinal[i][j] + "  ");
			}
			System.out.println();
		}
		sc.close();
		sc1.close();
	}
}
