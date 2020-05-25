// TO DO: add your implementation and JavaDoc

import java.util.Arrays;
// TO DO: add your implementation and JavaDoc

/**
 * BetterArray class is a resizable array that allows element to be added when the capacity  of the array is full.
 * BetterArray has a default capacity of 2 if it's not declared.
 * @param <T> BetterArray accept generic types.
 * @author Steve Tran.
 */

public class BetterArray<T> {
	/**
	 * Default initial capacity / minimum capacity.
	 */
	private static final int DEFAULT_CAPACITY = 2;
	/**
	 * Generic data array.
	 */
	private T[] data;	//underlying array, you MUST use this for full credit

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	/**
	 * Array size type int.
	 */
	private int size;
	/**
	 * Array capacity type int.
	 */
	private int capacity;

	/**
	 * Create a generic array  to hold data , can hold up to default capacity.
	 * We say size = 0 because current the array is empty after it's created.
	 */
	@SuppressWarnings("unchecked")
	public BetterArray() {
		//constructor
		//initial capacity of the array should be DEFAULT_CAPACITY
		capacity=DEFAULT_CAPACITY;
		data= (T[]) new Object[capacity];
		size=0;
	}

	/**
	 * Create a generic array to hold data , can hold up to initial.
	 * We say size = 0 because current the array is empty after it's created.
	 * @param initialCapacity initial capacity that's the array can hold.
	 * @throws IllegalArgumentException if the value of initialCapacity is less than 1 it will print out the exception.
	 */

	@SuppressWarnings("unchecked")
	public BetterArray(int initialCapacity) {
		// constructor
		// set the initial capacity of the smart array as initialCapacity
		capacity=initialCapacity;
		data= (T[]) new Object[capacity];
		size=0;
		// throw IllegalArgumentException if initialCapacity is smaller than 1
		if(initialCapacity<1){
			throw new IllegalArgumentException(" Capacity cannot by smaller than 1");
		}
	}

	/**
	 * Keeping tracks of number of elements in the array.
	 * @return the current size
	 */
	public int size() {
		//report number of elements in the smart array
		// O(1)

		return size;
	}

	/**
	 * Keeping track of the length of the array.
	 * @return the current length
	 */
	public int capacity() {
		//report max number of elements before the next expansion
		// O(1)

		return capacity;
	}

	/**
	 * The boolean method append will add an element at end of the array. In this method we have 2 others methods which is isFull() and expand().
	 * First it will check for if the array is full  ,then if it is full, the expand() will double the capacity of the the array and than create.
	 * A second array with that capacity then copy all the element from the old array to the new array.
	 * It then set the old array to the new array.
	 * @param value a generic T value that will be added to the end of the array.
	 * @return true because this will always work.
	 */

	@SuppressWarnings("unchecked")
	public boolean append(T value) {
		// add an element to the end
		// return true
		if(isFull()){
			expand();
		}
		T x = value;
		data[size]=x;
		size++;

		// double capacity if no space available
		// amortized O(1)

		return true;
	}

	/**
	 * This method allows you to add a value at the given index.
	 * It will  check to see if the array is full .
	 * If there are no more space, the expand() will create a new generic array that has double the capacity of the old array.
	 * The data from the old array then will be added into the new array.
	 * Old array will be set to new array and capacity will bee set to the new doubled capacity.
	 * @throws IndexOutOfBoundsException it will first check for valid index.
	 * @param index the location that the new element will be add in at.
	 * @param value the value of the element that will add in at given index.
	 *
	 */

	@SuppressWarnings("unchecked")
	public void add(int index, T value) {
		// insert value at index, shift elements if needed
		// double the capacity if no space is available
		// throw IndexOutOfBoundsException for invalid index
		// O(N) where N is the number of elements in the array
		if(index <0 || index>size()){
			throw new IndexOutOfBoundsException(" Invalid index");
		}
		if(isFull()){
			expand();
		}

		for( int i = size; i>index; i--){
			data[i]=data[i-1];
		}
		data[index]=value;
		size++;


		// Note: this method may be used to append items as
		// well as insert items
	}
	/**
	 * The expand() method will create a new T array that can hold double of what the current capacity is.
	 * It then will copy all the data from the old array to the new array.
	 * After it done copy, it will set the old array to the new array and change the current capacity to the doubled capacity.
	 *
	 */
	private void expand(){
		int length=size();
		T []  newData= (T[]) new Object[capacity*2];
		for( int i = 0; i < length; i++){
			newData[i]=data[i];
		}
		data=newData;
		capacity=capacity*2;
	}

