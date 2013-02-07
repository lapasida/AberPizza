package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

/**
 * Holds informations about pizza.
 * @author Stanislaw Klimaszewski
 */
public class Pizza implements Item{
	private BigDecimal[] prices;
	private String name, description;
	private PizzaSize size;
	
	/**
	 * Initialises instance variables with empty values.
	 */
	public Pizza(){
		this.prices = null;
		this.name = null;
		this.description = null;
		this.size = PizzaSize.UNKNOWN;
	}
	
	/**
	 * Copy object of Pizza class.
	 * @param pizza object of Pizza class
	 */
	public Pizza(Pizza pizza){
		this.prices = pizza.prices;
		this.name = pizza.name;
		this.description = pizza.description;
		this.size = pizza.size;
	}
	
	/**
	 * Initialises instance variables.
	 * @param prices pizza prices for each size
	 * @param name	pizza name
	 * @param description pizza description
	 * @param size pizza size
	 */
	public Pizza(BigDecimal[] prices, String name, String description, PizzaSize size){
		this.prices = prices;
		this.name = name;
		this.description = description;
		this.size = size;
	}
	
	/**
	 * @return pizza size
	 */
	public PizzaSize getSize() {
		return size;
	}

	/**
	 * Sets new pizza size.
	 * @param size pizza size
	 */
	public void setSize(PizzaSize size) {
		this.size = size;
	}

	/**
	 * @return prices for this pizza for each size
	 */
	public BigDecimal[] getPrices() {
		return prices;
	}

	/**
	 * Sets new pizza prices for each size.
	 * @param prices pizza prices for each size
	 */
	public void setPrices(BigDecimal[] prices) {
		this.prices = prices;
	}

	/**
	 * Returns pizza name.
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Sets new pizza name.
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns pizza description.
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * Sets new pizza description.
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns pizza price, null when pizza size is unknown.
	 */
	@Override
	public BigDecimal getPrice() {
		switch(size){
			case SMALL:
				return prices[0];
			case MEDIUM:
				return prices[1];
			case LARGE:
				return prices[2];
			default:
				return null;
		}
	}

	/**
	 * Empty method implemented from Item intarface, which isn't in use.
	 */
	@Override
	public void setPrice(BigDecimal price) {
		
	}
}
