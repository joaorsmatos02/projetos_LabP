package projeto1LabP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The TUTEDECC class provides various methods to change the contents of text files and / or
 * obtain information about them.
 *
 * @author João Matos fc56292
 * 
 * On Linux:
 * compile - javac TUTEDECC.java
 */

public class TUTEDECC {

	/**
	 * Writes to fileOut all lines of fileIn whose position in fileIn is a multiple
	 * of n
	 * 
	 * @param fileIn  - the file containing the lines to be copied
	 * @param n       - the number determining which lines are going to be copied (multiples of this number)
	 * @param fileOut - the file to where the lines are going to be copied
	 * @throws FileNotFoundException - if one or both fileIn and fileOut can't be found
	 * @requires fileIn != null && fileOut != null
	 */

	public static void copyPositionMultiple(String fileIn, int n, String fileOut) throws FileNotFoundException {

		Scanner myReader = new Scanner(new File(fileIn));
		PrintWriter myWriter = new PrintWriter(fileOut);
		int value = 0;
		while (myReader.hasNextLine()) {
			String line = myReader.nextLine();
			value++;
			if (value % n == 0) {
				myWriter.println(value + ": " + line);
		    }
		}
		myReader.close();
		myWriter.close();
	}

	/**
	 * Copies all contents of fileIn to fileOut, replacing all vowels with the
	 * chosen character
	 * 
	 * @param fileIn  - the file containing the text to be copied
	 * @param c       - the character that is going to replace all vowels
	 * @param fileOut - the file to where the text is going to be copied
	 * @throws FileNotFoundException - if one or both fileIn and fileOut can't be found
	 * @requires every vowel to not have accents
	 * @requires fileIn != null && fileOut != null
	 */

	public static void vowelSubstitution(String fileIn, char c, String fileOut) throws FileNotFoundException {

		Scanner myReader = new Scanner(new File(fileIn));
		PrintWriter myWriter = new PrintWriter(fileOut);
		while (myReader.hasNext()) {
			char character = 'a';
			try {
				character = myReader.findInLine(".").charAt(0);
			} catch (NullPointerException e) {
				myWriter.println();
				myReader.nextLine();
				character = myReader.findInLine(".").charAt(0);
			}
			if (character == 'a' || character == 'e' || character == 'i' || character == 'o' || character == 'u'
					|| character == 'A' || character == 'E' ||character == 'I' || character == 'O' || character == 'U') {
				character = c;
				myWriter.print(character);
			} else {
				myWriter.print(character);
			}
		}
		myWriter.println();
		myReader.close();
		myWriter.close();
	}

	/**
	 * Creates an array with the average word size for the first n lines of the
	 * fileIn file. The information for line x is in the x-1 index of the array.
	 * 
	 * @param fileIn - the file containing the text to be copied
	 * @param n      - the number of lines to be checked
	 * @return an array with the average word size in every line of the given text file
	 * @throws FileNotFoundException - if fileIn can't be found
	 * @requires fileIn != null
	 * @requires fileIn to have at least n lines
	 * @requires words to be separated by either spaces, commas, semicolons or periods.
	 */

	public static int[] averageWordSize(String fileIn, int n) throws FileNotFoundException {

		Scanner myReader = new Scanner(new File(fileIn));
		int[] result = new int[n];
		String[] lineWords;
		int lineNumber = 0;
		double letters = 0;
		while (myReader.hasNextLine()) {
			String line = myReader.nextLine();
			lineWords = line.split("\\s*[ ,.;]\\s*");
			for (int i = 0; i < lineWords.length; i++) {
				letters += lineWords[i].length();
			}
			double average = letters / lineWords.length;
			if (average - (int) average < 0.5) {
				result[lineNumber] = (int) average;
			} else {
				average += 0.5;
				result[lineNumber] = (int) average;
			}
			letters = 0;
			lineNumber++;
		}
		myReader.close();
		return result;
	}

	/**
	 * Copies the last word of every line in the fileIn file to the corresponding
	 * line in the fileOut file and numbers every line output.
	 * 
	 * @param fileIn  - the file containing the lines to be copied
	 * @param fileOut - the file to where the text is going to be copied
	 * @throws FileNotFoundException - if one or both fileIn and fileOut can't be found
	 * @requires words to be separated by either spaces, commas, semicolons or periods.
	 * @requires fileIn != null && fileOut != null
	 */

