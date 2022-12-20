package cegepst.finalGame;

import cegepst.engine.GameTime;
import cegepst.engine.graphics.Buffer;
import cegepst.finalGame.player.Player;

import java.awt.*;

public class Hud {

    public void draw(Buffer buffer, Player player) {
        buffer.drawString("FPS: " + GameTime.getCurrentFps(), 10, 15, Color.WHITE);
        buffer.drawString("TOGGLE FULLSCREEN: <T>, MELEE ATTACK: <SPACE>, DASH : <SHIFT>, FIREBALL : <F>, ", 10, 568, Color.WHITE);
        buffer.drawString("ICEBURST: <E> TOGGLE MUSIC: <M>, QUIT: <Q>", 14, 584, Color.WHITE);
        drawPlayerInfo(buffer, player);
        buffer.drawString("Score : " + Score.getInstance().getScore(),350, 20 , Color.WHITE);
        buffer.drawString("Level : " + Score.getInstance().getLevel(), 350, 40, Color.WHITE);
    }

    private void drawPlayerInfo(Buffer buffer, Player player) {
        drawPlayerHealthBar(buffer, player);
        drawPlayerManaBar(buffer, player);
    }

    private void drawPlayerManaBar(Buffer buffer, Player player) {
        double manaBarWidth = player.getManaPoint() / player.getMaxManaPoint();
        buffer.drawRectangle(15, 60, (int) player.getMaxManaPoint() * 8 + 10, 35, Color.BLACK);
        buffer.drawRectangle(20, 65, (int) (player.getMaxManaPoint() * 8 * manaBarWidth), 25, Color.CYAN);
    }

    private void drawPlayerHealthBar(Buffer buffer,Player player) {
        double healthBarWidth = player.getHealthPoint() / (double) player.getMaxHealthPoint();
        buffer.drawRectangle(15, 20, player.getMaxHealthPoint() * 8 + 10, 35, Color.BLACK);
        buffer.drawRectangle(20, 25, (int) (player.getMaxHealthPoint() * 8 * healthBarWidth), 25, getHealthBarColor(healthBarWidth));
    }

    private Color getHealthBarColor(double width) {
        if (width >= 0.75) {
            return Color.GREEN;
        }
        if(width >= 0.5) {
            return Color.yellow;
        }
        if(width >= 0.25) {
            return Color.orange;
        }
        return Color.red;
    }
}
