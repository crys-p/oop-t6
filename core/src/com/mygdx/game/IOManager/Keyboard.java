package com.mygdx.game.IOManager;

import java.util.*;

class Keyboard{
	protected Map<Integer, Boolean> keyStates = new HashMap<>();

	Set<Integer> keysAccepted = new HashSet<>(Arrays.asList(
			com.badlogic.gdx.Input.Keys.LEFT,
			com.badlogic.gdx.Input.Keys.RIGHT,
			com.badlogic.gdx.Input.Keys.UP,
			com.badlogic.gdx.Input.Keys.DOWN,
			com.badlogic.gdx.Input.Keys.A,
			com.badlogic.gdx.Input.Keys.D,
			com.badlogic.gdx.Input.Keys.W,
			com.badlogic.gdx.Input.Keys.S
	));

}
