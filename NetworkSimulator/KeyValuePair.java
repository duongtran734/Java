//This class represents a key-value pair. It is completed for you,
//but you need to add JavaDocs.

//--------------------------------------------------------
// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
//--------------------------------------------------------

/**
 * KeyValuePair class.
 * @param <K> is the key.
 * @param <V> is the value.
 */
public class KeyValuePair<K,V> {
	/**
	 * Key.
	 */
	private K key;
	/**
	 * Value.
	 */
	private V value;

	/**
	 * Constructor of the class.
	 * @param key type K.
	 * @param value type V.
	 */
	public KeyValuePair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Get the key.
	 * @return the key.
	 */
	public K getKey() {
		return key;
	}

	/**
	 * Get the value.
	 * @return the value.
	 */
	public V getValue() {
		return value;
	}

	/**
	 * Check to see if the object is an valid KeyValuePair.
	 * Then compare the key of the object to the current key.
	 * @param o is the object to check for.
	 * @return true of the current key equals the object key, else return false.
	 */
	public boolean equals(Object o) {
		if(o instanceof KeyValuePair) {
			return key.equals(((KeyValuePair)o).key);
		}
		return false;
	}

	/**
	 * Hashcode the key.
	 * @return the value as integer.
	 */
	public int hashCode() {
		return key.hashCode();
	}

	/**
	 * Display the key and value as a String representation.
	 * @return result.
	 */
	public String toString() {
		return "("+key+","+value+")";
	}
}