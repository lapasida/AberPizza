package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * My own JDialog class, which creates a grid of JButtons from an array of Strings
 * @author Stanislaw Klimaszewski
 */
public class MyDialog extends JDialog implements ActionListener{
	private String[] buttons;
	private JPanel panel;
	private String respond;
	
	/**
	 * Initialises JDialog.
	 * @param frame above this JFrame, JDialog will appear
	 * @param title title of JDialog
	 * @param buttons array of Strings with buttons names
	 */
	public MyDialog(JFrame frame, String title, String[] buttons){
		super(frame, title, true);
		this.buttons = buttons;
		this.respond = "";
		this.initialisePanel();
		this.setContentPane(panel);
		Dimension dim = panel.getPreferredSize();
		this.setSize((int)dim.getWidth()+5, (int)dim.getHeight()+30);
		this.setLocationRelativeTo(frame);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Generates size of a grid from number of buttons.
	 * @return array with 2 values: rows and columns of grid
	 */
	private int[] generateGridSize(){
		int a = (int)Math.sqrt(buttons.length);
		int b;
		if(a == 0){
			b = 1;
		}else{
			b = (int)(buttons.length/a)+1;
		}
		int[] size = { a, b };
		return size;
	}
	
	/**
	 * Initialises JPanel with all JButtons.
	 */
	private void initialisePanel(){
		this.panel = new JPanel();
		int[] size = this.generateGridSize();
		panel.setLayout(new GridLayout(size[0], size[1]));
		for(String name: buttons){
			JButton button = new JButton(name);
			button.setPreferredSize(new Dimension(200, 70));
			button.addActionListener(this);
			panel.add(button);
		}
	}
	
	/**
	 * @return value of choosen button, returns empty string when no button pressed.
	 */
	public String getRespond(){
		return respond;
	}

	/**
	 * Saves user choice and close JDialog window.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.respond = e.getActionCommand();
		this.dispose();
	}
}
