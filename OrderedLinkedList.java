package project2;

public class OrderedLinkedList<E extends Comparable<E>> implements OrderedList<E> {

	/**
	 * Implementation of this Node class is based on class notes, lecture notes, and
	 * Code from Chapter 3.6.2 Fundamental Data Structures, Cloning Data Structures (p. 144) in
	 * "Data Structures and Algorithms in Java 6th Edition",
	 * written by Michael T. GoodRich, Roberto Tamassia, Michael H. Goldwasser.
	 * @author Gayeon_Park
	 *
	 * @param <E>
	 */
	public class Node<E>{
		private E element;
		private Node<E> next;

		/**
		 * A default constructor that initializes all the
		 * instance variables 
		 */
		public Node(){

		}

		/**
		 * This constructor takes in two parameters, E e and Node<E>n 
		 * sets them to 'element' and 'next', respectively, and stores in the node. 
		 * @param e
		 * @param n
		 */
		public Node(E e, Node<E> n){
			element = e;
			next = n;
		}

		/**
		 * Retrieves the element stored in the node
		 * @return element that is stored 
		 */
		public E getElement(){
			return element;
		}

		/**
		 * Retrieves the next node in the list
		 * @return reference to the next node
		 */
		public Node getNext(){
			return next;
		}

		/**
		 * Sets the element of the node to the element value passed in as parameter
		 * @param element passed in
		 */
		public void setElement(E element){
			this.element = element;
		}

		/**
		 * Sets the next node of the list to the node passed in as parameter 
		 * @param next a node taken in as a parameter to be set as the next 
		 * node of the list
		 */
		public void setNext(Node<E> next){
			this.next = next;
		}	
	}

	private Node<E> head = null;
	private int size = 0;

	/**
	 * A default constructor of OrderedLinkedList Class
	 */
	public OrderedLinkedList(){

	}

	/**
	 * Adds the specified element to  this list in a sorted order. 
	 *
	 * <p>The element added must implement Comparable<E> interface. This list 
	 * does not permit null elements. 
	 *
	 * @param e element to be appended to this list
	 * @return <tt>true</tt> if this collection changed as a result of the call
	 * @throws ClassCastException if the class of the specified element
	 *         does not implement Comparable<E> 
	 * @throws NullPointerException if the specified element is null
	 */
	public boolean add(E e) throws NullPointerException, ClassCastException {
		//NullPointerException thrown
		if (e == null){
			throw new NullPointerException("The specified element is null.");
		}

		//ClassCastException thrown
		if (!(e instanceof Comparable)){
			throw new ClassCastException("The class of the specified element does not implement Comparable<E>.");
		}

		//the element that we want to be next node
		Node<E> newNode = new Node<E>();
		newNode.setElement(e);
		//the element that we are checking against
		Node<E> current = head;

		//Checks for empty list and add the specified element to the list
		if (head == null){
			head = newNode;
			size ++;
			return true;
		} else if (newNode.getElement().compareTo(head.getElement()) <= 0) {
			//Compare the specified element to head node
			//If the specified element is smaller than head, add node before current head 
			newNode.next = head;
			head = newNode;
			size++;
			return true;
		} else {
			//Adds the specified element after head node
			//Also compares with node after the current node to determine whether to
			//keep going through in the loop ot to add the specified element in ordered manner
			while (current.next != null && e.compareTo((current.next.getElement())) > 0) {
				//if (e.compareTo((current.next.getElement())) > 0){
				current = current.next;
				//}
			} 
			//Either: a). the specified element is "bigger" than all the nodes stored inside the list
			//or b). the specified element is less than current node it's being compared against
			newNode.next = current.next;
			current.next = newNode;
			size++;
			return true;
		}
	}


	/**
	 * Removes all of the elements from this list.
	 * The list will be empty after this call returns.
	 */
	public void clear() {
		head = null;
		size = 0;

	}

	/**
	 * Code from Chapter 3.6.2 Fundamental Data Structures, Cloning Data Structures (p. 144) in
	 * "Data Structures and Algorithms in Java 6th Edition",
	 * written by Michael T. GoodRich, Roberto Tamassia, Michael H. Goldwasser
	 * 
	 * Returns a deep copy of this list. (The elements
	 * themselves are not cloned.)
	 *
	 * @return a shallow copy of this list instance
	 * @throws CloneNotSupportedException - if the object's class does 
	 *         not support the Cloneable interface
	 */
	public Object clone() throws CloneNotSupportedException {
		OrderedLinkedList<E> newList = (OrderedLinkedList<E>) super.clone();
		if (this.size > 0){
			newList.head = new Node<E>(this.head.getElement(), null);
			Node<E> current = this.head;
			Node<E> copy = newList.head;
			while(current.next != null){
				//make new node that will stand for deep copy of a
				//node with corresponding element from the original list
				copy.setNext(new Node(current.getNext().getElement(),null));
				current = current.getNext();
				copy = copy.getNext();
			}
		}
		return newList;
	}

