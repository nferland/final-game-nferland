package cegepst.finalGame;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.entities.physic.CollidableRepository;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.ImageLoader;

import java.awt.*;

public class Blockade extends StaticEntity {

    public Blockade() {
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height,
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
