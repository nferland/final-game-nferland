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
        createBlockades();
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

    private void createBlockades() {
        createBlockade(new Dimension(2400, 48), 0, 0);
        createBlockade(new Dimension(2400, 32), 0, 928);
        createBlockade(new Dimension(32, 960), 0, 0);
        createBlockade(new Dimension(32, 960), 2368, 0);
    }

    private void createBlockade(Dimension dimension, int x, int y) {
        Blockade border = new Blockade(dimension);
        border.teleport(y, y);
        blockades.add(border);
    }
}
