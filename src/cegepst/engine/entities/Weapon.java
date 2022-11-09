package cegepst.engine.entities;

import cegepst.engine.GameTime;
import cegepst.engine.graphics.MovementAnimations;
import cegepst.engine.graphics.ImageLoader;
import cegepst.engine.graphics.WeaponAnimations;

public abstract class Weapon extends MovableEntity {

    protected final String SPRITE_PATH;
    private final long ATTACK_DURATION = 250;

    private boolean isAttacking = false;
    private long lastAttack = 0l;
    private Dimension hitboxDimension;
    protected WeaponAnimations animations;

    public Weapon(String path, Dimension hitboxDimension, Dimension spriteDimension) {
        SPRITE_PATH = path;
        this.dimension = new Dimension(spriteDimension.getWidth(), spriteDimension.getHeight());
        animations = new WeaponAnimations(SPRITE_PATH, getWidth(), getHeight(), 0, 0);
        this.hitboxDimension =  new Dimension(hitboxDimension.getWidth(), hitboxDimension.getHeight());
    }

    public void updateIsAttacking() {
        isAttacking = GameTime.getCurrentTime() - lastAttack < ATTACK_DURATION;
    }

    public void load(ImageLoader imageLoader){
        loadSpriteSheet(imageLoader);
        loadAnimationFrames();
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
