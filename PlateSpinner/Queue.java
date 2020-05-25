// --------------------------------------------------------
// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
// --------------------------------------------------------

/**
 * Queue interface class.
 * @param <T> T data type
 */
interface Queue<T> {
	/**
	 * Interface for enqueue method in air.
	 * @param value T data type
	 * @return true if can add else return false
	 */
	public boolean enqueue(T value);

	/**
	 * Interface for dequeue method in air.
	 * @return data removed
	 */
	public T dequeue(); //throw NoSuchElementException if nothing to dequeue

	/**
	 * Interface for size in air class.
	 * @return size
	 */
	public int size();

	/**
	 * Interface for isEmpty method in air.
	 * @return true for yes else return false
	 */
	public boolean isEmpty();

	/**
	 * Interface for clear method.
	 */
	public void clear();
}