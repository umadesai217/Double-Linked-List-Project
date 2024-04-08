import java.util.*;


public class MyDoubleLinkedList<E extends Comparable<E>>  {
	
   private Node<E> head, tail;
   private int size;

	/** Create a default list */
   public MyDoubleLinkedList() {
	   head = tail = null;
	   size = 0;
   }

	/** Create a list from an array of objects */
   public MyDoubleLinkedList(E[] objects) {
	   	  //  Left as Exercise
	   for (int i = 0; i < objects.length; i++) {
		   addLast(objects[i]);
	   }  
   }

	    /** Return the head element in the list */
   public E getFirst() {
      if (size == 0) {
         return null;
      } else {
         return head.element;
      }
   }

	/** Return the last element in the list */
   public E getLast() {
      if (size == 0) {
         return null;
      } else {
         return tail.element;
      }
   }

	/** Add an element to the beginning of the list */
   public void addFirst(E e) {
      Node<E> newNode = new Node<E>(e); // Create a new node
      newNode.next = head; // link the new node with the head
      head = newNode; // head points to the new node
      size++; // Increase list size
   
      if (tail == null) // the new node is the only node in list
         tail = head;
   
      if (head != tail)
         head.next.previous = head; // For a two-way linked list
   }

	/** Add an element to the end of the list */
   public void addLast(E e) {
      Node<E> newNode = new Node<E>(e); // Create a new for element e
   
      Node<E> temp = tail; // For a two-way linked list
   
      if (tail == null) {
         head = tail = newNode; // The new node is the only node in list
      } else {
         tail.next = newNode; // Link the new with the last node
         tail = tail.next; // tail now points to the last node
      }
   
      size++; // Increase size
   
      tail.previous = temp; // For a two-way linked list
   }
   
   /** Return true if this list contains no elements */
   public  boolean isEmpty() {
      return size() == 0;
   }
   
   /**
	 * Remove the head node and return the object that is contained in the
	 * removed node.
	*/
   public E removeFirst() {
      if (size == 0) {
         return null;
      } else {
         Node<E> temp = head;
         head = head.next;
         size--;
         if (head == null) {
            tail = null;
         }
         return temp.element;
      }
   }

   /**
	 * Remove the last node and return the object that is contained in the
	 * removed node.
	 */
   public E removeLast() {
      if (size == 0) {
         return null;
      } else if (size == 1) {
         Node<E> temp = head;
         head = tail = null;
         size = 0;
         return temp.element;
      } else {
         Node<E> current = head;
      
         for (int i = 0; i < size - 2; i++) {
            current = current.next;
         }
      
         Node<E> temp = tail;
         tail = current;
         tail.next = null;
         size--;
         return temp.element;
      }
   }

   @Override
   public String toString() {
      StringBuilder result = new StringBuilder("[");
   
      Node<E> current = head;
      for (int i = 0; i < size; i++) {
         result.append(current.element);
         current = current.next;
         if (current != null) {
            result.append(", "); // Separate two elements with a comma
         } else {
            result.append("]"); // Insert the closing ] in the string
         }
      }
      return result.toString();
   }

   /** Return number of elements in the list */
   public int size() {
      return size;
   }
   
   
	/** Clear the list */
   public void clear() {
      head = tail = null;
      size = 0;
   }



	/**
	 * Add a new element at the specified index in this list The index of the
	 * head element is 0
	 */
   public void add(int index, E e) {
	   	  // "Implementation left as an exercise";
	   if(index == 0) {
			addFirst(e);
	   }		
	   else if(index >= size) {
			addLast(e);
	   }		
	   else {
		   Node<E> newNode = new Node<>(e);
		   Node<E> current = head;			
		   for(int i = 1; i < index; ++i) {
			   current = current.next;
		   }				
		   Node<E> temp = current.next;
		   temp.previous =  newNode;
		   newNode.next = temp;
		   newNode.previous = current;
		   current.next = newNode;
		   size++;
		}
   }
	    
	/** Add a new element at the specified index in this list in ascending order */
   public void addInOrder(E e) {
	   	  // "Implementation left as an exercise";
	   addLast(e);
	   ArrayList<E> arrayLinkedList = new ArrayList<E>();
	   Node<E> current = head;
	   for(int i = 0; i <= (size-1); ++i) {
		   arrayLinkedList.add(this.get(i));
		   current = current.next;
	   }
	   int ogSize = size;
	   Collections.sort(arrayLinkedList);
	   this.clear();
	   for(int i = 0; i <= (ogSize - 1); ++i) {
		   addLast(arrayLinkedList.get(i));
	   }
   }
	    
	    
	/** Check to see if this list contains element e */
   public boolean contains(E e) {
     	  // "Implementation left as an exercise";
	   for(Node<E> current = head; current!= null; current = current.next) {
		   if(current.element.equals(e)) {
			   return true;
		   }
	   }
	   return false;
   }

	
	/**
	 * Remove the element at the specified position in this list. Return the
	 * element that was removed from the list.
	 */
   public E remove(int index) {
	   	  // "Implementation left as an exercise";
	   if(size ==0) {
		   return null;
	   }
	   if(index < 0 || index >= size) {
		   return null;
	   }
	   if(index==0) {
		   return removeFirst();
		}
	   if(index== (size-1)) {
		   return removeLast();
		}
	   Node<E> current = head;
	   for(int i = 0; i < (index-1) ; ++i) {
		   current = current.next;
		}
	   Node <E> removeNode = current.next;
	   current.next = removeNode.next;
	   size--;
	   return removeNode.element;
   }
	    
