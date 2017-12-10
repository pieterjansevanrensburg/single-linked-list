package linkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/***
 * An Implementation of a Singly Linked List for a Generic Type.
 * @author Pieter Janse van Rensburg (pieterjvr50@gmail.com)
 * @category Data Structures
 * @since 10/12/2017
 * @version 10/12/2017
 * @param <T> A Generic Type T representing an Object in Java.
 */
public class LinkedList<T> implements Collection<T> {

	// instance variables
	private int size;
	// the first Node in the LinkedList
	private Node<T> head;
	// the last Node in the LinkedList
	private Node<T> tail;
	
	/***
	 * The Constructor of the LinkedList Class.
	 * Creates an Empty Linked List with its Nodes set to null.
	 * @see LinkedList#initialize()
	 */
	public LinkedList() {
		this.initialize();
		}
	
	/***
	 * A Method used to set the size of the Linked List to 0.
	 * Furthermore, it also sets its head and tail nodes to null.
	 */
	private void initialize() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		}

	/***
	 * A Method used to get the data of the Node in the given position in the Linked List.
	 * @param index The index of the Node from which the data must be retrieved.
	 * @return The data of the Node in the given index in the Linked List.
	 */
	public T get(int index) {
		if(this.isEmpty() || index > this.size)
			throw new IllegalArgumentException();
		else if(index == 0)
			return this.head.getData();
		else {
			Node<T> temp = this.head;
			T returnData = null;
			int pos = 0;
			while(temp.hasNextNode()) {
				pos ++;
				temp = temp.getNextNode();
				if(pos == index) {
					returnData = temp.getData();
					break;
					}
				}
			
			return returnData;
			}
		}


	@Override
	public boolean add(T data) {
		if(this.isEmpty())
			this.head = this.tail = new Node<T>(data);
		else {
			Node<T> temp = this.tail;
			this.tail = new Node<T>(data);
			temp.setNextNode(this.tail);
		}
		
		this.size++;
		return true;
	}


	/***
	 * Method used to add a new element to the Linked List in the given index.
	 * @param data The data to be stored in the new Node to be added to the Linked List.
	 * @param index The index of the New Node in the Linked List.
	 * @return A boolean indicating whether the addition of the new element was successful.
	 */
	public boolean add(T data, int index) {
		if(index < 0 || index > size)
			throw new IllegalArgumentException();
		
		Node<T> temp = new Node<T>(data);
		
		if(index == 0) {
			temp.setNextNode(this.head);
			this.head = temp;
			this.size++;
			return true;
			}
		
		Node<T> currentNode = this.head;
		Node<T> previousNode = this.head;
		int pos = 0;
		
		while(pos != index) {
			
			currentNode = currentNode.getNextNode();
			pos++;
			if(pos == index) {
				temp.setNextNode(currentNode);
				previousNode.setNextNode(temp);
				size++;
				return true;
				}
			previousNode = currentNode;
			}
			
		return false;
		
		}
	
	@Override
	public boolean addAll(Collection<? extends T> c) {
		for(T dataItem: c)
			this.add(dataItem);		
		return true;
	}

	
	@Override
	public void clear() {
		this.initialize();
	}

	
	@Override
	public boolean contains(Object o) {
		if(this.isEmpty())
			return false;
		
		if(this.head.equals(o))
			return true;
		
		Node<T> temp = this.head;
		while(!temp.equals(o) && temp.hasNextNode()) {
			temp = temp.getNextNode();
			if(temp.equals(o))
				return true;
			}
		
		return false;
	}

	
	@Override
	public boolean containsAll(Collection<?> c) {
		for(Object o: c)
			if(!this.contains(o))
				return false;	
		return true;
	}

	
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			private Node<T> currentNode = null;
			
			@Override
			public boolean hasNext() {
				if(isEmpty())
					return false;
				else if(currentNode == null)
					return true;
				else
					return currentNode.hasNextNode();
			}

			@Override
			public T next() {
				if(this.hasNext() && currentNode == null) {
					this.currentNode = head;
					return head.getData();
				}
					
				else if (this.hasNext()){
					this.currentNode = this.currentNode.getNextNode();
					return currentNode.getData();
				}
				throw new NoSuchElementException();
			}
			
			
			
		};
		}

	
	@Override
	public boolean remove(Object o) {
		
		if(this.isEmpty())
			throw new IllegalArgumentException();
		else {
			Node<T> temp = this.head;
			if(temp.getData().equals(o)) {
				this.head = temp.getNextNode();
				this.size--;
				return true;
				}
			
			Node<T> previousNode;
			while(!temp.getData().equals(o) && temp.hasNextNode()) {
				
				previousNode = temp;
				temp = temp.getNextNode();
				if(temp.getData().equals(o)) {
					previousNode.setNextNode(temp.getNextNode());
					this.size--;
					return true;
					}
				}
		}
		throw new IllegalArgumentException();
	}

	
	@Override
	public boolean removeAll(Collection<?> c) {
		for(Object o: c)
			this.remove(o);	
		return true;
	}

	
	@Override
	public boolean retainAll(Collection<?> c) {
		
		if(this.isEmpty())
			return true;
		
		Node<T> temp = this.head;
		if(!c.contains(this.head))
			this.remove(this.head);
		
		while(temp.hasNextNode()) {
			temp = temp.getNextNode();
			if(!c.contains(temp)) 
				this.remove(temp);
		}
		return true;
	}

	
	@Override
	public int size() {
		return this.size;
	}

	
	@Override
	public Object[] toArray() {
		if(this.isEmpty())
			return null;
		
		Object[] dataArray = new Object[this.size];
		Node<T> temp = this.head;
		int pos = 0;
		dataArray[0] = temp.getData();
		pos++;
		while(temp.hasNextNode()) {
			temp = temp.getNextNode();
			dataArray[pos] = temp.getData();
			pos++;
		}
		
		return dataArray;
	}

	
	@SuppressWarnings({ "hiding", "unchecked" })
	@Override
	public <T> T[] toArray(T[] a) {
		if(a== null)
			throw new IllegalArgumentException();
		
		if(this.isEmpty()) {
			for(int i = 0; i < a.length; i++)
				a[i] = null;
		
			return a;
		}
		
		if(this.size <= a.length) {
			int pos = 0;
			Node<T> temp = (Node<T>) this.head;
			a[pos] = temp.getData();
			pos++;
			
			while(temp.hasNextNode()) {
				temp = temp.getNextNode();
				a[pos] = temp.getData();
				pos++;
			}
			
			return a;
		}
		else 	
			a = (T[])this.toArray();
			return a;
	}
	

