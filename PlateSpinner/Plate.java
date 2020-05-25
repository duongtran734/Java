// --------------------------------------------------------
// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
// --------------------------------------------------------

/**
 * Plate class.
 */
public class Plate {
	/**
	 * Number as integer type.
	 */
	private int number;

	/**
	 * Create a plate with the number.
	 * @param number int type
	 */
	public Plate(int number) {
		this.number = number;
	}

	/**
	 * To see the plate as a String form.
	 * @return String palte representation
	 */
	public String toString() {
		return "("+((char)(this.number+96))+")";
	}

	/**
	 * Get the plate number.
	 * @return plate number
	 */
	public int getNumber() {
		return this.number;
	}
}
