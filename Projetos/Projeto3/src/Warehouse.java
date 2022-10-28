
/**
 * The Warehouse class simulates a real warehouse, acting as an article storage
 * 
 * @author João Matos nº 56292
 *
 */

public class Warehouse {

//	private int nLines;
	private String boxPrefix;
	private String boxPrefixInvert;
	private Article[] articles;
	private int numberOfArticles;

	/**
	 * The Warehouse constructor builds a new warehouse
	 * 
	 * @param nLines - the number of different articles to be stored
	 * @param code   - the code that simbolizes a new box of articles
	 */

	public Warehouse(int nLines, String code) {
		// this.nLines = nLines;
		this.boxPrefix = code;
		articles = new Article[nLines];
		StringBuilder sb = new StringBuilder();
		for (int i = boxPrefix.length() - 1; i >= 0; i--) {
			sb.append(boxPrefix.charAt(i));
		}
		boxPrefixInvert = sb.toString();
	}

	/**
	 * Checks if a given article is already registred in the warehouse catalog
	 * 
	 * @param code - the code of the article to be checked
	 * @return true if article is in the catalog, regardless of stock, false if item
	 *         is not in catalog
	 */

	public boolean inCatalog(String code) {
		boolean result = false;
		for (int i = 0; i < numberOfArticles; i++) {
			if (articles[i].getCode().contentEquals(code)) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Adds a new article to the catalog with the given information
	 * 
	 * @param code            - the new item's code
	 * @param description     - the new item's description
	 * @param volume          - the new item's volume
	 * @param weight          - the new item's weight
	 * @param quantityInStock - the new item's stock
	 * @requires !inCatalog(code)
	 */

	public void addArticle(String code, String description, double volume, double weight, int quantityInStock) {
		articles[numberOfArticles] = new Article(code, description, volume, weight, quantityInStock);
		numberOfArticles++;
	}

	/**
	 * Returns the volume and weight of the selected article
	 * 
	 * @param code - the code of the article to be checked
	 * @return a double array with two positions, where [0] = volume and [1] =
	 *         weight
	 * @requires inCatalog(code)
	 */

	public double[] articleVolumeWeight(String code) {
		double[] result = new double[2];
		int i = 0;
		while (!articles[i].getCode().contentEquals(code))
			i++;
		result[0] = articles[i].getVolume();
		result[1] = articles[i].getWeight();
		return result;
	}

	/**
	 * Checks if the given description is a valid order
	 * 
	 * @param description - the order description to be checked
	 * @return VALID_ORDER if the description is a valid order
	 * @return NO_BOX_START if the order does not start with a box
	 * @return MISMATCHED_BOX_COVER if the box covers have been mismatched
	 * @return ORDER_VOLUME_EXCEEDS_BOX if the order is too big for the box
	 * @return NO_AVAILABLE_STOCK if an item does not have enough stock
	 * @return UNCLOSED_BOX if a box has not been closed
	 * @requires inCatalog(code) for all codes in description
	 */

	public OrderStatus validOrder(String description) {
		ArrayStack<String> as = new ArrayStack<String>();
		String[] items = description.split("\\s*[ ,.;]\\s*");
		int boxCount = 0;
		int index = 0;
		for (int i = 0; i < items.length; i++) { // quantas caixas
			if (items[i].substring(0, boxPrefix.length()).contentEquals(boxPrefix)) {
				boxCount++;
			}
		}
		int[] boxLocations = new int[boxCount];
		for (int i = 0; i < items.length; i++) { // localização das caixas no array
			if (items[i].substring(0, boxPrefix.length()).contentEquals(boxPrefix)) {
				boxLocations[index] = i;
				index++;
			}
		}
		double[] boxInfoVolume = new double[boxCount]; // array para guardar os volumes dentro de cada caixa
		int currentBox = 1;
		boolean isBox = false;
		OrderStatus status = OrderStatus.VALID_ORDER;
		if (!items[0].substring(0, boxPrefix.length()).contentEquals(boxPrefix)) {
			// se não começar com uma caixa
			status = OrderStatus.NO_BOX_START;
		}
		if (status != OrderStatus.NO_BOX_START) {
			for (int i = 0; i < items.length; i++) {
				if (items[i].substring(0, boxPrefix.length()).contentEquals(boxPrefix)) { // se for uma caixa
					as.push(items[i]);
					isBox = true;
				}
				if (items[i].substring(items[i].length() - boxPrefixInvert.length()).contentEquals(boxPrefixInvert)) {
					// se for uma tampa
					if (!items[i].substring(0, items[i].length() - boxPrefixInvert.length())
							.contentEquals((String) as.peek().substring(boxPrefix.length()))) {
						status = OrderStatus.MISMATCHED_BOX_COVER;
					} else {
						as.pop();
						currentBox--; // passar para a caixa de fora
					}
				} else if (i != 0) { // se não for a primeira caixa caixa nem tampa
					index = 0;
					while (!articles[index].getCode().contentEquals(items[i]))
						index++;
					boxInfoVolume[currentBox - 1] += articles[index].getVolume();
					int index2 = 0;
					while (!articles[index2].getCode().contentEquals(items[boxLocations[currentBox - 1]])) {
						// localizar codigo da caixa atual no array de artigos
						index2++;
					}
					if (boxInfoVolume[currentBox - 1] > articles[index2].getVolume()) {
						status = OrderStatus.ORDER_VOLUME_EXCEEDS_BOX;
					}
					if (isBox) {
						currentBox++;
						// pointer da caixa atual
					}
				}
				if (!items[i].substring(items[i].length() - boxPrefixInvert.length()).contentEquals(boxPrefixInvert)) {
					// se não for tampa
					int itemsOrdered = 0;
					for (int k = 0; k < items.length; k++) { // quantos items deste tipo foram pedidos
						if (items[k].contentEquals(items[i]))
							itemsOrdered++;
					}
					int index3 = 0;
					// localizar codigo no array de artigos
					while (!articles[index3].getCode().contentEquals(items[i])) {
						index3++;
					}
					if (articles[index3].getStock() < itemsOrdered) {
						status = OrderStatus.NO_AVAILABLE_STOCK;
					}
				}
				isBox = false;
				if (status != OrderStatus.VALID_ORDER) // terminar caso não seja valido
					return status;
			}
		}
		if (!as.isEmpty()) { // se não estiver vazio a primeira caixa está aberta
			status = OrderStatus.UNCLOSED_BOX;
		}
		return status;
	}

	/**
	 * Deletes a unit of stock of every item in the order (or more if more than one
	 * unit has been ordered) and constructs a string containing the instructions to
	 * build the order
	 * 
	 * @param description
	 * @return a String containing the instructions to build the order
	 * @requires validOrder(description)
	 */

	public String putOrder(String description) {
		StringBuilder sb = new StringBuilder();
		ArrayStack<String> as = new ArrayStack<String>();
		String[] items = description.split("\\s*[ ,.;]\\s*");
		int index = 0;
		while (!articles[index].getCode().contentEquals(items[0])) {
			index++;
		}
		int firstBoxIndex = index;
		sb.append("Go fetch " + articles[index].getDescription() + " and put it on the table\r\n");
		articles[index].removeStock(1);
		as.push(items[0]);
		for (int i = 1; i < items.length - 1; i++) {
			int cbIndex = 0;
			String currentBox = as.peek();
			while (!articles[cbIndex].getCode().contentEquals(currentBox)) {
				cbIndex++;
			}
			index = 0;
			if (!items[i].substring(items[i].length() - boxPrefix.length()).contentEquals(boxPrefixInvert)) {
				// se não for uma tampa
				while (!articles[index].getCode().contentEquals(items[i])) {
					index++;
				}
			}
			if (items[i].substring(0, boxPrefix.length()).contentEquals(boxPrefix)) {
				// se for uma caixa
				sb.append("Go fetch " + articles[index].getDescription() + " and put it in "
						+ articles[cbIndex].getDescription() + "\r\n");
				articles[index].removeStock(1);
				as.push(items[i]);
			} else if (items[i].substring(items[i].length() - boxPrefix.length()).contentEquals(boxPrefixInvert)) {
				// se for uma tampa
				sb.append("Go fetch the cover of " + articles[cbIndex].getDescription() + " and close it\r\n");
				as.pop();
			} else {
				// se for um artigo normal
				sb.append("Go fetch " + articles[index].getDescription() + " and put it in "
						+ articles[cbIndex].getDescription() + "\r\n");
				articles[index].removeStock(1);
			}
		}
		sb.append("Go fetch the cover of " + articles[firstBoxIndex].getDescription() + " and close it\r\n");
		// fechar a primeira caixa
		return sb.toString();
	}

	/**
	 * Calculates the order's total weight and volume
	 * 
	 * @param description - the description of the order
	 * @return a double array with two positions, where [0] = volume and [1] =
	 *         weight
	 * @requires validOrder(description)
	 */

	public double[] orderVolumeWeight(String description) {
		double[] result = new double[2];
		String[] order = description.split("\\s*[ ,.;]\\s*");
		int i = 0;
		while (!articles[i].getCode().contentEquals(order[0])) {
			i++;
		}
		result[0] = articles[i].getVolume();
		i = 0;
		for (int j = 0; j < order.length - 1; j++) {
			if (!order[j].substring(order[j].length() - boxPrefixInvert.length()).contentEquals(boxPrefixInvert)) {
				// se não for uma tampa
				while (!articles[i].getCode().contentEquals(order[j])) {
					i++;
				}
				result[1] += articles[i].getWeight();
				i = 0;
			}
		}
		return result;
	}
}