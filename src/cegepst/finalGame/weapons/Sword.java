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
    public void draw(Buffer buffer) {
        buffer.drawImage(Animator.draw(getDirection(), animations, animations.getCurrentAnimationFrame()),
                x - Camera.getInstance().getX(), y - Camera.getInstance().getY());
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

}
