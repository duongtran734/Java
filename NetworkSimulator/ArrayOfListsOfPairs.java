//This structure is an array where each entry is the head of its own
//linked list. The linked lists are made up of "bare nodes" (i.e.
//they are not "wrapped" in a nice linked list class). Each node
//in each linked list contains a key-value pair (rather than an single
//value).

//This class will form the baseline for creating a hash table for a
//map that uses separate chaining (each entry in a map is a key-value
//pair). This class will also form a baseline for creating an adjacency
//list (where each entry is a key-value pair where keys are the
//"adjacent" node and values are the connection between them). This way
//we have a universal way for to access your internal structures when
//grading these two classes.

//You have a lot of freedom in how you design this class, as long as
//the provided code work as described. However, because this is only
//a baseline for the other classes you are writing, it would be bad
//design on your part to add in anything specific to hash tables
//(such as rehashing) or specific to graphs (such as source/destination
//information for edges). Our advice to you is: (1) keep it simple
//and (2) think before you code.

//Read the "do not edit" section carefully so that you know what is
//already available. This should help you form some ideas of what
//types of things are missing.

//You may: Add additional methods or variables of any type (even
//public!), but again, you _must_ use the provided Node class, the
//provided "storage" instance variable, and all provided methods
//_must still work_.

//You may not import anything from java.util (and you may not use anything
//from java.util in your part of the code). We use java.util.ArrayList in
//the provided code, but it is not available to you.


/**
 * This class will create an Array of Linked List for storing information.
 * @author Steve Tran
 * @param <K> is the key to locate the element.
 * @param <V> is the value of the element.
 */
public class ArrayOfListsOfPairs<K,V> {
	//This is your internal structure, you must use this
	//you may not change the type, name, privacy, or anything
	//else about this variable. It is initialized in the
	//provided constructor (see the do-not-edit section)
	//and the Node class is also defined there.
	/**
	 * Node array.
	 */
	private Node<K,V>[] storage;

	// Your code goes here!


