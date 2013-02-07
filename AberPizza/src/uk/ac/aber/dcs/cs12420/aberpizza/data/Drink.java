package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

/**
 * Holds informations about drink.
 * @author Stanislaw Klimaszewski
 */
public class Drink implements Item {
	private String name, description;
	private BigDecimal price;
	
	/**
	 * Initialises instance variables with empty values.
	 */
	public Drink(){
		this.name = null;
		this.description = null;
		this.price = null;
	}
	
	/**
	 * Initialises instance variables.
	 * @param name drink name
	 * @param description drink description
	 * @param price drink price
	 */
	public Drink(String name, String description, BigDecimal price){
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	/**
	 * Returns drink name.
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * Returns drink price.
	 */
	@Override
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * Returns drink description.
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * Sets new drink name.
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets new drink description.
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Sets new drink price.
	 */
	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
