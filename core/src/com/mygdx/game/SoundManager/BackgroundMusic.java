package com.mygdx.game.SoundManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class BackgroundMusic {
    private Music music;
    private float position; // Variable to manually track playback position

    public BackgroundMusic(String filename) {
        music = Gdx.audio.newMusic(Gdx.files.internal(filename));
    }

    public void play() {
        if (music != null) {
            music.play();
            music.setPosition(position); // Set the playback position
        }
    }

    public void stop() {
        if (music != null) {
            position = music.getPosition(); // Store the playback position
            music.stop();
        }
    }

    public void dispose() {
        if (music != null) {
            music.dispose();
        }
    }
}
