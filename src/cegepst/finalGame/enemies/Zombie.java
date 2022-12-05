package cegepst.finalGame.enemies;

import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.Dimension;
import cegepst.engine.entities.Enemy;
import cegepst.engine.entities.EnemyRepository;
import cegepst.engine.entities.stateMachines.HurtState;
import cegepst.engine.graphics.*;
import cegepst.finalGame.audio.Sound;

public class Zombie extends Enemy {

    private MovementAnimations movementAnimations;

    public Zombie(ControllableEntity player, int x, int y) {
        super(player);
        setSpeed(1);
        teleport(x, y);
        setDamage(2);
        setMaxHealthPoint(10);
        setHealthPoint(getMaxHealthPoint());
        this.dimension = new Dimension(32);
        movementAnimations = EnemyRepository.getInstance().getZombieAnimation();
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
    }

    @Override
    protected void loadSpriteSheet(ImageLoader imageLoader) {
    }

    @Override
    protected void loadAnimationFrames() {
    }

}
