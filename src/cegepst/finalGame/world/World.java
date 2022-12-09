package cegepst.finalGame.world;

import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.Dimension;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.ImageLoader;
import cegepst.engine.graphics.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class World {

    private static final String MAP_PATH = "maps/map.png";
    private Image background;
    private ArrayList<Blockade> blockades;
    private ArrayList<SpawnPoint> spawnPoints;
    private Dimension dimension;
    private int x;
    private int y;

    public World(Dimension dimension) {
        blockades = new ArrayList<>();
        spawnPoints = new ArrayList<>();
        x = 0;
        y = 0;
        this.dimension = dimension;
        createBlockades();
//        createSpawnPoints();
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

    public ArrayList<SpawnPoint> getSpawnPoints() {
        return spawnPoints;
    }

    public void drawBlockades(Buffer buffer) {
        for (Blockade blockade : blockades) {
            blockade.draw(buffer);
        }
    }

    private void createBlockades() {
        createBlockade(new Dimension(1920, 108), 0, 0);
        createBlockade(new Dimension(1920, 64), 0, 1856);
        createBlockade(new Dimension(96, 1920), 0, 0);
        createBlockade(new Dimension(64, 1920), 1856, 0);
        createBlockade(new Dimension(64, 108), 96,352);
        createBlockade(new Dimension(64, 108), 256, 352);
        createBlockade(new Dimension(192, 560), 320, 64);
        createBlockade(new Dimension(96, 128), 96, 576);
        createBlockade(new Dimension(64, 48), 256, 576);
        createBlockade(new Dimension(160, 416), 192, 704);
        createBlockade(new Dimension(96, 272), 416, 608);
        createBlockade(new Dimension(416, 32), 352, 1120);
        createBlockade(new Dimension(160, 112), 512, 768);
        createBlockade(new Dimension(96, 160), 576,880);
        createBlockade(new Dimension(64, 1024), 768, 832);
        createBlockade(new Dimension(464, 144), 832, 832);
        createBlockade(new Dimension(128, 880), 1280, 576);
        createBlockade(new Dimension(384, 384), 1280, 108);
        createBlockade(new Dimension(192, 112), 1664, 256);
        createBlockade(new Dimension(160, 416), 1504, 492);
        createBlockade(new Dimension(160, 928), 1504, 992);
        createBlockade(new Dimension(192, 32), 1664, 1120);
        createBlockade(new Dimension(128, 80), 1376, 1504);
    }

    private void createBlockade(Dimension dimension, int x, int y) {
        Blockade border = new Blockade(dimension);
        border.teleport(x, y);
        blockades.add(border);
    }

    private void createSpawnPoints() {
        createSpawnPoint(500, 500);
        createSpawnPoint(50, 450);
    }

    private void createSpawnPoint(int x, int y) {
        spawnPoints.add(new SpawnPoint(x, y, 3000));
    }
}
