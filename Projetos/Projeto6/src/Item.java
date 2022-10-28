import java.util.Objects;
import java.util.Random;

/**
 * 
 * The Item class represents a supermarket item. This class is immutable
 * 
 * @author João Matos nº 56292
 *
 */
public class Item {

	private final String description;
	private final boolean isRefrigerated;

	/**
	 * The constructor builds a new Item
	 * 
	 * @param description    - The name of the item
	 * @param isRefrigerated - Weather or not the item needs to be refrigerated
	 * @requires description != null
	 */
	public Item(String description, boolean isRefrigerated) {
		this.description = description;
		this.isRefrigerated = isRefrigerated;
	}

	/**
	 * Creates a new Item with a random name between 3 and 10 characters and need
	 * for refrigeration
	 * 
	 * @param rand - The Random object to be used
	 * @return The created Item
	 */
	public static Item getRandomItem(Random rand) {
		StringBuilder sb = new StringBuilder();
		sb.append((char) ('A' + rand.nextInt(26)));
		int chars = rand.nextInt(7) + 2; // gerar numero entre 2 e 9
		for (int i = 0; i < chars; i++) {
			sb.append((char) ('a' + rand.nextInt(26)));
		}
		return new Item(sb.toString(), rand.nextBoolean());
	}

	/**
	 * Gets the description of the Item
	 * 
	 * @return The description of the Item
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the Item's need for refrigeration
	 * 
	 * @return true if the item needs to be refrigerated, false if not
	 */
	public boolean isRefrigerated() {
		return isRefrigerated;
	}

	@Override
	/**
	 * Compares the given object to the current instance of Item
	 * 
	 * @param other - The object to compare
	 * @return true if other is equal to the current Item
	 */
	public boolean equals(Object other) {
		return this == other || other instanceof Item && compare((Item) other);
	}

	/**
	 * This method acts as an auxiliary to the equals method, it compares two Item
	 * objects
	 * 
	 * @param other - The item to compare
	 * @return true if other is equal to the current Item
	 */
	private boolean compare(Item other) {
		return this.description.equals(other.description) && this.isRefrigerated == other.isRefrigerated;
	}

	@Override
	/**
	 * Generates a new hash code for the object
	 */
	public int hashCode() {
		return Objects.hash(description, isRefrigerated);
	}

	@Override
	/**
	 * Builds a textual representation of the object
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(description + " - needs refrigeration: " + isRefrigerated);
		return sb.toString();
	}
}
