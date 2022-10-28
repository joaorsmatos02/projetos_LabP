import java.util.Arrays;

/**
 * Executes some simple calls of the functions defined in class Recursive
 * 
 * @author LabP Team
 *
 **/
public class RunRecursive {
	
	public static final String EOL = System.getProperty("line.separator");

	/**
	 * Executes some calls to test the functions of the class Recursive
	 *  
	 */
	public static void main(String[] args) {
		
		TestsRecursive.testProduct();
		TestsRecursive.testNumberDigits();
		TestsRecursive.testMinimumMaximum();
		TestsRecursive.testSequence();
		TestsRecursive.testIsPalindrome();
		String[] grupos = {"At� 12", "De 13 a 29", "De 30 a 59",
				 "De 60 a 79", "de 80 a 99", "100 ou mais"};
		int[] valores = {1, 5, 7, 5, 13, 2};
		int ident = 13;
		String s = "Pacientes em UCI por idades:";
		System.out.println( Recursive.histogram(s, grupos, ident, valores));
		
		
		System.out.println("BEGIN" + EOL);
		
		int naturalNumber = 20;
		long prod = Recursive.product(naturalNumber);
		System.out.println("Product of the first "+ naturalNumber+ " natural numbers = "+ prod + EOL);
		
		int digits = Recursive.numberDigits(naturalNumber);
		System.out.println("Number of digits in "+ naturalNumber+ " = "+ digits + EOL);
		
		int[] values1 = {5, -7, 3, 5, 10, -19, 14, 3, 20};
		int[] interval = Recursive.minimumMaximum(values1);
		System.out.println("Values in array "+ Arrays.toString(values1)+ 
				" are contained in interval " + Arrays.toString(interval) + EOL);
		
		String s1 = "rapa";
		boolean result = Recursive.isPalindrome(s1);
		System.out.println("Is string '"+ s1 + "' a palindrome? " + result + EOL);
		
		long seq = Recursive.sequence(naturalNumber);
		System.out.println("Sequence("+ naturalNumber + ") = "+ seq + EOL);
		
		System.out.println("END");
		}
}
	
