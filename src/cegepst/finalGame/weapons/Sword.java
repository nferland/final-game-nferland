package cegepst.finalGame.weapons;

import cegepst.engine.controls.Direction;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.Weapon;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.ImageLoader;
import cegepst.engine.entities.Dimension;

import java.awt.*;

public class Sword extends Weapon {

    public Sword(String path, Dimension hitboxDimension, Dimension spriteDimension) {
        super(path, hitboxDimension,spriteDimension);

    }

    @Override
    public void update() {
        super.update();
        updateIsAttacking();
        updateDimension();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, getWidth(), getHeight(), new Color(255, 0, 0, 100));
    }

    public void updatePlacement(MovableEntity master) {
        switch (getDirection()) {
            case UP -> teleportUp(master);
            case DOWN -> teleportDown(master);
            case LEFT -> teleportLeft(master);
            case RIGHT -> teleportRight(master);
        }
    }

    @Override
    protected void loadSpriteSheet(ImageLoader imageLoader) {

    }

    @Override
    protected void loadAnimationFrames() {

    }

    private void updateDimension() {
        if (getDirection() == Direction.DOWN || getDirection() == Direction.UP) {
            setHitboxDimension(52, 26);
            return;
        }
        setHitboxDimension(26, 52);
    }

    private void teleportUp(MovableEntity master) {
        teleport(master.getX() - (getWidth() - master.getWidth()) / 2, master.getY() - getHeight());
    }

    private void teleportDown(MovableEntity master) {
        teleport(master.getX() - (getWidth() - master.getWidth()) / 2, master.getY() + master.getHeight());
    }

    private void teleportLeft(MovableEntity master) {
         teleport(master.getX() - getWidth(), master.getY() - (getHeight() - master.getHeight()) / 2);
    }

    private void teleportRight(MovableEntity master) {
        teleport(master.getX() + master.getWidth(), master.getY() - (getHeight() - master.getHeight()) / 2);
    }
}
