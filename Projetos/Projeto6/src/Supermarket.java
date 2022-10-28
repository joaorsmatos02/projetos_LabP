import java.util.HashMap;

/**
 * 
 * The Supermarket class implements the Shop interface to create an item shop
 * 
 * @author João Matos nº 56292
 *
 */
public class Supermarket implements Shop {

	private String name;
	private int basePrice;
	private HashMap<Item, Integer> items = new HashMap<>();

	/**
	 * The constructor builds a new Supermarket with the given name and base item
	 * price
	 * 
	 * @param name          - The Supermarket's name
	 * @param standardPrice - The Item price by omission
	 */
	public Supermarket(String name, int basePrice) {
		this.name = name;
		this.basePrice = basePrice;
	}

	/**
	 * Gets the name of the supermarket
	 * 
	 * @return the name of the supermarket
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the base item price
	 * 
	 * @return the base item price
	 */
	public double getBasePrice() {
		return basePrice;
	}

	/**
	 * Gets the full supermarket item catalogue
	 * 
	 * @return the item catalogue
	 */
	public HashMap<Item, Integer> getCatalogue() {
		return items;
	}

	/**
	 * Sets the price of the choosen item to the given value
	 * 
	 * @param item  - The item to be changed
	 * @param price - The new price
	 * @requires item != null && price > 0
	 */
	public void setPriceOf(Item item, int price) {
		items.put(item, price);
	}

	@Override
	public int priceOf(Item item) {
		return items.getOrDefault(item, basePrice);
	}

	@Override
	public Item cheapestItem() {
		int price = Integer.MAX_VALUE;
		Item result = null;
		for (Item i : items.keySet()) {
			if (items.get(i) < price) {
				result = i;
				price = items.get(i);
			}
		}
		return result;
	}

	@Override
	/**
	 * Builds a textual representation of the supermarket
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name + " - base price: " + basePrice);
		return sb.toString();
	}
}
