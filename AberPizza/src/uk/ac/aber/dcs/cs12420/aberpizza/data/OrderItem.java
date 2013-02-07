package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

/**
 * Holds informations about single ordered item.
 * @author Stanislaw Klimaszewski
 */
public class OrderItem {
	private int quantity;
	private Item item;
	
	/**
	 * Initialises instance variables with empty values.
	 */
	public OrderItem(){
		this.item = null;
		this.quantity = 0;
	}
	
	/**
	 * Initialises instance variables.
	 * @param item object of class which implements item interface
	 * @param quantity item quantity
	 */
	public OrderItem(Item item, int quantity){
		this.item = item;
		this.quantity = quantity;
	}
	
	/**
	 * @return object of class which implements item interface
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Sets new item.
	 * @param item object of class which implements item interface
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * Returns string with informations about single ordered item.
	 */
	public String toString(){
		return item.getName()+" (£"+item.getPrice()+") x "+quantity;
	}
	
	/**
	 * @return price for one item multiplied by quantity
	 */
	public BigDecimal getOrderItemTotal(){
		BigDecimal price = item.getPrice();
		return price.multiply(new BigDecimal(""+quantity));
	}
	
	/**
	 * Sets new item quantity.
	 * @param quantity item quantity
	 */
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	/**
	 * @return item quantity
	 */
	public int getQuantity(){
		return quantity;
	}
}
