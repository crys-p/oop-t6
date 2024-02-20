package com.mygdx.game.IOManager;


import com.badlogic.gdx.Gdx;
import com.mygdx.game.EntityManager.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class IOManager {
	//initialize from GameMaster?
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;

	//Setting initial size of window
	public void setWindowedMode() {
		Gdx.graphics.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	private List<Input> inputList;
	//Reference to EntityManager
	private EntityManager entityManager;
	
	public IOManager() {
		inputList = new ArrayList<>();
	}

	//Add input to list
	public void addInput(Input input) {
		inputList.add(input);
	}

	//Update all input in the list and communicate with EntityManager
	public void updateInputs() {
		for (Input input : inputList) {
			input.update();
			List<Integer> keys = input.getKeys();
			entityManager.inputMovement(keys);
		}
	}


}