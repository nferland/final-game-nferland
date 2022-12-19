package cegepst.engine;

public class CountDown {

    private final long DURATION;
    private long lastActivation = 0L;
    private boolean activated = false;

    public CountDown(long duration) {
        this.DURATION = duration;
    }

    public void start() {
        lastActivation = GameTime.getCurrentTime();
        activated = true;
    }

    public boolean isStillActive() {
        return GameTime.getCurrentTime() - lastActivation <= DURATION;
    }

    public boolean expired() {
        return activated && !isStillActive();
    }

    public boolean notActivated() {
        return !activated;
    }
}
