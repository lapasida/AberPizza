package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.beans.PersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Holds all settings information, which are editable in PreferencesWindow.
 * @author Stanislaw Klimaszewski
 */
public class Settings {
	private ArrayList<Item> availableItems;
	private ArrayList<Discount> discounts;
	private String restaurantName, restaurantTel;
	
	/**
	 * Initialises instance variables.
	 */
	public Settings(){
		this.availableItems = new ArrayList<Item>();
		this.discounts = new ArrayList<Discount>();
		this.restaurantName = "";
		this.restaurantTel = "";
	}
	
	/**
	 * Load settings from "settings.xml" file.
	 * @return object of Setting class with all loaded settings.
	 */
	public static Settings load(){
		Settings settings = new Settings();
		XMLDecoder decoder;
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("settings.xml")));
			settings = (Settings)decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) {
			System.err.println("Setting file not found!");
		}
		return settings;
	}
	
	/**
	 * Saves settings to "settings.xml" file.
	 */
	public void save(){
		File saveFile = new File("settings.xml");
		XMLEncoder encoder;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(saveFile)));
			PersistenceDelegate pd = encoder.getPersistenceDelegate(Integer.class);   
			encoder.setPersistenceDelegate(BigDecimal.class, pd); 
			encoder.writeObject(this);
			encoder.close();
		} catch (FileNotFoundException e) {
			System.err.println("Setting file not found!");
		}
	}
	
	/**
	 * @param foodType type of food, we're looking for
	 * @return array of Strings with names of available items of this type
	 */
	public String[] getAvailableItems(FoodType foodType){
		ArrayList<String> toReturn = new ArrayList<String>();
		for(Item item: availableItems){
			switch(foodType){
			case PIZZA:
				if(item instanceof Pizza){
					toReturn.add(item.getName());
				}
				break;
			case DRINK:
				if(item instanceof Drink){
					toReturn.add(item.getName());
				}
				break;
			case SIDE:
				if(item instanceof Side){
					toReturn.add(item.getName());
				}
				break;
			}
		}
		return toReturn.toArray(new String[toReturn.size()]);
	}
	
	/**
	 * @param name name of item
	 * @param foodType type of item
	 * @return object implemented by Item class
	 */
	public Item getItem(String name, FoodType foodType){
		for(Item item: availableItems){
			if(item.getName().equals(name)){
				switch(foodType){
				case PIZZA:
					if(item instanceof Pizza){
						Pizza pizza = (Pizza)item;
						return pizza;
					}
					break;
				case DRINK:
					if(item instanceof Drink){
						Drink drink = (Drink)item;
						return drink;
					}
					break;
				case SIDE:
					if(item instanceof Side){
						Side side = (Side)item;
						return side;
					}
					break;
				}
			}
		}
		return null;
	}
	
	/**
	 * @param foodType type of food, we want a list of
	 * @return array of string with names and prices of food
	 */
	public String[] getAvailableItemsWithPrices(FoodType foodType){
		ArrayList<String> toReturn = new ArrayList<String>();
		for(Item item: availableItems){
			switch(foodType){
			case PIZZA:
				if(item instanceof Pizza){
					Pizza pizza = (Pizza)item;
					BigDecimal[] prices = pizza.getPrices();
					toReturn.add(pizza.getName()+" (£"+prices[0]+" / £"+prices[1]+" / £"+prices[2]+")");
				}
				break;
			case DRINK:
				if(item instanceof Drink){
					toReturn.add(item.getName()+" (£"+item.getPrice()+")");
				}
				break;
			case SIDE:
				if(item instanceof Side){
					toReturn.add(item.getName()+" (£"+item.getPrice()+")");
				}
				break;
			}
		}
		return toReturn.toArray(new String[toReturn.size()]);
	}

	/**
	 * Adds item to the ArrayList with available items.
	 * @param item
	 */
	public void addNewItem(Item item){
		availableItems.add(item);
	}
	
	
	/**
	 * @param index place in JList with food in the same type
	 * @param foodType type of food
	 * @return null when item not found
	 */
	public Item getItem(int index, FoodType foodType){
		ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
		ArrayList<Drink> drinks = new ArrayList<Drink>();
		ArrayList<Side> sides = new ArrayList<Side>();
		for(Item item: availableItems){
			switch(foodType){
			case PIZZA:
				if(item instanceof Pizza){
					pizzas.add((Pizza)item);
				}
				break;
			case DRINK:
				if(item instanceof Drink){
					drinks.add((Drink)item);
				}
				break;
			case SIDE:
				if(item instanceof Side){
					sides.add((Side)item);
				}
				break;
			}
		}
		switch(foodType){
		case PIZZA:
			return pizzas.get(index);
		case DRINK:
			return drinks.get(index);
		case SIDE:
			return sides.get(index);
		default:
			return null;
		}
		
	}
	
	/**
	 * @param index index place in JList with food in the same type
	 * @param foodType type of food
	 * @return position in ArrayList with all items
	 */
	private int getIndexFromAllItems(int index, FoodType foodType){
		Item oldItem = this.getItem(index, foodType);
		int oldIndex = -1, i = 0;
		for(Item item: availableItems){
			if(item.getName().equals(oldItem.getName())){
				switch(foodType){
					case PIZZA:
						if(item instanceof Pizza){
							oldIndex = i;
						}
						break;
					case DRINK:
						if(item instanceof Drink){
							oldIndex = i;
						}
						break;
					case SIDE:
						if(item instanceof Side){
							oldIndex = i;
						}
						break;
				}
			}
		i++;
		}
		return oldIndex;
	}
	
	
	/**
	 * Sets Item "newItem" of type "foodType" in position "index" in arraylist with available items.
	 * @param newItem object of class, which implements Item
	 * @param index position in JList with the same food type
	 * @param foodType type of food
	 */
	public void setItem(Item newItem, int index, FoodType foodType){
		int oldIndex = getIndexFromAllItems(index, foodType);
		if(oldIndex >= 0){
			availableItems.set(oldIndex, newItem);
		}else{
			System.err.println("Item not found!");
		}
	}
	
	/**
	 * Deletes item from arraylist with available items.
	 * @param index position in JList with the same food type
	 * @param foodType type of food
	 */
	public void deleteItem(int index, FoodType foodType){
		if(index >= 0){
			int oldIndex = getIndexFromAllItems(index, foodType);
			if(oldIndex >= 0){
				availableItems.remove(oldIndex);
			}else{
				System.err.println("Item not found!");
			}
		}
	}
	
	/**
	 * Adds new discount to the ArrayList.
	 * @param discount object of Discount class
	 */
	public void addDiscount(Discount discount){
		this.discounts.add(discount);
	}
	
	/**
	 * Edits current discount in the ArrayList.
	 * @param discount object of Discount class
	 * @param index position in the ArrayList
	 */
	public void setDiscount(Discount discount, int index){
		this.discounts.set(index, discount);
	}
	
	/**
	 * @param index position in the ArrayList
	 * @return object from ArrayList of Discount class
	 */
	public Discount getDiscount(int index){
		return this.discounts.get(index);
	}
	
	/**
	 * Removes discount from the ArrayList
	 * @param index position in the ArrayList
	 */
	public void deleteDiscount(int index){
		this.discounts.remove(index);
	}
	
	/**
	 * @return array of string with available discounts
	 */
	public String[] getDiscountsList(){
		String[] toReturn = new String[discounts.size()];
		int i = 0;
		for(Discount discount: discounts){
			toReturn[i] = discount.toString();
			i++;
		}
		return toReturn;
	}
	
	/**
	 * @return ArrayList of available items in restaurant
	 */
	public ArrayList<Item> getAvailableItems() {
		return availableItems;
	}

	/**
	 * Sets new list of available items in restaurant.
	 * @param availableItems list of available items in the restaurant
	 */
	public void setAvailableItems(ArrayList<Item> availableItems) {
		this.availableItems = availableItems;
	}

	/**
	 * @return ArrayList of available discounts
	 */
	public ArrayList<Discount> getDiscounts() {
		return discounts;
	}

	/**
	 * Sets new list of available discounts in the restaurant
	 * @param discounts ArrayList of available discounts
	 */
	public void setDiscounts(ArrayList<Discount> discounts) {
		this.discounts = discounts;
	}
	
	/**
	 * @return restaurant name
	 */
	public String getRestaurantName() {
		return restaurantName;
	}

	/**
	 * Sets new restaurant name.
	 * @param restaurantName restaurant name
	 */
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	/**
	 * @return restaurant telephone number
	 */
	public String getRestaurantTel() {
		return restaurantTel;
	}

	/**
	 * Sets new restaurant telephone number.
	 * @param restaurantTel new restaurant telephone number
	 */
	public void setRestaurantTel(String restaurantTel) {
		this.restaurantTel = restaurantTel;
	}
}
