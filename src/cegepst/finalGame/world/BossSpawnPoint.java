package cegepst.finalGame.world;

import cegepst.engine.EngineMath;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.SpawnPoint;
import cegepst.finalGame.Score;
import cegepst.finalGame.enemies.Lich;

public class BossSpawnPoint extends SpawnPoint {

    public BossSpawnPoint(int x, int y, long spawnDelay) {
        super(x, y, spawnDelay, 1);
    }

    @Override
    public void update(ControllableEntity player) {
        spawnLich(player);
    }

    private void spawnLich(ControllableEntity player) {
        if (Score.getInstance().getScore() % 100 == 0
                && Score.getInstance().getScore() != 0 && spawnedEnemy < maxEnemy
                && playerIsNear(player)) {
            createLich(player);
            incrementSpawnedEnemy(1);
        }
    }

    private void createLich(ControllableEntity player) {
        new Lich(player, x, y);
    }
}
