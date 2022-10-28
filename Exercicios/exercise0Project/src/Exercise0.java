
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Exercise0 {
	
	/**
	 * The copyText method copies the text from the fileIn file to the fileOut file
	 * 
	 * @param fileIn - Input File
	 * @param fileOut - Output File
	 * @throws FileNotFoundException - In case the files are not found 
	 */
	
  public static void copyText (String fileIn, String fileOut) throws FileNotFoundException{
	  Scanner myReader = new Scanner(new File(fileIn));
	  PrintWriter myWriter = new PrintWriter(fileOut);
	  while (myReader.hasNextInt()){
		  int value = myReader.nextInt();
		  myWriter.println(value);
	  }
	  myReader.close();
	  myWriter.close();
  }
  
  /**
   * The writeSquares method writes to fileOut the squares of every whole number contained in fileIn
   * 
   * @param fileIn - Input File
   * @param fileOut - Output File
   * @throws FileNotFoundException - In case the files are not found 
   */
  
  public static void writeSquares (String fileIn, String fileOut) throws FileNotFoundException{
	  Scanner myReader = new Scanner(new File(fileIn));
	  PrintWriter myWriter = new PrintWriter(fileOut);
	  while (myReader.hasNextInt()){
		  int value = myReader.nextInt();
		  myWriter.println(value * value);
	  }
	  myReader.close();
	  myWriter.close();
  }
  
  /**
   * The writeMultiples method writes to fileOut the numbers contained in fileIn that are multiples of n
   * 
   * @param fileIn - Input File
   * @param fileOut - Output File
   * @param n - The int to check the multiples
   * @throws FileNotFoundException - In case the files are not found 
   */
  
  public static void writeMultiples (String fileIn, String fileOut, int n) throws FileNotFoundException{
	  Scanner myReader = new Scanner(new File(fileIn));
	  PrintWriter myWriter = new PrintWriter(fileOut);
	  while (myReader.hasNextInt()){
		  int value = myReader.nextInt();
		  if(value % n == 0){
		     myWriter.println(value);
		  }
	  }
	  myReader.close();
	  myWriter.close();
  }
  
  /**
   * The lowerUpper method copies the text from fileIn into fileOut, alternating between large and small characters  from line to line 
   * 
   * @param fileIn - Input File
   * @param fileOut - Output File
   * @throws FileNotFoundException - In case the files are not found 
   */
  
  public static void lowerUpper(String fileIn, String fileOut) throws FileNotFoundException{
	  Scanner myReader = new Scanner(new File(fileIn));
	  PrintWriter myWriter = new PrintWriter(fileOut);
	  int juri = 1;
	  while (myReader.hasNextInt()){
		  String value = myReader.nextLine();
		  if(juri % 2 != 0) {
		      myWriter.println(value.toLowerCase());
		  }
		  else{
			  myWriter.println(value.toUpperCase());
		  }
		  juri ++;
	  }
	  myReader.close();
	  myWriter.close();
  }
  
  /**
   * The commonElements method copies the values from fileIn that are also contained in the values array into fileOut
   * 
   * @param fileIn - Input File
   * @param fileOut - Output File
   * @param values - Array with the values that can be copied
   * @throws FileNotFoundException - In case the files are not found 
   */
  
  public static void commonElements(String fileIn, String fileOut, int[] values) throws FileNotFoundException{
	  Scanner myReader = new Scanner(new File(fileIn));
	  PrintWriter myWriter = new PrintWriter(fileOut);
	  while (myReader.hasNextInt()){
		  int value = myReader.nextInt();
		  int index = 0;
		  boolean exists = false;
		  while(!exists && index < values.length){
		     exists = (value == values[index]);
			 index ++;
		  }
		  if(exists){
			  myWriter.println(value);  
		  }
	  }
	  myReader.close();
	  myWriter.close();
  }
}
