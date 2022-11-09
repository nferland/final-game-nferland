package cegepst.finalGame.world;

import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.ImageLoader;
import cegepst.engine.graphics.Camera;

import java.awt.*;
import java.util.ArrayList;

public class World extends StaticEntity {

    private static final String MAP_PATH = "images/demo.png";
    private Image background;
    private ArrayList<Blockade> blockades;
    private int x;
    private int y;

    public World() {
        blockades = new ArrayList<>();
        x = 0;
        y = -64;

        Blockade topBorder = new Blockade(400, 48);
        topBorder.teleport(0, 0);

        Blockade topBorder2 = new Blockade(400, 48);
        topBorder2.teleport(464, 0);

        blockades.add(topBorder);
        blockades.add(topBorder2);
    }

    public void load(ImageLoader imageLoader) {
        background = imageLoader.loadImage(MAP_PATH);
    }

    @Override
    protected void loadSpriteSheet(ImageLoader imageLoader) {

    }

    @Override
    protected void loadAnimationFrames() {

    }

    public void draw(Buffer buffer, Camera camera) {
        buffer.drawImage(background, x - camera.getxOffset(), y - camera.getyOffset());
//        for (Blockade blockade : blockades) {
//            blockade.draw(buffer, camera);
//        }
    }
}
