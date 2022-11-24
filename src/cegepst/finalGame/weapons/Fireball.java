package cegepst.finalGame.weapons;

import cegepst.engine.GameTime;
import cegepst.engine.controls.Direction;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.Dimension;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.Spell;
import cegepst.engine.entities.stateMachines.SpellState;
import cegepst.engine.graphics.Animator;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.Camera;
import cegepst.engine.graphics.ImageLoader;

public class Fireball extends Spell {

    public Fireball(long lifespan, ImageLoader imageLoader, MovableEntity caster) {
        super("images/fire-Sheet.png", new Dimension(16), new Dimension(16), 6, 6);
        setLifespan(lifespan);
        load(imageLoader);
        setDirection(caster.getDirection());
        setSpeed(2);
        setState(SpellState.Traveling);
        teleport(caster.getX(), caster.getY());
    }

    public void update() {
        super.update();
        updateAnimation();
        move(getDirection());
        updateHitEnemy();
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

    }

    private void updateAnimation() {
        Animator.animate(true, animations, 0);
    }
}
