package com.mygdx.game.IOManager;

import java.security.Key;
import java.util.Map;

public class Keyboard {
	private Map<Key, Boolean> keys;
	
	public Keyboard (Map keys) {
		this.keys = keys;
	}
	
	public boolean IsKeyPressed(Key key) {
		return true;
	}

}
