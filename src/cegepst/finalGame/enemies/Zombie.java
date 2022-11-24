package cegepst.finalGame.enemies;

import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.Dimension;
import cegepst.engine.entities.Enemy;
import cegepst.engine.entities.EnemyRepository;
import cegepst.engine.entities.stateMachines.HurtState;
import cegepst.engine.graphics.*;
import cegepst.finalGame.audio.Sound;

public class Zombie extends Enemy {
    private final String SPRITE_PATH = "images/zombie.png";

    private MovementAnimations movementAnimations;

    public Zombie(ControllableEntity player, int x, int y) {
        super(player);
        setSpeed(1);
        teleport(x, y);
        setDamage(1);
        setMaxHealthPoint(6);
        setHealthPoint(6);
        this.dimension = new Dimension(32);
        movementAnimations = new MovementAnimations(SPRITE_PATH, getWidth(), getHeight(), 0, 0);
    }

    public void update() {
        super.update();
        moveTowardPlayer();
        hurtPlayer();
        Animator.animate(hasMoved(), movementAnimations, 1);
    }

    @Override
    protected void die() {
        hurtState = HurtState.Dead;
        EnemyRepository.getInstance().unregisterEntity(this);
        Sound.ZOMBIE_DEATH.play();
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
