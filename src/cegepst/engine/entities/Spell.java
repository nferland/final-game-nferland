package cegepst.engine.entities;

import cegepst.engine.GameTime;
import cegepst.engine.entities.physic.CollidableRepository;
import cegepst.engine.entities.stateMachines.SpellState;
import cegepst.engine.graphics.WeaponAnimations;
import cegepst.finalGame.audio.Sound;

import java.util.ArrayList;

public abstract class Spell extends MovableEntity {

    protected WeaponAnimations animations;
    private Dimension hitboxDimension;
    private int damage = 1;
    private int manaCost;
    private long lifespan;
    private long apparition = 0L;
    private SpellState state;
    private MovableEntity caster;

    public Spell(MovableEntity caster,Dimension hitboxDimension, Dimension spriteDimension, int manaCost, int damage) {
        super();
        this.manaCost = manaCost;
        this.damage = damage;
        this.hitboxDimension = hitboxDimension;
        this.dimension = spriteDimension;
        this.caster = caster;
        state = SpellState.Idle;
        apparition = GameTime.getCurrentTime();
        SpellRepository.getInstance().registerEntity(this);
    }

    public boolean stillActive() {
        return (GameTime.getCurrentTime() - apparition < lifespan) && state != SpellState.Expired;
    }


    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public long getLifespan() {
        return lifespan;
    }

    public void setLifespan(long lifespan) {
        this.lifespan = lifespan;
    }

    public int getHitboxWidth() {
        return hitboxDimension.getWidth();
    }

    public int getHitboxHeight() {
        return hitboxDimension.getHeight();
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public SpellState getState() {
        return state;
    }

    public void setState(SpellState state) {
        this.state = state;
    }

    protected void updateHitEnemy() {
        ArrayList<Enemy> killedEntities = new ArrayList<>();
        for (Enemy enemy : EnemyRepository.getInstance()) {
            if(hitBoxIntersectWith(enemy) && enemy != caster) {
                enemy.hurt(damage, getDirection());
                setState(SpellState.Expired);
                Sound.FIREBALL_HIT.play();
            }
            if(enemy.isDead()) {
                killedEntities.add(enemy);
            }
        }
        for (Enemy entity: killedEntities) {
            entity.die();
        }
    }

    protected void updateHitPlayer(MovableEntity player) {
        if(player != caster) {
            if(hitBoxIntersectWith(player)) {
                player.hurt(damage, getDirection());
            }
        }
    }

    protected void updateHitBlockade() {
        for (StaticEntity entity :
                CollidableRepository.getInstance()) {
            if (hitBoxIntersectWith(entity)) {
                setState(SpellState.Expired);
            }
        }
    }

    public abstract void update(MovableEntity player);
}
