package cegepst.finalGame.player;

import cegepst.engine.GameTime;

public class Mana {

    private double maxManaPoint;
    private double manaPoint;
    private double regenValue;
    private long lastRegen;

    public Mana(int maxManaPoint, int regenValue) {
        this.maxManaPoint = maxManaPoint;
        this.manaPoint = maxManaPoint;
        this.regenValue =regenValue;
    }

    public void update() {
        if (manaPoint < maxManaPoint) {
            regenMana();
        }
    }

    public void regenMana() {
        if (GameTime.getCurrentTime() - lastRegen > 250) {
            manaPoint = Math.min(manaPoint + regenValue / 4, maxManaPoint);
            lastRegen = GameTime.getCurrentTime();
        }
    }

    public double getMaxManaPoint() {
        return maxManaPoint;
    }

    public void setMaxManaPoint(int maxManaPoint) {
        this.maxManaPoint = maxManaPoint;
    }

    public double getManaPoint() {
        return manaPoint;
    }

    public void setManaPoint(int manaPoint) {
        this.manaPoint = manaPoint;
    }

    public void reduceMana(int value) {
        manaPoint = Math.max(manaPoint - value, 0);
    }
}
