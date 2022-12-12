package cegepst.finalGame.weapons;

import cegepst.engine.entities.*;
import cegepst.engine.entities.stateMachines.SpellState;
import cegepst.engine.graphics.Animator;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.Camera;
import cegepst.engine.graphics.ImageLoader;
import cegepst.finalGame.audio.Sound;

public class Fireball extends Spell {

    public static final int MANA_COST = 6;

    public Fireball(long lifespan, MovableEntity caster) {
        super(caster, new Dimension(16), new Dimension(16), MANA_COST, 6);
        animations = SpellRepository.getInstance().getFireballAnimations();
        setLifespan(lifespan);
        setDirection(caster.getDirection());
        setSpeed(4);
        setState(SpellState.Traveling);
        teleport(caster.getX(), caster.getY());
        Sound.FIREBALL.play();
    }

    @Override
    public void update(MovableEntity player) {
        super.update();
        updateAnimation();
        move(getDirection());
        updateHitEnemy();
        updateHitPlayer(player);
        updateHitBlockade();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(Animator.draw(getDirection(), animations, animations.getCurrentAnimationFrame()),
                x - Camera.getInstance().getX(), y - Camera.getInstance().getY());
    }

    @Override
    public void load(ImageLoader imageLoader) {
        loadSpriteSheet(imageLoader);
        loadAnimationFrames();
    }

    @Override
    protected void loadSpriteSheet(ImageLoader imageLoader) {
        animations.loadSpriteSheet(imageLoader);
    }

    @Override
    protected void loadAnimationFrames() {
        animations.loadAnimations();
    }

    @Override
    protected void die() {
        Sound.FIREBALL_HIT.play();
        SpellRepository.getInstance().unregisterEntity(this);
    }

    private void updateAnimation() {
        Animator.animate(true, animations, 0);
    }
}
