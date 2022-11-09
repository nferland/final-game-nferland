package cegepst.finalGame.weapons;

import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.Weapon;
import cegepst.engine.graphics.Animator;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.Camera;
import cegepst.engine.graphics.ImageLoader;
import cegepst.engine.entities.Dimension;

public class Sword extends Weapon {

    public Sword(String path, Dimension hitboxDimension, Dimension spriteDimension) {
        super(path, hitboxDimension,spriteDimension);

    }

    @Override
    public void update() {
        super.update();
        updateIsAttacking();
        updateAnimation();

    }

    @Override
    public void draw(Buffer buffer, Camera camera) {
        buffer.drawImage(Animator.draw(getDirection(), animations, animations.getCurrentAnimationFrame()),
                x - camera.getxOffset(), y - camera.getyOffset());
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
        animations.loadSpriteSheet(imageLoader);
    }

    @Override
    protected void loadAnimationFrames() {
        animations.loadAnimations();
    }

    private void updateAnimation() {
        Animator.animate(isAttacking(), animations, 0);
    }

    private void teleportUp(MovableEntity master) {
        teleport(master.getX() + topXValue(master), master.getY() - halfOf(getHitboxHeight()));
    }

    private void teleportDown(MovableEntity master) {
        teleport(master.getX(), master.getY() + master.getHeight() - halfOf(getHitboxHeight()));
    }

    private void teleportLeft(MovableEntity master) {
         teleport(master.getX() - halfOf(getHitboxWidth()), master.getY() + sideYValue(master));
    }

    private void teleportRight(MovableEntity master) {
        teleport(master.getX() + rightXValue(master), master.getY() + sideYValue(master));
    }

    private int sideYValue(MovableEntity master) {
        return (master.getHeight() - threeQuarterOf(getHitboxHeight()));
    }

    private int topXValue(MovableEntity master) {
        return halfOf(master.getWidth() - getHitboxWidth());
    }

    private int rightXValue(MovableEntity master) {
        return master.getWidth() - halfOf(getHitboxWidth());
    }

    private int halfOf(int value) {
        return value / 2;
    }

    private int threeQuarterOf(int value) {
        return (3 * value) / 4;
    }
}
