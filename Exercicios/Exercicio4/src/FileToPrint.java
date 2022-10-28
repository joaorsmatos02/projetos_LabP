/**
 * This classe implements a file to be printed
 *
 * @author LabP team 2021
 * @author João Matos nº 56292
 *
 */

public class FileToPrint {
	String name; // name of the file
	int size; // size of the file
	String owner; // owner of the file

	/**
	 * Creates a new file
	 * 
	 * @param name  of the file
	 * @param size  of the file
	 * @param owner of the file
	 */
	FileToPrint(String name, int size, String owner) {
		this.name = name;
		this.size = size;
		this.owner = owner;
	}

	/**
	 * Gets the name of the file
	 * 
	 * @return name of the file
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the size of the file
	 * 
	 * @return size of the file
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Gets the owner of the file
	 * 
	 * @return owner of the file
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * The textual representation of the file
	 */
	@Override
	public String toString() {
		String st = "[" + name + "," + size + "," + owner + "]";
		return st;
	}

	/**
	 * Redefinition of the method equals
	 */
	@Override
	public boolean equals(Object other) {
		return this == other || other instanceof FileToPrint && compare((FileToPrint) other);
	}

	/**
	 * An auxiliary method to compare two FileToPrint objects
	 * 
	 * @param other - the object to compare with this
	 * @return true if objects are equal, false if otherwise
	 */
	private boolean compare(FileToPrint other) {
		boolean result = true;
		if (this.getSize() != other.getSize())
			result = false;
		else if (!this.getName().equals(other.getName())) {
			result = false;
		}
		return result;
	}

}
