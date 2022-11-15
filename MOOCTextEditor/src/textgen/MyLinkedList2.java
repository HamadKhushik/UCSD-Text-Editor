//package textgen;
//
//import java.util.AbstractList;
//
//
///** A class that implements a doubly linked list
// * 
// * @author UC San Diego Intermediate Programming MOOC team
// *
// * @param <E> The type of the elements stored in the list
// */
//public class MyLinkedList2<E> extends AbstractList<E> {
//	LLNode<E> head;
//	LLNode<E> tail;
//	int size;
//
//	/** Create a new empty LinkedList */
//	public MyLinkedList2() {
//		// TODO: Implement this method
//		size = 0;
//		head = tail;
//		tail = head;
//	}
//
//	/**
//	 * Appends an element to the end of the list
//	 * @param element The element to add
//	 */
//	public boolean add(E element ) 
//	{
//		// TODO: Implement this method
//		
//		if (element == null) {
//			throw new NullPointerException("null elements are now allowed - add()");
//		}
//		
//		if (size == 0) {
//		LLNode<E> n = new LLNode<E>(element, null, null);
//		head = n;
//		tail = n;
//		size++;
//		}
//		else {
//			LLNode<E> n = new LLNode<E>(element, null, this.tail);
//			this.tail.next = n;
//			this.tail = n;
//			size++;
//		}
//		
//		return true;
//	}
//
//	/** Get the element at position index 
//	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
//	public E get(int index) 
//	{
//		// TODO: Implement this method.
//
//		if ((index >= size) || (size == 0) || (index <0)) {
//			throw new
//			IndexOutOfBoundsException("this element does not Exist - get() - 1"); } 
//
//		LLNode<E> curr = head;
//		for (int i = 0; i <= index; i++) {
//			
//			if (i != 0) {  // i its nor first iteration, update curr
//				curr = curr.next;
//			}
//			
//			if (i == index) {
//
//				return curr.data;
//			}
//
//		}
//		return null;
//	}
//
//	/**
//	 * Add an element to the list at the specified index
//	 * @param The index where the element should be added
//	 * @param element The element to add
//	 */
//	public void add(int index, E element ) 
//	{
//		// TODO: Implement this method
//		
//		if ((index < 0) || (index > size())) {
//			throw new 
//			IndexOutOfBoundsException("This index does not exist - add(int, E)");
//		}
//		if (element == null) {
//			throw new 
//			NullPointerException("null element can not be added");
//		}
//		if (index == size()) {
//			add(element);
//		}
//		else {
//		
//		LLNode<E> toAdd;
//		LLNode<E> curr = head;
//		for (int i = 0; i <= index; i++) {
//			if (i == index) {
//				toAdd = new LLNode<E>(element, curr, curr.prev);
//				curr.prev.next = toAdd;
//				curr.prev = toAdd;
//				size++;
//			}
//			curr = curr.next;
//		}
//		}
//	}
//
//
//	/** Return the size of the list */
//	public int size() 
//	{
//		// TODO: Implement this method
//		return size;
//	}
//
//	/** Remove a node at the specified index and return its data element.
//	 * @param index The index of the element to remove
//	 * @return The data element removed
//	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
//	 * 
//	 */
//	public E remove(int index) 
//	{
//		// TODO: Implement this method
//		if ((index < 0) || (index > size())) {
//			throw new 
//			IndexOutOfBoundsException("This index does not exist - add(int, E)");
//		}
//		
//		LLNode<E> curr = head;
//		if (curr.next == null && tail.prev == null) {  // special case: when the only element in the list is to be deleted
//			head = tail;
//			tail = head;
//			size--;
//			return curr.data;
//		}
//		else {
//			
//		
//		for (int i = 0; i <= index; i++) {
//			if (i != 0) {  // if its nor the first iteration, update curr [as there are no sentinel nodes]
//			curr = curr.next;
//			}
//			if (i == index) {
//				
//				if (head == curr) {  // special case when the first element needs to be deleted
//					head = curr.next;
//					curr.next.prev = curr.prev;
//					size--;
//					return curr.data;
//				}
//				if (tail == curr) {  // special case when last element needs to be deleted
//					tail = curr.prev;
//					curr.prev.next = null;
//					size--;
//					return curr.data;
//				} 
//				else{					// when element to be deleted is in the middle
//					
//					curr.prev.next = curr.next;
//					curr.next.prev = curr.prev;
//					
//				}
//				
//  				
//				curr.next = null;
//				curr.prev = null;
//				size--;
//				return curr.data;
//				
//			}
//		}
//		}
//		return null;
//	}
//
//	/**
//	 * Set an index position in the list to a new element
//	 * @param index The index of the element to change
//	 * @param element The new element
//	 * @return The element that was replaced
//	 * @throws IndexOutOfBoundsException if the index is out of bounds.
//	 */
//	public E set(int index, E element) 
//	{
//		// TODO: Implement this method
//		
//		if ((index < 0) || (index >= size())) {
//			throw new 
//			IndexOutOfBoundsException("This index does not exist - add(int, E)");
//		}
//		
//		if (element == null) {
//			throw new 
//			NullPointerException("Null Element can not be added");
//		}
//		
//		LLNode<E> curr = head;
//		LLNode<E> temp = new LLNode<E>(element);
//		
//		for (int i = 0; i <= index; i++) {
//			
//			if (i != 0) {
//			curr = curr.next;
//			}
//			
//			if (i == index) {
//				temp.data = curr.data;
//				curr.data = element;
//				return temp.data;
//			}
//		}
//		return null;
//	}   
//	
//	public String toString() {
//		
//		String result = "";
//		LLNode<E> curr = head;
//		while (curr.next != null) {
//			result += curr.data;
//			if (curr.next != null) {
//				result += ", ";
//			}
//			curr = curr.next;
//		}
//		return result;
//	}
//}
//
///*
// * class LLNode<E> { LLNode<E> prev; LLNode<E> next; E data;
// * 
// * // TODO: Add any other methods you think are useful here // E.g. you might
// * want to add another constructor
// * 
// * public LLNode(E e) { this.data = e; this.prev = null; this.next = null; }
// * 
// * public LLNode(E e, LLNode<E> forward, LLNode<E> backward) {
// * 
// * this.data = e; this.next = forward; this.prev = backward;
// * 
// * }
// * 
// * public String toString() { return data.toString(); }
// * 
// * }
// */