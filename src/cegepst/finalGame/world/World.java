package cegepst.finalGame.world;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.ImageLoader;

import java.awt.*;
import java.util.ArrayList;

public class World {

    private static final String MAP_PATH = "images/demo.png";
    private Image background;
    private ArrayList<Blockade> blockades;

    public World() {
        blockades = new ArrayList<>();

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

    public void draw(Buffer buffer) {
        buffer.drawImage(background, 0, -64);
        //for (Blockade blockade : blockades) {
        //    blockade.draw(buffer);
        //}
    }
}
