package cegepst.finalGame;

import cegepst.engine.GameTime;
import cegepst.engine.graphics.Buffer;

import java.awt.*;

public class Hud {

    public void draw(Buffer buffer) {
        buffer.drawString("FPS: " + GameTime.getCurrentFps(), 10, 20, Color.WHITE);
        buffer.drawString("TOGGLE FULLSCREEN: <T>, FIRE SOUND: <SPACE>, TOGGLE MUSIC: <M>, QUIT: <Q>", 10, 584, Color.WHITE);
    }
}
