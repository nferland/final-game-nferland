package cegepst.finalGame.world;

import cegepst.engine.entities.Dimension;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.entities.physic.CollidableRepository;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.ImageLoader;

import java.awt.*;

public class Blockade extends StaticEntity {

    public Blockade(int width, int height) {
        dimension = new Dimension(width, height);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, getWidth(), getHeight(),
                new Color(255, 0, 0, 100));
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
