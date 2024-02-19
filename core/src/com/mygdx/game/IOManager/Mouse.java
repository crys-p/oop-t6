package com.mygdx.game.IOManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class Mouse implements MouseListener {

	JFrame frame = new JFrame();

	public static void main(String[] args) {
		new Mouse();
	}

	public Mouse() {
		// Initialize your game or any setup here
		initialize();
	}

	public void initialize() {
		// Add the mouse listener to your game component ->not sure about this part
		// For example:
		// gameComponent.addMouseListener(this);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit application
		frame.setSize(1280, 720); //width, height
		frame.setLocationRelativeTo(null); //center to the screen
		frame.setVisible(true); //frame appear on screen

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Handle mouse click event
		int x = e.getX();
		int y = e.getY();
		System.out.println("Mouse Clicked at (" + x + ", " + y + ")");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("You have pressed the mouse");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("Mouse Released at (" + x + ", " + y + ")");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("Mouse cursor enters bounds");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("Mouse cursor out of bounds");
	}

}