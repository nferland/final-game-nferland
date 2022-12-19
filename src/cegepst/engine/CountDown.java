package cegepst.engine;

public class CountDown {

    private final long DURATION;
    private long lastActivation = 0L;

    public CountDown(long duration) {
        this.DURATION = duration;
    }

    public void start() {
        lastActivation = GameTime.getCurrentTime();
    }

    public boolean isStillActive() {
        return GameTime.getCurrentTime() - lastActivation <= DURATION;
    }
}
