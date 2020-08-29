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
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		
		if (element == null) {
			throw new NullPointerException("null elements are now allowed - add()");
		}
		
		LLNode<E> n = new LLNode<E>(element);
		n.prev = tail.prev;
		n.next = n.prev.next;
		n.prev.next = n;
		tail.prev = n;
		size++;
		
		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.

		if ((index >= size) || (size == 0) || (index <0)) {
			throw new
			IndexOutOfBoundsException("this element does not Exist - get() - 1"); } 

		LLNode<E> curr = head;
		for (int i = 0; i <= index; i++) {
			curr = curr.next;
			if (i == index) {

				return curr.data;
			}

		}
		return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		
		if ((index < 0) || (index > size())) {
			throw new 
			IndexOutOfBoundsException("This index does not exist - add(int, E)");
		}
		if (element == null) {
			throw new 
			NullPointerException("null element can not be added");
		}
		
		LLNode<E> toAdd = new LLNode<E>(element);
		LLNode<E> curr = head;
		for (int i = 0; i <= index; i++) {
			curr = curr.next;
			if (i == index) {
				toAdd.next = curr;
				toAdd.prev = curr.prev;
				curr.prev = toAdd;
				toAdd.prev.next = toAdd;
				size++;
			}
		}
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
		if ((index < 0) || (index > size())) {
			throw new 
			IndexOutOfBoundsException("This index does not exist - add(int, E)");
		}
		
		LLNode<E> curr = head;
		for (int i = 0; i <= index; i++) {
			curr = curr.next;
			if (i == index) {
				curr.prev.next = curr.next;
				curr.next.prev = curr.prev;
				curr.next = null;
				curr.prev = null;
				size--;
				return curr.data;
				
			}
		}
		return null;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		
		if ((index < 0) || (index >= size())) {
			throw new 
			IndexOutOfBoundsException("This index does not exist - add(int, E)");
		}
		
		if (element == null) {
			throw new 
			NullPointerException("Null Element can not be added");
		}
		
		LLNode<E> curr = head;
		LLNode<E> temp = new LLNode<E>(element);
		
		for (int i = 0; i <= index; i++) {
			curr = curr.next;
			
			if (i == index) {
				temp.data = curr.data;
				curr.data = element;
				return temp.data;
			}
		}
		return null;
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

}
