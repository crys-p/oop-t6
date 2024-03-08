package com.mygdx.engine.SoundManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

class BackgroundMusic {
    private Music music;
    private float position; // Variable to manually track playback position

    protected BackgroundMusic(String filename) {
        music = Gdx.audio.newMusic(Gdx.files.internal(filename));
    }

    protected void play() {
        if (music != null) {
            music.play();
            music.setPosition(position); // Set the playback position
        }
    }

    protected void stop() {
        if (music != null) {
            position = music.getPosition(); // Store the playback position
            music.stop();
        }
    }

    protected void dispose() {
        if (music != null) {
            music.dispose();
        }
    }
}
