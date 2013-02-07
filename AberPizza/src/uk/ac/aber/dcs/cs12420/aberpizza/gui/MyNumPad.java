package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * My own JDialog, which looks like NumPad. It works also with keyboard's NumPad.
 * @author Stanislaw Klimaszewski
 */
public class MyNumPad extends JDialog implements ActionListener, KeyListener{
	private int number;
	private String tmpNumber;
	private JPanel panel;
	private JTextField textField;
	private JButton[] buttons;
	
	/**
	 * Initialise JDialog.
	 * @param frame above this frame, JDialog will appear
	 * @param title title of JDialog
	 */
	public MyNumPad(JFrame frame, String title){
		super(frame, title, true);
		this.setFocusable(true);
		this.addKeyListener(this);
		this.number = 0;
		this.tmpNumber = "0";
		this.buttons = new JButton[12];
		this.initialisePanel();
		this.setContentPane(panel);
		this.setSize(250, 350);
		this.setLocationRelativeTo(frame);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Initialise all components in JDialog.
	 */
	private void initialisePanel(){
		panel = new JPanel();
		textField = new JTextField(tmpNumber, 18);
		textField.setFocusable(false);
		textField.setEditable(false);
		textField.setFont(new Font("Arial", Font.PLAIN, 14));
		textField.setHorizontalAlignment(JTextField.RIGHT);
		panel.add(textField);
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(4,3));
		String numbers = "7894561230";
		int index = 0;
		for(int i=0; i<numbers.length(); i++){
			buttons[index] = new JButton(""+numbers.charAt(i));
			buttons[index].setPreferredSize(new Dimension(70, 70));
			buttons[index].addActionListener(this);
			buttons[index].setFocusable(false);
			gridPanel.add(buttons[index]);
			index++;
		}
		buttons[10] = new JButton("Clear");
		buttons[10].setPreferredSize(new Dimension(70, 70));
		buttons[10].addActionListener(this);
		buttons[10].setFocusable(false);
		gridPanel.add(buttons[10]);
		buttons[11] = new JButton("OK");
		buttons[11].setPreferredSize(new Dimension(70, 70));
		buttons[11].addActionListener(this);
		buttons[11].setFocusable(false);
		gridPanel.add(buttons[11]);
		panel.add(gridPanel);
	}
	
	/**
	 * @return choosen number from this NumPad
	 */
	public int getNumber(){
		return number;
	}

	/**
	 * Updates JTextField, when button pressed, and saves choosen number, when NumPad is closed.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String key = e.getActionCommand();
		if(key.equals("OK")){
			if(tmpNumber.length() < 10){
				this.number = Integer.parseInt(tmpNumber);
			}
			this.dispose();
		}else if(key.equals("Clear")){
			this.tmpNumber = "0";
			textField.setText(tmpNumber);
		}else{
			if(tmpNumber.equals("0")){
				this.tmpNumber = key;
			}else{
				this.tmpNumber = tmpNumber+key;
			}
			textField.setText(tmpNumber);
		}
	}
	
	/**
	 * @param number name of button, which we're looking for
	 * @return index in array of buttons
	 */
	private int findButton(char number){
		for(int i=0; i<buttons.length; i++){
			if(buttons[i].getText().equals(""+number)){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Some fancy button click animation, when key on NumPad is pressed.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		int index = this.findButton(e.getKeyChar());
		if(index >= 0){
			buttons[index].doClick();
		}
	}

	/**
	 * Some fancy button click animation, when key on NumPad is pressed.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			buttons[11].doClick();
		}
	}

	/**
	 * Empty method implemented from KeyListener class.
	 */
	@Override
	public void keyReleased(KeyEvent e) {

	}
}
