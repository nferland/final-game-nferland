package cegepst.finalGame;

import cegepst.engine.CountDown;
import cegepst.engine.entities.*;
import cegepst.engine.entities.Dimension;
import cegepst.engine.entities.stateMachines.HurtState;
import cegepst.engine.graphics.*;
import cegepst.engine.Game;
import cegepst.finalGame.audio.Music;
import cegepst.finalGame.enemies.Zombie;
import cegepst.finalGame.player.Player;
import cegepst.engine.entities.SpawnPoint;
import cegepst.finalGame.world.World;

import java.awt.*;

public class DungeonCrawlerGame extends Game {

    private ImageLoader imageLoader;
    private GamePad gamePad;
    private Player player;
    private World world;
    private Hud hud;
    private int currentLevel;
    private CountDown nextLevelCountDown;
    private CountDown gameOverCountDown;

    @Override
    protected void initialize() {
        imageLoader = new ImageLoader();
        initializeCountDowns();
        loadAnimations();
        initializeLevel();
        Music.PRICE_BACKGROUND.play();
        //RenderingEngine.getInstance().getScreen().fullScreen();
        RenderingEngine.getInstance().getScreen().hideCursor();
    }

    @Override
    protected void update() {
        if (nextLevelCountDown.isStillActive() || gameOverCountDown.isStillActive()) {
            return;
        }
        updateLevel();
    }

    @Override
    protected void drawOnBuffer(Buffer buffer) {
        if(nextLevelCountDown.isStillActive()) {
            drawBlackScreenMessage(buffer, "Next Level...");
            return;
        }
        if (gameOverCountDown.isStillActive()) {
            drawBlackScreenMessage(buffer, "Game Over");
            return;
        }
        drawLevel(buffer);
    }

    @Override
    protected void stop() {
        if(gameOverCountDown.expired()) {
            super.stop();
        }
    }

    private void updateGameContinuation() {
        if (player.getHurtState() == HurtState.Dead) {
            Music.PRICE_BACKGROUND.stop();
            if (gameOverCountDown.notActivated()){
                gameOverCountDown.start();
            }
            stop();
            return;
        }
        if (Score.getInstance().getLevel() != currentLevel) {
            nextLevel();
        }
    }

    private void updateLevel() {
        updateEntities();
        updateTriggers();
        updateGameContinuation();
        updateInputs();
    }

    private void nextLevel() {
        currentLevel = Score.getInstance().getLevel();
        player.nextLevel();
        EnemyRepository.getInstance().clear();
        SpellRepository.getInstance().clear();
        nextLevelCountDown.start();
    }

    private void updateInputs() {
        if (gamePad.isQuitPressed()) {
            Music.PRICE_BACKGROUND.stop();
            super.stop();
        }
        if (gamePad.isTogglePressed()) {
            RenderingEngine.getInstance().getScreen().toggleFullScreen();
        }
        if (gamePad.isMusicPressed()) {
            Music.PRICE_BACKGROUND.toggle();
        }
        if (gamePad.isAttackPressed()) {
            player.attack();
        }
        if(gamePad.isDashPressed()) {
            player.dash();
        }
        if (gamePad.isSpellPressed()) {
            player.cast();
        }
        if(gamePad.isBurstPressed()) {
            player.burst();
        }
    }

    private void updateEntities() {
        player.update();
        Camera.getInstance().update(world.getDimension());
        updateSpells();
        updateSpwanPoints();
        updateEnemies();
    }

    private void updateTriggers() {
        world.updateTrigger(player);
    }

    private void updateSpells() {
        for (Spell spell : SpellRepository.getInstance()) {
            spell.update(player);
        }
        SpellRepository.getInstance().uregisterInactiveSpells();
    }

    private void updateEnemies() {
        for (Enemy enemy : EnemyRepository.getInstance()) {
            enemy.update();
        }
    }

    private void updateSpwanPoints() {
        for (SpawnPoint spawnPoint : world.getSpawnPoints()) {
            spawnPoint.update(player);
        }
    }

    private void drawLevel(Buffer buffer) {
        Camera.getInstance().draw(buffer, world.getBackground());
        drawEnemies(buffer);
        hud.draw(buffer, player);
        world.drawTriggerInfo(buffer);
    }

    private void drawBlackScreenMessage(Buffer buffer, String message) {
        buffer.drawRectangle(0, 0, 800, 600, Color.BLACK);
        buffer.drawString(message, 350, 275, Color.WHITE);
    }

    private void initializeCountDowns() {
        nextLevelCountDown = new CountDown(1500);
        gameOverCountDown = new CountDown(2000);
    }

    private void initializeLevel() {
        initializePlayer();
        initializeWorld();
        currentLevel = Score.getInstance().getLevel();
        Camera.getInstance().setMainEntity(player);
    }

    private void loadAnimations() {
        EnemyRepository.getInstance().loadAnimations(imageLoader);
        SpellRepository.getInstance().loadAnimations(imageLoader);
    }

    private void initializePlayer() {
        gamePad = new GamePad();
        gamePad.useWASDMovement();
        hud = new Hud();
        player = new Player(gamePad);
        player.load(imageLoader);
        player.teleport(120, 120);
    }

    private void initializeWorld() {
        world = new World(new Dimension(1920, 1920));
        world.load(imageLoader);
    }

    private void drawEnemies(Buffer buffer) {
        for (Enemy enemy :
                EnemyRepository.getInstance()) {
            enemy.draw(buffer);
        }
    }
}
