package cegepst.finalGame;

import cegepst.engine.graphics.AnimationFrames;
import cegepst.engine.graphics.Animator;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.controls.Direction;
import cegepst.engine.controls.MovementController;
import cegepst.engine.graphics.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends ControllableEntity {

    private static final String SPRITE_PATH = "images/player.png";

    private AnimationFrames animationFrames;

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        setSpeed(3);
        animationFrames = new AnimationFrames("images/player.png", width, height, 0, 128);
    }


    public void load(ImageLoader imageLoader){
        loadSpriteSheet(imageLoader);
        loadAnimationFrames();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(Animator.draw(getDirection(), animationFrames, animationFrames.getCurrentAnimationFrame()), x, y);
    }

    @Override
    public void update() {
        super.update();
        moveWithController();
        Animator.animate(hasMoved(), animationFrames);
    }

    private void loadSpriteSheet(ImageLoader imageLoader) {
        animationFrames.loadSpriteSheet(imageLoader);
    }

    private void loadAnimationFrames() {
        animationFrames.loadAnimations();
    }
}
