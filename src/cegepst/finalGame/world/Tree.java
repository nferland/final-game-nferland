package cegepst.finalGame.world;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Camera;
import cegepst.engine.graphics.ImageLoader;

import java.awt.*;

public class Tree extends StaticEntity {

    private static final String SPRITE_PATH = "images/tree.png";
    private Image image;
    private Blockade blockade;

    public Tree(int x, int y) {
        teleport(x, y);
        blockade = new Blockade(30, 16);
        blockadeFromTop();
    }

    @Override
    public void draw(Buffer buffer, Camera camera) {
        buffer.drawImage(image, x - camera.getxOffset(), y - camera.getyOffset());
        //blockade.draw(buffer);
    }

    @Override
    public void load(ImageLoader imageLoader) {
        loadSpriteSheet(imageLoader);
    }

    @Override
    protected void loadSpriteSheet(ImageLoader imageLoader) {
        image = imageLoader.loadImage(SPRITE_PATH);
    }

    @Override
    protected void loadAnimationFrames() {

    }

    public void blockadeFromTop() {
        blockade.teleport(x + 16, y + 64);
    }

    public void blockadeFromBottom() {
        blockade.teleport(x + 16, y + 48);
    }
}
