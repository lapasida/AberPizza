package uk.ac.aber.dcs.cs12420.aberpizza.data;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Holds informations about one order.
 * @author Stanislaw Klimaszewski
 */
public class Order {
	private Date date;
	private String customerName;
	private ArrayList<OrderItem> orderItems;
	private BigDecimal discount;
	
	/**
	 * Initialise instance variables.
	 */
	public Order(){
		orderItems = new ArrayList<OrderItem>();
		date = new Date();
		discount = new BigDecimal("0");
	}
	
	/**
	 * Adds new item to the order.
	 * @param item
	 * @param quantity
	 */
	public void addItem(Item item, int quantity){
		OrderItem orderItem = new OrderItem(item, quantity);
		orderItems.add(orderItem);
	}
	
	/**
	 * @return array of strings with all names of items
	 */
	public String[] getOrderList(){
		String[] toReturn = new String[orderItems.size()];
		int i = 0;
		for(OrderItem item: orderItems){
			toReturn[i] = item.toString();
			i++;
		}
		return toReturn;
	}
	
	/**
	 * @return subtotal for this order
	 */
	public BigDecimal getSubTotal(){
		BigDecimal toReturn = new BigDecimal("0");
		for(OrderItem item: orderItems){
			BigDecimal itemTotal = item.getOrderItemTotal();
			toReturn = toReturn.add(itemTotal);
		}
		toReturn = toReturn.subtract(this.discount);
		return toReturn;
	}
	
	/**
	 * @param index where item is in order list
	 * @param quantity change current quantity to this value
	 */
	public void updateItemQuantity(int index, int quantity){
		OrderItem selected = orderItems.get(index);
		selected.setQuantity(quantity);
		orderItems.set(index, selected);
	}
	
	/**
	 * Removes item from order list.
	 * @param index which item will be deleted
	 */
	public void deleteItem(int index){
		orderItems.remove(index);
	}
	
	/**
	 * @return formatted in HTML text about ordered item by a customer
	 */
	public String getReceipt(){
		String toReturn = "";
		for(OrderItem orderItem: orderItems){
			Item item = orderItem.getItem();
			toReturn += "<span>"+item.getName()+".x"+orderItem.getQuantity();
			for(int i=0; i<(34-item.getName().length()-item.getPrice().toString().length()); i++){
				toReturn += ".";
			}
			toReturn += "£"+item.getPrice()+"</span><br />";
		}
		if(!this.discount.equals(new BigDecimal("0"))){
			toReturn += "<span>DISCOUNT";
			for(int i=0; i<(28-this.getDiscount().toString().length()); i++){
				toReturn += ".";
			}
			toReturn += "-£"+this.getDiscount()+"</span><br />";
		}
		toReturn += "<div style=\"text-align:center;\">--------------------------------------<br /></div>";
		toReturn += "<span>SUBTOTAL";
		for(int i=0; i<(29-this.getSubTotal().toString().length()); i++){
			toReturn += ".";
		}
		toReturn += "£"+this.getSubTotal()+"</span><br />";
		toReturn += "<div style=\"text-align:center;\">--------------------------------------<br />";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = formatter.format(this.date);
		formatter = new SimpleDateFormat("HH:mm");
		dateString += " Time: "+formatter.format(this.date);
		toReturn += "<span>Date: "+dateString+"</span><br /><span>CUSTOMER: "+this.customerName+"</span><br />--------------------------------------</div>";
		return toReturn;
	}
	
	/**
	 * @return order date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets new order date.
	 * @param date new order date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return list of ordered items.
	 */
	public ArrayList<OrderItem> getOrderItems() {
		return orderItems;
	}

	/**
	 * Sets new list of ordered items.
	 * @param orderItems list of ordered items
	 */
	public void setOrderItems(ArrayList<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	/**
	 * @return discount value
	 */
	public BigDecimal getDiscount(){
		return discount;
	}
	
	/**
	 * Sets new discount value for this order.
	 * @param discount new discount value
	 */
	public void setDiscount(BigDecimal discount){
		this.discount = discount;
	}
	
	/**
	 * @return customer name
	 */
	public String getCustomerName(){
		return customerName;
	}

	/**
	 * Sets new customer name to this order.
	 * @param name new customer name
	 */
	public void setCustomerName(String name){
		this.customerName = name;
	}
}
