package cegepst.finalGame.enemies;

import cegepst.engine.EngineMath;
import cegepst.engine.controls.Direction;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.Dimension;
import cegepst.engine.entities.Enemy;
import cegepst.engine.entities.EnemyRepository;
import cegepst.engine.entities.stateMachines.HurtState;
import cegepst.engine.graphics.*;
import cegepst.finalGame.Score;
import cegepst.finalGame.audio.Sound;
import cegepst.finalGame.weapons.Fireball;

import java.util.ArrayList;

public class Lich extends Enemy {

    private MovementAnimations movementAnimations;
    private ArrayList<Fireball> fireballs;
    private long fireballLifeSpan = 2000;


    public Lich(ControllableEntity player, int y, int x) {
        super(player, 150);
        setSpeed(2);
        setMaxHealthPoint(30);
        teleport(x, y);
        setHealthPoint(getMaxHealthPoint());
        setDamage(5);
        this.dimension = new Dimension(32);
        movementAnimations = EnemyRepository.getInstance().getLichAnimation();
    }

    public void update() {
        super.update();
        moveTowardPlayer(750);
        hurtPlayer();
        Animator.animate(hasMoved(), movementAnimations, 1);
        fireballs = new ArrayList<>();
    }

@Override
    public void hurt(int damage, Direction kbDirection) {
        if (hurtState != HurtState.Invulnerable){
            Sound.LICH_HURT.play();
        }
        super.hurt(damage, kbDirection);

    }



    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(Animator.draw(getDirection(), movementAnimations, movementAnimations.getCurrentAnimationFrame()),
                x - Camera.getInstance().getX(), y - Camera.getInstance().getY());
        drawHealthBar(buffer);
    }

    @Override
    public void load(ImageLoader imageLoader) {

    }

    @Override
    protected void hurtPlayer() {
        if (player.getX() == getX()) {
            if (player.getY() < getY()) {
                fireballs.add(new Fireball(fireballLifeSpan, this));
            }
        }
    }

    @Override
    protected void loadSpriteSheet(ImageLoader imageLoader) {

    }

    @Override
    protected void loadAnimationFrames() {

    }

    @Override
    protected void die() {
        hurtState = HurtState.Dead;
        EnemyRepository.getInstance().unregisterEntity(this);
        Sound.LICH_DEATH.play();
        Score.getInstance().increment(getScoreValue());
    }

}
