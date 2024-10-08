package cegepst.finalGame.player;

import cegepst.engine.GameTime;
import cegepst.engine.controls.Direction;
import cegepst.engine.entities.Dimension;
import cegepst.engine.entities.SpellRepository;
import cegepst.engine.entities.stateMachines.AttackState;
import cegepst.engine.entities.stateMachines.HurtState;
import cegepst.engine.graphics.*;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.controls.MovementController;
import cegepst.finalGame.Score;
import cegepst.finalGame.audio.Sound;
import cegepst.finalGame.weapons.Fireball;
import cegepst.finalGame.weapons.IceBurst;
import cegepst.finalGame.weapons.Sword;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Player extends ControllableEntity {

    private static final String SPRITE_PATH = "images/hero.png";
    private static final String DASH_PATH = "images/bluescale-dash.png";
    private final Sound[] ATTACK_SOUNDS = {Sound.PLAYER_ATTACK1, Sound.PLAYER_ATTACK2, Sound.PLAYER_ATTACK3};

    private MovementAnimations walkingAnimations;
    private ArrayList<DashGhost> dashGhosts;
    private Sword sword;
    private long ghostApparitionRate = 20;
    private long lastGhostApparition = 0l;
    private Mana mana;
    private long fireballLifeSpan = 2000;
    private long iceburstLifeSpan = 1500;
    private int attackSoundIndex = 0;

    public Player(MovementController controller) {
        super(controller);
        this.dimension = new Dimension(32);
        setSpeed(3);
        setNormalSpeed(3);
        setDashSpeed(15);
        walkingAnimations = new MovementAnimations(SPRITE_PATH, getWidth(), getHeight(), 0, 0);
        sword = new Sword("images/sword.png", new Dimension(32), new Dimension(32), 6);
        dashGhosts = new ArrayList<>();
        mana = new Mana( 20, 3);
        setMaxHealthPoint(30);
        setHealthPoint(getMaxHealthPoint());
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
        attackState = attackState.nextState(sword.isAttacking());
        moveWithController();
        Animator.animate(hasMoved(), walkingAnimations, 1);
        updateDashGhosts();
        updateSword();
        mana.update();
    }

    @Override
    protected void die() {
        Sound.PLAYER_DEATH.play();
    }

    public void attack() {
        attackState = AttackState.Melee;
        ATTACK_SOUNDS[attackSoundIndex].play();
        updateIndex();
        sword.attack();
    }

    public void cast() {
        if (Fireball.MANA_COST <= mana.getManaPoint()) {
            Fireball fireball = new Fireball(fireballLifeSpan, this);
            mana.reduceMana(fireball.getManaCost());
        }
    }

    public void burst() {
        if (IceBurst.MANA_COST <= mana.getManaPoint()) {
            IceBurst iceBurst = new IceBurst(iceburstLifeSpan, this);
            mana.reduceMana(iceBurst.getManaCost());
        }
    }

    @Override
    public void hurt(int damage, Direction kbDirection) {
        super.hurt(damage, kbDirection);
        if (hurtState != HurtState.Invulnerable) {
            Sound.PLAYER_HURT.play();
        }
        if (hurtState == HurtState.Dead) {
            die();
        }
    }

    public HurtState getHurtState() {
        return hurtState;
    }

    public void nextLevel() {
        teleport(120, 120);
        resetHealth();
        resetMana();
    }

    public double getMaxManaPoint() {
        return mana.getMaxManaPoint();
    }


    public void setMaxManaPoint(int maxManaPoint) {
        mana.setMaxManaPoint(maxManaPoint);
    }

    public double getManaPoint() {
        return mana.getManaPoint();
    }

    public void setManaPoint(int manaPoint) {
        mana.setManaPoint(manaPoint);
    }

    protected void loadSpriteSheet(ImageLoader imageLoader) {
        walkingAnimations.loadSpriteSheet(imageLoader, DASH_PATH);
    }

    private void resetHealth() {
        setMaxHealthPoint(getMaxHealthPoint() + Score.getInstance().getLevel() * 2);
        setHealthPoint(getMaxHealthPoint());
    }

    private void resetMana() {
        setMaxManaPoint((int) (getMaxManaPoint() + Score.getInstance().getLevel() * 2));
        setManaPoint((int) getMaxManaPoint());
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
        sword.update(this);
        sword.updatePlacement(this);
    }

    private void updateIndex() {
        attackSoundIndex++;
        if (attackSoundIndex > ATTACK_SOUNDS.length - 1) {
            attackSoundIndex = 0;
        }
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