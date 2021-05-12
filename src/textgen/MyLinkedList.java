package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		tail.prev = head;
		head.next = tail;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) throws NullPointerException
	{
		// TODO: Implement this method
		if (element == null) throw new NullPointerException();

		LLNode<E> last = tail.prev;
		LLNode<E> newAdded = new LLNode<E>(null);
		last.next = newAdded;
		newAdded.prev = last;
		newAdded.next = tail;
		newAdded.data = element;
		tail.prev = newAdded;
		this.size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method.
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> current = head;
		for (int i = -1; i < index; i++) {
			current = current.next;
		}
		return current.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element) throws NullPointerException, 
													IndexOutOfBoundsException
	{
		// TODO: Implement this method
		if (element == null) throw new NullPointerException();
		if (index < 0 || index > size) throw new IndexOutOfBoundsException();
		
		if (size == 0) {
			new LLNode<E>(element, head, tail); 
		} else {
			LLNode<E> curr = getNode(index);
			new LLNode<E>(element, curr.prev, curr);
		}

		this.size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		LLNode<E> removed = getNode(index);
		E element = removed.data;
		LLNode<E> after = getNode(index).next;
		LLNode<E> before = getNode(index).prev;
		after.prev = removed.prev;
		before.next = after;
		
		this.size--;
		
		return element;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) throws NullPointerException
	{
		// TODO: Implement this method
		if (element == null) throw new NullPointerException();
		
		E old = get(index);
		getNode(index).data = element;
		return old;
	}   
	
	public LLNode<E> getNode(int index){
		if(index >= size || index<0) {
			throw new IndexOutOfBoundsException("Index "+ index+" is out of bounds");
		}
		LLNode<E> holder = head;
		for(int i = 0; i <= index; i++) {
				holder = holder.next;
		}
		return holder;
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode(E e, LLNode<E> prev, LLNode<E> next) 
	{
		this.data = e;
		this.prev = prev;
		this.next = next;
		this.next.prev = this;
		this.prev.next = this;
	}
	
}
