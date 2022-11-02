package cegepst.finalGame.player;

import cegepst.engine.GameTime;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.ImageLoader;
import cegepst.engine.graphics.SpriteSplicer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DashGhost extends StaticEntity {

    private Image sprite;
    private final long lifespan = 75;
    private final long creationTime;
    private final int x;
    private final int y;

    public DashGhost(Image sprite, int x, int y, long creationTime) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.creationTime = creationTime;
    }

    public boolean stillAlive() {
        return GameTime.getCurrentTime() - creationTime < lifespan;
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(sprite, x, y);
    }

    @Override
    public void load(ImageLoader imageLoader) {

    }

    @Override
    protected void loadSpriteSheet(ImageLoader imageLoader) {

    }

    @Override
    protected void loadAnimationFrames() {

    }
}
