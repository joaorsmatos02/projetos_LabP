package tutorial1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * @author Joao Matos  fc56292
 *
 */

public class organizeException {
   public static void main(String[] args) {
	   organizeExceptionsExample("myInput.txt", "myOutput.txt");
   }
   
   /**
   * Read integers from a texto file e write their logarithm, by reverse order,
   * into another file.
   * @param fileInName – The name of the file containg the integers
   * @param fileOutName – The name of the file where the logs are to be written.
   * @requires fileInName!= null && fileOutName!= null
   */
   public static void organizeExceptionsExample(String fileInName,
                                                String fileOutName) {
	   try (Scanner sc = new Scanner(new File(fileInName));
                PrintWriter out = new PrintWriter(fileOutName)) {
		   
       Stack<Integer> myStack = new Stack<Integer>();
       
       while(sc.hasNextLine()) {
           String newLine = sc.nextLine();
           myStack.push(Integer.parseInt(newLine));
       }
       for (int number : myStack) {
           Double x = Math.log(number);
           out.write(x.toString());
       }
      } catch(NumberFormatException e) {
           System.out.println("Nao consegue converter num Integer");
      } catch(InputMismatchException e) {
           System.out.println("O valor nao tem o formato esperado");
      } catch(RuntimeException e) {
           System.out.println("Durante a execucao algo correu mal");
      } catch(FileNotFoundException e) {
           System.out.println("O ficheiro nao existe!");
      } catch(Exception e) {
           System.out.println("Algo correu mal!");
      }
   }
}
