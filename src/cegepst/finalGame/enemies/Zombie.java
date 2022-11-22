package cegepst.finalGame.enemies;

import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.graphics.*;

public class Zombie extends Enemy{
    private final String SPRIT_PATH = "images/zombie.png";

    private MovementAnimations movementAnimations;

    public Zombie(ControllableEntity player) {
        super(player);
    }

    public void update() {
        super.update();
        Animator.animate(hasMoved(), movementAnimations, 1);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(Animator.draw(getDirection(), movementAnimations, movementAnimations.getCurrentAnimationFrame()),
                x - Camera.getInstance().getX(), y - Camera.getInstance().getY());
    }

    @Override
    public void load(ImageLoader imageLoader) {
        loadSpriteSheet(imageLoader);
        loadAnimationFrames();
    }

    @Override
    protected void loadSpriteSheet(ImageLoader imageLoader) {
        movementAnimations.loadSpriteSheet(imageLoader);
    }

    @Override
    protected void loadAnimationFrames() {
        movementAnimations.loadAnimations();
    }
}
