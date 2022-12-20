package cegepst.engine.entities;

import cegepst.engine.EngineMath;
import cegepst.engine.entities.ControllableEntity;

public abstract class SpawnPoint {

    private final long spawnDelay;
    private long lastSpawn = 0L;
    protected final int x;
    protected final int y;
    protected final int maxEnemy;
    protected int spawnedEnemy = 0;

    public SpawnPoint(int x, int y, long spawnDelay, int maxEnemy) {
        this.x = x;
        this.y = y;
        this.spawnDelay = spawnDelay;
        this.maxEnemy = maxEnemy;
    }

    public abstract void update(ControllableEntity player);

    public long getSpawnDelay() {
        return spawnDelay;
    }

    public void setLastSpawn(long lastSpawn) {
        this.lastSpawn = lastSpawn;
    }

    public long getLastSpawn() {
        return lastSpawn;
    }

    public int getMaxEnemy() {
        return maxEnemy;
    }

    public int getSpawnedEnemy() {
        return spawnedEnemy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incrementSpawnedEnemy(int spawnedEnemy) {
        this.spawnedEnemy += spawnedEnemy;
    }

    protected boolean playerIsNear(ControllableEntity player) {
        return EngineMath.getDistanceBetween(this, player) < 600;
    }
}
