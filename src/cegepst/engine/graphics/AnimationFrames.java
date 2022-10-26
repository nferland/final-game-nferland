package cegepst.engine.graphics;

import cegepst.engine.entities.StaticEntity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimationFrames {

    private final String SPRITE_PATH;
    private final int WIDTH;
    private final int HEIGTH;
    private final int START_X;
    private final int START_Y;

    private Image[] rightFrames;
    private Image[] leftFrames;
    private Image[] upFrames;
    private Image[] downFrames;
    private int currentAnimationFrame = 1;
    private BufferedImage spriteSheet;
    private int nextFrame = StaticEntity.ANIMATION_SPEED;

    public AnimationFrames(String path, int width, int heigth, int x, int y) {
        rightFrames = new Image[3];
        leftFrames = new Image[3];
        upFrames = new Image[3];
        downFrames = new Image[3];
        SPRITE_PATH = path;
        WIDTH = width;
        HEIGTH = heigth;
        START_X = x;
        START_Y = y;
    }

    public void loadSpriteSheet(ImageLoader imageLoader) {
        spriteSheet = imageLoader.loadBufferedImage(SPRITE_PATH);
    }

    public void loadAnimations() {
        downFrames = SpriteSplicer.splice(START_X, START_Y, WIDTH, HEIGTH, spriteSheet);
        leftFrames = SpriteSplicer.splice(START_X, START_Y + HEIGTH, WIDTH, HEIGTH, spriteSheet);
        rightFrames = SpriteSplicer.splice(START_X, START_Y + HEIGTH * 2, WIDTH, HEIGTH, spriteSheet);
        upFrames = SpriteSplicer.splice(START_X, START_Y + HEIGTH * 3, WIDTH, HEIGTH, spriteSheet);
    }

    public int getAnimationLength() {
        return leftFrames.length;
    }

    public Image[] getRightFrames() {
        return rightFrames;
    }

    public Image[] getLeftFrames() {
        return leftFrames;
    }

    public Image[] getUpFrames() {
        return upFrames;
    }

    public Image[] getDownFrames() {
        return downFrames;
    }

    public int getCurrentAnimationFrame() {
        return currentAnimationFrame;
    }

    public void setCurrentAnimationFrame(int frame) {
        currentAnimationFrame = frame;
    }

    public int getNextFrame() {
        return nextFrame;
    }

    public void setNextFrame(int value) {
        nextFrame = value;
    }
}
