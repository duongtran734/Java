import java.util.NoSuchElementException;

/**
 * Bin class to hold plates. Is a Stack implementation.
 * @author Steve Tran
 */
public class Bin implements Stack<Plate> {
	//this is your internal storage
	/**
	 * Internal storage, also known as bin.
	 */
	public AttachedList<Plate> internalList;

	//you may _NOT_ add any additional instance variables, use the list above!
	//you should not need any additional private helper methods, but you
	//may add them if you like
	/**
	 * Constructor of Bin class.
	 */
	public Bin() {
		//any initialization goes here
		internalList= new AttachedList<>();
		internalList.size();


	}

	/**
	 * Insert new element into the stack.
	 * @param value Plate Type
	 * @return true because can always insert a new element
	 */
	@Override
	public boolean push(Plate value){
		internalList.add(0,value);
		return true;
	}

	/**
	 * Remove an element at the top of stack.
	 * @return the removed element
	 */
	@Override
	public Plate pop(){
		if(isEmpty()){
			throw new NoSuchElementException("Pop attemped on empty list");
		}

		Plate plate = internalList.remove(0);
		return plate;
	}

	/**
	 * Size of the stack.
	 * @return size
	 */
	@Override
	public int size(){
		return internalList.size();
	}

	/**
	 * Check to see if the stack is empty.
	 * @return true if size == 0 else return false
	 */
	@Override
	public boolean isEmpty(){
		return internalList.isEmpty();
	}

	/**
	 * Remove all element from the stack.
	 */
	@Override
	public void clear(){
		internalList.clear();
	}

	//override all the required methods
	//all methods must be O(1)
	//all methods can be written in 3 lines or less of code
	//if you're writing much more than that, something is probably wrong...


	// --------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	// --------------------------------------------------------

	/**
	 * Run the tests cases for the Bin class.
	 * @param args argument array String
	 */
	public static void main(String[] args) {
		Plate plate = new Plate(1);
		Plate plate1 = new Plate(2);
		Plate plate2= new Plate(3);
		Plate plate3 = new Plate(4);

		Bin a = new Bin();
		a.push(plate);
		a.push(plate1);
		a.push(plate2);
		a.push(plate3);
		a.pop();
		System.out.println(a);













	}

	// --------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	// --------------------------------------------------------

	/**
	 * Too String method allowing us to see the results of what we printing out.
	 * @return String result
	 */
	public String toString() {
		String returnString = "";
		boolean first = true;
		for(Plate p : internalList) {
			if(first) { returnString = returnString+p; first = false; }
			else {returnString = returnString+"|"+p; }
		}
		return returnString;
	}
}
