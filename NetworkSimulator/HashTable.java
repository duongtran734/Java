//Hash table with separate chaining. Each key and value gets
//placed together as a single entry in the table. The hash code
//of a key is used to place the pair in the table and to look
//for it again. Note that KeyValuePair is a structure for
//ArrayOfListsOfPairs, this part of the code needs to be able to
//deal with keys and values separately.

import java.util.Collection;


/**
 * This is HashTable class using Separate Chaining Method.
 * @author Steve Tran
 * @param <K> is Key
 * @param <V> is Value
 */
public class HashTable<K,V> {
	//This is the minimum number of slots in the hash table
	//Do not change this.
	/**
	 * The default capacity.
	 */
	private static final int MIN_SLOTS = 2;
	/**
	 * The default size.
	 */
	private int size=0;

	//You must use this as your internal storage, you may not change
	//the type, name, privacy, or anything else about this variable.
	/**
	 * Internal storage.
	 */
	protected ArrayOfListsOfPairs<K,V> storage;
	
	//If the number of slots requested is less than the minimum
	//number of slots, use the minimum instead.

	/**
	 * Constructor of Hashtable class.
	 * @param numSlots is the capacity of the table.
	 */
	public HashTable(int numSlots) {
		if (numSlots < 0) throw new IllegalArgumentException("Error Illegal capacity");
		else if(numSlots<MIN_SLOTS){
			storage=new ArrayOfListsOfPairs<>(MIN_SLOTS);
		}
		else{
			storage=new ArrayOfListsOfPairs<>(numSlots);
		}
	}
	
	//The number of key-value entries in the table.
	//O(1)

	/**
	 * The number of elements are in the table.
	 * @return size
	 */
	public int size() {
		return size;
	}
	
	//Returns the number of slots in the table.
	//O(1)

	/**
	 * Get number of slots in the table.
	 * @return number of slots
	 */
	public int getNumSlots() {
		return storage.getNumLists();
	}
	
	//Returns the load on the table.
	//O(1)

	/**
	 * Loadfactor of the table.
	 * @return the load of the table
	 */
	public double getLoad() {
		return (double)size()/(double)getNumSlots();
	}
	
	//If the key is not in the table, add the key-value entry to the table
	//and return true. If unable to add the entry, return false. Keys and
	//values are _not_ allowed to be null in this table, so return false if
	//either of those are provided instead of trying to add them.
	
	//If the load goes above 3 after adding an entry, this method should
	//rehash to three times the number of slots.
	
	//Must run in worst case O(n) and average case O(n/m) where n is the
	//number of key-value pairs in the table and m is the number of "slots"
	//in the table.

	/**
	 * Add an element into the list.
	 * @param key of the element to add
	 * @param value of the element to add
	 * @return true if able to add else return false
	 */
	public boolean add(K key, V value)
	{
		if (key == null || value == null){ return false; }
		KeyValuePair<K,V> pair = new KeyValuePair<>(key,value);
		int index =hashKeyToIndex(key);
		if(!contains(key)){
			storage.add(pair,index);
			size++;
			if(getLoad()>3){
				rehash(3*getNumSlots());}
			return true;
		}
		return false;
	}
	
	//Rehashes the table to the given new size (new number of
	//slots). If the new size is less than the minimum number
	//of slots, use the minimum instead.
	
	//Must run in worst case O(n+m) where n is the number of
	//key-value pairs in the table and m is the number of
	//"slots" in the table.

	/**
	 * Rehash the storage into bigger storage.
	 * @param newSize is the new size of the new storage
	 */
	public void rehash(int newSize) {
		int sizeToUse= newSize<getNumSlots()? getNumSlots(): newSize;
		ArrayOfListsOfPairs<K,V> newStorage = new ArrayOfListsOfPairs<>(sizeToUse);
		storage.rehash(sizeToUse,newStorage);
		storage=newStorage;
	}
	
	//If the key requested is in the table, change the associated value
	//to the provided value and return true. Otherwise return false.
	
	//Must run in worst case O(n) and average case O(n/m) where n is the
	//number of key-value pairs in the table and m is the number of "slots"
	//in the table.

	/**
	 * Replace the existed key with the new value.
	 * @param key is the key to be replace
	 * @param value is the new value to replace
	 * @return true if successfully replaced key with new value else return false
	 */
	public boolean replace(K key, V value) {
		int index = hashKeyToIndex(key);
		return storage.replaceValueOfKey(key,value,index);
	}
	
	//If the key requested is in the table, remove the key-value entry
	//and return true. Otherwise return false.
	
	//Must run in worst case O(n) and average case O(n/m) where n is the
	//number of key-value pairs in the table and m is the number of "slots"
	//in the table.

