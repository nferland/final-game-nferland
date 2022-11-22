package cegepst.finalGame.enemies;

import cegepst.engine.EngineMath;
import cegepst.engine.controls.Direction;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.MovableEntity;

public abstract class Enemy extends MovableEntity {

    private final ControllableEntity player;
    private int damage;

    public Enemy(ControllableEntity player) {
        this.player = player;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    protected void moveTowardPlayer() {
        if (EngineMath.getDistanceBetween(this, player) < 700) {
            if(player.getX() > getX() || player.getX() < getX()) {
                horizontalMovement();
                if (hasMoved()) {
                    return;
                }
            }
            verticalMovement();
        }
    }

    protected boolean intersectWithPlayer() {
        return hitBoxIntersectWith(player);
    }

    protected void hurtPlayer() {
        if (intersectWithPlayer()) {
            player.hurt(getDamage(), getDirection());
        }
    }

    private void horizontalMovement() {
        if (player.getX() < getX()) {
            if (getCollision().getAllowedSpeed(Direction.LEFT) > 0) {
                moveLeft();
            }
            return;
        }
        if (getCollision().getAllowedSpeed(Direction.RIGHT) > 0) {
            moveRight();
        }
    }

    private void verticalMovement() {
        if (player.getY() < getY()) {
            if (getCollision().getAllowedSpeed(Direction.UP) > 0) {
                moveUp();
            }
            return;
        }
        if (getCollision().getAllowedSpeed(Direction.DOWN) > 0) {
            moveDown();
        }
    }
}
