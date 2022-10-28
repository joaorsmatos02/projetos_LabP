import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

/**
 * 
 * Tests the RecognizePatterns class
 * 
 * @author João Matos nº 56292
 *
 */
public class Tests {

	@Test
	/**
	 * Tests an identifier satisfying: starts with an uppercase letter: true has
	 * only letters, digits, _ or $: true has k characters: k = 1
	 */
	public void testJavaClassIdentifier_mTnTk1() {
		assertTrue(RecognisePatterns.isJavaClassIdentifier("A"));
	}

	@Test
	/**
	 * Tests an identifier satisfying: starts with an uppercase letter: true has
	 * only letters, digits, _ or $: true has k characters: k = 5
	 */
	public void testJavaClassIdentifier_mTnTk2() {
		assertTrue(RecognisePatterns.isJavaClassIdentifier("Bb_$1"));
	}

	@Test
	/**
	 * Tests an identifier satisfying: starts with an uppercase letter: true has
	 * only letters, digits, _ or $: false has k characters: k > 1
	 */
	public void testJavaClassIdentifier_mTnFk2() {
		assertFalse(RecognisePatterns.isJavaClassIdentifier("C+"));
	}

	@Test
	/**
	 * Tests an identifier satisfying: starts with an uppercase letter: false has
	 * only letters, digits, _ or $: false has k characters: k = 0
	 */
	public void testJavaClassIdentifier_mTnTk0() {
		assertFalse(RecognisePatterns.isJavaClassIdentifier(""));
	}

	@Test
	/**
	 * Tests an identifier satisfying: starts with an uppercase letter: false has
	 * only letters, digits, _ or $: true has k characters: k = 1
	 */
	public void testJavaClassIdentifier_mFnTk1() {
		assertFalse(RecognisePatterns.isJavaClassIdentifier("d"));
	}

	@Test
	/**
	 * Tests an identifier satisfying: starts with an uppercase letter: false has
	 * only letters, digits, _ or $: true has k characters: k = 5
	 */
	public void testJavaClassIdentifier_mFnTk2() {
		assertFalse(RecognisePatterns.isJavaClassIdentifier("ef_12"));
	}

	@Test
	/**
	 * Tests an identifier satisfying: starts with an uppercase letter: false has
	 * only letters, digits, _ or $: false has k characters: k = 1
	 */
	public void testJavaClassIdentifier_mFnFk1() {
		assertFalse(RecognisePatterns.isJavaClassIdentifier("."));
	}

	@Test
	/**
	 * Tests an identifier satisfying: starts with an uppercase letter: false has
	 * only letters, digits, _ or $: false has k characters: k = 4
	 */
	public void testJavaClassIdentifier_mFnFk2() {
		assertFalse(RecognisePatterns.isJavaClassIdentifier("_Ef+"));
	}

	/////////////////////////

	@Test
	/**
	 * Tests a string with timestamp satisfying: has correct number limit: true, has
	 * correct separators: true, correctly placed blank characters: true, number of
	 * sections: 4
	 */
	public void testMatchTimeStampLiteral1() {
		assertTrue(RecognisePatterns.matchTimeStampLiteral("8:26:30.543"));
	}

	/**
	 * Tests a string with timestamp satisfying: has correct number limit: true, has
	 * correct separators and is separated from other text: true, correctly placed
	 * blank characters: true, number of sections: 3
	 */
	@Test
	public void testMatchTimeStampLiteral2() {
		assertTrue(RecognisePatterns.matchTimeStampLiteral("08:06:05"));
	}

	@Test
	/**
	 * Tests a string with timestamp satisfying: has correct number limit: true, has
	 * correct separators: true, correctly placed blank characters: true, number of
	 * sections: 2
	 */
	public void testMatchTimeStampLiteral3() {
		assertFalse(RecognisePatterns.matchTimeStampLiteral("08:06"));
	}

	@Test
	/**
	 * Tests a string with timestamp satisfying: has correct number limit: true, has
	 * correct separators: true, correctly placed blank characters: false, number of
	 * sections: 4
	 */
	public void testMatchTimeStampLiteral4() {
		assertFalse(RecognisePatterns.matchTimeStampLiteral("a8:06:09:456"));
	}

	@Test
	/**
	 * Tests a string with timestamp satisfying: has correct number limit: true, has
	 * correct separators: true, correctly placed blank characters: false, number of
	 * sections: 3
	 */
	public void testMatchTimeStampLiteral5() {
		assertFalse(RecognisePatterns.matchTimeStampLiteral("08: 6:09"));
	}

	@Test
	/**
	 * Tests a string with timestamp satisfying: has correct number limit: true, has
	 * correct separators: false, correctly placed blank characters: true, number of
	 * sections: 4
	 */
	public void testMatchTimeStampLiteral6() {
		assertFalse(RecognisePatterns.matchTimeStampLiteral("08:06.09.456"));
	}

