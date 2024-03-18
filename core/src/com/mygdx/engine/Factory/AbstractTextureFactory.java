package com.mygdx.engine.Factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entities.EntityType;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class AbstractTextureFactory {
    protected HashMap<EntityType, String> entityImageMap;
    protected HashMap<EntityType, String[]> entityMultiImageMap;
    protected ArrayList<Texture> textureList;

    public AbstractTextureFactory() {
        entityImageMap = new HashMap<>();
        entityMultiImageMap = new HashMap<>();
        textureList = new ArrayList<>();
    }

    protected void create() {
        createSingleTextureEntity();
        createMultiTextureEntity();
    }

    protected abstract void createSingleTextureEntity();
    protected abstract void createMultiTextureEntity();

    // Get Texture for entities with only 1 texture
    public Texture getTexture(EntityType entityType) {
        String image = entityImageMap.get(entityType);
        return new Texture(Gdx.files.internal(image));
    }

    // Get array of Textures for entities with more than 1 texture (e.g. multi direction)
    public Texture[] getTextures(EntityType entityType) {
        String[] images = entityMultiImageMap.get(entityType);
        Texture[] textures = new Texture[images.length];
        for (int i = 0; i < images.length; i++) {
            textures[i] = scaleTexture(2, images[i], new Texture(Gdx.files.internal(images[i])));
        }
        return textures;
    }

    // Scale texture by factor
    protected Texture scaleTexture(int factor, String img_path, Texture originalTexture) {
        // Get original texture dimensions
        int originalWidth = originalTexture.getWidth();
        int originalHeight = originalTexture.getHeight();
        // Create a pixmap from the original texture data
        Pixmap originalPixmap = new Pixmap(Gdx.files.internal(img_path));
        // Scale the pixmap by 2x
        Pixmap scaledPixmap = new Pixmap(originalWidth * factor, originalHeight * factor, originalPixmap.getFormat());
        scaledPixmap.drawPixmap(originalPixmap, 0, 0, originalWidth, originalHeight, 0, 0, scaledPixmap.getWidth(), scaledPixmap.getHeight());
        // Create a new texture from the scaled pixmap
        Texture newTexture = new Texture(scaledPixmap);
        // Dispose of the original texture and pixmap to free up memory
        originalTexture.dispose();
        originalPixmap.dispose();
        scaledPixmap.dispose();
        return newTexture;
    }
}