	/** Remove the first occurrence of the element e 
	 *  from this list. Return true if the element is removed. 
    */
   public boolean removeElement(E e) {
	   	  // "Implementation left as an exercise";
	   for(Node<E> current = head; current!= null; current = current.next) {
		   if(current.element.equals(e)) {
			   remove(indexOf(e));
			   return true;
		   }
	   }
	   return false;
   }
	    
	/** Return the length of this list using recursion */
   public int getLength() {
	   	  // "Implementation left as an exercise";
	   return getLength(head, 0);
   }
   public int getLength(Node<E> current, int listLength) {
	  if(current == null) {
		  return listLength;
	  }
	  listLength++;
	  return getLength(current.next, listLength);
   }
	     
 	    
	     
    	    
	/** Print the list in reverse order */
   public void printReverse() {
	   	  // "Implementation left as an exercise";
	   printList(tail, 1); //used recursive method printList 
	   System.out.println();
   }
	    
	/** Print this list using recursion */
   public void printList() {
	   	  // "Implementation left as an exercise";
	   printList(head);
	   System.out.println();
   }
   public void printList(Node<E> current) {
	   if(current == null) {
		   return;
	   }
	   System.out.print(current.element + " ");
	   printList(current.next);
   }
   public void printList(Node<E> current, int reverseFlag) {
	   if(current == null) {
		   return;
	   }
	   System.out.print(current.element + " ");
	   printList(current.previous, 1);
   }
	    
      
	  

	/** Return the element from this list at the specified index */
   public E get(int index) {
	   	  // "Implementation left as an exercise";
	   if(index==0) {
		   return getFirst();
	   }
	   if(index == (size-1)) {
		   return getLast();
	   }
	   if(index < 0 || index >= size) {
		   return null;
	   }
	   Node<E> current = head;
	   for(int i = 0; i <= index; ++i) {
		   if(i == index) {
			   return current.element; 
		   }
		   current = current.next;
	   }
	   return null;
   }

	/**
	 * Return the index of the head matching element in this list. Return -1 if
	 * no match.
	 */
   public int indexOf(E e) {
	   	  // "Implementation left as an exercise";
	   Node<E> current = head;
	   for(int i = 0; i < size; ++i) {
		   if(current.element.equals(e)) {
			   return i;
		   }
		   else {
			   current = current.next;
		   }
	   }
	   return -1;
   }

	/**
	 * Return the index of the last matching element in this list Return -1 if
	 * no match.
	 */
   public int lastIndexOf(E e) {
	   	  // "Implementation left as an exercise";
	   Node<E> current = tail;
	   for(int i = (size - 1); i >= 0; i--) {
		   if(current.element.equals(e)) {
			   return i;
		   }
		   current = current.previous;
	   }
	   return -1;
   }

	/** Replace the element at the specified position 
   *  in this list with the specified element.
   *  throw exception if index out of bound and     
   *  return null */
   public E set(int index, E e) {
	   	  // "Implementation left as an exercise";
	   //index is negative or > list size
	   if(index < 0 || index > (size-1)){
		   return null;
	   }
	   Node<E> newNode = new Node<E>(e);
	   Node<E> current = head;
	   try {
		   for(int i = 0; i < size; ++i) {
			   if (i == index) {
				   //find node to be replaced and adjust pointers
				   //set pointer of the node before to point to new node
				   current.previous.next = newNode;
				   //set newNode to point to the one immediately following the replaced node
				   newNode.next = current.next;
				   return newNode.element;
			   }
			   current = current.next;
		   }
	   }
	   catch (Exception exc) {
		   throw new ArrayIndexOutOfBoundsException("Index is out of bounds"); 			
	   }
	   return null;	
   }
   
   
	/** Split the original list in half. The original     
	 * list will continue to reference the 
	 * front half of the original list and the method 
	 * returns a reference to a new list that stores the 
	 * back half of the original list. If the number of 
	 * elements is odd, the extra element should remain 
	 * with the front half of the list. 
    */
   public MyDoubleLinkedList<E> split() {
	   	  // "Implementation left as an exercise";
	   int newListBeg;
	   //list is empty or only has one entry
	   if(size <= 1) {
		   return null;
	   }
	   if(size % 2 == 0) {
		   newListBeg = (size/2);
	   }
	   else {
		   newListBeg = ((size/2 ) + 1);
	   }
	   MyDoubleLinkedList<E> newList = new MyDoubleLinkedList<>();
	   Node<E> current = head;
	   //position the node where split starts
	   for(int i = 0; i < newListBeg; i++) {
		   current = current.next;
	   }
	   //copy nodes to the new list
	   for(int i = newListBeg; i < size; i++) {
		   newList.addLast(current.element);
		   current = current.next;
	   }
	   //remove nodes that were copied
	   //calculate how many were copied and remove that many from the end
	   for(int i = 1; i <= newList.size; i++) {
		   this.removeLast();
	   }	       
		return newList;
   }
   
   /** Check to see of two given lists are eqaul */
   public boolean equals(Object o) {
	   	  // "Implementation left as an exercise";
	   MyDoubleLinkedList<E> newList = ((MyDoubleLinkedList<E>) o);
	   Node<E> thisListNode = head;
	   Node<E> newListNode = newList.head;
	   if(!(this.size == newList.size)) {
		   return false;
	   }
	   while (thisListNode != null && newListNode != null) {
		   if (!(thisListNode.element.equals(newListNode.element))) {
			   return false;
		   }
		   thisListNode = thisListNode.next;
		   newListNode = newListNode.next;
	   }
	   return true;   
   }
	    
   public static class Node<E extends Comparable<E>> {
      E element;
      Node<E> next;
      Node<E> previous;
   
      public Node(E o) {
         element = o;
      }
   }
 
}
	