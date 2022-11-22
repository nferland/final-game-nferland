package cegepst.finalGame.enemies;

import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.MovableEntity;

public abstract class Enemy extends MovableEntity {

    private final ControllableEntity player;

    public Enemy(ControllableEntity player) {
        this.player = player;
    }
}
