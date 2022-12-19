package cegepst.finalGame.world;

import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.Dimension;
import cegepst.engine.entities.DoorTrigger;
import cegepst.engine.entities.SpawnPoint;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.ImageLoader;
import cegepst.finalGame.Score;
import cegepst.finalGame.player.Player;

import java.awt.*;
import java.util.ArrayList;

public class World {

    private static final String MAP_PATH = "maps/map.png";
    private Image background;
    private ArrayList<Blockade> blockades;
    private ArrayList<SpawnPoint> spawnPoints;
    private Dimension dimension;
    private DoorTrigger doorTrigger;
    private int x;
    private int y;

    public World(Dimension dimension) {
        blockades = new ArrayList<>();
        spawnPoints = new ArrayList<>();
        x = 0;
        y = 0;
        this.dimension = dimension;
        initializeDoorTrigger();
        createBlockades();
        createSpawnPoints();
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

    public void updateTrigger(Player player) {
        doorTrigger.update(player);
    }

    public void drawTriggerInfo(Buffer buffer) {
        int neededScore =  500 * (Score.getInstance().getLevel());
        doorTrigger.draw(buffer, neededScore);
    }

    private void initializeDoorTrigger() {
        doorTrigger = new DoorTrigger(new Dimension(32, 64), 1744, 368);
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
        createBossSpawnPoint(1100, 1500);
        createMobSpawnPoint(900, 300);
        createMobSpawnPoint(450, 950);
    }

    private void createMobSpawnPoint(int x, int y) {
        spawnPoints.add(new MobSpawnPoint(x, y, 3000));
    }

    private void createBossSpawnPoint(int x, int y) {
        spawnPoints.add(new BossSpawnPoint(x, y, 0));
    }
}
