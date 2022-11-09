package cegepst.engine.entities;

import cegepst.engine.GameTime;
import cegepst.engine.graphics.MovementAnimations;
import cegepst.engine.graphics.ImageLoader;

public abstract class Weapon extends MovableEntity {

    private final String SPRITE_PATH;
    private MovementAnimations movementAnimations;
    private boolean isAttacking = false;
    private final long attackDuration = 250;
    private long lastAttack = 0l;
    private Dimension hitboxDimension;

    public Weapon(String path, Dimension hitboxDimension, Dimension spriteDimension) {
        SPRITE_PATH = path;
        setDimension(spriteDimension);
        movementAnimations = new MovementAnimations(SPRITE_PATH, getWidth(), getHeight(), 0, 128);
        setHitboxDimension(hitboxDimension);
    }

    public void updateIsAttacking() {
        isAttacking = GameTime.getCurrentTime() - lastAttack < attackDuration;
    }

    public void load(ImageLoader imageLoader){
//        loadSpriteSheet(imageLoader);
//        loadAnimationFrames();
    }

    public void attack() {
        isAttacking = true;
        lastAttack = GameTime.getCurrentTime();
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public void setHitboxDimension(int hitboxWidth, int hitboxHeight) {
        hitboxDimension.setDimension(hitboxWidth, hitboxHeight);
    }

    public void setHitboxDimension(Dimension dimension) {
        hitboxDimension.setDimension(dimension);
    }

    public int getHitboxWidth() {
        return hitboxDimension.getWidth();
    }

    public int getHitboxHeight() {
        return hitboxDimension.getHeight();
    }
}