	/**
	 * Returns <tt>true</tt> if this list contains the specified element.
	 *
	 * @param o element whose presence in this list is to be tested
	 * @return <tt>true</tt> if this list contains the specified element
	 * @throws ClassCastException if the type of the specified element
	 *         is incompatible with this list
	 * @throws NullPointerException if the specified element is null 
	 */
	public boolean contains(Object o) throws ClassCastException, NullPointerException{
		//NullPointerException thrown
		if (o == null || this == null){
			throw new NullPointerException("The specified element is null.");
		}

		//Checks for empty list
		if (head == null){
			return false;
		}

		//ClassCastException thrown
		if ( !(o instanceof Comparable)){
			throw new ClassCastException("The type of the specified element is incompatible with this list.");
		}

		Node<E> current = head;

		//For a list with more than one element, checks if the specified element is contained in the list
		while (current != null){
			if (current.getElement().equals(o)){
				return true;
			}
			current = current.next;
		}
		return false;
	}

	/**
	 * Compares the specified object with this list for equality.  Returns
	 * {@code true} if and only if the specified object is also a list, both
	 * lists have the same size, and all corresponding pairs of elements in
	 * the two lists are <i>equal</i>.  (Two elements {@code e1} and
	 * {@code e2} are <i>equal</i> if {@code e1.equals(e2)}.)  
	 * In other words, two lists are defined to be
	 * equal if they contain the same elements in the same order.<p>
	 *
	 * @param o the object to be compared for equality with this list
	 * @return {@code true} if the specified object is equal to this list
	 */	public boolean equals(Object obj) {
		 //Checks if the specified object is null
		 if (obj == null){
			 return false;
		 }
		 //Checks if the specified object is also a list
		 if ( !(getClass().equals(obj.getClass()))){
			 return false;
		 }

		 @SuppressWarnings("unchecked")
		 OrderedLinkedList<E> list = (OrderedLinkedList<E>) obj;

		 //Checks if the lists have the same size
		 if (list.size() != this.size()){
			 return false;
		 }

		 Node<E> current = head;
		 Node<E> elementOfList = list.head;
		 //Checks if all corresponding pairs of elements in the two lists are equal
		 while (current != null){
			 //Not all the corresponding pairs of elements in the two list are equal
			 if (!current.getElement().equals(elementOfList.getElement())){
				 return false;
			 }
			 current = current.next;
			 elementOfList = elementOfList.next;
		 }
		 return true;
	 }

	 /**
	  * Returns the element at the specified position in this list.
	  *
	  * @param index index of the element to return
	  * @return the element at the specified position in this list
	  * @throws IndexOutOfBoundsException if the index is out of range 
	  * <tt>(index < 0 || index >= size())</tt>
	  */
	 public E get(int index) {
		 //IndexOutOfBoundsException thrown
		 if (index < 0 || index >= size()){
			 throw new IndexOutOfBoundsException("The index entered is out of range.");
		 }

		 //checks for empty list
		 if (size() == 0){
			 return null;
		 }

		 //loops through the list to find the object at the specified index
		 Node<E> current = head;
		 for (int i = 0; i < index; i ++){
			 current = current.next;
		 }

		 //Element at the specified position in the list is retrieved
		 return current.getElement();
	 }

	 /**
	  * Returns the index of the first occurrence of the specified element
	  * in this list, or -1 if this list does not contain the element.
	  *
	  * @param o element to search for
	  * @return the index of the first occurrence of the specified element in
	  *         this list, or -1 if this list does not contain the element
	  */
	 public int indexOf(Object o) {
		 Node<E> current = head;
		 int index = 0;

		 //object passed in is empty or list is empty
		 if (o == null || head == null){
			 return -1;
		 }

		 //Go through every node in the list and checks if the element at that node 
		 //equals to the object looking for 
		 while (current.next != null){
			 if (current.getElement().equals(o)){
				 return index;
			 } 
			 index ++;
			 current = current.next;
		 }

		 //last element on the list
		 if (current.next == null){
			 //Checks if the last element on the list equals to the element searched for
			 if (current.getElement().equals(o)){
				 return index;
			 }
		 }
		 //The specified element was not found in the list
		 return -1;
	 }

