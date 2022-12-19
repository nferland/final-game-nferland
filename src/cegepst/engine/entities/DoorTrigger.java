package cegepst.engine.entities;

import cegepst.engine.EngineMath;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.Camera;
import cegepst.finalGame.Score;

import java.awt.*;

public class DoorTrigger extends Trigger {
    private Color color = Color.RED;

    public DoorTrigger(Dimension dimension, int x, int y) {
        super(dimension, x, y);
    }

    @Override
    public void update(ControllableEntity player) {
        if (intersectWith(player)) {
            color = Color.BLUE;
            if(Score.getInstance().hasScoreForNextLevel()) {
                Score.getInstance().nextLevel();
            }
            return;
        }
        color = Color.RED;
    }

    public void draw(Buffer buffer) {
        buffer.drawRectangle(getX() - Camera.getInstance().getX(), getY() - Camera.getInstance().getY(),
                getHeight(), getWidth(), color);
    }

    @Override
    protected boolean intersectWith(MovableEntity entity) {
        if (EngineMath.between(entity.getX(), getX(), getWidth())
                || EngineMath.between(entity.getX() + entity.getWidth(), getX(), getWidth())) {
            return EngineMath.between(entity.getY(), getY(), getHeight())
                    || EngineMath.between(entity.getY() + entity.getHeight(), getY(), getHeight());
        }
        return false;
    }
}
