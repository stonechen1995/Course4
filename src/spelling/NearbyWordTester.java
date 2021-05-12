package spelling;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class NearbyWordTester {
	
	String s0;
	String s1;
	String s2;
	String s3;
	String s4;
	NearbyWords nbw;
	List<String> s0List;
	List<String> s1List;
	List<String> s2List;
	List<String> s3List;
	List<String> s4List;

	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		s0 = "";
		s1 = null;
		s2 = "o";
 		s3 = "wan";
		
		Dictionary d = new DictionaryHashSet();
        DictionaryLoader.loadDictionary(d, "test_cases/dict.txt");
		nbw = new NearbyWords(d);
		
		s0List = new ArrayList<String>();
		s1List = new ArrayList<String>();
		s2List = new ArrayList<String>();
		s3List = new ArrayList<String>();
		s4List = new ArrayList<String>();

	}
	
	/** Test the insertions method */
	@Test
	public void insertionsTester()
	{
		try {
			nbw.insertions(s0, s0List, true);
		} catch (NullPointerException e) {
			;
		}
		for (String ss : s0List) {
			System.out.println(ss);
		}
		assertEquals("Testing insertions on s0", false, (s0List.contains("a")));
		s0List.clear();
		
		nbw.insertions(s0, s0List, false);
		assertEquals("Testing insertions on s0", false, (s0List.contains("a")));

		nbw.insertions(s1, s1List, true);
		
		nbw.insertions(s2, s2List, true);
		
		nbw.insertions(s3, s3List, true);
		assertEquals("Testing insertions on s1", true, (s1List.contains("want") && s1List.contains("wand")));

		nbw.insertions(s4, s4List, true);
		
		

		
	}	
	
	
	
	
}
