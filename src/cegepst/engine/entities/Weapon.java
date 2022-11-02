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

    public Weapon(String path, int width, int height) {
        SPRITE_PATH = path;
        setDimension(26, 26);
        movementAnimations = new MovementAnimations(SPRITE_PATH, width, height, 0, 128);
        setDimension(width, height);
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
}
