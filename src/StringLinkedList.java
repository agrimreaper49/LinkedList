public class StringLinkedList {
    
    private int size;
    private Node headNode;
    private Node tailNode;
    
    private class Node {
        
        private String data;
        private Node next;
        
        Node (String data){
            this(data , null);
        }
        Node (String data, Node next){
            this.data = data;
            this.next = next;
        }
        
    }
    
    /**
     * Default constructor
     */
    public StringLinkedList() {
        size = 0;
        headNode = null;
        tailNode = null;
    }
    
    /**
     * Checks if the 
     * LinkedList is empty
     * @return true or false if the 
     * 
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Returns the size
     * @return the size
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns the data at that index
     * @param index
     * @return the data needed
     */
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int counter = 0;
        Node temp  = headNode;
        while (counter < index) {
            counter++;
            temp = temp.next;
        }
        return temp.data;
    }
    
    /**
     * Adds an object to 
     * the end of the list
     * 
     * @param o the object 
     * to be added
     * @return true if it was added
     */
    public boolean add(String o) {
        add(o, size);
        return true;
    }
    
    /**
     * Adds an object at a specified index
     * 
     * @param o the object to be added
     * @param index the location 
     * to be added
     */
    public void add(String str, int index) {
        checkIndexAdd(index);
        if (size == 0 || headNode == null) {
            headNode = new Node(str);
            tailNode = headNode;
            size++;
            return;
        }
        if (index == 0) {
            headNode = new Node(str, headNode);
        }
        if (index == size) {
            tailNode.next = new Node(str, null);
            tailNode = tailNode.next;
        }
        Node temp = headNode;
        int counter = 0;
        while (counter < index - 1) {
            temp = temp.next;
        }
        temp.next = new Node(str, temp.next);
        size++;
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
    
    /**
     * Changes the string 
     * at that location
     * 
     * @param index the location to set it
     * @param replacement the string to be set
     * @return the string that was replaced
     */
    public String set(int index, String replacement) {
        checkIndexAdd(index);
        Node temp = headNode;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        String str = temp.data;
        temp.data = replacement;
        return str;
    }
    
    /**
     * Finds the first index of the object desired
     * 
     * @param o the object the found
     * @return the first index of the object or -1
     */
    public int indexOf(String str) {
        Node temp = headNode;
        for (int i = 0; i < size; i++) {
            if (temp.data.equals(str)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Finds the last index of the object desired
     * 
     * @param o the object to be found
     * @return the last index of the object or -1
     */
    public int lastIndexOf(String str) {
        if(str == null || headNode == null) {
            return -1;
        }
        Node temp = headNode;
        int notFound = -1;
        for (int i = 0; i < size; i++) {
            if (temp.data.equals(str)) {
                notFound = i;
            }
            temp = temp.next;
        }
        return notFound;
    }
    
}
