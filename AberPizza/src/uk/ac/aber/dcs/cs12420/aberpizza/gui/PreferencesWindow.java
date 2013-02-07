package uk.ac.aber.dcs.cs12420.aberpizza.gui;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.*;
import uk.ac.aber.dcs.cs12420.aberpizza.data.*;

/**
 * Creates window, where you can change all settings.
 * @author Stanislaw Klimaszewski
 */
public class PreferencesWindow extends JDialog implements ActionListener {
	private JTabbedPane tabbedPane;
	private Settings settings;
	private JTextField restaurantName, restaurantTel;
	private JList pizzasList, sidesList, drinksList, discountsList;
	
	/**
	 * Initialises JDialog.
	 * @param frame above this JFrame, JDialog will appear
	 * @param settings object of Settings class, with current settings
	 */
	public PreferencesWindow(JFrame frame, Settings settings){
		super(frame, "Aber Pizza - Preferences", true);
		this.settings = settings;
		this.initialisePanel();
		this.setContentPane(tabbedPane);
		this.setSize(320, 318);
		this.setLocationRelativeTo(frame);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Initilises all tabs.
	 */
	private void initialisePanel(){
		tabbedPane = new JTabbedPane();
		JPanel pizzas = createFoodTab(FoodType.PIZZA);
		tabbedPane.addTab("Pizzas", null, pizzas,"Modify current pizza menu.");
		JPanel sides = createFoodTab(FoodType.SIDE);
		tabbedPane.addTab("Sides", null, sides,"Modify current sides menu.");
		JPanel drinks = createFoodTab(FoodType.DRINK);
		tabbedPane.addTab("Drinks", null, drinks,"Modify current drinks menu.");
		JPanel discounts = createDiscountTab();
		tabbedPane.addTab("Discounts", null, discounts,"Modify current discounts.");
		JPanel other = createOtherTab();
		tabbedPane.addTab("Other", null, other,"Modify other settings.");
	}
	
	/**
	 * Creates tab with food informations.
	 * @param type type of food
	 * @return JPanel with all components
	 */
	private JPanel createFoodTab(FoodType type){
		JPanel pizzaTab = new JPanel();
		String[] items;
		JScrollPane itemScroller;
		switch(type){
			case PIZZA:
				items = settings.getAvailableItemsWithPrices(type);
				pizzasList = new JList(items);
				itemScroller = new JScrollPane(pizzasList);
				break;
			case SIDE:
				items = settings.getAvailableItemsWithPrices(type);
				sidesList = new JList(items);
				itemScroller = new JScrollPane(sidesList);
				break;
			default:
				items = settings.getAvailableItemsWithPrices(type);
				drinksList = new JList(items);
				itemScroller = new JScrollPane(drinksList);
				break;
		}
		itemScroller.setPreferredSize(new Dimension(300, 200));
		pizzaTab.add(itemScroller);
		JButton addButton = new JButton("Add");
		addButton.setPreferredSize(new Dimension(80, 25));
		addButton.addActionListener(this);
		pizzaTab.add(addButton);
		JButton editButton = new JButton("Edit");
		editButton.setPreferredSize(new Dimension(80, 25));
		editButton.addActionListener(this);
		pizzaTab.add(editButton);
		JButton delButton = new JButton("Delete");
		delButton.setPreferredSize(new Dimension(80, 25));
		delButton.addActionListener(this);
		pizzaTab.add(delButton);
		return pizzaTab;
	}
	
	/**
	 * Creates tab with discount informations.
	 * @return JPanel with all components
	 */
	private JPanel createDiscountTab(){
		JPanel pizzaTab = new JPanel();
		String[] discounts = settings.getDiscountsList();
		discountsList = new JList(discounts);
		JScrollPane itemScroller = new JScrollPane(discountsList);
		itemScroller.setPreferredSize(new Dimension(300, 200));
		pizzaTab.add(itemScroller);
		JButton addButton = new JButton("Add");
		addButton.setPreferredSize(new Dimension(80, 25));
		addButton.addActionListener(this);
		pizzaTab.add(addButton);
		JButton editButton = new JButton("Edit");
		editButton.setPreferredSize(new Dimension(80, 25));
		editButton.addActionListener(this);
		pizzaTab.add(editButton);
		JButton delButton = new JButton("Delete");
		delButton.setPreferredSize(new Dimension(80, 25));
		delButton.addActionListener(this);
		pizzaTab.add(delButton);
		return pizzaTab;
	}
	
	/**
	 * Creates tab with other informations.
	 * @return JPanel with all components
	 */
	private JPanel createOtherTab(){
		JPanel otherTab = new JPanel();
		JLabel nameLabel = new JLabel("Restaurant name:");
		nameLabel.setPreferredSize(new Dimension(205, 15));
		otherTab.add(nameLabel);
		restaurantName = new JTextField(settings.getRestaurantName(),25);
		otherTab.add(restaurantName);
		JLabel telLabel = new JLabel("Restaurant phone number:");
		telLabel.setPreferredSize(new Dimension(205, 15));
		otherTab.add(telLabel);
		restaurantTel = new JTextField(settings.getRestaurantTel(),25);
		otherTab.add(restaurantTel);
		JButton accept = new JButton("Accept");
		accept.setPreferredSize(new Dimension(100, 25));
		accept.addActionListener(this);
		otherTab.add(accept);
		JButton cancel = new JButton("Cancel");
		cancel.setPreferredSize(new Dimension(100, 25));
		cancel.addActionListener(this);
		otherTab.add(cancel);
		return otherTab;
	}
	
	/**
	 * @param number string with number
	 * @return true if number from the string can be a BigDecimal
	 */
	private boolean isBigDecimal(String number){
		try{
			new BigDecimal(number);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}
	
	/**
	 * Updates lists with current available items.
	 */
	private void updateItemLists(){
		pizzasList.setListData(settings.getAvailableItemsWithPrices(FoodType.PIZZA));
		sidesList.setListData(settings.getAvailableItemsWithPrices(FoodType.SIDE));
		drinksList.setListData(settings.getAvailableItemsWithPrices(FoodType.DRINK));
		discountsList.setListData(settings.getDiscountsList());
	}
	
	/**
	 * Asks user about new item and add new item to available items in settings.
	 * @param foodType food type of new item
	 */
	private void addNewItem(FoodType foodType){
		// -- Create window --
		ArrayList<JComponent> components = new ArrayList<JComponent>();
		components.add(new JLabel("Name:"));
		JTextField name = new JTextField(15);
		components.add(name);
		components.add(new JLabel("Description:"));
		JTextField description = new JTextField(15);
		components.add(description);
		JTextField price = null, smallPrice = null, mediumPrice = null, largePrice = null;
		if(foodType.equals(FoodType.PIZZA)){
			components.add(new JLabel("Price for small size:"));
			smallPrice = new JTextField(15);
			components.add(smallPrice);
			components.add(new JLabel("Price for medium size:"));
			mediumPrice = new JTextField(15);
			components.add(mediumPrice);
			components.add(new JLabel("Price for large size:"));
			largePrice = new JTextField(15);
			components.add(largePrice);
		}else{
			components.add(new JLabel("Price:"));
			price = new JTextField(15);
			components.add(price);
		}
		int answer = JOptionPane.showConfirmDialog(this, components.toArray(new JComponent[components.size()]), "Add New "+foodType, JOptionPane.OK_CANCEL_OPTION);
		// -- Process answer --
		if(answer == 0){
			if(foodType.equals(FoodType.PIZZA)){
				if(name.getText().length() > 0 && description.getText().length() > 0 && smallPrice.getText().length() > 0 && mediumPrice.getText().length() > 0 && largePrice.getText().length() > 0){
					if(this.isBigDecimal(smallPrice.getText()) && this.isBigDecimal(mediumPrice.getText()) && this.isBigDecimal(largePrice.getText())){
						BigDecimal small = new BigDecimal(smallPrice.getText());
						small = small.setScale(2, BigDecimal.ROUND_HALF_UP);
						BigDecimal medium = new BigDecimal(mediumPrice.getText());
						medium = medium.setScale(2, BigDecimal.ROUND_HALF_UP);
						BigDecimal large = new BigDecimal(largePrice.getText());
						large = large.setScale(2, BigDecimal.ROUND_HALF_UP);
						BigDecimal[] prices = { small, medium, large };
						Item item = new Pizza(prices, name.getText(), description.getText(), PizzaSize.UNKNOWN);
						settings.addNewItem(item);
						this.updateItemLists();
					}else{
						JOptionPane.showMessageDialog(this, "Wrong price format.");
					}
				}else{
					JOptionPane.showMessageDialog(this, "You have to fill in all fields.");
				}
			}else{
				if(name.getText().length() > 0 && description.getText().length() > 0 && price.getText().length() > 0){
					if(this.isBigDecimal(price.getText())){
						Item item;
						if(foodType.equals(FoodType.SIDE)){
							BigDecimal itemPrice = new BigDecimal(price.getText());
							itemPrice = itemPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
							item = new Side(name.getText(), description.getText(), itemPrice);
						}else{
							BigDecimal itemPrice = new BigDecimal(price.getText());
							itemPrice = itemPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
							item = new Drink(name.getText(), description.getText(), itemPrice);
						}
						settings.addNewItem(item);
						this.updateItemLists();
					}else{
						JOptionPane.showMessageDialog(this, "Wrong price format.");
					}
				}else{
					JOptionPane.showMessageDialog(this, "You have to fill in all fields.");
				}
			}
		}
	}
	
	/**
	 * Se
	 * @param index position in the list of food with the same type
	 * @param foodType type of food
	 */
	private void editCurrentItem(int index, FoodType foodType){
		if(index >= 0){
			Item currentItem = settings.getItem(index, foodType);
			// -- Create window --
			ArrayList<JComponent> components = new ArrayList<JComponent>();
			components.add(new JLabel("Name:"));
			JTextField name = new JTextField(currentItem.getName(), 15);
			components.add(name);
			components.add(new JLabel("Description:"));
			JTextField description = new JTextField(currentItem.getDescription(), 15);
			components.add(description);
			JTextField price = null, smallPrice = null, mediumPrice = null, largePrice = null;
			if(foodType.equals(FoodType.PIZZA)){
				Pizza pizza = (Pizza)currentItem;
				BigDecimal[] prices = pizza.getPrices();
				components.add(new JLabel("Price for small size:"));
				smallPrice = new JTextField(prices[0].toString(), 15);
				components.add(smallPrice);
				components.add(new JLabel("Price for medium size:"));
				mediumPrice = new JTextField(prices[1].toString(), 15);
				components.add(mediumPrice);
				components.add(new JLabel("Price for large size:"));
				largePrice = new JTextField(prices[2].toString(), 15);
				components.add(largePrice);
			}else{
				components.add(new JLabel("Price:"));
				price = new JTextField(currentItem.getPrice().toString(), 15);
				components.add(price);
			}
			int answer = JOptionPane.showConfirmDialog(this, components.toArray(new JComponent[components.size()]), "Edit "+foodType, JOptionPane.OK_CANCEL_OPTION);
			// -- Process answer --
			if(answer == 0){
				if(foodType.equals(FoodType.PIZZA)){
					if(name.getText().length() > 0 && description.getText().length() > 0 && smallPrice.getText().length() > 0 && mediumPrice.getText().length() > 0 && largePrice.getText().length() > 0){
						if(this.isBigDecimal(smallPrice.getText()) && this.isBigDecimal(mediumPrice.getText()) && this.isBigDecimal(largePrice.getText())){
							BigDecimal small = new BigDecimal(smallPrice.getText());
							small = small.setScale(2, BigDecimal.ROUND_HALF_UP);
							BigDecimal medium = new BigDecimal(mediumPrice.getText());
							medium = medium.setScale(2, BigDecimal.ROUND_HALF_UP);
							BigDecimal large = new BigDecimal(largePrice.getText());
							large = large.setScale(2, BigDecimal.ROUND_HALF_UP);
							BigDecimal[] prices = { small, medium, large };
							Item item = new Pizza(prices, name.getText(), description.getText(), PizzaSize.UNKNOWN);
							settings.setItem(item, index, FoodType.PIZZA);
							this.updateItemLists();
						}else{
							JOptionPane.showMessageDialog(this, "Wrong price format.");
						}
					}else{
						JOptionPane.showMessageDialog(this, "You have to fill in all fields.");
					}
				}else{
					if(name.getText().length() > 0 && description.getText().length() > 0 && price.getText().length() > 0){
						if(this.isBigDecimal(price.getText())){
							Item item;
							if(foodType.equals(FoodType.SIDE)){
								BigDecimal itemPrice = new BigDecimal(price.getText());
								itemPrice = itemPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
								item = new Side(name.getText(), description.getText(), itemPrice);
								settings.setItem(item, index, FoodType.SIDE);
							}else{
								BigDecimal itemPrice = new BigDecimal(price.getText());
								itemPrice = itemPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
								item = new Drink(name.getText(), description.getText(), itemPrice);
								settings.setItem(item, index, FoodType.DRINK);
							}
							this.updateItemLists();
						}else{
							JOptionPane.showMessageDialog(this, "Wrong price format.");
						}
					}else{
						JOptionPane.showMessageDialog(this, "You have to fill in all fields.");
					}
				}
			}
		}
		
	}
	
	/**
	 * Call different methods, when button is pressed.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("Add") && tabbedPane.getSelectedIndex() == 0){
			this.addNewItem(FoodType.PIZZA);
		}else if(action.equals("Add") && tabbedPane.getSelectedIndex() == 1){
			this.addNewItem(FoodType.SIDE);
		}else if(action.equals("Add") && tabbedPane.getSelectedIndex() == 2){
			this.addNewItem(FoodType.DRINK);
		}else if(action.equals("Add") && tabbedPane.getSelectedIndex() == 3){
			new DiscountWindow(this, settings, null, -1);
			this.updateItemLists();
		}else if(action.equals("Edit") && tabbedPane.getSelectedIndex() == 0){
			this.editCurrentItem(pizzasList.getSelectedIndex(), FoodType.PIZZA);
		}else if(action.equals("Edit") && tabbedPane.getSelectedIndex() == 1){
			this.editCurrentItem(sidesList.getSelectedIndex(), FoodType.SIDE);
		}else if(action.equals("Edit") && tabbedPane.getSelectedIndex() == 2){
			this.editCurrentItem(drinksList.getSelectedIndex(), FoodType.DRINK);
		}else if(action.equals("Edit") && tabbedPane.getSelectedIndex() == 3){
			new DiscountWindow(this, settings, settings.getDiscount(discountsList.getSelectedIndex()), discountsList.getSelectedIndex());
			this.updateItemLists();
		}else if(action.equals("Delete") && tabbedPane.getSelectedIndex() == 0){
			settings.deleteItem(pizzasList.getSelectedIndex(), FoodType.PIZZA);
			this.updateItemLists();
		}else if(action.equals("Delete") && tabbedPane.getSelectedIndex() == 1){
			settings.deleteItem(sidesList.getSelectedIndex(), FoodType.SIDE);
			this.updateItemLists();
		}else if(action.equals("Delete") && tabbedPane.getSelectedIndex() == 2){
			settings.deleteItem(drinksList.getSelectedIndex(), FoodType.DRINK);
			this.updateItemLists();
		}else if(action.equals("Delete") && tabbedPane.getSelectedIndex() == 3){
			if(discountsList.getSelectedIndex() >= 0){
				settings.deleteDiscount(discountsList.getSelectedIndex());
				this.updateItemLists();
			}
		}else if(action.equals("Accept")){
			settings.setRestaurantName(restaurantName.getText());
			settings.setRestaurantTel(restaurantTel.getText());
		}else if(action.equals("Cancel")){
			restaurantName.setText(settings.getRestaurantName());
			restaurantTel.setText(settings.getRestaurantTel());
		}
	}
}
