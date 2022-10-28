
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class RockTestGenerator {

	/**
	 * Generates a rock analysis test and writes it to a file
	 *
	 * @param numberOfPieces number of pieces to split the original rock
	 * @param width          width of the original rock
	 * @param height         height of the original rock
	 * @param fileOut        filename to write the .rock test
	 * @requires para completar
	 * @throws FileNotFoundException
	 */

	public static void generateToFile(int numberOfPieces, int width, int height, String fileOut)
			throws FileNotFoundException {
		String rockFile = generate(numberOfPieces, width, height);
		PrintWriter writer = new PrintWriter(fileOut);
		writer.println(rockFile);
		writer.close();
	}

	/**
	 * Generates a test for the computeOriginalHeight method.
	 * 
	 * This method starts by creating the original rock and randomly dividing it,
	 * then randomly choosing from the avaliable pieces an dividing them further (as
	 * long as they are bigger than 1x1), both vertically and horizontally into
	 * smaller parts until the desired number of pieces has been reached. It
	 * achieves this by having two arrays, one for the width of all parts and other
	 * with the corresponding heights. An index, when applied to these arrays,
	 * completely describes a part. Indexes are choosen randombly from the avaliable
	 * ones and are divided either vertically or horizontally (also randomly
	 * chosen). The information about the newly created part is then added to the
	 * arrays and the process continues until the desired number of pieces has been
	 * reached.
	 * 
	 * @param numberOfPieces - the number of pieces in which to break the rock
	 * @param width          - the original width of the rock
	 * @param height         - the original height of the rock
	 * @return a String, following the .rock format, containing the original width,
	 *         number of pieces and the size of all pieces
	 * @requires 1 <= numberOfPieces <= min(5000000, width * height)
	 * @requires 1 <= width, height <= 10000
	 */

	public static String generate(int numberOfPieces, int width, int height) {
		StringBuilder sb = new StringBuilder();
		Random rd = new Random();
		sb.append(width + "\r\n" + numberOfPieces + "\r\n");
		if (numberOfPieces == width * height) { // caso seja o nº maximo de peças possivel
			for (int i = 0; i < numberOfPieces; i++) {
				sb.append("1 1\r\n");
			}
		} else {
			int[] piecesWidth = new int[numberOfPieces];
			int[] piecesHeight = new int[numberOfPieces];
			piecesWidth[0] = width;
			piecesHeight[0] = height;
			for (int i = 1; i < numberOfPieces; i++) {
				double divide = 1;
				int index = 0;
				boolean divideWidth = rd.nextInt(2) == 1; // corte vertical? (upper bound é excluida, logo é
															// efetivamente entre 0 e 1)
				int tries = 0;
				while (divide == 1 && tries < numberOfPieces) { // escolher aleatoriamente qual a peça a dividir para
																// tentar obter uma distribuição equitativa
					tries++;
					if (tries % 2 == 0) {
						divideWidth = !divideWidth; // pode não haver uma peça divisivel dessa forma
					}
					if (i == 1)
						index = 0;
					else
						index = rd.nextInt(i - 1);
					if (divideWidth)
						divide = piecesWidth[index];
					else
						divide = piecesHeight[index];

				}

				if (divide == 1) { // caso o random esteja com dificuldade em encontrar uma peça divisivel vamos
									// percorrer 1 a 1
					do {
						if (divideWidth) {
							for (index = 0; index < i - 1 && divide == 1; index++) {
								divide = piecesWidth[index];
							}
						} else {
							for (index = 0; index < i - 1 && divide == 1; index++) {
								divide = piecesHeight[index];
							}
						}
						divideWidth = !divideWidth; // trocar caso não seja possivel dividir nesta direção
					} while (divide == 1);
					divideWidth = !divideWidth; // voltar á direção possivel
				}

				if (divideWidth) { // caso seja um corte vertical
					double widthDivision = rd.nextDouble() + 1; // dividir entre 1 e 2
					if (widthDivision == 1)
						widthDivision += 0.001;
					piecesWidth[i] = (int) (divide / widthDivision);
					piecesWidth[index] -= piecesWidth[i];
					piecesHeight[i] = piecesHeight[index]; // a altura mantem-se inalterada
				} else {
					double heightDivision = rd.nextDouble() + 1; // caso seja um corte horizontal
					if (heightDivision == 1)
						heightDivision += 0.001;
					piecesHeight[i] = (int) (divide / heightDivision);
					piecesHeight[index] -= piecesHeight[i];
					piecesWidth[i] = piecesWidth[index]; // a largura mantem-se inalterada
				}
			}
			for (int i = 0; i < numberOfPieces; i++) {
				sb.append(piecesWidth[i] + " " + piecesHeight[i] + "\r\n");
			}
		}
		return sb.toString();
	}
}
