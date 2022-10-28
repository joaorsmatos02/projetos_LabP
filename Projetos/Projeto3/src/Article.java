
/**
 * The article class contains the methods necessary to create articles, obtain
 * information about them and remove units stock
 * 
 * @author João Matos nº 56292
 *
 */

public class Article {

	private int quantityInStock;
	private double weight;
	private double volume;
	private String description;
	private String code;
	
	/**
	 * The Article constructor builds a new article using the given information
	 * 
	 * @param code - the new article's code
	 * @param description - the new article's description
	 * @param volume - the new article's volume
	 * @param weight - the new article's weight
	 * @param quantityInStock - the new article's stock
	 */

	public Article(String code, String description, double volume, double weight, int quantityInStock) {
		this.description = description;
		this.volume = volume;
		this.weight = weight;
		this.quantityInStock = quantityInStock;
		this.code = code;
	}
	
	/**
	 * Gives the article's volume
	 * 
	 * @return the article's volume
	 */

	public double getVolume() {
		return volume;
	}
	
	/**
	 * Gives the article's weight
	 * 
	 * @return the article's weight
	 */

	public double getWeight() {
		return weight;
	}
	
	/**
	 * Gives the article's stock
	 * 
	 * @return the article's stock
	 */

	public int getStock() {
		return quantityInStock;
	}
	
	/**
	 * Gives the article's description
	 * 
	 * @return the article's description
	 */

	public String getDescription() {
		return description;
	}
	
	/**
	 * Gives the article's code
	 * 
	 * @return the article's code
	 */

	public String getCode() {
		return code;
	}
	
	/**
	 * Removes i items of the selected article from stock
	 */
	public void removeStock(int i) {
		this.quantityInStock -= i;
	}
}
