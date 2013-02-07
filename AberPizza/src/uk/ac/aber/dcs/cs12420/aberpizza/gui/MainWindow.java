package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import javax.swing.*;
import uk.ac.aber.dcs.cs12420.aberpizza.data.FoodType;
import uk.ac.aber.dcs.cs12420.aberpizza.data.MainModel;

/**
 * Shows main window of this application.
 * @author Stanislaw Klimaszewski
 */
public class MainWindow extends JFrame implements WindowListener, ActionListener {
	private JMenuBar menuBar = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JMenu edit = new JMenu("Edit");
	private JMenu help = new JMenu("Help");
	private JLabel label;
	private JList orderList;
	private JButton payButton;
	private JMenuItem startTill, closeTill, salesRecord;	
	private JPanel listPanel, buttonPanel;
	private MainModel model;
	
	/**
	 * Creates and initialises window with all components.
	 */
	public MainWindow(){
		this.model = new MainModel(this);
		this.addWindowListener(this);
		this.setLocation(200,100);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setTitle("Aber Pizza");
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setJMenuBar(menuBar);
		this.initialiseManuBar();
		this.initialiseComponents();
		this.pack();
		this.setSize(700,420);
		this.closeTill();
	}
	
	/**
	 * Initialise menu bar and all menu items.
	 */
	private void initialiseManuBar(){
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(help);
		// -- File Menu --
		ImageIcon startTillIcon = new ImageIcon(getClass().getResource("/icons/startTill.png"));	
		startTill = new JMenuItem("Start till", startTillIcon);
		startTill.setToolTipText("Start till");
		startTill.addActionListener(this);
		file.add(startTill);
		ImageIcon closeTillIcon = new ImageIcon(getClass().getResource("/icons/closeTill.png"));	
		closeTill = new JMenuItem("Close till", closeTillIcon);
		closeTill.setToolTipText("Close till");
		closeTill.addActionListener(this);
		file.add(closeTill);
		file.add(new JSeparator());
		ImageIcon closeAppIcon = new ImageIcon(getClass().getResource("/icons/closeApp.png"));	
		JMenuItem closeApp = new JMenuItem("Exit", closeAppIcon);
		closeApp.setToolTipText("Exit");
		closeApp.addActionListener(this);
		file.add(closeApp);
		// -- Edit Menu --
		ImageIcon salesRecordIcon = new ImageIcon(getClass().getResource("/icons/salesRecord.png"));	
		salesRecord = new JMenuItem("View Sales Record", salesRecordIcon);
		salesRecord.setToolTipText("View Sales Record");
		salesRecord.setEnabled(false);
		salesRecord.addActionListener(this);
		edit.add(salesRecord);
		ImageIcon settingsIcon = new ImageIcon(getClass().getResource("/icons/settings.png"));	
		JMenuItem settings = new JMenuItem("Preferences", settingsIcon);
		settings.setToolTipText("Preferences");
		settings.addActionListener(this);
		edit.add(settings);
		// -- Help Menu --
		ImageIcon aboutIcon = new ImageIcon(getClass().getResource("/icons/about.png"));	
		JMenuItem about = new JMenuItem("About", aboutIcon);
		about.setToolTipText("About");
		about.addActionListener(this);
		help.add(about);
	}
	
