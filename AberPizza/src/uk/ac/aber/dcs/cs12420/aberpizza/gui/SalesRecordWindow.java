package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

/**
 * Shows window with sales record history.
 * @author Stanislaw Klimaszewski
 */
public class SalesRecordWindow extends JDialog implements ItemListener, ListSelectionListener {
	private Till till;
	private JComboBox dateList;
	private JList orderList, orderItemsList;
	
	/**
	 * Creates and initialises all components.
	 * @param frame above this frame, new window will appear
	 * @param till object of Till class
	 */
	public SalesRecordWindow(JFrame frame, Till till){
		super(frame, "Sales record", true);
		this.till = till;
		JPanel panel = new JPanel();
		dateList = new JComboBox(till.getOrderDates());
		dateList.addItemListener(this);
		dateList.setPreferredSize(new Dimension(180,20));
		panel.add(dateList);
		if(till.getOrderDates().length > 0){
			orderList = new JList(till.getOrders((String)dateList.getSelectedItem()));
		}else{
			orderList = new JList();
		}
		orderList.addListSelectionListener(this);
		JScrollPane orderScroller = new JScrollPane(orderList);
		orderScroller.setPreferredSize(new Dimension(180, 70));
		panel.add(orderScroller);
		orderItemsList = new JList();
		JScrollPane orderItemsScroller = new JScrollPane(orderItemsList);
		orderItemsScroller.setPreferredSize(new Dimension(180, 100));
		panel.add(orderItemsScroller);	
		this.setContentPane(panel);
		this.setSize(200, 240);
		this.setLocationRelativeTo(frame);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * Does an operation when state of JComboBox has changed.
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(dateList.getSelectedIndex() >= 0){
			orderList.setListData(till.getOrders((String)dateList.getSelectedItem()));
			orderItemsList.setListData(new String[] {""});
		}
	}

	/**
	 * Does an operation when state of JList has changed.
	 */
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if(orderList.getSelectedIndex() >= 0){
			orderItemsList.setListData(till.getOrderItems((String)dateList.getSelectedItem(), orderList.getSelectedIndex()));
		}
	}
}
