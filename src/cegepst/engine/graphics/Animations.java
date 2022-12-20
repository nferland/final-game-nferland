package cegepst.engine.graphics;

import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public abstract class Animations {

    protected final String SPRITE_PATH;
    protected final int WIDTH;
    protected final int HEIGTH;
    protected final int START_X;
    protected final int START_Y;

    protected Image[][] animations;
    private int currentAnimationFrame = 1;
    private int nextFrame = StaticEntity.ANIMATION_SPEED;

    public abstract void loadSpriteSheet(ImageLoader imageLoader);
    public abstract void loadAnimations();

    public Animations(String path, int width, int heigth, int x, int y, int quantity) {
        animations = new Image[quantity][3];
        SPRITE_PATH = path;
        WIDTH = width;
        HEIGTH = heigth;
        START_X = x;
        START_Y = y;
    }

    public Image[] getAnimation(int index) {
        return animations[index];
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

    public int getAnimationLength(int index) {
        return animations[index].length;
    }

    public int getAnimationLength() {
        return animations[0].length;
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
