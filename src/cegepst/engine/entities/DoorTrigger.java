package cegepst.engine.entities;

import cegepst.engine.EngineMath;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.Camera;
import cegepst.finalGame.Score;

import java.awt.*;

public class DoorTrigger extends Trigger {
    private boolean intersectWithPlayer = false;

    public DoorTrigger(Dimension dimension, int x, int y) {
        super(dimension, x, y);
    }

    @Override
    public void update(ControllableEntity player) {
        intersectWithPlayer = intersectWith(player);
        if (intersectWithPlayer) {
            if(Score.getInstance().hasScoreForNextLevel()) {
                Score.getInstance().nextLevel();
            }
        }
    }

    public void draw(Buffer buffer, int neededScore) {
        if (intersectWithPlayer && neededScore > Score.getInstance().getScore()) {
            buffer.drawRectangle(getX() - Camera.getInstance().getX() - 64, getY() - 48 - Camera.getInstance().getY(),
                    160, 44, new Color(0,0,0, 175));
            buffer.drawString("You need a score of " + neededScore,
                    getX() - Camera.getInstance().getX() - 55, getY() - 32 - Camera.getInstance().getY(), Color.WHITE);
            buffer.drawString(" to pass to the next level",
                    getX() - Camera.getInstance().getX() - 55, getY() - 16 - Camera.getInstance().getY(), Color.WHITE);
        }
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
