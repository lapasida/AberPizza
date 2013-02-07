package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.beans.PersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Holds informations about all orders.
 * @author Stanislaw Klimaszewski
 */
public class Till {
	private ArrayList<Order> orders;
	
	/**
	 * Initialises instance variables.
	 */
	public Till(){
		this.orders = new ArrayList<Order>();
	}
	
	/**
	 * @return array of Strings with dates of orders
	 */
	public String[] getOrderDates(){
		ArrayList<String> dates = new ArrayList<String>();
		for(Order order: orders){
			Date date = order.getDate();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String dateString = formatter.format(date);
			if(!dates.contains(dateString)){
				dates.add(dateString);
			}
		}
		return dates.toArray(new String[dates.size()]);
	}
	
	/**
	 * @param date order date
	 * @return array of Strings with informations about orders for specified date.
	 */
	public String[] getOrders(String date){
		ArrayList<String> toReturn = new ArrayList<String>();
		for(Order order: orders){
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String dateString = formatter.format(order.getDate());
			if(date.equals(dateString)){
				SimpleDateFormat time = new SimpleDateFormat("HH:mm");
				toReturn.add(time.format(order.getDate())+" - "+order.getCustomerName()+"");
			}
		}
		return toReturn.toArray(new String[toReturn.size()]);
	}
	
	/**
	 * @param date order date
	 * @param index position of order from that day
	 * @return ordered items
	 */
	public String[] getOrderItems(String date, int index){
		ArrayList<Order> formatedOrders = new ArrayList<Order>();
		for(Order order: orders){
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String dateString = formatter.format(order.getDate());
			if(date.equals(dateString)){
				formatedOrders.add(order);
			}
		}
		Order order = formatedOrders.get(index);
		ArrayList<OrderItem> orderItems = order.getOrderItems();
		ArrayList<String> toReturn = new ArrayList<String>();
		for(OrderItem orderItem: orderItems){
			Item item = orderItem.getItem();
			toReturn.add(orderItem.getQuantity()+"x "+item.getName());
		}
		return toReturn.toArray(new String[toReturn.size()]);
	}
	
	/**
	 * Adds new order to an ArrayList of orders in this till.
	 * @param order
	 */
	public void addOrder(Order order){
		orders.add(order);
	}
	
	/**
	 * @return ArrayList of Orders for this till
	 */
	public ArrayList<Order> getOrders() {
		return orders;
	}

	/**
	 * Sets new list of Orders for this till.
	 * @param orders ArrayList of new Orders
	 */
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	/**
	 * Loads sales record from file.
	 * @return object of Till class with all loaded informations
	 */
	public static Till load(){
		Till till = new Till();
		XMLDecoder decoder;
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("till.xml")));
			till = (Till)decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found!");
		}
		return till;
	}
	
	/**
	 * Saves current sales record to the file.
	 */
	public void save(){
		XMLEncoder encoder;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("till.xml")));
			PersistenceDelegate pd = encoder.getPersistenceDelegate(Integer.class);   
			encoder.setPersistenceDelegate(BigDecimal.class, pd); 
			encoder.writeObject(this);
			encoder.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found!");
		}
	}
}
