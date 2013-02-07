package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

/**
 * Interface for each ordered item.
 * @author Stanislaw Klimaszewski
 */
public interface Item {
	
	/**
	 * @return item price
	 */
	public BigDecimal getPrice();
	
	/**
	 * Sets new item price.
	 * @param price item price.
	 */
	public void setPrice(BigDecimal price);
	
	/**
	 * @return item description
	 */
	public String getDescription();
	
	/**
	 * Sets new item description.
	 * @param description item description
	 */
	public void setDescription(String description);
	
	/**
	 * @return item name
	 */
	public String getName();
	
	/**
	 * Sets new item name.
	 * @param name item name
	 */
	public void setName(String name);
	
	/**
	 * @return returns information about single item
	 */
	public String toString();
	
}
