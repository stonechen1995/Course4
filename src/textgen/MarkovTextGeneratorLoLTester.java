package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.runners.*;

import org.junit.BeforeClass;
import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MarkovTextGeneratorLoLTester {
	static MarkovTextGeneratorLoL gen_shortString;
	static MarkovTextGeneratorLoL gen_longString;
	static MarkovTextGeneratorLoL gen_nullString;
	static MarkovTextGeneratorLoL gen_emptyString;
	static MarkovTextGeneratorLoL gen_noTrainString;
	static String shortString;
	static String longString;
	static String nullString;
	static String emptyString;
	
	@BeforeClass
	public static void setUpClass() throws Exception { //run before each test
		//TODO:
		gen_shortString = new MarkovTextGeneratorLoL(new Random());
		gen_longString = new MarkovTextGeneratorLoL(new Random());
		gen_nullString = new MarkovTextGeneratorLoL(new Random());
		gen_emptyString = new MarkovTextGeneratorLoL(new Random());
		gen_noTrainString = new MarkovTextGeneratorLoL(new Random());
		longString = "Hello.  Hello there.  This is a test.  "
				+ "Hello there.  Hello Bob.  Test again.";
		shortString = "Hello";
		nullString = null;
		emptyString = "";
	}
	
	@Test
	public void test1Train()
	{
		{
			System.out.println("testTrain 1: longString");
			gen_longString.train(longString);
			System.out.println(gen_longString);
			System.out.println("");
		}
		{
			System.out.println("testTrain 2: shortString");
			gen_shortString.train(shortString);
			System.out.println(gen_shortString);
			System.out.println("");
		}
		{
			System.out.println("testTrain 3: emptyString");
			gen_emptyString.train(emptyString);
			System.out.println("pass\n");
			
		}
		{
			try {
				System.out.println("testTrain 4: nullString");
				gen_nullString.train(nullString);
				fail("check out null");
			} catch (NullPointerException e) {
				System.out.println("pass\n");
			}
		}
	}
	
	@Test
	public void test2GenerateText() {
		/*test gen_longString*/
		{ //testGenerateText 1
			int num = 100; //modify this

			System.out.println("testGenerateText 1: longString");
			String str = gen_longString.generateText(num);
			int len = 0; 
			if (str.split(" ")[0].equals("")) {
				len = str.split(" ").length-1;
			} else {
				len = str.split(" ").length;
			}
			assertEquals("testGenerateText 1", num, len);
			System.out.println("pass\n");
		}
		{ //testGenerateText 2
			int num = 0; //modify this

			System.out.println("testGenerateText 2: longString");
			String str = gen_longString.generateText(num);
			int len = 0; 
			if (str.split(" ")[0].equals("")) {
				len = str.split(" ").length-1;
			} else {
				len = str.split(" ").length;
			}
			assertEquals("testGenerateText 2", num, len);
			System.out.println("pass\n");
		}
		{ //testGenerateText 3
			System.out.println("testGenerateText 3: longString");
			try {
				gen_longString.generateText(-1);
				fail("testGenerateText 3 -- check index out of bounds");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("pass\n");
			}
		}
		
		/*test gen_shortString*/
		{ //testGenerateText 4
			int num = 1; //modify this

			System.out.println("testGenerateText 4: shortString");
			String str = gen_shortString.generateText(num);
			int len = 0; 
			if (str.split(" ")[0].equals("")) {
				len = str.split(" ").length-1;
			} else {
				len = str.split(" ").length;
			}
			assertEquals("testGenerateText 2", num, len);
			System.out.println("pass\n");		}
		{ //testGenerateText 5
			int num = 0; //modify this

			System.out.println("testGenerateText 5: shortString");
			String str = gen_shortString.generateText(num);
			int len = 0; 
			if (str.split(" ")[0].equals("")) {
				len = str.split(" ").length-1;
			} else {
				len = str.split(" ").length;
			}
			assertEquals("testGenerateText 2", num, len);
			System.out.println("pass\n");		}

		/*test gen_emptyString*/
		{ //testGenerateText 6
			System.out.println("testGenerateText 6: emptyString");
			assertEquals("testGenerateText 6", gen_emptyString.generateText(0), "");
			System.out.println("pass\n");
		}
		
		{ //testGenerateText 6.5
			System.out.println("testGenerateText 6.5: emptyString");
			assertEquals("testGenerateText 6.5", gen_emptyString.generateText(10), "");
			System.out.println("pass\n");
		}
		
		{ //testGenerateText 6.6
			System.out.println("testGenerateText 6.6: emptyString");
			try {
				gen_emptyString.generateText(-1);
				fail("check out null");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("pass\n");
			}
		}
		
		/*test gen_nullString*/
		{ //testGenerateText 7
			System.out.println("testGenerateText 7: nullString");
			assertEquals("testGenerateText 7", gen_noTrainString.generateText(0), "");
			System.out.println("pass\n");
		}
		
		/*generate text before training*/
		{ //testGenerateText 8
			System.out.println("testGenerateText 8: generate text before training");
			assertEquals("testGenerateText 8", "", gen_noTrainString.generateText(10));
			System.out.println("pass\n");
		}
		
	}
}
