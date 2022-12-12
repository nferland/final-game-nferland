package cegepst.engine;

import cegepst.engine.entities.StaticEntity;
import cegepst.engine.entities.SpawnPoint;

public class EngineMath {

    public static int quarterOf(int value) {
        return value / 4;
    }

    public static int halfOf(int value) {
        return value / 2;
    }

    public static int threeQuarterOf(int value) {
        return (3 * value) / 4;
    }

    public static int squareOf(int value) {
        return value * value;
    }

    public static int getDistanceBetween(StaticEntity entity1, StaticEntity entity2) {
        int deltaX = Math.abs(entity1.getX() - entity2.getX());
        int deltaY = Math.abs(entity1.getY() - entity2.getY());
        return (int) Math.floor(Math.sqrt(squareOf(deltaX) + squareOf(deltaY)));
    }

    public static int getDistanceBetween(SpawnPoint spawnPoint, StaticEntity entity) {
        int deltaX = Math.abs(entity.getX() - spawnPoint.getX());
        int deltaY = Math.abs(entity.getY() - spawnPoint.getY());
        return (int) Math.floor(Math.sqrt(squareOf(deltaX) + squareOf(deltaY)));
    }
}
