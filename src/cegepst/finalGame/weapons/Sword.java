package cegepst.finalGame.weapons;

import cegepst.engine.controls.Direction;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.Weapon;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.ImageLoader;

import java.awt.*;

public class Sword extends Weapon {

    public Sword(String path, int width, int height) {
        super(path, width, height);

    }

    @Override
    public void update() {
        super.update();
        updateIsAttacking();
        updateDimension();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height, new Color(255, 0, 0, 100));
    }

    public void updatePlacement(MovableEntity master) {
        switch (getDirection()) {
            case UP: teleportUp(master); break;
            case DOWN: teleportDown(master); break;
            case LEFT: teleportLeft(master); break;
            case RIGHT: teleportRight(master); break;
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
            width = 78;
            height = 26;
            return;
        }
        width = 26;
        height = 78;
    }

    private void teleportUp(MovableEntity master) {
        teleport(master.getX() - (width - master.getWidth()) / 2, master.getY() - height);
    }

    private void teleportDown(MovableEntity master) {
        teleport(master.getX() - (width - master.getWidth()) / 2, master.getY() + master.getHeight());
    }

    private void teleportLeft(MovableEntity master) {
        teleport(master.getX() - width, master.getY() - (height - master.getHeight()) / 2);
    }

    private void teleportRight(MovableEntity master) {
        teleport(master.getX() + master.getWidth(), master.getY() - (height - master.getHeight()) / 2);
    }
}