	@Test
	/**
	 * Tests a string with timestamp satisfying: has correct number limit: false,
	 * has correct separators: true, correctly placed blank characters: true, number
	 * of sections: 4
	 */
	public void testMatchTimeStampLiteral7() {
		assertFalse(RecognisePatterns.matchTimeStampLiteral("25:06:09.456"));
	}

	@Test
	/**
	 * Tests a string with timestamp satisfying: has correct number limit: false,
	 * has correct separators: true, correctly placed blank characters: true, number
	 * of sections: 3
	 */
	public void testMatchTimeStampLiteral8() {
		assertFalse(RecognisePatterns.matchTimeStampLiteral("08:78:09"));
	}

	@Test
	/**
	 * Tests a string with timestamp satisfying: has correct number limit: true, has
	 * correct separators: false, correctly placed blank characters: false, number
	 * of sections: 4
	 */
	public void testMatchTimeStampLiteral9() {
		assertFalse(RecognisePatterns.matchTimeStampLiteral("17: 58!12.89"));
	}

	@Test
	/**
	 * Tests a string with timestamp satisfying: has correct number limit: false,
	 * has correct separators: false, correctly placed blank characters: true,
	 * number of sections: 3
	 */
	public void testMatchTimeStampLiteral10() {
		assertFalse(RecognisePatterns.matchTimeStampLiteral("17:68!12"));
	}

	@Test
	/**
	 * Tests a string with timestamp satisfying: has correct number limit: false,
	 * has correct separators: false, correctly placed blank characters: false,
	 * number of sections: 3
	 */
	public void testMatchTimeStampLiteral11() {
		assertFalse(RecognisePatterns.matchTimeStampLiteral("17, 68| 45"));
	}

	/////////////////////////

	/*
	 * In this method there can be any number of spaces and the first section is
	 * optional
	 */

	@Test
	/**
	 * Tests a string with timestamp satisfying: has first section: true, even
	 * number of spaces: true, acceptable contents: true, correct separators: true,
	 * number of elements: 1|0
	 */
	public void testMatchListNotation1() {
		assertTrue(RecognisePatterns.matchListNotation("1|<>"));
	}

	@Test
	/**
	 * Tests a string with timestamp satisfying: has first section: true, even
	 * number of spaces: true, acceptable contents: true, correct separators: true,
	 * number of elements: 1|3
	 */
	public void testMatchListNotation2() {
		assertTrue(RecognisePatterns.matchListNotation("1 | < 2, 3, 4 >"));
	}

	@Test
	/**
	 * Tests a string with timestamp satisfying: has first section: false, even
	 * number of spaces: true, acceptable contents: true, correct separators: true,
	 * number of elements: 0|5
	 */
	public void testMatchListNotation3() {
		assertTrue(RecognisePatterns.matchListNotation("< 12, 13, 1, 123, 456>"));
	}

	@Test
	/**
	 * Tests a string with timestamp satisfying: has first section: false, even
	 * number of spaces: true, acceptable contents: true, correct separators: true,
	 * number of elements: 0|0
	 */
	public void testMatchListNotation4() {
		assertTrue(RecognisePatterns.matchListNotation("<  >"));
	}

	@Test
	/**
	 * Tests a string with timestamp satisfying: has first section: true, even
	 * number of spaces: true, acceptable contents: false, correct separators: true,
	 * number of elements: 1|5
	 */
	public void testMatchListNotation5() {
		assertFalse(RecognisePatterns.matchListNotation("1 | < 12, a, 1, 123, 456>"));
	}

	@Test
	/**
	 * Tests a string with timestamp satisfying: has first section: false, even
	 * number of spaces: true, acceptable contents: true, correct separators: true,
	 * number of elements: 0|5
	 */
	public void testMatchListNotation6() {
		assertTrue(RecognisePatterns.matchListNotation("teste< 12, 13, 1, 123, 456>teste"));
	}

	@Test
	/**
	 * Tests a string with timestamp satisfying: has first section: false, even
	 * number of spaces: true, acceptable contents: true, correct separators: false,
	 * number of elements: 1|3
	 */
	public void testMatchListNotation7() {
		assertFalse(RecognisePatterns.matchListNotation("1 | 2, 3, 4 >"));
	}

	@Test
	/**
	 * Tests a string with timestamp satisfying: has first section: true, even
	 * number of spaces: false, acceptable contents: true, correct separators: true,
	 * number of elements: 1|3
	 */
	public void testMatchListNotation8() {
		assertTrue(RecognisePatterns.matchListNotation("1 | <        2,3    ,    4 >"));
	}

	@Test
	/**
	 * Tests a string with timestamp satisfying: has first section: true, even
	 * number of spaces: true, acceptable contents: true, correct separators: false,
	 * number of elements: 2|3
	 */
	public void testMatchListNotation9() {
		assertFalse(RecognisePatterns.matchListNotation("1, 2, < 1 | 2 | 3 >"));
	}

