package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

/**
 * Holds informations about single side.
 * @author Stanislaw Klimaszewski
 */
public class Side implements Item {
	private String name, description;
	private BigDecimal price;
	
	/**
	 * Initialises instance variables with empty values.
	 */
	public Side(){
		this.name = null;
		this.description = null;
		this.price = null;
	}
	
	/**
	 * Initialises instance variables.
	 * @param name side name
	 * @param description side description
	 * @param price side price
	 */
	public Side(String name, String description, BigDecimal price){
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	/**
	 * Returns side name.
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * Returns side price.
	 */
	@Override
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * Returns side description.
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * Sets new side name.
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets new side description.
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Sets new side price.
	 */
	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
