package cegepst.finalGame.world;

import cegepst.engine.GameTime;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.EnemyRepository;
import cegepst.engine.entities.SpawnPoint;
import cegepst.finalGame.enemies.Zombie;

import java.util.Random;

public class MobSpawnPoint extends SpawnPoint {

    private Random rnd;

    public MobSpawnPoint(int x, int y, long spawnDelay) {
        super(x, y, spawnDelay, 10);
        rnd = new Random();
    }

    @Override
    public void update(ControllableEntity player) {
        spawnZombie(player);
    }

    private void spawnZombie(ControllableEntity player) {
        if (GameTime.getCurrentTime() - getLastSpawn() > getSpawnDelay() &&
                        EnemyRepository.getInstance().count() < maxEnemy &&
                        playerIsNear(player)) {
            createZombie(player);
            setLastSpawn(GameTime.getCurrentTime());
            incrementSpawnedEnemy(1);

        }
    }

    private void createZombie(ControllableEntity player) {
        new Zombie(player, rnd.nextInt(x-20, x+20), rnd.nextInt(y-20, y+20));
    }
}
