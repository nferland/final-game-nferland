package cegepst.finalGame.weapons;

import cegepst.engine.EngineMath;
import cegepst.engine.controls.Direction;
import cegepst.engine.entities.Dimension;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.Spell;
import cegepst.engine.entities.SpellRepository;
import cegepst.engine.entities.stateMachines.SpellState;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.Camera;
import cegepst.engine.graphics.ImageLoader;
import cegepst.finalGame.audio.Sound;

import java.awt.*;

public class IceBurst extends Spell {

    public static final int MANA_COST = 8;

    public IceBurst(long lifespan, MovableEntity caster ) {
        super(caster, new Dimension(96), new Dimension(96), MANA_COST, 10);
//        animations = SpellRepository.getInstance().getIceburstAnimation();
        teleport(caster.getX() + EngineMath.halfOf(caster.getWidth()) - EngineMath.halfOf(getWidth()),
                caster.getY() + EngineMath.halfOf(caster.getHeight()) - EngineMath.halfOf(getHeight()));
        setLifespan(lifespan);
        setState(SpellState.Idle);
        setDirection(Direction.NONE);
        Sound.ICE_BURST.play();
    }

    @Override
    public void update(MovableEntity player) {
        super.update();
        updateHitEnemy();
        setState(SpellState.Idle);
    }

    @Override
    public void draw(Buffer buffer) {
        Rectangle bounds = getBounds();
//        buffer.drawImage(Animator.draw(getDirection(), animations, animations.getCurrentAnimationFrame()),
//                x - Camera.getInstance().getX(), y - Camera.getInstance().getY());
        buffer.drawRectangle(x - Camera.getInstance().getX(), y - Camera.getInstance().getY(), getWidth(), getHeight(), Color.CYAN);
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

    @Override
    protected void die() {
        SpellRepository.getInstance();
    }
}
