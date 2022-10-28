import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to recognise some patterns of regular expressions and to use them to
 * analyse the contents of file "in.txt"
 * 
 * @author João Matos nº 56292
 *
 */
public class RecognisePatterns {

	/**
	 * Determines if the provided string is a valid Java class name
	 * 
	 * @param s - The string to be checked
	 * @return true if it is a valid class name, false if not
	 */
	public static boolean isJavaClassIdentifier(String s) {
		boolean result = true;
		Pattern valid = Pattern.compile("([A-Z])(([A-Za-z0-9$_])*)?");
		Matcher Matcher = valid.matcher(s);
		if (!Matcher.matches())
			result = false;
		return result;
	}

	/**
	 * Determines if the provided string contains a valid time stamp in the form
	 * hh:mm:ss.sss
	 * 
	 * @param s - The string to be checked
	 * @return true if a time stamp was found, false if not
	 */
	public static boolean matchTimeStampLiteral(String s) {
		boolean result = true;
		Pattern valid = Pattern
				.compile("(^| )(2[0-3]|1[0-9]|0[0-9]|[0-9]):[0-5]?[0-9]:[0-5]?[0-9]([.][(0-9)]{1,3})?($| )");
		Matcher Matcher = valid.matcher(s);
		if (!Matcher.find())
			result = false;
		return result;
	}

	/**
	 * Determines if the provided string contains a list in one of the accepted
	 * formats: < elem1, elem2, …, elemn > or elemfirst | list
	 * 
	 * @param s - The string to be checked
	 * @return true if a list was found, false if not
	 */
	public static boolean matchListNotation(String s) {
		boolean result = true;
		Pattern valid = Pattern
				.compile("((([ ]+)?[0-9]+([ ]+)?[|])+([ ]+)?)?(<([ ]+)?([0-9]+)?((([ ]+)?,([ ]+)?[0-9]+)+)?([ ]+)?>)");
		Matcher Matcher = valid.matcher(s);
		if (!Matcher.find())
			result = false;
		return result;
	}

	/**
	 * Determines if the provided string contains any numbers in scientific notation
	 * 
	 * @param s - The string to be checked
	 * @return a list containing all numbers in scientific notation found
	 */
	public static List<Double> numbersInScientificNotation(String s) {
		LinkedList<Double> result = new LinkedList<Double>();
		Pattern valid = Pattern.compile("([+]|-)?[0-9]+(.[0-9]+)?E([+]|-)?[0-9]+");
		Matcher Matcher = valid.matcher(s);
		while (Matcher.find())
			result.add(Double.parseDouble(Matcher.group()));
		return result;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("in.txt"));
		PrintWriter out = new PrintWriter("out.txt");

		if (in.hasNextLine()) {
			boolean isIdentifier = isJavaClassIdentifier(in.nextLine());
			out.println("Is the whole line 1 a java class identifier? " + isIdentifier);
		}
		int lineNo = 2;
		while (in.hasNextLine()) {
			String linha = in.nextLine();
			if (matchTimeStampLiteral(linha))
				out.println("Line " + lineNo + " contains at least one time stamp literal");
			if (matchListNotation(linha))
				out.println("Line " + lineNo + " contains at least one list");
			List<Double> doublesList = numbersInScientificNotation(linha);
			if (!doublesList.isEmpty()) {
				out.print("Line " + lineNo + " contains at least one number in scientific notation. ");
				out.print("List of recognised numbers: ");
				out.println(doublesList.toString());
			}

			lineNo++;
		}

		in.close();
		out.close();
	}
}
