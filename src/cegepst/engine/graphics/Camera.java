package cegepst.engine.graphics;

import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.Dimension;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.entities.physic.CollidableRepository;

import java.awt.image.BufferedImage;

public class Camera extends MovableEntity {

    private static Camera instance;

    private MovableEntity mainEntity;
    private Dimension screenDimension;

    public static Camera getInstance() {
        if (instance == null) {
            instance = new Camera();
        }
        return instance;
    }

    private Camera() {
        this.screenDimension = new Dimension(800, 600);
        this.dimension = new Dimension(800, 600);
        teleport(0, 0);
    }

    public void update(MovableEntity followedEntity) {
        mainEntity = followedEntity;
        int camX = (mainEntity.getX() - mainEntity.getWidth() / 2) - screenDimension.getWidth() / 2;
        int camY = (mainEntity.getY() - mainEntity.getHeight() / 2) - screenDimension.getHeight() / 2;
        if (camX < 0) {
            camX = 0;
        }
        if (camY < 0) {
            camY = 0;
        }
        if (camX > screenDimension.getWidth() ) {
            camX = screenDimension.getWidth() / 2 + mainEntity.getSpeed();
        }
        if (camY > screenDimension.getHeight()) {
            camY = screenDimension.getHeight() / 2 + mainEntity.getSpeed();
        }
        teleport(camX, camY);
    }

    public int getxOffset(StaticEntity entity) {
        return (entity.getWidth() / 2 - mainEntity.getWidth() / 2) - x;
    }

    public int getyOffset(StaticEntity entity) {
        return (entity.getHeight() / 2 - mainEntity.getHeight() / 2) - y;
    }


    public void draw(Buffer buffer, BufferedImage world) {
        buffer.drawImage(world.getSubimage(Camera.getInstance().getX(), Camera.getInstance().getY(), 800, 600), 0, 0);
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

//    private void checkOffset(int deltaX, int deltaY) {
//        if(xOffset < minXOffset) {
//            xOffset -= deltaX;
//        }
//        if (yOffset < minYOffset) {
//            yOffset -= deltaY;
//        }
//    }
}