	/**
	 * Remove a key from the lists.
	 * @param key to remove
	 * @return true if key is removed else return false
	 */
	public boolean remove(K key) {
		int index = hashKeyToIndex(key);
		if (key == null){
			return false;
		}
		else if(storage.removeKey(key,index)){
			size--;
			return true;
		}
		return false;
	}
	
	//If the key requested is in the table, return true. Otherwise return false.
	//Must run in worst case O(n) and average case O(n/m) where n is the
	//number of key-value pairs in the table and m is the number of "slots"
	//in the table.

	/**
	 * Check to see if the key is already in the lists.
	 * @param key is the key to search for
	 * @return true if its already in the list else return false
	 */
	public boolean contains(K key) {
		int index=hashKeyToIndex(key);
		return storage.containsKey(key, index);
	}
	
	//If the key requested is in the table, return the associated value.
	//Otherwise return null.
	
	//Must run in worst case O(n) and average case O(n/m) where n is the
	//number of key-value pairs in the table and m is the number of "slots"
	//in the table.

	/**
	 * Get the value of a given key.
	 * @param key is the key to search for
	 * @return the value of that key
	 */
	public V get(K key) {
		if(key == null){ return null; }
		int index = hashKeyToIndex(key);
		if(storage.storageSeekNode(key,index)!=null){
			return storage.getValueOfKey(key,index);
		}
		return null;
	}



	/**
	 * Hash function for the key.
	 * @param key to hash
	 * @return index
	 */
	private int hashKeyToIndex(K key){
			int x= (key.hashCode() & 0xfffffff);
		x=x%getNumSlots();
		return x;
	}


	
	//--------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	//--------------------------------------------------------

	/**
	 * Tostring method.
	 * @return string representation.
	 */
	public String toString() {
		//you may edit this to make string representations of your
		//lists for testing
		return storage.toString();
	}


	/**
	 * Main string args.
	 * @param args arguments
	 */
	public static void main(String[] args) {
		//Some example testing code...
		
		//make a hash table and add something to it
		HashTable<Integer,String> ht = new HashTable<>(2);


		ht.add(2,"Apple");
		ht.add(4,"House");
		ht.add(6,"Window");
		ht.add(8,"Door");
		ht.add(1,"Carpet");
		ht.add(3,"Floor");
		ht.add(5,"Roof");
		ht.add(78,"Star");
		ht.add(7,"Stair");





		System.out.println("THIS IS LOAD " + ht.getLoad());
		System.out.println("SIZE " + ht.size());

		//get all pairs at location 0
		Collection<KeyValuePair<Integer,String>> pairs = ht.getInternalTable().getAllPairs(0);
		Collection<KeyValuePair<Integer,String>> pairs1 = ht.getInternalTable().getAllPairs(1);
		Collection<KeyValuePair<Integer,String>> pairs2= ht.getInternalTable().getAllPairs(2);
		Collection<KeyValuePair<Integer,String>> pairs3 = ht.getInternalTable().getAllPairs(3);
		Collection<KeyValuePair<Integer,String>> pairs4 = ht.getInternalTable().getAllPairs(4);
		Collection<KeyValuePair<Integer,String>> pairs5 = ht.getInternalTable().getAllPairs(5);


		System.out.println(" 0 "+ pairs);
		System.out.println(" 1 "+ pairs1);
		System.out.println(" 2 " +pairs2);
		System.out.println(" 3 " +pairs3);
		System.out.println(" 4 " +pairs4);
		System.out.println(" 5 " +pairs5);

		System.out.println("-----------------------------------------");
		System.out.println(ht.get(1));
		System.out.println(ht.get(2));
		System.out.println(ht.get(3));
		System.out.println(ht.get(4));
		System.out.println(ht.get(5));
		System.out.println(ht.get(6));
		System.out.println(ht.get(7));
		System.out.println(ht.get(78));


		//should be one pair there...
		if(pairs.size() == 1) {
			//get the first pair from the list
			KeyValuePair<Integer,String> pair = pairs.iterator().next();
			//make sure it's the pair expected
			if(pair.getKey().equals(2) && pair.getValue().equals("Apple")) {
				System.out.println("Yay");
			}
		}




	}
	
	//--------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	//--------------------------------------------------------
	
	
	//This will be used to check that you are setting
	//the storage up correctly... it's very important
	//that you (1) are using the ArrayOfListsOfPairs 
	//provided and (2) don't edit this at all

	/**
	 * Get info of inner storage.
	 * @return storage
	 */
	public ArrayOfListsOfPairs<K,V> getInternalTable() {
		return storage;
	}
}