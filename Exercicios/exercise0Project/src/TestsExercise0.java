import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class TestsExercise0 {

    public static final String eol = System.getProperty("line.separator");

	@Test
	public void testCopyText () throws IOException {

		String expected = "abcdefghijklmnopqrstuvwxyz" + eol;

		String text = "abcdefghijklmnopqrstuvwxyz";

		PrintWriter in = new PrintWriter("input.txt");
		in.write(text);
		in.close();

		Exercise0.copyText("input.txt", "output.txt");
		String obtainedText = new String(Files.readAllBytes(Paths.get("output.txt")), StandardCharsets.UTF_8);

		assertEquals(expected, obtainedText);
	}

	@Test
	public void testLowerUpper () throws IOException {
		String expected = "abcdefghi" + eol + "JKLMNOPQRSTUVWXYZ" + eol;

		String text = "ABcdefghi" + eol + "jklmnopqrstuvWXYZ";

		PrintWriter in = new PrintWriter("input.txt");
		in.write(text);
		in.close();

		Exercise0.lowerUpper("input.txt", "output.txt");
		String obtainedText = new String(Files.readAllBytes(Paths.get("output.txt")), StandardCharsets.UTF_8);

		assertEquals(expected, obtainedText);
	}

	@Test
	public void testWriteMultiples () throws IOException {
		String expected = 	"12" + eol + "30" + eol + "15" + eol;

		String text = "12" + eol + "11" + eol + "30" + eol + "15" + eol + "32";

		PrintWriter in = new PrintWriter("input.txt");
		in.write(text);
		in.close();

		Exercise0.writeMultiples("input.txt", "output.txt",3);
		String obtainedText = new String(Files.readAllBytes(Paths.get("output.txt")), StandardCharsets.UTF_8);

		assertEquals(expected, obtainedText);
	}

	@Test
	public void testWriteSquares () throws IOException {
		String expected = 	"25" + eol + "4" + eol + "36" + eol + "16" + eol;

		String text = "5" + eol + "2" + eol + "6" + eol + "4" + eol;
		PrintWriter in = new PrintWriter("input.txt");
		in.write(text);
		in.close();

		Exercise0.writeSquares("input.txt", "output.txt");
		String obtainedText = new String(Files.readAllBytes(Paths.get("output.txt")), StandardCharsets.UTF_8);

		assertEquals(expected, obtainedText);	
	}

	@Test
	public void testCommonElements () throws IOException {

		String expected = 	"1" + eol + "5" + eol + "11" + eol + "21" + eol +
				            "15" + eol + "1" + eol;

	    int[] vals = {1, 5, 11, 15, 21, 25};

		String text = "1"+ eol + "4" + eol + "5" + eol + "2" + eol + "4"+ eol + 
		               "11"+ eol + "33" + eol + "21" + eol + "123" + eol+ 
				       "15" + eol + "10" + eol + "1";
		PrintWriter in = new PrintWriter("input.txt");
		in.write(text);
		in.close();
		
		Exercise0.commonElements("input.txt", "output.txt",vals);
		String obtainedText = new String(Files.readAllBytes(Paths.get("output.txt")), StandardCharsets.UTF_8);
		assertEquals(expected, obtainedText);	
	}
}