	/**
	 * Check if Key if inside the list.
	 * @param key is key to search
	 * @param index is index of array to search in
	 * @return true if key is found else return false
	 */
	public boolean containsKey(K key, int index) {
		if(key==null){
			return false;
		}
		Node<K,V> current = storage[index];
		while (current != null) {
			if (key.equals(current.pair.getKey())) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	/**
	 * This method will replace the given key with the given value.
	 * @param key is the key to replace with
	 * @param newValue is the value to replace
	 * @param index is the index to find the key in the list
	 * @return true if successfully replaced else return false
	 */
	public  boolean replaceValueOfKey(K key,V newValue ,int index){
		if(key==null){ return false; }
		KeyValuePair<K,V> newPair = new KeyValuePair<>(key,newValue);
		Node<K,V> current = storage[index];
		while (current != null) {
			if (key.equals(current.pair.getKey())) {

				current.pair=newPair;
				return true;
			}
			current = current.next;
		}
		return false;

	}

	/**
	 * This method will get the value of a given key.
	 * @param key is the key to look for
	 * @param index key located at this index array
	 * @return the value of the key
	 */
	public V getValueOfKey(K key, int index){
		Node<K,V> temp = storageSeekNode(key,index);
		return  temp.pair.getValue();
	}

	/**
	 * This method will remove an element from the list.
	 * @param key to remove
	 * @param index of which list given key is located in
	 * @return true if successfully removed else return false
	 */
	public boolean removeKey(K key, int index){
		Node<K,V> current= storage[index];
		Node<K,V> previous = null;
		while(current != null){
			if(current.pair.getKey().equals(key)) {
				if (current == storage[index]) {
					storage[index] = current.next;
				}
				else {
					previous.next=current.next;
				}
				return true;
			}
			previous=current;
			current=current.next;
		}
		return false;
	}

	/**
	 * This storage will get the node based on the given key.
	 * @param key is the key of the node to search for
	 * @param index is location of which array the key located in
	 * @return the node
	 */
	public Node<K,V> storageSeekNode(K key, int index){
		if(key == null){return null;}
		if(storage[index]==null){return null;}
		Node<K,V> current = storage[index];
		while(current != null){
			if(current.pair.getKey().equals(key)){
				return current;
			}
			current = current .next;
		}
		return null;
	}

	/**
	 * Check to see if the sub list is empty.
	 * @param index of what sublist to check for
	 * @return true if it's empty else return false
	 */
	public boolean isEmpty(int index){
		return storage[index]==null;
	}

	/**
	 * This method let you add a KeyValuePair into the table.
	 * @param pair is the KVP to add
	 * @param index is the location of which sublist to add to
	 */
	public void add(KeyValuePair<K,V> pair , int index){

		Node<K,V> entry=new Node<K,V>(pair);
		//if head is null
		if(isEmpty(index)){
			storage[index]=entry;
		}else{
			Node<K,V> current = storage[index];

			while(current.next != null){
				current = current.next;
			}
			current.next=entry;
		}

	}

	/**
	 * This method is to rehash the table in hashtable.
	 * @param newSize is the new table size
	 * @param newStorage is the new table to rehash into
	 */
	public void rehash(int newSize,ArrayOfListsOfPairs<K,V> newStorage) {
		Node<K,V> current;
		for(int i = 0; i<getNumLists();i++){
			current= storage[i];
			while(current != null){
				int index = (current.pair.getKey().hashCode() & 0xfffffff);
				index = index%newSize;
				newStorage.add(current.pair,index);
				current = current .next;
			}

		}
	}

	/**
	 * Remove a an edge.
	 * @param edgeToRemove  is the edge to remove
	 * @return true if successfully removed else return false
	 */
	public boolean removeValue( V edgeToRemove){
		for(int i = 0; i< getNumLists();i++){
			Node<K,V> current= storage[i];
			Node<K,V> previous = null;
			while(current != null){
				if(current.pair.getValue().equals(edgeToRemove)) {
					if (current == storage[i]) {
						storage[i] = current.next;
					}
					else {
						previous.next=current.next;
					}
					return true;
				}
				previous=current;
				current=current.next;
			}
		}
		return false;
	}

	/**
	 * Find the Host Destination for the edge.
	 * @param d is the directed edge
	 * @return the host
	 */
	public K findDestHost(V d){
		for(int i=0 ;i<getNumLists();i++){
			Node<K,V> current = storage[i];
			while(current!=null){
				if (current.pair.getValue().equals(d)) {
					return current.pair.getKey();

				}
				current=current.next;
			}
		}
		return null;
	}


	/**
	 * Find out where the source located.
	 * @param d is the edge of the source
	 * @return the location of the source
	 */
	public int findSource(V d){

		for(int i = 0; i< getNumLists();i++){
			Node<K, V> current = storage[i];
			while(current != null){
				if(current.pair.getValue().equals(d)){
					return i;
				}
				current = current .next;
			}
		}
		return -1;
	}

	/**
	 * Pointer of the head in the list.
	 * @param index is the find which head.
	 * @return the Node pointing to head.
	 */
	public Node<K, V> headPointer(int index){
		return storage[index];
	}

	/**
	 * Get edge of a Host.
	 * @param v is the host
	 * @param index is the location of where in the array.
	 * @return the edge
	 */
	public V getEdge(Host v, int index ){
		Node<K,V> current=storage[index];
		while(current != null){
			if(current.pair.getKey().equals(v)){
				return current.pair.getValue();
			}
			current = current.next;
		}
		return null;
	}

	/**
	 * Find predecessor of a vertex.
	 * @param vertex to look for
	 * @param index where in array to look for
	 * @return true if found predecessor
	 */
	public boolean findPredecessor(Host vertex, int index){
		Node<K, V> current = storage[index];
		while(current!=null){
			if(current.pair.getKey().equals(vertex)){
				return true;
			}
			current=current.next;
		}
		return false;
	}


	
	//--------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	//--------------------------------------------------------

	/**
	 * toString class allowing us to see the results.
	 * @return the results.
	 */
	public String toString() {
		//you may edit this to make string representations of your
		//lists for testing
		StringBuffer sb = new StringBuffer();
		for(Object x : storage){
			sb.append(x + " ");
			Node<K,V> current = storage[0];
			while(current != null){
				sb.append(current + " ");
				current = current .next;
			}

		}
		return sb.toString();
	}

	/**
	 * Main string args to do test cases in .
	 * @param args arguments
	 */
	public static void main(String[] args) {
		KeyValuePair<Integer,Integer> test = new KeyValuePair<>(1 ,2);
		KeyValuePair<Integer,Integer> test2 = new KeyValuePair<>(3 ,4);
		ArrayOfListsOfPairs<Integer,Integer> arr = new ArrayOfListsOfPairs<>(10);
		System.out.println(arr);
	}




	//--------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	//--------------------------------------------------------

	/**
	 * Node class.
	 * @param <K> key of the node.
	 * @param <V> value of the node.
	 */
	//This is what one node in one linked list looks like
	public static class Node<K,V> {
		//it contains one key-value pair
		/**
		 * A pair of key and value.
		 */
		public KeyValuePair<K,V> pair;
		//and one pointer to the next node
		/**
		 * Point to next node.
		 */
		public Node<K,V> next;
		//convenience constructor
		/**
		 * Constructor of node class.
		 * @param pair contain key and pair.
		 */
		public Node(KeyValuePair<K,V> pair) {
			this.pair = pair;
		}
		//convenience constructor
		/**
		 * Constructor of Node class.
		 * @param pair contain key and value.
		 * @param next is next node.
		 */
		public Node(KeyValuePair<K,V> pair, Node<K,V> next) {
			this.pair = pair;
			this.next = next;
		}
	}
	
	//Creates an array with the specified number of lists-of-pairs

	/**
	 * Contructor of the class.
	 * @param numLists is the capacity
	 */
	@SuppressWarnings("unchecked")
	public ArrayOfListsOfPairs(int numLists) {

		storage = (Node<K,V>[]) new Node[numLists];
	}
	
	//Returns the number of lists in this collection

	/**
	 * This class will get number of elements the list can hold.
	 * @return the list length.
	 */
	public int getNumLists() {

		return storage.length;
	}
	
	//Returns all key-value pairs in the specified sublist of this collection
	/**
	 * Get all the pairs in sublist and return it.
	 * @param listId lists to return.
	 * @return the lists.
	 */
	public java.util.ArrayList<KeyValuePair<K,V>> getAllPairs(int listId) {
		java.util.ArrayList<KeyValuePair<K,V>> lst = new java.util.ArrayList<>();
		
		Node<K,V> current = storage[listId];
		while(current != null) {
			lst.add(current.pair);
			current = current.next;
		}
		
		return lst;
	}
	
	//Returns all key-value pairs in this collection

	/**
	 * Get all the pairs and return it.
	 * @return all pairs.
	 */
	public java.util.ArrayList<KeyValuePair<K,V>> getAllPairs() {
		java.util.ArrayList<KeyValuePair<K,V>> lst = new java.util.ArrayList<>();
		
		for(int i = 0; i < storage.length; i++) {
			lst.addAll(getAllPairs(i));
		}
		
		return lst;
	}
}
