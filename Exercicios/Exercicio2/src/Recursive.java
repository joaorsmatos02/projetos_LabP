
/**
 * 
 * The Recursive class contains several methods, all with different
 * implementations of recursion.
 * 
 * @author João Matos nº56292
 *
 */

public class Recursive {
	// -------
	// 1
	// -------

	/**
	 * Calculates n! (n factorial)
	 * 
	 * @param n - the last number to multiply
	 * @return the product of all ints from 0 to n
	 * @requires n >= 0
	 * @requires n <= 20 or overflow will occour
	 */

	static long product(int n) {
		long result;
		if (n == 0) {
			result = 1;
		} else {
			result = n * product(n - 1);
		}
		return result;
	}

	// -------
	// 2
	// -------

	/**
	 * Counts the number of digits in a number
	 * 
	 * @param n - the number whose digits are going to be counted
	 * @return the number of n's digits
	 * @requires n >= 0
	 */

	static int numberDigits(int n) {
		int result = 0;
		if (n > 10) {
			result = numberDigits(n / 10);
		}
		result++;
		return result;
	}

	// -------
	// 3
	// -------

	/**
	 * Creates an array with two positions containing v's lowest and highest value,
	 * respectively
	 * 
	 * @param v - the int array containing the values to be evaluated
	 * @return an array with v's highest and lowest values
	 * @requires v to have at least one int
	 */

	static int[] minimumMaximum(int[] v) {
		int min = minimum(v, 0);
		int max = maximum(v, 0);
		int[] result = { min, max };
		return result;
	}

	/**
	 * This method acts as an auxiliary to the minimumMaximum method
	 * 
	 * @param v     - the array containing the values to be evaluated
	 * @param index - the index to be used in the recursion
	 * @return the minimum value in the array
	 */

	static int minimum(int[] v, int index) {
		int result = 0;
		if (v.length == 1) {
			result = v[0];
		} else {
			if (index + 1 < v.length) {
				result = Math.min(v[index], minimum(v, index + 1));
			} else {
				result = v[index];
			}
		}
		return result;
	}

	/**
	 * This method acts as an auxiliary to the minimumMaximum method
	 * 
	 * @param v     - the array containing the values to be evaluated
	 * @param index - the index to be used in the recursion
	 * @return the maximum value in the array
	 */

	static int maximum(int[] v, int index) {
		int result = 0;
		if (v.length == 1) {
			result = v[0];
		} else {
			if (index + 1 < v.length) {
				result = Math.max(v[index], maximum(v, index + 1));
			} else {
				result = v[index];
			}
		}
		return result;
	}

	// -------
	// 4
	// -------

	/**
	 * Checks if the provided string is a palindrome (can be reversed and mantain
	 * its meaning)
	 * 
	 * @param s - the string to be evaluated
	 * @return true if s is a palindrome, false if not
	 * @requires s != null && s != ""
	 */

	static boolean isPalindrome(String s) {
		int index = 0;
		boolean result = true;
		return isPalidromeAux(s, index, result);
	}

	/**
	 * This method acts as an auxiliary to the isPalindrome method
	 * 
	 * @param s      - the string to be evaluated
	 * @param index  - the index to be used in the recursion
	 * @param result - the final result to be output
	 * @return true if s is a palindrome, false if not
	 */

	static boolean isPalidromeAux(String s, int index, boolean result) {
		if (s.charAt(index) != s.charAt(s.length() - 1)) {
			result = false;
		}
		if (index < (s.length() / 2) - 1) {
			isPalidromeAux(s, index + 1, result);
		}
		return result;
	}
	// -------
	// 5 Opcional
	// -------

	/**
	 * Creates a complete histogram using all information provided
	 * 
	 * @param header - the string containing the title of the histogram
	 * @param c      - the string array containing the description of every line of
	 *               the histogram
	 * @param ident  - the identation of every line
	 * @param v      - the int array containing the values of every line
	 * @return a string that, if printed, represents a histogram
	 * @requires all of v's values to be equal or greater than 0
	 */

	static String histogram(String header, String c[], int ident, int[] v) {
		StringBuilder sb = new StringBuilder();
		sb.append(header + "\r\n" + "\r\n" + histogramAux(c, ident, v, v.length - 1));
		return sb.toString();
	}

	/**
	 * This method acts as an auxiliary to the histogram method
	 * 
	 * @param c     - the string array containing the description of every line of
	 *              the histogram
	 * @param ident - the identation of every line
	 * @param v     - the int array containing the values of every line
	 * @param index - the index to be used in the recursion
	 * @return a string that, if printed, represents a histogram
	 */

	static String histogramAux(String c[], int ident, int[] v, int index) {
		StringBuilder sb = new StringBuilder();
		if (index > 0) {
			sb.append(histogramAux(c, ident, v, index - 1));
		}
		sb.append(c[index]);
		for (int i = c[index].length(); i < ident; i++) {
			sb.append(" ");
		}
		sb.append("|");
		for (int j = 0; j < v[index]; j++) {
			sb.append("*");
		}
		sb.append("\r\n");
		return sb.toString();
	}

	// -------
	// 6
	// -------

	/**
	 * Calculates the nth element of a sequence with the following rules: The first
	 * 3 elements are equal to 1 sequence(n) = sequence(n-2) + sequence(n-3)
	 * 
	 * @param n - the element of the sequence to be returned
	 * @return the nth element of the sequence
	 * @requires n to be positive
	 */

	static long sequence(int n) {
		long[] mem = new long[n + 1];
		for (int i = 0; i < 3; i++) {
			mem[i] = 1;
		}
		long[] result = new long[n];
		result = sequenceMem(n, mem);
		return result[n - 1];
	}

	/**
	 * This method acts as an auxiliary to the sequence method
	 * 
	 * @param n   - the last element of the sequence to be calculated
	 * @param mem - a long array used to memorize values that were previously
	 *            obtained
	 * @return an array containing every value of the sequence until n
	 */

	static long[] sequenceMem(int n, long[] mem) {
		if (mem[n - 1] != 1) {
			mem = sequenceMem(n - 1, mem);
		}
		mem[n] = mem[n - 2] + mem[n - 3];
		return mem;
	}
}