	 /**
	  * Removes the element at the specified position in this list.  Shifts any
	  * subsequent elements to the left (subtracts one from their indices if such exist).
	  * Returns the element that was removed from the list.
	  *
	  * @param index the index of the element to be removed
	  * @return the element previously at the specified position
	  * @throws IndexOutOfBoundsException  if the index is out of range 
	  * <tt>(index < 0 || index >= size())</tt>
	  */
	 public E remove(int index) throws IndexOutOfBoundsException{
		 //IndexOutOfBoundsException thrown
		 if (index < 0 || index >= size()){
			 throw new IndexOutOfBoundsException("The index is out of range.");
		 }

		 //NullPointerException thrown
		 if (this == null){
			 throw new NullPointerException("Cannot remove from a null"); 
		 }

		 //checks for empty list
		 if (head == null){
			 throw  new IndexOutOfBoundsException("The index is out of range.");
		 }

		 Node<E> current = head;
		 int counter = 0;

		 //Checks if the object at index to be removed is at the very beginning of the list
		 if (index == 0){
			 Node<E> temp = current;
			 head = head.next;
			 size --;
			 return temp.getElement();
		 }

		 //continue to loop through the list to find the object in the list that's one before the
		 //object at index to be removed in the list
		 for (int i = 0; i < index; i ++){
			 current = current.next;
		 }

		 //remove the object at the specified index
		 Node<E> temp = current.next; //save the object at the specified index so the data won't be lost
		 current.next = current.next.next; //redirect the arrows so that the rest of the list won't get lost
		 size --;
		 return temp.getElement();
	 }

	 /**
	  * Removes the first occurrence of the specified element from this list,
	  * if it is present.  If this list does not contain the element, it is
	  * unchanged.  More formally, removes the element with the lowest index
	  * {@code i} such that
	  * <tt>(o.equals(get(i))</tt>
	  * (if such an element exists).  Returns {@code true} if this list
	  * contained the specified element (or equivalently, if this list
	  * changed as a result of the call).
	  *
	  * @param o element to be removed from this list, if present
	  * @return {@code true} if this list contained the specified element
	  * @throws ClassCastException if the type of the specified element
	  *         is incompatible with this list
	  * @throws NullPointerException if the specified element is null and this
	  *         list does not permit null elements
	  */
	 public boolean remove(Object o) throws NullPointerException, ClassCastException{
		 //NullPointerException thrown
		 if (o == null){
			 throw new NullPointerException("The specified element is null and this "
					 + "list doesn't permit null elements");
		 }

		 //ClassCastException thrown
		 if (! (o instanceof Comparable)){
			 throw new ClassCastException("The type of the specified element is incompatiable with this list.");
		 }

		 //Checks for an empty list
		 if (this.head == null){
			 return false;
		 }

		 Node<E> current = head;

		 //Checks if the object to be removed is at the very beginning of the list
		 if (head.getElement().equals(o)){
			 head = head.next;
			 size --;
			 return true;			 
		 } 

		 //Checks for the object to be removed that's either in the middle or at the end of the list
		 while (current.next != null){
			 //checks if the current object equals to the object to be removed
			 if (current.getElement().equals(o)){
				 current.next = current.next.next;
				 size --;
				 return true;
			 }

			 //checks if the last object in the list is equal to the object to be removed
			 if (current.next.next == null && current.next.getElement().equals(o)){
				 current.next = null;
				 size --;
				 return true;
			 }

			 //continue to loop through the list to find the object in the list
			 //that matches the object to be removed
			 current = current.next;
		 }
		 //the specified element in the parameter was not found in the list
		 return false;
	 }

	 /**
	  * Returns the number of elements in this list.
	  *
	  * @return the number of elements in this list
	  */
	 public int size() {
		 return size;
	 }

	 /**
	  * Returns a string representation of this list.  The string
	  * representation consists of a list of the list's elements in the
	  * order they are stored in this list, enclosed in square brackets
	  * (<tt>"[]"</tt>).  Adjacent elements are separated by the characters
	  * <tt>", "</tt> (comma and space).  Elements are converted to strings 
	  * by the {@code toString()} method of those elements.
	  *
	  * @return a string representation of this list
	  */
	 public String toString() {
		 String list = "";
		 //Returns a formatted string representation of the list that's empty
		 if (this.head == null){
			 return String.format("%1$1s%2$1s", "[", "]");
		 }

		 Node<E> current = head;
		 //Returns a formatted string representation of the list that has one element
		 if (current.next == null){
			 list = current.getElement().toString();
			 return String.format("%1$1s%2$1s%3$1s", "[", list, "]");
		 }

		 //Returns a formatted string representation of the list that has more than one element
		 while (current.next != null){
			 //format the inside elements of the list except for the very last element
			 //excluded the last element because of formatting
			 list += String.format("%1$1s, ",current.getElement().toString());
			 current = current.next;
		 }
		 //formats in the very last element of the list
		 list += String.format("%1$1s", current.getElement().toString());
		 return String.format("%1$1s%2$1s%3$1s", "[", list, "]");
	 }
}
