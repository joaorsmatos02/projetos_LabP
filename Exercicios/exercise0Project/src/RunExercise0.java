
import java.io.IOException;

/**
 * This class contains a main method that invoques the methods
 * that the students have to build in Exercise0.
 *
 * @author LabP team 
 *
 */
public class RunExercise0 {

    /**
     * It calls methods that read from a given file and write in another
     * given file some information obtained from the first file
     */

    public static void main(String[] args) throws IOException {

        // It writes in the file named myCopy.txt a copy of the lines of  
	    // the file named myText.txt
        Exercise0.copyText("myText.txt", "myCopy.txt");

    	// It writes in the file named mySquares.txt the squares of the values 
    	// in the file named myNumbers.txt
        Exercise0.writeSquares("myNumbers.txt", "mySquares.txt");

        // It writes in the file named myMultiples.txt the values in   
        // myNumbers.txt that are multiple of 3
        Exercise0.writeMultiples("myNumbers.txt", "myMultiples.txt", 3);
        
        // It writes in the file named lowerUpperCase.txt the lines in myText.txt
        // with all lower case and all upper case letters, every other line
        Exercise0.lowerUpper("myText.txt", "lowerUpperCase.txt");
        
        // It writes in the file name commons.txt the numbers in the file named 
        // myNumbers.txt that also appear in the array values 
    	int[] values = {1, 5, 11, 15, 21, 25};
        Exercise0.commonElements("myNumbers.txt", "commons.txt", values);
       
    }
}