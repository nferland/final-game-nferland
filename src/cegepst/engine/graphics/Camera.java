package cegepst.engine.graphics;

import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.Dimension;
import cegepst.engine.entities.StaticEntity;
import cegepst.finalGame.player.Player;

import java.util.ArrayList;

public class Camera {

    private ControllableEntity mainEntity;
    private Dimension screenDimension;
    private int xOffset;
    private int yOffset;

    public Camera( ControllableEntity mainEntity, Dimension screenDimension) {
        this.mainEntity = mainEntity;
        this.screenDimension = screenDimension;
    }

    public void update() {
        centerOnPlayer();
    }

    private void centerOnPlayer() {
        xOffset = mainEntity.getX() - (screenDimension.getWidth() / 2) + (mainEntity.getWidth() / 2);
        yOffset = mainEntity.getY() - (screenDimension.getHeight() / 2) + (mainEntity.getHeight() / 2);
    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }
}
