package cegepst.finalGame.world;

import cegepst.engine.graphics.*;
import cegepst.engine.entities.StaticEntity;
import cegepst.finalGame.Score;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Door extends StaticEntity {

    private static final String SPRITE_PATH = "images/door.png";
    private BufferedImage image;
    private Blockade blockade;
    private boolean closed = true;
    private Image[] animations;

    public Door(int x, int y) {
        teleport(x, y);
        blockade = new Blockade(10, 32);
        animations = new Image[3];
    }

    public void update() {
        closed = Score.getInstance().getScore() < 400;
    }

    @Override
    public void draw(Buffer buffer) {
        if (closed) {
            buffer.drawImage(animations[0], x - Camera.getInstance().getX(), y - Camera.getInstance().getY());
        } else {
           buffer.drawImage(animations[2], x - Camera.getInstance().getX(), y - Camera.getInstance().getY());
        }
        //blockade.draw(buffer);
    }

    @Override
    public void load(ImageLoader imageLoader) {
        loadSpriteSheet(imageLoader);
    }

    @Override
    protected void loadSpriteSheet(ImageLoader imageLoader) {
        image = imageLoader.loadBufferedImage(SPRITE_PATH);
    }

    @Override
    protected void loadAnimationFrames() {
            animations = SpriteSplicer.splice(0,  0, 32, 32, image);
    }
}
