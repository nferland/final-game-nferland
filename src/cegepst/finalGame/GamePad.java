package cegepst.finalGame;

import cegepst.engine.controls.MovementController;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    private int quitKey = KeyEvent.VK_Q;
    private int attackKey = KeyEvent.VK_SPACE;
    private int toggleKey = KeyEvent.VK_T;
    private int musicKey = KeyEvent.VK_M;
    private int dashKey = KeyEvent.VK_SHIFT;
    private int spellKey = KeyEvent.VK_F;
    private int burstKey = KeyEvent.VK_E;

    public GamePad() {
        bindKey(quitKey);
        bindKey(attackKey, 750);
        bindKey(toggleKey, 2000);
        bindKey(musicKey, 100);
        bindKey(dashKey, 1000);
        bindKey(spellKey, 500);
        bindKey(burstKey, 1000);
    }

    public void useWASDMovement() {
        setUpKey(KeyEvent.VK_W);
        setDownKey(KeyEvent.VK_S);
        setLeftKey(KeyEvent.VK_A);
        setRightKey(KeyEvent.VK_D);
    }

    public boolean isQuitPressed() {
        return isKeyPressed(quitKey);
    }

    public boolean isAttackPressed() {
        return isKeyPressed(attackKey);
    }

    public boolean isMusicPressed() {
        return isKeyPressed(musicKey);
    }

    public boolean isTogglePressed() {
        return isKeyPressed(toggleKey);
    }

    public boolean isDashPressed() {
        return isKeyPressed(dashKey);
    }

    public boolean isSpellPressed() {
        return isKeyPressed(spellKey);
    }

    public boolean isBurstPressed() {
        return isKeyPressed(burstKey);
    }
}