	/**
	 * The boolean isFull() method is to check to see if the array is full or not.
	 * @return true if the capacity cant hold any more else @return false
	 */
	private boolean isFull(){
		return size()==capacity;
	}

	/**
	 * This method will get the value at the given index.
	 * @param index index of the element that we want to get.
	 * @return the data at the given index.
	 * @throws IndexOutOfBoundsException will not work if you try to get value at index that is <0 or > size.
	 */
	public T get(int index)  {
		// return the item at index
		// throw IndexOutOfBoundsException for invalid index
		// O(1)
		if(index <0 || index>size()){
			throw new IndexOutOfBoundsException(" Invalid index");
		}

		return data[index];
	}
	/**
	 *This method let you replace the element at the given index.
	 * @param index index of the element that want to replace.
	 * @param value the value to be replace with.
	 * @return return the value of the element that got replace.
	 * @throws IndexOutOfBoundsException is checks for valid index before replacing.
	 */

	public T replace(int index, T value){
		// change item at index to be value
		// return old item at index
		// throw IndexOutOfBoundsException for invalid index
		// O(1)
		if(index <0 || index >size){
			throw new IndexOutOfBoundsException("Invalid index");
		}
		T x = data[index];
		data[index]=value;


		// Note: you cannot add new items with this method

		return x;
	}

	/**
	 * This method will remove the element at the given index.
	 * The capacity will be reduced to half if the current capacity go bellow 1/4.
	 * @param index index of the element to be remove.
	 * @return the old variable;
	 * @throws IndexOutOfBoundsException it will first check for valid index before removing.
	 */
	@SuppressWarnings("unchecked")
	public T delete(int index){
		// remove and return element at position index
		// shift elements to remove any gap in the array
		// throw IndexOutOfBoundsException for invalid index
		//halve

		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Invalid Index! (at add)");
		}
		if(size() <= (capacity/4)){
			halve();

		}

