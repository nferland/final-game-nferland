package cegepst.engine.entities;

import cegepst.engine.GameTime;
import cegepst.engine.controls.Direction;
import cegepst.engine.entities.physic.Collision;
import cegepst.engine.graphics.Buffer;
import cegepst.finalGame.audio.Sound;

import java.awt.*;

public abstract class MovableEntity extends StaticEntity {

    private int speed = 1;
    private int dashSpeed = 5;
    private long dashDuration = 150;
    private long dashLastUsage = 0L;
    private Direction direction = Direction.UP;
    private int lastX = Integer.MIN_VALUE;
    private int lastY = Integer.MIN_VALUE;
    private boolean moved = false;
    private Collision collision;

    public abstract void draw(Buffer buffer);

    public MovableEntity() {
        collision = new Collision(this);
    }

    public void update() {
        moved = false;
    }

    public void move() {
        int allowedDistance = collision.getAllowedSpeed(direction);
        x += direction.calculateVelocityX(allowedDistance);
        y += direction.calculateVelocityY(allowedDistance);
        moved = (x != lastX || y != lastY);
        lastX = x;
        lastY = y;
    }

    public boolean hasMoved() {
        return moved;
    }


    public void move(Direction direction) {
        this.direction = direction;
        move();
    }

    public void moveUp() {
        move(Direction.UP);
    }

    public void moveDown() {
        move(Direction.DOWN);
    }

    public void moveLeft() {
        move(Direction.LEFT);
    }

    public void moveRight() {
        move(Direction.RIGHT);
    }

    public Rectangle getHitBox() {
        switch (direction) {
            case UP: return getUpperHitBox();
            case DOWN: return getLowerHitBox();
            case LEFT: return getLeftHitBox();
            case RIGHT: return getRightHitBox();
        }
        return getBounds();
    }

    public boolean hitBoxIntersectWith(StaticEntity other) {
        if (other == null) {
            return false;
        }
        return getHitBox().intersects(other.getBounds());
    }

    public void drawHitBox(Buffer buffer) {
        Rectangle rectangle = getHitBox();
        Color color = new Color(255, 0, 0, 200);
        buffer.drawRectangle(rectangle.x, rectangle.y,
                rectangle.width, rectangle.height, color);
    }

    public void dash() {
        dashLastUsage = GameTime.getCurrentTime();
        Sound.DASH.play();
    }

    public boolean isDashing() {
        return (GameTime.getCurrentTime() - dashLastUsage < dashDuration);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        if(isDashing()){
            return dashSpeed;
        }
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDashSpeed(int strength) {
        dashSpeed = strength;
    }

    public void setDashDuration(long dashDuration) {
        this.dashDuration = dashDuration;
    }

    private Rectangle getUpperHitBox() {
        return new Rectangle(x, y - speed, getWidth(), speed);
    }

    private Rectangle getLowerHitBox() {
        return new Rectangle(x, y + getHeight(), getWidth(), speed);
    }

    private Rectangle getLeftHitBox() {
        return new Rectangle(x - speed, y, speed, getHeight());
    }

    private Rectangle getRightHitBox() {
        return new Rectangle(x + getWidth(), y, speed, getHeight());
    }

}
