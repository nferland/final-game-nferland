package cegepst.engine.entities;

import cegepst.engine.graphics.ImageLoader;
import cegepst.engine.graphics.WeaponAnimations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SpellRepository implements Iterable<Spell>{

    private static SpellRepository instance;
    private final List<Spell> registeredSpells;
    private final String FIREBALL_SPRITE_PATH = "images/fire-Sheet.png";
    private final WeaponAnimations fireballAnimation;

    public static SpellRepository getInstance() {
        if (instance == null) {
            instance = new SpellRepository();
        }
        return instance;
    }

    public void loadAnimations(ImageLoader imageLoader) {
        loadFireballAnimation(imageLoader);
    }

    public WeaponAnimations getFireballAnimations() {
        return fireballAnimation;
    }

    public void registerEntity(Spell entity) {
        registeredSpells.add(entity);
    }

    public void registerEntities(Collection<Spell> entities) {
        registeredSpells.addAll(entities);
    }

    public void unregisterEntity(Spell entity) {
        registeredSpells.remove(entity);
    }

    @Override
    public Iterator<Spell> iterator() {
        return registeredSpells.iterator();
    }

    private SpellRepository() {
        registeredSpells = new ArrayList<>();
        fireballAnimation = new WeaponAnimations(FIREBALL_SPRITE_PATH, 16, 16, 0, 0);
    }

    private void loadFireballAnimation(ImageLoader imageLoader) {
        fireballAnimation.loadSpriteSheet(imageLoader);
        fireballAnimation.loadAnimations();
    }
}
