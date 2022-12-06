package cegepst.finalGame.world;

import cegepst.engine.GameTime;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.EnemyRepository;
import cegepst.finalGame.enemies.Zombie;

import java.util.Random;

public class SpawnPoint {

    private final int x;
    private final int y;
    private final long spawnDelay;
    private long lastSpawn = 0L;
    private Random rnd;

    public SpawnPoint(int x, int y, long spawnDelay) {
        this.x = x;
        this.y = y;
        this.spawnDelay = spawnDelay;
        rnd = new Random();
    }

    public void update(ControllableEntity player, ImageLoader imageLoader) {
        if(
    public void update(ControllableEntity player) {
        if (GameTime.getCurrentTime() - lastSpawn > spawnDelay && EnemyRepository.getInstance().count() <= 10) {
            createZombie(player);
            lastSpawn = GameTime.getCurrentTime();
        }
    }

    private void createZombie(ControllableEntity player, ImageLoader imageLoader) {
        Zombie zombie = new Zombie(player, rnd.nextInt(x-2, x+3), rnd.nextInt(y-2, y+3));
//    private void createLich(ControllableEntity player) {
//        Lich lich = new Lich(player, );
        EnemyRepository.getInstance().registerEntity(zombie);

    }
}
