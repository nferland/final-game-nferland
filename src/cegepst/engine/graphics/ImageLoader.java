package cegepst.engine.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    public Image loadImage(String path) {
        Image image = null;
        try {
            image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(path));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return image;
    }

    public BufferedImage loadBufferedImage(String path) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(path));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return bufferedImage;
    }
}
