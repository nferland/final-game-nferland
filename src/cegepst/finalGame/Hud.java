package cegepst.finalGame;

import cegepst.engine.GameTime;
import cegepst.engine.graphics.Buffer;
import cegepst.finalGame.player.Player;

import java.awt.*;

public class Hud {

    public void draw(Buffer buffer, Player player) {
        buffer.drawString("FPS: " + GameTime.getCurrentFps(), 10, 15, Color.WHITE);
        buffer.drawString("TOGGLE FULLSCREEN: <T>, FIRE SOUND: <SPACE>, TOGGLE MUSIC: <M>, QUIT: <Q>", 10, 584, Color.WHITE);
        drawPlayerHealthBar(buffer, player);
    }

    private void drawPlayerHealthBar(Buffer buffer,Player player) {
        double healthBarWidth = player.getHealthPoint() / (double) player.getMaxHealthPoint();
        buffer.drawRectangle(15, 20, 210, 35, Color.BLACK);
        buffer.drawRectangle(20, 25, (int) (200 * healthBarWidth), 25, getHealthBarColor(healthBarWidth));
    }

    private Color getHealthBarColor(double width) {
        if (width >= 0.6) {
            return Color.GREEN;
        }
        if(width >= 0.3) {
            return Color.yellow;
        }
        return Color.red;
    }
}
