package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import uk.ac.aber.dcs.cs12420.aberpizza.data.*;

/**
 * Window for adding and editing discounts in the application.
 * @author Stanislaw Klimaszewski
 */
public class DiscountWindow extends JDialog implements ActionListener {
	private JPanel panel;
	private Settings settings;
	private JList discountItems;
	private Discount currentDiscount;
	private JTextField discount;
	private int index;
	
	/**
	 * Initialises JDialog.
	 * @param frame above this JFrame, JDialog will appear
	 * @param settings object of Setting class
	 * @param currentDiscount current discount or null when adding new discount
	 * @param index position of currentDiscount in discount list
	 */
	public DiscountWindow(JDialog frame, Settings settings, Discount currentDiscount, int index){
		super(frame, "Discount Manager", true);
		if(currentDiscount == null){
			currentDiscount = new Discount();
		}
		this.index = index;
		this.currentDiscount = currentDiscount;
		this.settings = settings;
		this.initialisePanel();
		this.setContentPane(panel);
		this.setSize(200, 190);
		this.setLocationRelativeTo(frame);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Initialises all components.
	 */
	private void initialisePanel(){
		panel = new JPanel();
		String[] itemList = currentDiscount.getDiscountItemsList();
		discountItems = new JList(itemList);
		JScrollPane itemScroller = new JScrollPane(discountItems);
		itemScroller.setPreferredSize(new Dimension(190, 50));
		panel.add(itemScroller);
		JButton addButton = new JButton("Add item");
		addButton.setPreferredSize(new Dimension(90, 25));
		addButton.addActionListener(this);
		panel.add(addButton);
		JButton deleteButton = new JButton("Delete item");
		deleteButton.setPreferredSize(new Dimension(90, 25));
		deleteButton.addActionListener(this);
		panel.add(deleteButton);
		panel.add(new JLabel("Discount (%):"));
		if(index >= 0){
			discount = new JTextField(currentDiscount.getDiscount()+"", 20);
		}else{
			discount = new JTextField(20);
		}
		panel.add(discount);
		JButton finalButton;
		if(index >= 0){
			finalButton = new JButton("Edit discount");
		}else{
			finalButton = new JButton("Add new discount");
		}
		finalButton.setPreferredSize(new Dimension(185, 25));
		finalButton.addActionListener(this);
		panel.add(finalButton);
	}
	
	/**
	 * Check if string can be an int value.
	 * @param number string which we want to check
	 * @return true if string is an integer
	 */
	private boolean isIntValue(String number){
		try{
			Integer.parseInt(number);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}

	/**
	 * When button pressed, do some operations.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("Add item")){
			String[] nameList = {"Pizza", "Small Pizza", "Medium Pizza", "Large Pizza", "Side", "Drink"};
			JComboBox names = new JComboBox(nameList);
			names.setSelectedIndex(0);
			JTextField quantity = new JTextField(14);
			JComponent[] inputs = new JComponent[] { new JLabel("Select item:"), names, new JLabel("Quantity:"), quantity };
			int answer = JOptionPane.showConfirmDialog(this, inputs, "Add new item", JOptionPane.OK_CANCEL_OPTION);
			if(answer == 0){
				if(this.isIntValue(quantity.getText())){
					String discountName = (String)names.getSelectedItem();
					discountName = discountName.toUpperCase();
					discountName = discountName.replace(" ", "_");
					DiscountType type = DiscountType.valueOf(discountName);
					DiscountItem tmp = new DiscountItem(type, Integer.parseInt(quantity.getText()));
					currentDiscount.addNewDiscountItem(tmp);
					discountItems.setListData(currentDiscount.getDiscountItemsList());
				}else{
					JOptionPane.showMessageDialog(this, "Wrong number format in quantity text field.");
				}
				
			}
		}else if(action.equals("Delete item")){
			int index = discountItems.getSelectedIndex();
			if(index >= 0){
				currentDiscount.deleteDiscount(index);
				discountItems.setListData(currentDiscount.getDiscountItemsList());
			}
		}else if(action.equals("Add new discount")){
			if(discountItems.getModel().getSize() > 0){
				if(this.isIntValue(discount.getText())){
					int percentage = Integer.parseInt(discount.getText());
					if(percentage > 0 && percentage <= 100){
						if(index >= 0){
							currentDiscount.setDiscount(percentage);
							settings.setDiscount(currentDiscount, index);
							this.dispose();
						}else{
							currentDiscount.setDiscount(percentage);
							settings.addDiscount(currentDiscount);
							this.dispose();
						}
					}else{
						JOptionPane.showMessageDialog(this, "Discount can be only from 1% to 100%.");
					}
				}else{
					JOptionPane.showMessageDialog(this, "Wrong number format in discount text field.");
				}
			}else{
				JOptionPane.showMessageDialog(this, "You have to enter discount items.");
			}
			
		}else if(action.equals("Edit discount")){
			if(discountItems.getModel().getSize() > 0){
				if(this.isIntValue(discount.getText())){
					int percentage = Integer.parseInt(discount.getText());
					if(percentage > 0 && percentage <= 100){
						currentDiscount.setDiscount(percentage);
						settings.setDiscount(currentDiscount, index);
						this.dispose();
					}else{
						JOptionPane.showMessageDialog(this, "Discount can be only from 1% to 100%.");
					}
				}else{
					JOptionPane.showMessageDialog(this, "Wrong number format in discount text field.");
				}
			}else{
				JOptionPane.showMessageDialog(this, "You have to enter discount items.");
			}
		}
	}
}
