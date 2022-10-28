import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * The PurchasePlanner class helps to decide the best supermarket to buy the
 * items from
 * 
 * @author João Matos nº 56292
 *
 */
public class PurchasePlanner {

	private ArrayList<ItemOrder> orderList = new ArrayList<>();
	private int totalItems;

	/**
	 * Adds the ItemOrders in the given list to the total current order
	 * 
	 * @param order - The list of ItemOrder to add to the total
	 */
	public void addToOrder(List<ItemOrder> order) {
		for (ItemOrder io : order) {
			if (order.contains(io)) {
				int index = orderList.indexOf(io);
				orderList.get(index).addQuantity(io.getQuantity());
			} else
				orderList.add(io);
			totalItems += io.getQuantity();
		}
	}

	/**
	 * Adds the given ItemOrder to the current total order
	 * 
	 * @param itemOrder - The ItemOrder to be added
	 */
	public void addToOrder(ItemOrder itemOrder) {
		if (orderList.contains(itemOrder)) {
			int index = orderList.indexOf(itemOrder);
			orderList.get(index).addQuantity(itemOrder.getQuantity());
		} else
			orderList.add(itemOrder);
		totalItems += itemOrder.getQuantity();
	}

	/**
	 * Adds the given item in the provided quantity to the current order
	 * 
	 * @param item - The item to be added
	 * @param qtty - The quantity of the item
	 */
	public void addToOrder(Item item, int qtty) {
		boolean found = false;
		for (ItemOrder io : orderList) {
			if (io.getItem().equals(item)) {
				io.addQuantity(qtty);
				found = true;
			}
		}
		if (!found)
			orderList.add(new ItemOrder(item, qtty));
		totalItems += qtty;
	}

	/**
	 * Removes the ItemOrders in the given list from the total current order
	 * 
	 * @param order - The list of ItemOrder to be removed from the total
	 */
	public void removeFromOrder(List<ItemOrder> order) {
		for (ItemOrder io : order) {
			int quantity = io.getQuantity();
			int index = orderList.indexOf(io);
			if (quantity >= orderList.get(index).getQuantity()) {
				orderList.remove(index);
				quantity = orderList.get(index).getQuantity();
			} else
				orderList.get(index).removeQuantity(quantity);
			totalItems -= quantity;
		}
	}

	/**
	 * Removes the given ItemOrder from the current total order
	 * 
	 * @param itemOrder - The ItemOrder to be removed
	 */
	public void removeFromOrder(ItemOrder itemOrder) {
		int quantity = itemOrder.getQuantity();
		int index = orderList.indexOf(itemOrder);
		if (quantity >= orderList.get(index).getQuantity()) {
			quantity = orderList.get(index).getQuantity();
			orderList.remove(index);
		} else
			orderList.get(index).removeQuantity(quantity);
		totalItems -= quantity;
	}

	/**
	 * Removes the given item in the provided quantity from the current order
	 * 
	 * @param item - The item to be removed
	 * @param qtty - The quantity of the item
	 */
	public void removeFromOrder(Item item, int qtty) {
		for (ItemOrder io : orderList) {
			if (io.getItem().equals(item)) {
				int quantity = io.getQuantity();
				if (qtty >= quantity) {
					qtty = quantity;
					orderList.remove(io);
				} else
					io.removeQuantity(qtty);
			}
		}
		totalItems -= qtty;
	}

	/**
	 * Calculates the price of the current order in the given shop
	 * 
	 * @param market - The shop to check the price
	 * @return the price of the order in market
	 * @requires market != null
	 */
	public int priceInMarket(Shop market) {
		int price = 0;
		for (ItemOrder io : orderList) {
			price += market.priceOf(io.getItem()) * io.getQuantity();
		}
		return price;
	}

	/**
	 * Calculates which of the shops in the provided list is the cheapest to buy the
	 * current order. In case of a tie it will return the first one on the list
	 * 
	 * @param markets - The list of shops to be considered
	 * @return The shop in which the order would be cheapest
	 * @requires markets != null && markets.size() > 0
	 * @requires all shops in markets to be != null
	 */
	public Shop cheapestMarket(List<Shop> markets) {
		Shop bestShop = null;
		int currentBest = Integer.MAX_VALUE;
		int current = 0;
		for (Shop s : markets) {
			for (ItemOrder io : orderList) {
				current += s.priceOf(io.getItem());
			}
			if (current < currentBest) {
				bestShop = s;
				currentBest = current;
			}
		}
		return bestShop;
	}

	/**
	 * Determines, if it exists, which item from the current order is the cheapest
	 * in most shops (over half of them).
	 * 
	 * @param markets - The list of shops to be checked
	 * @return The cheapest item in most shops or null if there is none
	 * @requires markets != null && markets.size() > 0
	 * @requires all shops in markets to be != null
	 */
	public Item mostlyCheaper(List<Shop> markets) {
		HashMap<Item, Integer> itemCount = new HashMap<>();
		Item currentBest = null;
		int currentBestCount = 0;
		for (Shop s : markets) {
			Item cheapest = s.cheapestItem();
			int count = 0;
			boolean contains = false;
			if (contains = itemCount.containsKey(cheapest))
				count = itemCount.get(cheapest) + 1;
			else
				count = 1;
			if (cheapest != null) {
				if (contains)
					itemCount.remove(cheapest);
				itemCount.put(cheapest, count);
				if (count > currentBestCount) {
					currentBestCount = count;
					currentBest = cheapest;
				}
			}
		}
		if (currentBestCount <= markets.size() / 2)
			currentBest = null;
		return currentBest;
	}

	@Override
	/**
	 * Builds a textual representation of the order
	 */
	public String toString() {
		System.out.println("The current plan is:");
		StringBuilder sb = new StringBuilder();
		for (ItemOrder i : orderList) {
			sb.append(i.toString());
		}
		sb.append("Total: " + totalItems + " items");
		return sb.toString();
	}
}
