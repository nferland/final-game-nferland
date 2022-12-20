package cegepst.engine.entities;

import cegepst.engine.graphics.MovementAnimations;

public abstract class Trigger {

    private int x, y;
    private Dimension dimension;

    public Trigger(Dimension dimension, int x, int y) {
        this.x = x;
        this.y = y;
        this.dimension = dimension;
    }

    public abstract void update(ControllableEntity player);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return dimension.getWidth();
    }

    public int getHeight() {
        return dimension.getHeight();
    }

    public Dimension getDimension() {
        return dimension;
    }

    protected abstract boolean intersectWith(MovableEntity entity);
}
