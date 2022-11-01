package cegepst.finalGame;

import cegepst.engine.entities.Weapon;
import cegepst.engine.graphics.MovementAnimations;
import cegepst.engine.graphics.Animator;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.controls.MovementController;
import cegepst.engine.graphics.ImageLoader;
import cegepst.finalGame.audio.Sound;

import java.util.ArrayList;

public class Player extends ControllableEntity {

    private static final String SPRITE_PATH = "images/hero.png";

    private MovementAnimations movementAnimations;
    private ArrayList<Weapon> weapons;

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        setSpeed(3);
        setDashStrength(15);
        movementAnimations = new MovementAnimations(SPRITE_PATH, width, height, 0, 0);
        weapons = new ArrayList<>();
    }

    @Override
    public void load(ImageLoader imageLoader){
        loadSpriteSheet(imageLoader);
        loadAnimationFrames();
        loadWeapons(imageLoader);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(Animator.draw(getDirection(), movementAnimations, movementAnimations.getCurrentAnimationFrame()), x, y);
    }

    @Override
    public void update() {
        super.update();
        moveWithController();
        Animator.animate(hasMoved(), movementAnimations);
    }

    public void attack() {
        Sound.PLAYER_ATTACK.play();
    }

    protected void loadSpriteSheet(ImageLoader imageLoader) {
        movementAnimations.loadSpriteSheet(imageLoader);
    }

    protected void loadAnimationFrames() {
        movementAnimations.loadAnimations();
    }

    private void loadWeapons(ImageLoader imageLoader) {
        for (Weapon weapon : weapons) {
            weapon.load(imageLoader);
        }
    }
}
