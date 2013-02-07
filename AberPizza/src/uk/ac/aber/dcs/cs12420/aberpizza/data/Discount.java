package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Holds informations about all discounts.
 * @author Stanislaw Klimaszewski
 */
public class Discount {
	private ArrayList<DiscountItem> items;
	private int procentage;
	
	/**
	 * Initialise instance variables.
	 */
	public Discount(){
		this.items = new ArrayList<DiscountItem>();
		this.procentage = 0;
	}
	
	/**
	 * Adds new discount item to the array list.
	 * @param di discount item
	 */
	public void addNewDiscountItem(DiscountItem di){
		items.add(di);
	}
	
	/**
	 * @return array of string with discount items in one discount
	 */
	public String[] getDiscountItemsList(){
		String[] toReturn = new String[items.size()];
		int i = 0;
		for(DiscountItem item: items){
			toReturn[i] = item.toString();
			i++;
		}
		return toReturn;
	}
	
	/**
	 * Removes discount item from array list.
	 * @param index position of discount item in the array list
	 */
	public void deleteDiscount(int index){
		items.remove(index);
	}
	
	/**
	 * @param orderItems list of ordered items
	 * @return discount value, zero when there's no discount
	 */
	public BigDecimal getDiscount(ArrayList<OrderItem> orderItems){
		BigDecimal price = new BigDecimal("0");
		for(DiscountItem item: items){
			if(item.itemDiscount(orderItems).equals(new BigDecimal("0"))){
				return new BigDecimal("0");
			}
			price = price.add(item.itemDiscount(orderItems));
		}
		BigDecimal proc = new BigDecimal(this.procentage);
		proc = proc.movePointLeft(2);
		price = price.multiply(proc);
		price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
		return price;
	}
	
	/**
	 * Return string with informations about discount
	 */
	public String toString(){
		String toReturn = "";
		for(DiscountItem item: items){
			toReturn += item.toString()+" + ";
		}
		toReturn = toReturn.substring(0, toReturn.length()-3);
		toReturn += " ("+this.procentage+"%)";
		return toReturn;
	}
	
	/**
	 * @return list of discount items
	 */
	public ArrayList<DiscountItem> getItems() {
		return items;
	}

	/**
	 * Sets new list of discount items.
	 * @param items list of discount items
	 */
	public void setItems(ArrayList<DiscountItem> items) {
		this.items = items;
	}

	/**
	 * @return procentage value of this discount
	 */
	public int getDiscount() {
		return procentage;
	}

	/**
	 * Sets new procentage value to this discount.
	 * @param discount procentage value
	 */
	public void setDiscount(int discount) {
		this.procentage = discount;
	}
}
