package tutorial1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Joao Matos fc56292
 *
 */
public class ExceptionsExample {

	public static final double ERRO = 0.001;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			double n = lerDeUmFicheiro("inteiros1.txt");
			if (n <= ERRO) {
				sc.close();
				throw new InputMismatchException("Ocorreu um erro. \n" + "O argumento nao eh positivo!");
			}
		} catch (InputMismatchException e) {
			if (e.getMessage() == null) {
				System.out.println("Ocorreu um erro. Formato errado.");
			} else {
				System.out.println(e.getMessage());
			}
		} catch (FileNotFoundException e) {
			System.out.println("Ocorreu uma excecao porque o ficheiro nao existe");
		}
		sc.close();
	}

	public static double lerDeUmFicheiro(String nomeFicheiro) throws FileNotFoundException {
		double n = 0;
		try (Scanner sc = new Scanner(new File(nomeFicheiro))) {
			n = sc.nextDouble();
			if (n <= ERRO) {
				throw new InputMismatchException("O argumento eh negativo!");
			}
			return n;
		}
	}
}
