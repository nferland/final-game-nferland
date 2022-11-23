package cegepst.engine.graphics;

import cegepst.engine.EngineMath;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.Dimension;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.entities.physic.CollidableRepository;
import java.awt.*;

public class Camera extends MovableEntity {

    private static Camera instance;

    private MovableEntity mainEntity;

    public static Camera getInstance() {
        if (instance == null) {
            instance = new Camera();
        }
        return instance;
    }

    private Camera() {
        this.dimension = new Dimension(800, 600);
        teleport(0, 0);
    }

    @Override
    protected void die() {

    }

    public void update(MovableEntity followedEntity, Dimension worldDimension) {
        mainEntity = followedEntity;
        int camX = (mainEntity.getX() - EngineMath.halfOf(mainEntity.getWidth())) - EngineMath.halfOf(getWidth());
        int camY = (mainEntity.getY() - EngineMath.halfOf(mainEntity.getHeight())) - EngineMath.halfOf(getHeight());
        if (camX < 0) {
            camX = 0;
        }
        if (camY < 0) {
            camY = 0;
        }
        if (camX + this.getWidth() > worldDimension.getWidth() ) {
            camX = worldDimension.getWidth() - this.getWidth();
        }
        if (camY + this.getHeight() > worldDimension.getHeight()) {
            camY = worldDimension.getHeight() - this.getHeight();
        }
        teleport(camX, camY);
    }


    public void draw(Buffer buffer, Image world) {
        buffer.drawImage(world, -getX(), -getY());
        for (StaticEntity entity: CollidableRepository.getInstance()) {
            entity.draw(buffer);
        }
        mainEntity.draw(buffer);
    }

    @Override
    public void draw(Buffer buffer) {

    }

    @Override
    public void load(ImageLoader imageLoader) {

    }

    @Override
    protected void loadSpriteSheet(ImageLoader imageLoader) {

    }

    @Override
    protected void loadAnimationFrames() {

    }
}