	public static void oneWordPerLine(String fileIn, String fileOut) throws FileNotFoundException {

		Scanner myReader = new Scanner(new File(fileIn));
		PrintWriter myWriter = new PrintWriter(fileOut);
		int lineNumber = 1;
		String[] lineWords;
		while (myReader.hasNextLine()) {
			String line = myReader.nextLine();
			lineWords = line.split("\\s*[ ,.;]\\s*");
			myWriter.println(lineNumber + " " + lineWords[lineWords.length - 1]);
			lineNumber++;
		}
		myReader.close();
		myWriter.close();
	}

	/**
	 * Copies every word in the fileIn file to the fileOut file. If the word has
	 * over n characters, the last n characters are moved to the beginning of the
	 * word. If the word has n or less characters it remains unchanged.
	 * 
	 * @param fileIn  - the file containing the lines to be copied
	 * @param n       - the number of characters to rotate in every word
	 * @param fileOut - the file to where the text is going to be copied
	 * @throws FileNotFoundException - if one or both fileIn and fileOut can't be found
	 * @requires words to be separated by spaces
	 * @requires fileIn != null && fileOut != null
	 */

	public static void rotateEveryWord(String fileIn, int n, String fileOut) throws FileNotFoundException {

		Scanner myReader = new Scanner(new File(fileIn));
		PrintWriter myWriter = new PrintWriter(fileOut);
		String[] lineWords;
		String lastN = null;
		String[] finished;
		String line = null;
		StringBuilder sb = new StringBuilder();
		while (myReader.hasNextLine()) {
			line = myReader.nextLine();
			lineWords = line.split("\\s*[ ]\\s*");
			finished = lineWords;
			for (int i = 0; i < lineWords.length; i++) {
				if (lineWords[i].length() > n) {
					lastN = lineWords[i].substring(lineWords[i].length() - n, lineWords[i].length());
					sb.append(lastN + lineWords[i].substring(0, lineWords[i].length() - n));
					finished[i] = sb.toString();
					sb.setLength(0);
				}
			}
			for (int j = 0; j < finished.length-1; j++) {
				myWriter.print(finished[j] + " ");
			}
			myWriter.println(finished[finished.length-1]);			
		}
		myReader.close();
		myWriter.close();
	}

	/**
	 * Copies the text from the fileIn file to the fileOut file switching, in every
	 * pair of lines the word in the k position of the first line to the k+1
	 * position of the second line. Only Words in odd positions in the first line
	 * and even on the second are affected. If fileIn has an odd amount of lines,
	 * the last one is unaffected.
	 * 
	 * @param fileIn  - the file containing the lines to be copied
	 * @param fileOut - the file to where the text is going to be copied
	 * @throws FileNotFoundException - if one or both fileIn and fileOut can't be found
	 * @requires words to be separated by spaces
	 * @requires fileIn != null && fileOut != null
	 */

	public static void switchNextWordNextLine(String fileIn, String fileOut) throws FileNotFoundException {

		Scanner myReader = new Scanner(new File(fileIn));
		PrintWriter myWriter = new PrintWriter(fileOut);
		String line1;
		String line2;
		String[] line1a;
		String[] line2a;
		while (myReader.hasNextLine()) {
			line1 = myReader.nextLine();
			line1a = line1.split("\\s*[ ]\\s*");
			try {
				line2 = myReader.nextLine();
				line2a = line2.split("\\s*[ ]\\s*");
				try {
					for (int i = 0; i < line2a.length - 1; i++) {
						if (i % 2 == 0) {
							String bottom = line2a[i + 1];
							line2a[i + 1] = line1a[i];
							line1a[i] = bottom;
						}
					}
				} catch (ArrayIndexOutOfBoundsException f) {
				}
				for (int j = 0; j < line1a.length-1; j++) {
					myWriter.print(line1a[j] + " ");
				}
				myWriter.println(line1a[line1a.length-1]); 
				for (int k = 0; k < line2a.length-1; k++) {
					myWriter.print(line2a[k] + " ");
				}
				myWriter.println(line2a[line2a.length-1]);
			} catch (NoSuchElementException e) {
				for (int l = 0; l < line1a.length-1; l++) {
					myWriter.print(line1a[l] + " ");
				}
				myWriter.print(line1a[line1a.length-1]);
			}

		}
		myReader.close();
		myWriter.close();
	}

}
