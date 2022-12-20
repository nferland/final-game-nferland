package cegepst.engine.entities;

import cegepst.engine.EngineMath;
import cegepst.engine.controls.Direction;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.Camera;

import java.awt.*;

public abstract class Enemy extends MovableEntity {

    protected final ControllableEntity player;
    private int damage;
    private int scoreValue;

    public Enemy(ControllableEntity player, int scoreValue) {
        this.player = player;
        this.scoreValue = scoreValue;
        EnemyRepository.getInstance().registerEntity(this);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    protected void moveTowardPlayer(int farthest) {
        if ( EngineMath.getDistanceBetween(this, player) < farthest) {
            if(Math.abs(player.getX() - getX()) > Math.abs(player.getY() - getY())) {
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

    protected void drawHealthBar(Buffer buffer) {
        double barWidth = getHealthPoint() /( getMaxHealthPoint() * 1.0);
        buffer.drawRectangle(x - Camera.getInstance().getX(), y - Camera.getInstance().getY() - 5, (int) (barWidth * getWidth()), 3, Color.RED);
    }

    protected void hurtPlayer() {
        if (intersectWithPlayer()) {
            player.hurt(getDamage(), getDirection());
        }
    }

    private void horizontalMovement() {
        if (player.getX() < getX()) {
            moveLeft();
            return;
        }
        moveRight();
    }

    private void verticalMovement() {
        if (player.getY() < getY()) {
            moveUp();
            return;
        }
        moveDown();
    }
}
