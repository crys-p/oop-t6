package com.mygdx.game.SoundManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class BackgroundMusic {
    private Music music;

    public BackgroundMusic(String filename) {
        music = Gdx.audio.newMusic(Gdx.files.internal(filename));
    }

    public void play() {
        music.play();
    }

    public void stop() {
        music.stop();
    }

    public void dispose() {
        music.dispose();
    }
}
