import java.util.*;

/**
 * Creates the GenericLinkedList class
 * 
 * @author asharma
 * @version 11.13.20
 * @param <E> any type of element
 */
public class GenericLinkedList<E> {
    
    private int size;
    private Node<E> headNode;
    private Node<E> tailNode;
    
    /**
     * Creates the node class
     * 
     * @author asharma
     * @version 11.13.20
     * @param <E> any type of element
     */
    private class Node<E> {
        private Object data;
        private Node<E> next;
        
        /**
         * Takes in the data 
         * and the next node
         * 
         * @param data the data that get stored
         * @param next the link to the next node
         */
        public Node(Object data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
        
        /**
         * Default constructor 
         * for the Node class
         */
        public Node(Object data) {
            this(data, null);
        }
    }
    
    /**
     * Default constructor
     */
    public GenericLinkedList() {
        size = 0;
        headNode = null;
        tailNode = null;
    }
    
    /**
     * Takes in another GenericLinkedList
     * @param listy the list to be added
     */
    public GenericLinkedList(GenericLinkedList<E> listy) {
        addAll(listy);     
    }
    
    /**
     * Checks if the 
     * LinkedList is empty
     * 
     * @return true or false if empty
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Returns the size
     * 
     * @return the size
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns the data at that index
     * 
     * @param index the location of the data
     * @return the data needed
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int counter = 0;
        Node<E> temp = headNode;
        while (temp != null) {
            if (counter == index) {
                return (E)temp.data;
            }
            temp = temp.next;
            counter++;
        }
        return null;
    }
    
    /**
     * Returns headNode data
     * @return headNode data
     */
    public E getFirst() {
        if (headNode == null) {
            throw new NoSuchElementException();
        }
        return (E)headNode.data;
    }
    
    /**
     * Returns tailNode data
     * @return tailNode data
     */
    public E getLast() {
        if (tailNode == null) {
            throw new NoSuchElementException();
        }
        return (E)tailNode.data;
    }
    
    /**
     * Returns the headNode if it exists
     * @return the headNode;
     */
    public E element() {
        if (headNode == null) {
            throw new NoSuchElementException();
        }
        return (E)headNode.data;
    }
    
    /**
     * Adds an object to 
     * the end of the list
     * 
     * @param o the object 
     * to be added
     * @return true if it was added
     */
    public boolean add(E o) {
        add(size, o);
        return true;
    }
    
    /**
     * Adds an object at a specified index
     * 
     * @param o the object to be added
     * @param index the location 
     * to be added
     */
    public void add(int index, E o) {
        checkIndexAdd(index);
        if (size == 0 || headNode == null) {
            headNode = new Node<E>(o);
            tailNode = headNode;
            size++;
            return;
        }
        if (index == 0) {
            headNode = new Node<E>(o, headNode);
            size++;
            return;
        }
        if (index == size) {
            tailNode.next = new Node<E>(o, null);
            tailNode = tailNode.next;
            size++;
            return;
        }
        Node<E> temp = headNode;
        int counter = 0;
        while (counter < index - 1) {
            temp = temp.next;
            counter++;
        }
        temp.next = new Node<E>(o, temp.next);
        size++;
    }
    
    /**
     * Adds at the beginning
     * 
     * @param obj the object to be added
     */
    public void addFirst(E obj) {
        add(0, obj);
    }
    
    /**
     * Adds at the end
     * 
     * @param obj the object to be added
     */
    public void addLast(E obj) {
        add(size, obj);
    }
    
    /**
     * Changes the string 
     * at that location
     * 
     * @param index the location to set it
     * @param replacement the string to be set
     * @return the string that was replaced
     */
    public E set(int index, E replacement) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> n = getNode(index);
        E old = (E) n.data;
        n.data = (Object) replacement;
        return old;
    }
    
