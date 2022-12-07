package cegepst.finalGame.enemies;

import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.Dimension;
import cegepst.engine.entities.Enemy;
import cegepst.engine.entities.EnemyRepository;
import cegepst.engine.graphics.Animator;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.ImageLoader;
import cegepst.engine.graphics.MovementAnimations;
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
        setHealthPoint(getMaxHealthPoint());
        setDamage(5);
        this.dimension = new Dimension(32);
        movementAnimations = EnemyRepository.getInstance().getLichAnimation();
    }

    public void update() {
        super.update();
        moveTowardPlayer();
        hurtPlayer();
        Animator.animate(hasMoved(), movementAnimations, 1);
        fireballs = new ArrayList<>();
    }



    @Override
    public void draw(Buffer buffer) {

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

    }
}
