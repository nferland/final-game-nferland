package cegepst.engine.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MovementAnimations extends Animations {

    private BufferedImage spriteSheet;
    private BufferedImage bluescale;
    private int dashAnimationFrame;

    public MovementAnimations(String path, int width, int heigth, int x, int y) {
        super(path, width, heigth, x, y, 4);
        dashAnimationFrame = 2;
    }

    @Override
    public void loadSpriteSheet(ImageLoader imageLoader) {
        spriteSheet = imageLoader.loadBufferedImage(SPRITE_PATH);
    }

    public void loadSpriteSheet(ImageLoader imageLoader, String bluescalePath) {
        spriteSheet = imageLoader.loadBufferedImage(SPRITE_PATH);
        bluescale = imageLoader.loadBufferedImage(bluescalePath);
    }

    @Override
    public void loadAnimations( ) {
        for (int i = 0; i < animations.length; i++) {
            animations[i] = SpriteSplicer.splice(START_X, START_Y + HEIGTH * i, WIDTH, HEIGTH, spriteSheet);
        }
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

    public int getDashFrame() {
        return dashAnimationFrame;
    }

    public BufferedImage getBluescale() {
        return bluescale;
    }

}