/***
 * An Implementation of a Node for a Generic Type, which represents an element of a Linked List.
 * @author Pieter Janse van Rensburg (pieterjvr50@gmail.com)
 * @category Data Structures
 * @since 10/12/2017
 * @version 10/12/2017
 * @param <T> A Generic Type T representing an Object in Java.
 */
private static class Node<T> {
		
	// instance variables
	// the data stored in the Node
	private T data;
	// a link/pointer to the Next Node in the Linked List
	private Node<T> nextNode;
		
	/***
	 * The Constructor of the Node Class.
	 * @param data The data to be stored in the Node.
	 */
	public Node(T data) {
		this.data = data;
		this.nextNode = null;
		}
		
	/***
	 * A Method used to retrieve the data stored in the Node.
	 * @return The data stored in the Node.
	 */
	public T getData() {
		return this.data;
		}
	
	/***
	 * A Method used to get the Next Node (in the Linked List) from the current Node.
	 * @return The Next Node in the Linked List (or null if there isn't one).
	 */
	public Node<T> getNextNode() {
			return this.nextNode;
			}
		
	/***
	 * A Method used to check whether the current Node has a Next Node (in the Linked List).
	 * @return A boolean indicating whether the current Node has a Next Node.
	 */
	public boolean hasNextNode() {
		return this.nextNode != null;
		}
		
	/***
	 * A Method used to set the Next Node (in the Linked List) in relation to the current Node.
	 * @param nextNode The Next Node (in the Linked List) in relation to the current Node.
	 */
	public void setNextNode(Node<T> nextNode) {
		this.nextNode = nextNode;
		}
		
}
	
}
