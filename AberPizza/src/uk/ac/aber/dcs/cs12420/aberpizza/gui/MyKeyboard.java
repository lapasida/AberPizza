package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

/**
 * This is my own JDialog class, which displays "virtual keyboard".
 * @author Stanislaw Klimaszewski
 */
public class MyKeyboard extends JDialog implements ActionListener, KeyListener {
	private JPanel panel;
	private JButton[] keys;
	private JTextField textField;
	private String string, tmpString;
	
	/**
	 * Initialise JDialog
	 * @param frame above this JFrame, JDialog will appear
	 * @param title title of JDialog window
	 */
	public MyKeyboard(JFrame frame, String title){
		super(frame, title, true);
		this.tmpString = "";
		this.string = "";
		keys = new JButton[29];
		this.setFocusable(true);
		this.addKeyListener(this);
		this.initialisePanel();
		this.setContentPane(panel);
		this.setSize(560, 310);
		this.setLocationRelativeTo(frame);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Initialise "virtual keyboard".
	 */
	private void initialisePanel(){
		panel = new JPanel();
		panel.setLayout(new GridLayout(5,1));
		textField = new JTextField(tmpString, 30);
		textField.setEditable(false);
		textField.setFont(new Font("Arial", Font.PLAIN, 17));
		textField.setHorizontalAlignment(JTextField.CENTER);
		JPanel firstline = new JPanel();
		String keylist = "qwertyuiop";
		int index = 0;
		for(int i=0; i<keylist.length(); i++){
			keys[index] = new JButton(""+Character.toUpperCase(keylist.charAt(i)));
			keys[index].setPreferredSize(new Dimension(50, 50));
			keys[index].setFocusable(false);
			keys[index].addActionListener(this);
			firstline.add(keys[index]);
			index++;
		}
		JPanel secondline = new JPanel();
		keylist = "asdfghjkl";
		for(int i=0; i<keylist.length(); i++){
			keys[index] = new JButton(""+Character.toUpperCase(keylist.charAt(i)));
			keys[index].setPreferredSize(new Dimension(50, 50));
			keys[index].setFocusable(false);
			keys[index].addActionListener(this);
			secondline.add(keys[index]);
			index++;
		}
		JPanel thirdline = new JPanel();
		keylist = "zxcvbnm";
		for(int i=0; i<keylist.length(); i++){
			keys[index] = new JButton(""+Character.toUpperCase(keylist.charAt(i)));
			keys[index].setPreferredSize(new Dimension(50, 50));
			keys[index].setFocusable(false);
			keys[index].addActionListener(this);
			thirdline.add(keys[index]);
			index++;
		}
		JPanel fourthline = new JPanel();
		keys[index] = new JButton("BACK SPACE");
		keys[index].setPreferredSize(new Dimension(120, 50));
		keys[index].setFocusable(false);
		keys[index].addActionListener(this);
		fourthline.add(keys[index]);
		index++;
		keys[index] = new JButton("");
		keys[index].setPreferredSize(new Dimension(320, 50));
		keys[index].setFocusable(false);
		keys[index].addActionListener(this);
		fourthline.add(keys[index]);
		index++;
		keys[index] = new JButton("ENTER");
		keys[index].setPreferredSize(new Dimension(70, 50));
		keys[index].setFocusable(false);
		keys[index].addActionListener(this);
		fourthline.add(keys[index]);
		panel.add(textField);
		panel.add(firstline);
		panel.add(secondline);
		panel.add(thirdline);
		panel.add(fourthline);
	}
	
	/**
	 * @return typed string
	 */
	public String getString(){
		return string;
	}
	
	/**
	 * @param key letter on keyboard
	 * @return index of key in keys array
	 */
	private int findKey(char key){
		for(int i=0;i<keys.length;i++){
			if(keys[i].getText().equals(""+key) || keys[i].getText().equals(""+Character.toUpperCase(key))){
				return i;
			}
		}
		return -1;
	}

	/**
	 * Updates textfield and closes JDialog when Enter pressed.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String key = e.getActionCommand();
		if(key.length() == 1 && Character.isLetter(key.charAt(0))){
			tmpString = tmpString+key.charAt(0);
		}else if(key.length() == 0){
			tmpString = tmpString+" ";
		}else if(key.length() > 1 && key.equals("BACK SPACE")){
			if(tmpString.length() > 0){
				tmpString = tmpString.substring(0, tmpString.length()-1);
			}
		}else if(key.equals("ENTER")){
			this.string = tmpString;
			this.dispose();
		}
		textField.setText(tmpString);
	}
	
	
	/**
	 * Some fancy animation when key is pressed.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
			keys[26].doClick();
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			keys[27].doClick();
		}else if(e.getKeyCode() == KeyEvent.VK_ENTER){
			keys[28].doClick();
		}
	}
	
	/**
	 * Some fancy animation when key is pressed.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		int index = this.findKey(e.getKeyChar());
		if(index >= 0){
			keys[index].doClick();
		}
	}
	
	/**
	 * Empty method implemented from KeyListener
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
