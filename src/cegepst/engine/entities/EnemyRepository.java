package cegepst.engine.entities;

import cegepst.engine.entities.physic.CollidableRepository;
import cegepst.engine.graphics.ImageLoader;
import cegepst.engine.graphics.MovementAnimations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class EnemyRepository implements Iterable<Enemy> {

    private static EnemyRepository instance;
    private final List<Enemy> registeredEnemies;
    private final String ZOMBIE_SPRITE_PATH = "images/zombie.png";
    private final String LICH_SPRITE_PATH = "images/lich.png";
    private final MovementAnimations zombieAnimation;
    private final MovementAnimations lichAnimation;

    public static EnemyRepository getInstance() {
        if (instance == null) {
            instance = new EnemyRepository();
        }
        return instance;
    }

    public void registerEntity(Enemy entity) {
        registeredEnemies.add(entity);
    }

    public void registerEntities(Collection<Enemy> entities) {
        registeredEnemies.addAll(entities);
    }

    public void unregisterEntity(Enemy entity) {
        registeredEnemies.remove(entity);
    }

    public void loadAnimations(ImageLoader imageLoader) {
        loadZombieAnimation(imageLoader);
        loadLichAnimation(imageLoader);
    }

    public MovementAnimations getZombieAnimation() {
        return zombieAnimation;
    }

    public MovementAnimations getLichAnimation() {
        return lichAnimation;
    }

    public void unregisterEntities(Collection<Enemy> entities) {
        registeredEnemies.removeAll(entities);
    }

    public int count() {
        return registeredEnemies.size();
    }

    @Override
    public Iterator<Enemy> iterator() {
        return registeredEnemies.iterator();
    }

    private EnemyRepository() {
        registeredEnemies = new ArrayList<>();
        zombieAnimation = new MovementAnimations(ZOMBIE_SPRITE_PATH, 32, 32, 0, 0);
        lichAnimation = new MovementAnimations(LICH_SPRITE_PATH, 32, 32, 0, 0);
    }

    private void loadZombieAnimation(ImageLoader imageLoader) {
        zombieAnimation.loadSpriteSheet(imageLoader);
        zombieAnimation.loadAnimations();
    }

    private void loadLichAnimation(ImageLoader imageLoader) {
        lichAnimation.loadSpriteSheet(imageLoader);
        lichAnimation.loadAnimations();
    }
}
