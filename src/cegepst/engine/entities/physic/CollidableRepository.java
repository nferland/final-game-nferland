package cegepst.engine.entities.physic;

import cegepst.engine.entities.StaticEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CollidableRepository implements Iterable<StaticEntity> {

    private static CollidableRepository instance;
    private final List<StaticEntity> registeredCollidableEntities;

    public static CollidableRepository getInstance() {
        if (instance == null) {
            instance = new CollidableRepository();
        }
        return instance;
    }

    public void registerEntity(StaticEntity entity) {
        registeredCollidableEntities.add(entity);
    }

    public void registerEntities(Collection<StaticEntity> entities) {
        registeredCollidableEntities.addAll(entities);
    }

    public void unregisterEntity(StaticEntity entity) {
        registeredCollidableEntities.remove(entity);
    }

    public void unregisterEntities(Collection<StaticEntity> entities) {
        registeredCollidableEntities.removeAll(entities);
    }

    public int count() {
        return registeredCollidableEntities.size();
    }

    @Override
    public Iterator<StaticEntity> iterator() {
        return registeredCollidableEntities.iterator();
    }

    private CollidableRepository() {
        registeredCollidableEntities = new ArrayList<>();
    }
}
