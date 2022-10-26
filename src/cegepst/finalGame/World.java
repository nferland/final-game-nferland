package cegepst.finalGame;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class World {

    private static final String MAP_PATH = "images/demo.png";
    private Image background;
    private ArrayList<Blockade> blockades;

    public World() {
        blockades = new ArrayList<>();

        Blockade topBorder = new Blockade();
        topBorder.teleport(0, 0);
        topBorder.setDimension(400, 48);

        Blockade topBorder2 = new Blockade();
        topBorder2.teleport(464, 0);
        topBorder2.setDimension(400, 48);

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
