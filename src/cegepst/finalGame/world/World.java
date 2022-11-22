package cegepst.finalGame.world;

import cegepst.engine.entities.Dimension;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.ImageLoader;
import cegepst.engine.graphics.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class World {

    private static final String MAP_PATH = "maps/outdoor.png";
    private Image background;
    private ArrayList<Blockade> blockades;
    private Dimension dimension;
    private int x;
    private int y;

    public World(Dimension dimension) {
        blockades = new ArrayList<>();
        x = 0;
        y = 0;
        this.dimension = dimension;

        Blockade topBorder = new Blockade(2400, 48);
        topBorder.teleport(0, 0);

        Blockade bottomBorder = new Blockade(2400, 32);
        bottomBorder.teleport(0, 928);

        Blockade leftBorder = new Blockade(32, 960);
        leftBorder.teleport(0, 0);

        Blockade rightBorder = new Blockade(32, 960);
        rightBorder.teleport(2368, 0);

        blockades.add(topBorder);
        blockades.add(bottomBorder);
        blockades.add(leftBorder);
        blockades.add(rightBorder);
    }

    public void load(ImageLoader imageLoader) {
        background = imageLoader.loadImage(MAP_PATH);
    }

    public Image getBackground() {
        return background;
    }

    public Dimension getDimension() {
        return dimension;
    }
}
