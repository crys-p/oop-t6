package com.mygdx.game.IOManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Keyboard {
	protected Map<Integer, Boolean> keys;

	public Keyboard() {
		this.keys = new HashMap<>();
	}

	// Method to update the state of a key based on user input
	//keyCode: numerical id for each key on the keyboard
	public void updateKeyState(int keyCode, boolean isPressed) {
		keys.put(keyCode, isPressed);
	}

	// Method to check if a key is pressed
	public boolean isKeyPressed(int keyCode) {
		return keys.getOrDefault(keyCode, false);
	}

	public static void main(String[] args) {
		// Example usage in the main method
		Keyboard keyboard = new Keyboard();
		Scanner scanner = new Scanner(System.in);

		// Simulate continuous input checking (e.g., in a game loop)
		while (true) {
			System.out.println("Enter a key code (or 'q' to quit): ");
			String input = scanner.nextLine();

			if (input.equalsIgnoreCase("q")) {
				break;
			}

			try {
				int keyCode = Integer.parseInt(input);
				keyboard.updateKeyState(keyCode, true);

				// Simulate processing and checking key states
				System.out.println("Key " + keyCode + " pressed: " + keyboard.isKeyPressed(keyCode));

				// Simulate releasing the key
				keyboard.updateKeyState(keyCode, false);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid key code.");
			}
		}

		scanner.close();
	}
}
