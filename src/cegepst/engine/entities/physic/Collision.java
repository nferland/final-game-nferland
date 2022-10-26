package cegepst.engine.entities.physic;

import cegepst.engine.controls.Direction;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class Collision {

    private final MovableEntity entity;

    public Collision(MovableEntity entity) {
        this.entity = entity;
    }

    public int getAllowedSpeed(Direction direction) {
        switch (direction) {
            case UP: return getAllowedUpSpeed();
            case DOWN: return getAllowedDownSpeed();
            case LEFT: return getAllowedLeftSpeed();
            case RIGHT: return getAllowedRightSpeed();
        }
        return 0;
    }

    private int getAllowedUpSpeed() {
        return distance(other -> entity.getY() - (other.getY() + other.getHeight()));
    }

    private int getAllowedDownSpeed() {
        return distance(other -> other.getY() - (entity.getY() + entity.getHeight()));
    }

    private int getAllowedLeftSpeed() {
        return distance(other -> entity.getX() - (other.getX() + other.getWidth()));
    }

    private int getAllowedRightSpeed() {
        return distance(other -> other.getX() - (entity.getX() + entity.getWidth()));
    }

    private int distance(DistanceCalculator calculator) {
        Rectangle collisionBound = entity.getHitBox();
        int allowedDistance = entity.getSpeed();
        for (StaticEntity other : CollidableRepository.getInstance()) {
            if (collisionBound.intersects(other.getBounds())) {
                allowedDistance = Math.min(allowedDistance,
                        calculator.calculateWith(other));
            }
        }
        return allowedDistance;
    }

    private interface DistanceCalculator {
        int calculateWith(StaticEntity other);
    }
}
