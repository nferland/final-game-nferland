package cegepst.engine.entities;

import cegepst.engine.entities.physic.CollidableRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class EnemyRepository implements Iterable<Enemy> {

    private static EnemyRepository instance;
    private final List<Enemy> registeredEnemies;

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
    }
}