	/**
	 * Initialises JPanels with all JComponents
	 */
	private void initialiseComponents(){
		// -- Order List --
		listPanel = new JPanel();
		orderList = new JList();
		orderList.setFont(new Font("Arial", Font.PLAIN, 14));
		JScrollPane orderScroller = new JScrollPane(orderList);
		orderScroller.setPreferredSize(new Dimension(300, 300));
		listPanel.add(orderScroller);
		// -- Order Buttons --
		JButton edit = new JButton("Edit");
		edit.setPreferredSize(new Dimension(120, 30));
		edit.addActionListener(this);
		listPanel.add(edit);
		JButton delete = new JButton("Delete");
		delete.setPreferredSize(new Dimension(120, 30));
		delete.addActionListener(this);
		listPanel.add(delete);
		// -- Labels --
		label = new JLabel("Discount: £0 / Subtotal: £0");
		label.setFont(new Font("Arial", Font.BOLD, 14));
		listPanel.add(label);
		listPanel.setPreferredSize(new Dimension(310, 400));
		this.add(listPanel, BorderLayout.WEST);
		// -- Other Buttons --
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		buttonPanel = new JPanel();
		JButton pizzaButton = new JButton("Pizza");
		pizzaButton.setPreferredSize(new Dimension(120, 70));
		pizzaButton.addActionListener(this);
		buttonPanel.add(pizzaButton);
		JButton sideButton = new JButton("Side");
		sideButton.setPreferredSize(new Dimension(120, 70));
		sideButton.addActionListener(this);
		buttonPanel.add(sideButton);
		JButton drinkButton = new JButton("Drink");
		drinkButton.setPreferredSize(new Dimension(120, 70));
		drinkButton.addActionListener(this);
		buttonPanel.add(drinkButton);		
		payButton = new JButton("Pay");
		payButton.setPreferredSize(new Dimension(120, 70));
		payButton.addActionListener(this);
		buttonPanel.add(payButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setPreferredSize(new Dimension(120, 70));
		cancelButton.addActionListener(this);
		buttonPanel.add(cancelButton);
		// -- Logo and rest --
		JLabel logo = new JLabel(new ImageIcon(getClass().getResource("/logo.png")));
		logo.setPreferredSize(new Dimension(366, 70));
		centerPanel.add(logo, BorderLayout.CENTER);
		buttonPanel.setPreferredSize(new Dimension(400, 200));
		centerPanel.add(buttonPanel, BorderLayout.SOUTH);
		this.add(centerPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Sets new order list in JList.
	 * @param list Array of Strings with names of ordered items.
	 */
	public void setOrderList(String[] list){
		if(list.length == 0){
			payButton.setEnabled(false);
		}else{
			payButton.setEnabled(true);
		}
		orderList.setListData(list);
	}
	
	/**
	 * Sets new discount and subtotal values in JLabel.
	 * @param discount value
	 * @param subtotal value
	 */
	public void setNewValues(BigDecimal discount, BigDecimal subtotal){
		label.setText("Discount: £"+discount+" / Subtotal: £"+subtotal);
	}
	
	/**
	 * @return index of selected item in JList
	 */
	public int getSelected(){
		return orderList.getSelectedIndex();
	}
	
	/**
	 * Sets most of the buttons disabled.
	 */
	public void closeTill(){
		startTill.setEnabled(true);
		closeTill.setEnabled(false);
		salesRecord.setEnabled(false);
		Component[] com = listPanel.getComponents();   
		for (int a = 0; a < com.length; a++) {  
		     com[a].setEnabled(false);  
		}
		com = buttonPanel.getComponents();   
		for (int a = 0; a < com.length; a++) {  
		     com[a].setEnabled(false);  
		}
	}
	
	/**
	 * Sets most of the buttons enabled.
	 */
	public void startTill(){
		startTill.setEnabled(false);
		closeTill.setEnabled(true);
		salesRecord.setEnabled(true);
		Component[] com = listPanel.getComponents();
		for (int i = 0; i < com.length; i++) {  
			com[i].setEnabled(true);  
		}
		com = buttonPanel.getComponents();
		for (int i = 0; i < com.length; i++) {  
			com[i].setEnabled(true);  
		}
		payButton.setEnabled(false);
	}
	
	
	/**
	 * Holds all actions performed by buttons in main window.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("Pizza")){
			model.addNewItem(FoodType.PIZZA);
		}else if(actionCommand.equals("Side")){
			model.addNewItem(FoodType.SIDE);
		}else if(actionCommand.equals("Drink")){
			model.addNewItem(FoodType.DRINK);
		}else if(actionCommand.equals("Pay")){
			model.processOrder();
		}else if(actionCommand.equals("Cancel")){
			model.newOrder();
		}else if(actionCommand.equals("Edit")){
			model.modifySelectedItem();
		}else if(actionCommand.equals("Delete")){
			model.deleteSelectedItem();
		}else if(actionCommand.equals("Start till")){
			model.startTill();
		}else if(actionCommand.equals("Close till")){
			model.closeTill();
		}else if(actionCommand.equals("Exit")){
			if(!startTill.isEnabled()){
				int answer = JOptionPane.showConfirmDialog(this, "Do you want to close till?", "Aber Pizza", JOptionPane.YES_NO_CANCEL_OPTION);
				if(answer == 0){
					model.closeTill();
					this.dispose();
				}
				else if(answer == 1){
					this.dispose();
				}
			}else{
				this.dispose();
			}
		}else if(actionCommand.equals("Preferences")){
			model.showPreferences();
		}else if(actionCommand.equals("View Sales Record")){
			new SalesRecordWindow(this, model.getTill());
		}else if(actionCommand.equals("About")){
			JOptionPane.showMessageDialog(this, "AberPizza\r\nVersion: 1.0\r\n(c) Copyright AberPizza 2012.\r\nAll rights reserved.\r\nCode produced by Stanislaw Klimaszewski");
		}
	}
	
	/**
	 * Show message when user is closing window.
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		if(!startTill.isEnabled()){
			int answer = JOptionPane.showConfirmDialog(this, "Do you want to close till?", "Aber Pizza", JOptionPane.YES_NO_CANCEL_OPTION);
			if(answer == 0){
				model.closeTill();
				this.dispose();
			}
			else if(answer == 1){
				this.dispose();
			}
		}else{
			this.dispose();
		}
	}

	/**
	 * All empty methods implemented from WindowListener.
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}
}
