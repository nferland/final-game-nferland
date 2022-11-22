package cegepst.engine.entities;

import cegepst.engine.controls.Direction;
import cegepst.engine.controls.MovementController;

public abstract class ControllableEntity extends MovableEntity {

    private final MovementController controller;
    private int maxHealthPoint = 10;
    private int healthPoint = maxHealthPoint;

    public ControllableEntity(MovementController controller) {
        this.controller = controller;
    }

    public void moveWithController() {
        if (isDashing()){
            move(this.getDirection());
            return;
        }
        Direction direction = controller.getMoveDirection();
        if (direction != null) {
            move(direction);
        }
    }

    public void hurt(int damage) {
        healthPoint -= damage;
        if (healthPoint <= 0) {
            healthPoint = 0;
            die();
        }
    }

    public void hurt(int damage, Direction kbDirection) {
        hurt(damage);
        knockBack(kbDirection);
    }

    public int getMaxHealthPoint() {
        return maxHealthPoint;
    }

    public void setMaxHealthPoint(int maxHealthPoint) {
        this.maxHealthPoint = maxHealthPoint;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    private void die() {
        // TODO: kill the player and restart game
    }

    private void knockBack(Direction direction) {
        switch (direction){
            case UP -> teleport(getX(), getY() - 8);
            case LEFT -> teleport(getX() - 8, getY());
            case DOWN -> teleport(getX(), getY() + 8);
            case RIGHT -> teleport(getX() + 8, getY());
        }
    }
}
