package cegepst.finalGame.weapons;

import cegepst.engine.entities.Dimension;
import cegepst.engine.entities.Weapon;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.ImageLoader;

public class Spell extends Weapon {

    private int manaCost;
    private long lifespan;

    public Spell(String path, Dimension hitboxDimension, Dimension spriteDimension, int manaCost, int damage) {
        super(path, hitboxDimension, spriteDimension);
        this.manaCost = manaCost;
        setDamage(damage);
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    @Override
    public void draw(Buffer buffer) {

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
