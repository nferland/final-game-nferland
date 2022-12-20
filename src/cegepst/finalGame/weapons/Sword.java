package cegepst.finalGame.weapons;

import cegepst.engine.controls.Direction;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.Weapon;
import cegepst.engine.graphics.Animator;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.Camera;
import cegepst.engine.graphics.ImageLoader;
import cegepst.engine.entities.Dimension;
import java.awt.*;

public class Sword extends Weapon {

    private Direction direction;

    public Sword(String path, Dimension hitboxDimension, Dimension spriteDimension, int damage) {
        super(path, hitboxDimension,spriteDimension);
        setDamage(damage);
    }

    public void update(ControllableEntity master) {
        direction = master.getDirection();
        updateIsAttacking();
        updateAnimation();
        if (isAttacking()) {
            updateHitEnemy(master);
        }
    }

    @Override
    public void draw(Buffer buffer) {
        if( isAttacking()) {
            buffer.drawImage(Animator.draw(direction, animations, animations.getCurrentAnimationFrame()),
                    x - Camera.getInstance().getX(), y - Camera.getInstance().getY());
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
