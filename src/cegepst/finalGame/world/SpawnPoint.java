package cegepst.finalGame.world;

import cegepst.engine.GameTime;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.EnemyRepository;
import cegepst.finalGame.Score;
import cegepst.finalGame.enemies.Lich;
import cegepst.finalGame.enemies.Zombie;

import java.util.Random;

public class SpawnPoint {

    private final int x;
    private final int y;
    private final long spawnDelay;
    private long lastSpawn = 0L;
    private boolean spawnBoss = true;
    private Random rnd;

    public SpawnPoint(int x, int y, long spawnDelay) {
        this.x = x;
        this.y = y;
        this.spawnDelay = spawnDelay;
        rnd = new Random();
    }

    public void update(ControllableEntity player) {
        spawnZombie(player);
        spawnLich(player);
    }

    private void spawnZombie(ControllableEntity player) {
        if (
                GameTime.getCurrentTime() - lastSpawn > spawnDelay &&
                EnemyRepository.getInstance().count() <= 10) {
            createZombie(player);
            lastSpawn = GameTime.getCurrentTime();
        }
    }

    private void spawnLich(ControllableEntity player) {
        if (Score.getInstance().getScore() % 200 == 0 && Score.getInstance().getScore() != 0 && spawnBoss) {
            createLich(player);
            spawnBoss = false;
        }
        if(Score.getInstance().getScore() % 200 != 0 && !spawnBoss) {
            spawnBoss = true;
        }
    }

    private void createLich(ControllableEntity player) {
        new Lich(player, x, y);
    }

    private void createZombie(ControllableEntity player) {
        new Zombie(player, rnd.nextInt(x-20, x+20), rnd.nextInt(y-20, y+20));
    }
}
