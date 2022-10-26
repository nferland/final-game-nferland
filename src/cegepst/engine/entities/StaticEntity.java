package cegepst.engine.entities;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.ImageLoader;

import java.awt.*;

public abstract class StaticEntity {

    public static final int ANIMATION_SPEED = 8;

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public abstract void draw(Buffer buffer);

    public abstract void load(ImageLoader imageLoader);

    protected abstract void loadSpriteSheet(ImageLoader imageLoader);

    protected abstract void loadAnimationFrames();

    public void teleport(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setDimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean intersectWith(StaticEntity other) {
        return getBounds().intersects(other.getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
