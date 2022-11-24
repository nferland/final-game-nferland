package cegepst.engine.entities;

import cegepst.engine.GameTime;
import cegepst.engine.EngineMath;
import cegepst.engine.graphics.ImageLoader;
import cegepst.engine.graphics.WeaponAnimations;

import java.util.ArrayList;

public abstract class Weapon extends StaticEntity {

    protected final String SPRITE_PATH;
    private final long ATTACK_DURATION = 250;

    protected WeaponAnimations animations;
    private boolean isAttacking = false;
    private long lastAttack = 0L;
    private Dimension hitboxDimension;
    private int damage = 1;

    public Weapon(String path, Dimension hitboxDimension, Dimension spriteDimension) {
        SPRITE_PATH = path;
        this.dimension = spriteDimension;
        animations = new WeaponAnimations(SPRITE_PATH, getWidth(), getHeight(), 0, 0);
        this.hitboxDimension =  hitboxDimension;
    }

    public void load(ImageLoader imageLoader){
        loadSpriteSheet(imageLoader);
        loadAnimationFrames();
    }

    public void attack() {
        isAttacking = true;
        lastAttack = GameTime.getCurrentTime();
    }

    public void updatePlacement(MovableEntity master) {
        switch (master.getDirection()) {
            case UP -> teleportUp(master);
            case DOWN -> teleportDown(master);
            case LEFT -> teleportLeft(master);
            case RIGHT -> teleportRight(master);
        }
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    protected void updateIsAttacking() {
        isAttacking = GameTime.getCurrentTime() - lastAttack < ATTACK_DURATION;
    }

    protected void updateHitEnemy(ControllableEntity master){
        ArrayList<Enemy> killedEntities = new ArrayList<>();
        for (Enemy enemy : EnemyRepository.getInstance()) {
            if(enemy.hitBoxIntersectWith(this)) {
                enemy.hurt(damage, master.getDirection());
            }
            if(enemy.isDead()) {
                killedEntities.add(enemy);
            }
        }
        for (Enemy entity: killedEntities) {
            EnemyRepository.getInstance().unregisterEntity(entity);
        }
    }

    protected void teleportUp(MovableEntity master) {
        teleport(master.getX() + topXValue(master), master.getY() - EngineMath.threeQuarterOf(getHitboxHeight()));
    }

    protected void teleportDown(MovableEntity master) {
        teleport(master.getX(), master.getY() + master.getHeight());
    }

    protected void teleportLeft(MovableEntity master) {
        teleport(master.getX() - EngineMath.threeQuarterOf(getHitboxWidth()), master.getY() + sideYValue(master));
    }

    protected void teleportRight(MovableEntity master) {
        teleport(master.getX() + rightXValue(master), master.getY() + sideYValue(master));
    }

    private int sideYValue(MovableEntity master) {
        return (master.getHeight() - EngineMath.threeQuarterOf(getHitboxHeight()));
    }

    private int topXValue(MovableEntity master) {
        return EngineMath.halfOf(master.getWidth() - getHitboxWidth());
    }

    private int rightXValue(MovableEntity master) {
        return master.getWidth() - EngineMath.quarterOf(getHitboxWidth());
    }

}
