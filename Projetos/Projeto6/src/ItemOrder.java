
/**
 * 
 * The ItemOrder class represents the order of an amount of a specific Item
 * 
 * @author João Matos nº 56292
 *
 */
public class ItemOrder {

	private int quantity;
	private Item item;

	/**
	 * The constructor builds a new ItemOrder object with the given information
	 * 
	 * @param item     - The item in the order
	 * @param quantity - The amount to be ordered
	 * @requires item != null && quantity > 0
	 */
	public ItemOrder(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}

	/**
	 * Gets the amount of units to be ordered
	 * 
	 * @return the amount of units to be ordered
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Gets the item to be ordered
	 * 
	 * @return the item to be ordered
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Adds the given amount of units to be ordered
	 * 
	 * @param i - The amount of extra units to be ordered
	 */
	public void addQuantity(int i) {
		quantity += i;
	}

	/**
	 * Removes the given amount of units from the order
	 * 
	 * @param i - The amount of units to remove from the order
	 */
	public void removeQuantity(int i) {
		quantity -= i;
	}

	@Override
	/**
	 * Builds a textual representation of the order
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(item.toString() + ": " + quantity + " units\r\n");
		return sb.toString();
	}
}
