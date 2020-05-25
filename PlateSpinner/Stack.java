// --------------------------------------------------------
// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
// --------------------------------------------------------

/**
 * Interface class Stack.
 * @param <T> Data type
 */
interface Stack<T> {
	/**
	 * Interface for push method in Bin class.
	 * @param value T data type
	 * @return true if can push in else return false
	 */
	public boolean push(T value);

	/**
	 * Interface for pop method in Bin class.
	 * @return data removed
	 */
	public T pop(); //throw NoSuchElementException if nothing to pop

	/**
	 * Inhterface for size method in Bin class.
	 * @return size
	 */
	public int size();

	/**
	 * Interface for isEmpty method in Bin class.
	 * @return true is empty else return false
	 */
	public boolean isEmpty();

	/**
	 * Interface for clear method in Bin class.
	 */
	public void clear();
}