		T val = data[index];
		//shift data
		for(int i = index;i<size-1;i++){
			data[i]=data[i+1];
		}
		data[--size]=null;
		// halve capacity if the number of elements falls below 1/4 of the capacity
		// capacity should NOT go below DEFAULT_CAPACITY
		// O(N) where N is the number of elements in the list
		return val;
	}

	/**
	 * This method will reduce the capacity size to half.
	 * It will create a new array with the new capacity.
	 * Then it will copy all the element from old array to new array.
	 * Set old array to new array.
	 */
	private void halve() {
		int length = size();
		capacity=capacity/2;
		T []  newData= (T[]) new Object[capacity];
		for( int i = 0; i < length; i++){
			newData[i]=data[i];
		}
		data=newData;
	}


	/**
	 * Get the index of the of the value when its first occurrence.
	 * @param value the value that looking for when it's first occur in the array.
	 * @return the index when the value first occur or -1 if not found.
	 */
	public int firstIndexOf(T value){
		// return the index of the first occurrence or -1 if not found
		// O(n)
		if(value == null){
			for (int i = 0 ;i<size;i++){
				if(data[i]==null){
					return i;
				}
			}
		}else{
			for(int i = 0 ; i < size; i++){
				if(data[i].equals(value))
					return i;
			}
		}
		return -1;
	}

	/**
	 * This method forced the capacity to change before expansion.
	 * @param newCapacity newCapacity will be set to capacity if its is valid.
	 * @return true if its expanded, return false if newCapacity didn't get apply.
	 */
	@SuppressWarnings("unchecked")
	public boolean ensureCapacity(int newCapacity){
		// change the max number of items allowed before next expansion to newCapacity
		if(newCapacity>DEFAULT_CAPACITY && newCapacity>size){
			capacity = newCapacity;
			return true;
		}
		else



			// capacity should not be changed if:
			//   - newCapacity is below DEFAULT_CAPACITY; or
			//   - newCapacity is not large enough to accommodate current number of items

			// return true if newCapacity gets applied; false otherwise
			// O(N) where N is the number of elements in the array

			return false;
	}

	/**
	 * Make a copy of the data array.
	 * Set the size and capacity.
	 * @return the clone array
	 */
	public BetterArray<T> clone() {
		int length= size();

		//make a copy of 1 the current values
		//don't forget to set the capacity!
		//O(n)

		BetterArray<T> cloneData = new BetterArray<T>(capacity);
		for(int i = 0; i < length;i++){
			cloneData.data[i]=data[i];
		}
		cloneData.size=size();
		cloneData.capacity=capacity;


		return cloneData;
	}




	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------

	/**
	 * Run the tests cases for the BetterArray class.
	 * @param args argument array String
	 */
	public static void main(String args[]) {
		//create a smart array of integers
		BetterArray<Integer> nums = new BetterArray<>();
		if ((nums.size() == 0) && (nums.capacity() == 2)){
			System.out.println("Yay 1");
		}

		//append some numbers
		for (int i=0; i<3;i++)
			nums.add(i,i*2);

		if (nums.size()==3 && nums.get(2) == 4 && nums.capacity() == 4 ){
			System.out.println("Yay 2");
		}
		System.out.println("Nums size: "+nums.size());
		System.out.println("nums element: "+nums);
		System.out.println("Nums cap: " + nums.capacity);
		System.out.println("--------------------------------");

		//create a smart array of strings
		BetterArray<String> msg = new BetterArray<>();

		//insert some strings
		msg.add(0,"world");
		msg.add(0,"hello");
		msg.add(1,"new");
		msg.append("!");

		//replace and checking
		if (msg.get(0).equals("hello") && msg.replace(1,"beautiful").equals("new")
				&& msg.size() == 4 && msg.capacity() == 4 ){
			System.out.println("Yay 3");
		}
		System.out.println("MSG Element: "+ msg);
		System.out.println("MSg size: "+ msg.size());
		System.out.println("Msg Cap: "+ msg.capacity);
		System.out.println("--------------------------");



		//delete and shrinking
		if (msg.delete(1).equals("beautiful") && msg.get(1).equals("world")
		){
			System.out.println("Yay 5");
		}
		System.out.println("Message Size " + msg.size);
		System.out.println("Message: "+ msg);


		System.out.println("Capacity: " + msg.capacity());
		System.out.println("------------------------------------------------------");




		//firstIndexOf and clone
		//remember what == does on objects... not the same as .equals()
		BetterArray<String> msgClone = msg.clone();
		System.out.println("Clone message: " + msgClone);
		if (msgClone != msg && msgClone.get(1) == msg.get(1)
				&& msgClone.size() == msg.size()
				&& msgClone.capacity() == msg.capacity()
				&& msgClone.firstIndexOf("world") == 1
				&& msgClone.firstIndexOf("beautiful") == -1) {
			System.out.println("Yay 6");
		}

		BetterArray<Character> c = new BetterArray<>();
		c.append('a');
		c.append('b');
		c.append('c');
		System.out.println("First index of " + c.firstIndexOf('c'));

		System.out.println(nums.firstIndexOf(0));
		System.out.println(nums.firstIndexOf(4));
		System.out.println(msgClone.firstIndexOf("world") );

	}

	// --------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	// --------------------------------------------------------

	/**
	 * This method print all the element out as a String.
	 * @return a string output that users can read off
	 */
	public String toString() {
		if(size() == 0) return "";

		StringBuffer sb = new StringBuffer();
		sb.append(get(0));
		for(int i = 1; i < size(); i++) {
			sb.append(", ");
			sb.append(get(i));
		}
		return sb.toString();
	}
}