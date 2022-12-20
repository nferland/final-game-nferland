package cegepst.engine.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteSplicer {

    public static Image[] splice(int startX, int startY, int width, int heigth, BufferedImage bufferedImage) {
        Image[] frames = new Image[3];
        for (int i = 0; i < frames.length; i++) {
            frames[i] = bufferedImage.getSubimage(startX + width * i, startY, width, heigth);

        }
        return frames;
    }

    public static Image spliceSingleSprite(int startX, int startY, int width, int heigth, BufferedImage bufferedImage) {
        return bufferedImage.getSubimage(startX, startY, width, heigth);
    }
}
