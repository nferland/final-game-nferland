package cegepst.finalGame.player;

import cegepst.engine.GameTime;
import cegepst.engine.controls.Direction;
import cegepst.engine.entities.Weapon;
import cegepst.engine.graphics.*;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.controls.MovementController;
import cegepst.finalGame.audio.Sound;

import java.awt.*;
import java.util.ArrayList;

public class Player extends ControllableEntity {

    private static final String SPRITE_PATH = "images/hero.png";
    private static final String DASH_PATH = "images/bluescale-dash.png";

    private MovementAnimations walkingAnimations;
    private ArrayList<DashGhost> dashGhosts;
    private ArrayList<Weapon> weapons;
    private long ghostApparitionRate = 20;
    private long lastGhostApparition = 0l;

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        setSpeed(3);
        setDashSpeed(15);
        walkingAnimations = new MovementAnimations(SPRITE_PATH, width, height, 0, 0);
        weapons = new ArrayList<>();
        dashGhosts = new ArrayList<>();
    }

    @Override
    public void load(ImageLoader imageLoader) {
        loadSpriteSheet(imageLoader);
        loadAnimationFrames();
        loadWeapons(imageLoader);
    }

    @Override
    public void draw(Buffer buffer) {
        if (isDashing()) {
            buffer.drawImage(Animator.draw(getDirection(), walkingAnimations, walkingAnimations.getDashFrame()), x, y);
            drawDashGhosts(buffer);
            return;
        }
        buffer.drawImage(Animator.draw(getDirection(), walkingAnimations, walkingAnimations.getCurrentAnimationFrame()), x, y);
    }

    @Override
    public void update() {
        super.update();
        moveWithController();
        Animator.animate(hasMoved(), walkingAnimations);
        updateDashGhosts();
    }

    public void attack() {
        Sound.PLAYER_ATTACK.play();
    }

    protected void loadSpriteSheet(ImageLoader imageLoader) {
        walkingAnimations.loadSpriteSheet(imageLoader, DASH_PATH);
    }

    protected void loadAnimationFrames() {
        walkingAnimations.loadAnimations();
    }

    private void loadWeapons(ImageLoader imageLoader) {
        for (Weapon weapon : weapons) {
            weapon.load(imageLoader);
        }
    }

    private void updateDashGhosts() {
        addDashGhost();
        destroyDeadDashGhosts();
        if (!isDashing()) {
            dashGhosts.clear();
        }
    }

    private void drawDashGhosts(Buffer buffer) {
        for (DashGhost dashGhost : dashGhosts) {
            dashGhost.draw(buffer);
        }
    }

    private void destroyDeadDashGhosts() {
        dashGhosts.removeIf(dashGhost -> !dashGhost.stillAlive());
    }

    private void addDashGhost() {
        if (GameTime.getCurrentTime() - lastGhostApparition >= ghostApparitionRate) {
            dashGhosts.add(new DashGhost(getBluescale(), x, y, GameTime.getCurrentTime()));
            lastGhostApparition = GameTime.getCurrentTime();
        }
    }

    private Image getBluescale() {
        return SpriteSplicer.spliceSingleSprite(0, getStartY(), width, height, walkingAnimations.getBluescale());
    }

    private int getStartY() {
        if (getDirection() == Direction.DOWN) {
            return 0;
        }
        if (getDirection() == Direction.LEFT) {
            return height;
        }
        if (getDirection() == Direction.RIGHT) {
            return height * 2;
        }
        if (getDirection() == Direction.UP) {
            return height * 2;
        }
        return 0;
    }
}