import java.util.NoSuchElementException;

/**
 * Air class to hold plates in air. Is a Queue implementation.
 * @author Steve Tran
 */
public class Air implements Queue<Plate> {
	//Maximum allowed items in the air... don't allow more than this!
	/**
	 * Maximum number of plates that can hold in the air.
	 */
	public static final int MAX_CAPACITY = 13;

	//this is your internal storage
	/**
	 * Interal storage for air.
	 */
	public AttachedList<Plate> internalList;

	//you may _NOT_ add any additional instance variables, use the list above!
	//you should not need any additional private helper methods, but you
	//may add them if you like

	/**
	 * Constructor of Air class.
	 */
	public Air() {
		internalList= new AttachedList<>();
		//any initialization goes here
	}

	/**
	 * Add element to the queue air.
	 * @param value T data type
	 * @return true if can add to the queue else return false
	 */
	@Override
	public boolean enqueue(Plate value){
		if(size() != MAX_CAPACITY){
			internalList.add(value);
			return true;}

		return false;

	}

	/**
	 * Removing a plate from air.
	 * @return removed plate
	 */
	@Override
	public Plate dequeue(){
		if(isEmpty()){
			throw new NoSuchElementException("Dequeue attempted on an empty Queue");
		}
		Plate plateDequeued = internalList.remove(0);
		return plateDequeued;
	}

	/**
	 * Number of plates in the air.
	 * @return size
	 */
	@Override
	public int size(){
		return internalList.size();
	}

	/**
	 * Check to see if air is empty.
	 * @return true if is empty else reutrn false
	 */
	@Override
	public boolean isEmpty(){
		return internalList.isEmpty();
	}

	/**
	 * Remove everything from air.
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
	 * Run the tests cases for the Air class.
	 * @param args argument array String
	 */
	public static void main(String[] args) {
		Air a = new Air();
		Plate plate = new Plate(1);
		Plate plate1 = new Plate(2);
		Plate plate2= new Plate(3);
		Plate plate3 = new Plate(4);


		a.enqueue(plate);
		a.enqueue(plate1);
		a.enqueue(plate2);
		a.enqueue(plate3);
		System.out.println(a);
		System.out.println(a.size());

		System.out.println(a.dequeue());
		System.out.println(a);
		System.out.println(a.size());

		a.dequeue();
		System.out.println(a);
		System.out.println(a.size());

		a.dequeue();
		System.out.println(a);
		System.out.println(a.size());

		a.dequeue();
		System.out.println(a);
		System.out.println(a.size());

		System.out.println(a.isEmpty());





	}

	// --------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	// --------------------------------------------------------

	/**
	 * This method print all the element out as a String.
	 * @return a string output that users can read off
	 */
	public String toString() {
		String returnString = "";
		for(Plate p : internalList) {
			returnString = p+returnString;
		}
		return returnString;
	}
}
