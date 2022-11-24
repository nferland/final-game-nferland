package cegepst.engine.graphics;

import java.awt.image.BufferedImage;

public class WeaponAnimations extends Animations{

    private BufferedImage spriteSheet;

    public WeaponAnimations(String path, int width, int height, int x, int y) {
        super(path, width, height, x, y, 4);
    }

    @Override
    public void loadSpriteSheet(ImageLoader imageLoader) {
        spriteSheet = imageLoader.loadBufferedImage(SPRITE_PATH);
    }

    @Override
    public void loadAnimations() {
        for (int i = 0; i < animations.length; i++) {
            animations[i] = SpriteSplicer.splice(START_X, START_Y + HEIGTH * i, WIDTH, HEIGTH, spriteSheet);
        }
    }
}
