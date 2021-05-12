/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception { //run before each test
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		System.out.println(shortList.get(0));
		
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer) i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		for (int i = 0; i < 2; i++ ) {
			if (i == 0) assertEquals("Remove: iterate the loop", (Integer) 21, list1.get(i));
			if (i == 1) assertEquals("Remove: iterate the loop", (Integer) 42, list1.get(i));
		}
		// TODO: Add more tests here
		for (int i = 0; i < 10; i ++) {
			emptyList.add(i);
		}

		try {
			emptyList.remove(10);
			fail("Check index out of bounds");
		} catch (IndexOutOfBoundsException e) {
			System.out.println();
		}
		
		try {
			emptyList.remove(-1);
			fail("Check index out of bounds");
		} catch (IndexOutOfBoundsException e) {
			
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test

		//test1
		try {
			shortList.add(null);
			fail("Check out null");
		} catch (NullPointerException e) {
			//System.out.println("null element added");
		}
		//test2
		boolean b = shortList.add("C");
		assertEquals("Check the size of the new list", 3, shortList.size);
		assertEquals("Check element added", "C", shortList.get(2));
		assertEquals("Check success", true, b);
		assertEquals("Check tail.prev", "C", shortList.tail.prev.data);
		assertEquals("Check previous node", "B", shortList.tail.prev.prev.data);
		assertEquals("Check tail node", null, shortList.tail.prev.next.next);

		for(int i = 0; i < shortList.size; i++ ) {
			char character = shortList.get(i).charAt(0);
			//System.out.println(character);
			assertEquals("Check "+i+ " element", 65+i, (int) character);
		}
		//test3
		try {
			emptyList.add(null);
			fail("Check out null");
		} catch (NullPointerException e) {
			//System.out.println("null element added");
		}
		//test4
		boolean bb = emptyList.add(0);
		assertEquals("Check the size of the new list", 1, emptyList.size);
		assertEquals("Check element added", (Integer) 0, emptyList.get(0));
		assertEquals("Check success", true, bb);
		assertEquals("Check tail.prev", (Integer) 0, emptyList.tail.prev.data);
		assertEquals("Check previous node", null, emptyList.tail.prev.prev.prev);
		assertEquals("Check tail node", null, emptyList.head.next.next.next);

		for(int i = 0; i < emptyList.size; i++ ) {
			int number = emptyList.get(i);
			//System.out.println(character);
			assertEquals("Check "+i+ " element", (Integer) 0, (Integer) i);
		}
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		
		assertEquals("Check the size", 2, shortList.size);
		assertEquals("Check the size", 0, emptyList.size);
		assertEquals("Check the size", LONG_LIST_LENGTH, longerList.size);

	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		for (int i = 0; i < 10; i ++) {
			emptyList.add(i);
		}

		try {
			emptyList.add(11, 11);
			fail("Check index out of bounds");
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			emptyList.add(-1, 100);
			fail("Check index out of bounds");
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			emptyList.add(1, null);
			fail("Check out null");
		} catch (NullPointerException e) {
			
		}
		
		emptyList.add(9, 10);
		assertEquals("Check element at index 9", (Integer) 10, emptyList.get(9));
		assertEquals("Check the size of the new list", 11, emptyList.size);
		assertEquals("Check next node", (Integer) 9, emptyList.tail.prev.prev.next.data);
		assertEquals("Check previous node", (Integer) 8, emptyList.tail.prev.prev.prev.data);
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		for (int i = 0; i < 10; i ++) {
			emptyList.add(i);
		}

		try {
			emptyList.set(10, 10);
			fail("Check index out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}

		try {
			emptyList.set(-1, 100);
			fail("Check index out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}

		try {
			emptyList.set(1, null);
			fail("Check out null");
		} catch (NullPointerException e) {
		
		}
	
		int a = emptyList.set(5, 10);
		assertEquals("Check old element at index 5", (Integer) 5, (Integer) a);
		assertEquals("Check new element at index 5", (Integer) 10, emptyList.get(5));
	// TODO: Optionally add more test methods.
	}
}
