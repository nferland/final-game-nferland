package cegepst.finalGame;

import cegepst.engine.GameTime;
import cegepst.engine.graphics.Buffer;
import cegepst.finalGame.player.Player;

import java.awt.*;

public class Hud {

    public void draw(Buffer buffer, Player player) {
        buffer.drawString("FPS: " + GameTime.getCurrentFps(), 10, 20, Color.WHITE);
        buffer.drawString("TOGGLE FULLSCREEN: <T>, FIRE SOUND: <SPACE>, TOGGLE MUSIC: <M>, QUIT: <Q>", 10, 584, Color.WHITE);
        drawPlayerHealthBar(buffer, player);
    }

    private void drawPlayerHealthBar(Buffer buffer,Player player) {
        double healthBarWidth = player.getHealthPoint() / (double) player.getMaxHealthPoint();
        buffer.drawRectangle(15, 20, 210, 35, new Color(0, 0, 0));
        buffer.drawRectangle(20, 25, (int) (200 * healthBarWidth), 25, new Color(255, 0, 0));
    }
}
