package cegepst.finalGame.player;

import cegepst.engine.GameTime;
import cegepst.engine.controls.Direction;
import cegepst.engine.entities.Dimension;
import cegepst.engine.entities.stateMachines.AttackState;
import cegepst.engine.entities.stateMachines.HurtState;
import cegepst.engine.graphics.*;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.controls.MovementController;
import cegepst.finalGame.audio.Sound;
import cegepst.finalGame.weapons.Sword;

import java.awt.*;
import java.util.ArrayList;

public class Player extends ControllableEntity {

    private static final String SPRITE_PATH = "images/hero.png";
    private static final String DASH_PATH = "images/bluescale-dash.png";

    private MovementAnimations walkingAnimations;
    private ArrayList<DashGhost> dashGhosts;
    private Sword sword;
    private long ghostApparitionRate = 20;
    private long lastGhostApparition = 0l;

    public Player(MovementController controller) {
        super(controller);
        this.dimension = new Dimension(32);
        setSpeed(3);
        setDashSpeed(15);
        walkingAnimations = new MovementAnimations(SPRITE_PATH, getWidth(), getHeight(), 0, 0);
        sword = new Sword("images/sword.png", new Dimension(32), new Dimension(32), 6);
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
        drawWeapons(buffer);
        if (isDashing()) {
            buffer.drawImage(Animator.draw(getDirection(), walkingAnimations, walkingAnimations.getDashFrame()),
                    x - Camera.getInstance().getX() , y - Camera.getInstance().getY());
            drawDashGhosts(buffer);
            return;
        }
        buffer.drawImage(Animator.draw(getDirection(), walkingAnimations, walkingAnimations.getCurrentAnimationFrame()),
                x - Camera.getInstance().getX(), y - Camera.getInstance().getY());
        if (hurtState == HurtState.Invulnerable) {
            drawHurt(buffer);
        }
    }

    @Override
    public void update() {
        super.update();
        moveWithController();
        Animator.animate(hasMoved(), walkingAnimations, 1);
        updateDashGhosts();
        updateSword();
    }

    @Override
    protected void die() {

    }

    public void attack() {
        attackState = AttackState.Melee;
        Sound.PLAYER_ATTACK.play();
        sword.attack();
    }

    @Override
    public void hurt(int damage, Direction kbDirection) {
        super.hurt(damage, kbDirection);
        Sound.PLAYER_HURT.play();
    }

    protected void loadSpriteSheet(ImageLoader imageLoader) {
        walkingAnimations.loadSpriteSheet(imageLoader, DASH_PATH);
    }

    protected void loadAnimationFrames() {
        walkingAnimations.loadAnimations();
    }

    private void loadWeapons(ImageLoader imageLoader) {
        sword.load(imageLoader);
    }

    private void updateDashGhosts() {
        if (isDashing()) {
            addDashGhost();
            destroyDeadDashGhosts();
            return;
        }
        dashGhosts.clear();
    }

    private void updateSword() {
        sword.setDirection(getDirection());
        sword.update();
        sword.updatePlacement(this);
    }

    private void drawDashGhosts(Buffer buffer) {
        for (DashGhost dashGhost : dashGhosts) {
            dashGhost.draw(buffer);
        }
    }

    private void drawWeapons(Buffer buffer) {
        if(attackState == AttackState.Melee) {
            sword.draw(buffer);
        }
        if (attackState == AttackState.Range) {
            // TODO : draw range weapon
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
        return SpriteSplicer.spliceSingleSprite(0, getStartY(), getWidth(), getHeight(), walkingAnimations.getBluescale());
    }

    private int getStartY() {
        if (getDirection() == Direction.DOWN) {
            return 0;
        }
        if (getDirection() == Direction.LEFT) {
            return getHeight();
        }
        if (getDirection() == Direction.RIGHT) {
            return getHeight() * 2;
        }
        if (getDirection() == Direction.UP) {
            return getHeight() * 2;
        }
        return 0;
    }

    private void drawHurt(Buffer buffer) {
        buffer.drawRectangle(x - Camera.getInstance().getX(), y - Camera.getInstance().getY(), getWidth(), getHeight(), new Color(255, 0, 0, 75));
    }
}