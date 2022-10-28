import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * The RockAnalysis class provides the tools to obtain more information about
 * the martian rocks
 * 
 * @author João Matos nº 56292
 *
 */

public class RockAnalysis {

	/**
	 * Calculates the rock's original height, given it's original width and sizes of
	 * all pieces
	 * 
	 * This method works by calculating the combined area of all the pieces and
	 * dividing it by the original width. Since all pieces combined have the same
	 * area as the orignal rock and the area of a rectangle can be calculated by
	 * height*width, the height can be calculated by the combined area of all pieces
	 * divided by the original width.
	 * 
	 * @param filename - the name of the .rock file containing information about the
	 *                 rock
	 * @return the rock's original height or 0 if the file cannot be found
	 * @throws FileNotFoundException - if the .rock file cannot be found
	 * @requires the .rock file to contain the correct description of a rock and
	 *           it's pieces
	 */

	public static int computeOriginalHeight(String filename) {
		int area = 0;
		int width = 1;
		try {
			Scanner sc = new Scanner(new File(filename));
			width = sc.nextInt();
			int numberOfPieces = sc.nextInt();
			for (int i = 0; i < numberOfPieces; i++) {
				area += sc.nextInt() * sc.nextInt();
			}
			sc.close();
		} catch (FileNotFoundException e) {
		}
		return area / width;
	}
}
