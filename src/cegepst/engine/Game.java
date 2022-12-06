package cegepst.engine;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.RenderingEngine;

public abstract class Game {

    private final RenderingEngine renderingEngine;
    private boolean playing = true;
    private boolean gameWon = false;

    protected abstract void initialize();
    protected abstract void update();
    protected abstract void drawOnBuffer(Buffer buffer);

    public Game() {
        renderingEngine = RenderingEngine.getInstance();
    }

    public final void start() {
        initialize();
        run();
        conclude();
    }

    protected void conclude() {

    }

    protected void stop() {
        playing = false;
    }

    private void run() {
        renderingEngine.start();
        GameTime gameTime = new GameTime();
        while (playing) {
            update();
            drawOnBuffer(renderingEngine.buildBuffer());
            renderingEngine.drawBufferOnScreen();
            gameTime.synchronize();
        }
        endOfGame();
        renderingEngine.stop();
    }

    private void endOfGame() {
        if (gameWon) {
            //TODO : Win screen
        }
        //TODO: Game over screen
    }

    public void victory() {
        gameWon = true;
    }
}
