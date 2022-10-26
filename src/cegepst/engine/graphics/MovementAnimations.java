package cegepst.engine.graphics;

import cegepst.engine.entities.StaticEntity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MovementAnimations extends Animations {

    private BufferedImage spriteSheet;

    public MovementAnimations(String path, int width, int heigth, int x, int y) {
        super(path, width, heigth, x, y, 4);
    }

    @Override
    public void loadSpriteSheet(ImageLoader imageLoader) {
        spriteSheet = imageLoader.loadBufferedImage(SPRITE_PATH);
    }

    @Override
    public void loadAnimations() {
        for (int i = 0; i < animations.length; i++) {
            animations[i] = SpriteSplicer.splice(START_X, START_Y + HEIGTH * i, WIDTH, HEIGTH, spriteSheet);
        }
    }

    public int getAnimationLength() {
        return animations[1].length;
    }

    public Image[] getRightFrames() {
        return animations[2];
    }

    public Image[] getLeftFrames() {
        return animations[1];
    }

    public Image[] getUpFrames() {
        return animations[3];
    }

    public Image[] getDownFrames() {
        return animations[0];
    }
}