	/**
	 * Tests a string with timestamp satisfying: has first section: false, even
	 * number of spaces: true, acceptable contents: false, correct separators: true,
	 * number of elements: 0|3
	 */
	@Test
	public void testMatchListNotation10() {
		assertFalse(RecognisePatterns.matchListNotation("< a, 2, 8 >"));
	}

	@Test
	/**
	 * Tests a string with timestamp satisfying: has first section: true, even
	 * number of spaces: true, acceptable contents: false, correct separators:
	 * false, number of elements: 2|3
	 */
	public void testMatchListNotation11() {
		assertFalse(RecognisePatterns.matchListNotation("1, 2, < 1 , n | 3 >"));
	}

	///////////////////////

	/*
	 * In this method the decimal part is optional
	 */

	@Test
	/**
	 * Tests a string with numbers in scientific notation satisfying: how many
	 * correct numbers: 1, has decimal part: true, has correct spacing: true, has
	 * correct format : true
	 */
	public void testNumbersInScientificNotation1() {
		LinkedList<Double> res = new LinkedList<>();
		res.add(10.08E-3);
		assertEquals(res, RecognisePatterns.numbersInScientificNotation("teste 10.08E-3 teste"));
	}

	@Test
	/**
	 * Tests a string with numbers in scientific notation satisfying: how many
	 * correct numbers: 1, has decimal part: true, has correct spacing: true, has
	 * correct format : true
	 */
	public void testNumbersInScientificNotation2() {
		LinkedList<Double> res = new LinkedList<>();
		res.add(10.08E-3);
		assertEquals(res, RecognisePatterns.numbersInScientificNotation("teste10.08E-3teste"));
	}

	@Test
	/**
	 * Tests a string with numbers in scientific notation satisfying: how many
	 * correct numbers: 2, has decimal part: true, has correct spacing: true, has
	 * correct format : true
	 */
	public void testNumbersInScientificNotation3() {
		LinkedList<Double> res = new LinkedList<>();
		res.add(-10.08E-3);
		res.add(+213E4);
		assertEquals(res, RecognisePatterns.numbersInScientificNotation("teste -10.08E-3+213E4 teste"));
	}

	@Test
	/**
	 * Tests a string with numbers in scientific notation satisfying: how many
	 * correct numbers: 1, has decimal part: true, has correct spacing: true, has
	 * correct format : false
	 */
	public void testNumbersInScientificNotation4() {
		LinkedList<Double> res = new LinkedList<>();
		assertEquals(res, RecognisePatterns.numbersInScientificNotation("teste 10.08C-3 teste"));
	}

	/**
	 * Tests a string with numbers in scientific notation satisfying: how many
	 * correct numbers: 1, has decimal part: false, has correct spacing: true, has
	 * correct format : true
	 */
	@Test
	public void testNumbersInScientificNotation5() {
		LinkedList<Double> res = new LinkedList<>();
		res.add(0E0);
		assertEquals(res, RecognisePatterns.numbersInScientificNotation("teste 0E0 teste"));
	}

	@Test
	/**
	 * Tests a string with numbers in scientific notation satisfying: how many
	 * correct numbers: 0, has decimal part: true, has correct spacing: true, has
	 * correct format : false
	 */
	public void testNumbersInScientificNotation6() {
		LinkedList<Double> res = new LinkedList<>();
		assertEquals(res, RecognisePatterns.numbersInScientificNotation("teste 20.8E teste"));
	}

	@Test
	/**
	 * Tests a string with numbers in scientific notation satisfying: how many
	 * correct numbers: 0, has decimal part: false, has correct spacing: true, has
	 * correct format : false
	 */
	public void testNumbersInScientificNotation7() {
		LinkedList<Double> res = new LinkedList<>();
		assertEquals(res, RecognisePatterns.numbersInScientificNotation("teste E7 teste"));
	}

	@Test
	/**
	 * Tests a string with numbers in scientific notation satisfying: how many
	 * correct numbers: 0, has decimal part: true, has correct spacing: false, has
	 * correct format : true
	 */
	public void testNumbersInScientificNotation8() {
		LinkedList<Double> res = new LinkedList<>();
		assertEquals(res, RecognisePatterns.numbersInScientificNotation("teste 12.4E -3 teste"));
	}

	@Test
	/**
	 * Tests a string with numbers in scientific notation satisfying: how many
	 * correct numbers: 0, has decimal part: true, has correct spacing: false, has
	 * correct format : false
	 */
	public void testNumbersInScientificNotation9() {
		LinkedList<Double> res = new LinkedList<>();
		assertEquals(res, RecognisePatterns.numbersInScientificNotation("teste 12.4F -3 teste"));
	}
}
