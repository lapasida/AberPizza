package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Holds informations about one discount item.
 * @author Stanislaw Klimaszewski
 */
public class DiscountItem {
	private DiscountType type;
	private int quantity;
	
	/**
	 * Initialises empty instance variables.
	 */
	public DiscountItem(){
		this.type = null;
		this.quantity = 0;
	}
	
	/**
	 * Initialises instance variables.
	 * @param type type of discount
	 * @param quantity quantity of discount item
	 */
	public DiscountItem(DiscountType type, int quantity){
		this.type = type;
		this.quantity = quantity;
	}
	
	/**
	 * @param orderItems list of ordered items
	 * @return amout of discount
	 */
	public BigDecimal itemDiscount(ArrayList<OrderItem> orderItems){
		ArrayList<BigDecimal> prices = new ArrayList<BigDecimal>();
		int found = 0;
		for(OrderItem orderItem: orderItems){
			Item item = orderItem.getItem();
			switch(type){
			case PIZZA:
				if(item instanceof Pizza){
					found = found+orderItem.getQuantity();
					for(int i=1; i<=orderItem.getQuantity(); i++){
						prices.add(item.getPrice());
					}
				}
				break;
			case SMALL_PIZZA:
				if(item instanceof Pizza){
					Pizza pizza = (Pizza) item;
					if(pizza.getSize().equals(PizzaSize.SMALL)){
						found = found+orderItem.getQuantity();
						for(int i=1; i<=orderItem.getQuantity(); i++){
							prices.add(item.getPrice());
						}
					}
				}
				break;
			case MEDIUM_PIZZA:
				if(item instanceof Pizza){
					Pizza pizza = (Pizza) item;
					if(pizza.getSize().equals(PizzaSize.MEDIUM)){
						found = found+orderItem.getQuantity();
						for(int i=1; i<=orderItem.getQuantity(); i++){
							prices.add(item.getPrice());
						}
					}
				}
				break;
			case LARGE_PIZZA:
				if(item instanceof Pizza){
					Pizza pizza = (Pizza) item;
					if(pizza.getSize().equals(PizzaSize.LARGE)){
						found = found+orderItem.getQuantity();
						for(int i=1; i<=orderItem.getQuantity(); i++){
							prices.add(item.getPrice());
						}
					}
				}
				break;
			case SIDE:
				if(item instanceof Side){
					found = found+orderItem.getQuantity();
					for(int i=1; i<=orderItem.getQuantity(); i++){
						prices.add(item.getPrice());
					}
				}
				break;
			case DRINK:
				if(item instanceof Drink){
					found = found+orderItem.getQuantity();
					for(int i=1; i<=orderItem.getQuantity(); i++){
						prices.add(item.getPrice());
					}
				}
				break;
			}
		}
		if(found >= quantity){
			Collections.sort(prices);
			Collections.reverse(prices);
			BigDecimal toReturn = new BigDecimal("0");
			int i = 1;
			for(BigDecimal price: prices){
				toReturn = toReturn.add(price);
				if(i == quantity){
					return toReturn;
				}
				i++;
			}
		}
		return new BigDecimal("0");
	}
	
	/**
	 * Return string with information about single discount item.
	 */
	public String toString(){
		return quantity+"x "+type;
	}

	/**
	 * @return type of discount
	 */
	public DiscountType getType() {
		return type;
	}

	/**
	 * Sets new type of discount.
	 * @param type type of discount
	 */
	public void setType(DiscountType type) {
		this.type = type;
	}

	/**
	 * @return quantity of discount item
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets new quantity of discount item.
	 * @param quantity new quantity of discount item
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