    /**
     * Checks to see if this LinkedList is equal
     * to the object its being compared to
     * 
     * @param comparison the object
     * 
     * @return true if it is equal
     * false otherwise
     */
    public boolean equals(Object comparison) {
        if ( !( comparison instanceof GenericLinkedList ) ) {
            return false;
        }
        GenericLinkedList other = (GenericLinkedList)comparison;
        if (size() == other.size()) {
            for (int i = 0; i < size(); i++) {
                if (!(other.get(i).equals(get(i)))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    /**
     * Converts the LinkedList into a string
     * @return string version of LinkedList
     */
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        String result = "[" + headNode.data;
        Node<E> temp = headNode.next;
        for (int i = 1; i < size; i++) {
            result += ", " + temp.data;
            temp = temp.next;
        }
        return result + "]";
    }
    
    /**
     * Finds the first index of the object desired
     * 
     * @param o the object the found
     * @return the first index of the object or -1
     */
    public int indexOf(Object o) {
        Node<E> temp = headNode;
        for (int i = 0; i < size; i++) {
            if (temp.data.equals(o)) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }
    
    /**
     * Finds the last index of the object desired
     * 
     * @param o the object to be found
     * @return the last index of the object or -1
     */
    public int lastIndexOf(Object o) {
        if (o == null || headNode == null) {
            return -1;
        }
        Node<E> temp = headNode;
        int notFound = -1;
        for (int i = 0; i < size; i++) {
            if (temp.data.equals(o)) {
                notFound = i;
            }
            temp = temp.next;
        }
        return notFound;
    }
    
    /**
     * Removes the value at the specific index
     * 
     * @param index the location 
     * @return the value that was removed
     */
    public E remove(int index) {
        if (0 > index || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 1) {
            Object obj = headNode.data;
            headNode = null;
            tailNode = null;
            size = 0;
            return (E)obj;
        }
        if (index == 0) {
            Object obj = headNode.data;
            headNode = headNode.next;
            size--;
            return (E)obj;
        }
        if (index == (size - 1)) {
            Object obj = tailNode.data;
            tailNode = getNode(size - 2);
            size--;
            return (E)obj;
        }
        Node<E> removedBefore = getNode(index - 1);
        Node<E> removed = removedBefore.next;
        Node<E> afterRemoved = removedBefore.next.next;
        removedBefore.next = afterRemoved;
        size--;
        return (E)removed.data;
    }
    
    /**
     * Removes the head 
     * 
     * @return the head
     */
    public E remove() {
        if (headNode == null) {
            throw new NoSuchElementException();
        }
        return remove(0);
    }
    
    /**
     * Removes the head 
     * 
     * @return the head
     */
    public E removeFirst() {
        if (headNode == null) {
            throw new NoSuchElementException();
        }
        return remove();
    }
    
    /**
     * Removes the tail
     * @return the data that was removed
     */
    public E removeLast() {
        if (tailNode == null) {
            throw new NoSuchElementException();
        }
        return (remove(size - 1));
    }
    
    /**
     * Removes the first index of the located object
     * 
     * @param obj the object to be removed
     * @return true if it was removed false otherwise.
     */
    public boolean remove(E obj) {
        if (!contains(obj)) {
            return false;
        }
        remove(indexOf(obj));
        return true;
    }
    
    /**
     * Converts the LinkedList into an array
     * 
     * @return the array version
     * of the LinkedList
     */
    public Object[] toArray() {
        Node<E> temp = headNode;
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = temp.data;
            temp = temp.next;
        }
        return arr;
    }
    
    /**
     * Check if the object is in the LinkedList
     * 
     * @param o the object to be checked
     * @return true if it is there
     */
    public boolean contains(Object o) {
        Node<E> temp = headNode;
        while (temp != null) {
            if (temp.data.equals(o)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
    
    /**
     * Adds an entire LinkedList to the end
     * 
     * @param listy the linkedlist to be added
     * @return true if something was added;
     * false otherwise
     */
    public boolean addAll(GenericLinkedList<E> listy) {
        if (listy == null || listy.isEmpty()) {
            return false;
        }
        if (isEmpty()) {
            headNode = listy.headNode;
            tailNode = listy.tailNode;
            size = listy.size();
            return true;
        }
        tailNode.next = listy.headNode;
        tailNode = listy.tailNode;
        size += listy.size();
        return true;
    }
    
    /**
     * Adds an entire LinkedList to a specific index
     * 
     * @param listy the linkedlist to be added
     * @param index the index to begin at
     * @return true if something was added;
     * false otherwise
     */
    public boolean addAll(int index, GenericLinkedList<E> listy) {
        if (listy == null || listy.isEmpty()) {
            return false;
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            listy.tailNode.next = headNode;
            headNode = listy.headNode;
            size += listy.size();
            return true;
        }
        Node<E> beforeAdd = getNode(index - 1);
        Node<E> locationEnd = beforeAdd.next;
        beforeAdd.next = listy.headNode;
        listy.tailNode.next = locationEnd;
        tailNode = listy.tailNode;
        size += listy.size();
        return true;
    }
    
    /**
     * Clears the GenericLinkedList
     */
    public void clear() {
        headNode = null;
        tailNode = null;
        size = 0;
    }
    
    /**
     * Head data or null if empty
     * 
     * @return head data
     */
    public E peek() {
        if (headNode == null) {
            return null;
        }
        return (E)headNode.data;
    }
    
    /**
     * Head data or null if empty
     * 
     * @return head data
     */
    public E peekFirst() {
        if (headNode == null) {
            return null;
        }
        return (E)headNode.data;
    }
    
    /**
     * Tail data or null if empty
     * 
     * @return tail data or null
     */
    public E peekLast() {
        if (tailNode == null) {
            return null;
        }
        return (E)tailNode.data;
    }
    
    /**
     * Head data or null if empty
     * 
     * @return the head data or null
     */
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        return remove(0);
    }
    
    /**
     * Head data or null if empty
     * 
     * @return the head data or null
     */
    public E pollFirst() {
        return poll(); 
    }
    
    /**
     * Tail data or null if empty
     * 
     * @return the head data or null
     */
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        return remove(size - 1);
    }
    
    /**
     * Adds the object as the tail
     * 
     * @param obj the object 
     * to be changed into the tail
     * @return true once it has been added
     */
    public boolean offer(E obj) {
        add(size, obj);
        return true;
    }
    
    /**
     * Inserts the object at the front
     * 
     * @param obj the object to be added
     * @return true once it has been changed
     */
    public boolean offerFirst(E obj) {
        add(0, obj);
        return true;
    }
    
    /**
     * Adds the object as the tail
     * 
     * @param obj the object 
     * to be changed into the tail
     * @return true once it has been added
     */
    public boolean offerLast(E obj) {
        add(size, obj);
        return true;
    }
    
    /**
     * Inserts an object at the front
     * 
     * @param obj the object to be added
     */
    public void push(E obj) {
        addFirst(obj);
    }
    
    /**
     * Removes and returns the head
     * 
     * @return the head
     */
    public E pop() {
        return removeFirst();
    }
    
    /**
     * Removes any instances of 
     * any values from the LinkedList
     * that are in arr
     * 
     * @param arr the LinkedList which 
     * contains the values to be removed
     * @return true if the values were removed false otherwise
     */
    public boolean removeAll(GenericLinkedList<E> arr) {
        if (isEmpty() || arr.isEmpty()) {
            return false;
        }
        while (headNode != null && arr.contains(headNode.data)) {
            headNode = headNode.next;
            size--;
        }
        Node<E> temp = headNode;
        while (temp.next != null) {
            if (arr.contains(temp.next.data)) {
                temp.next = temp.next.next;
                size--;
            }
            else {
                temp = temp.next;
            }
        } 
        tailNode = temp;
        return true;
    }
    
    /**
     * Starts at a given index and ends at an index
     * 
     * @param start the index to begin at
     * @param end the index to end at
     * @return a portion of the LinkedList
     */
    public GenericLinkedList<E> subList(int start, int end) {
        if (start < 0 || start > end || end > size) {
            throw new IndexOutOfBoundsException();
        }
        GenericLinkedList<E> sub = new GenericLinkedList<E>();
        for (int i = start; i < end; i++) {
            sub.add(get(i));
        }
        return sub;
    }
    
    /**
     * Private helper method to help get nodes
     * 
     * @param index the location 
     * to grab the node
     * @return the node at index
     */
    private Node<E> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> temp = headNode;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }
    
    /**
     * Checks if the index is valid
     * 
     * @param index the int to be checked
     */
    private void checkIndexAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

}
