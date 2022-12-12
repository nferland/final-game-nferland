package cegepst.finalGame;

import cegepst.engine.entities.*;
import cegepst.engine.entities.stateMachines.HurtState;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.Game;
import cegepst.engine.graphics.Camera;
import cegepst.engine.graphics.ImageLoader;
import cegepst.engine.graphics.RenderingEngine;
import cegepst.finalGame.audio.Music;
import cegepst.finalGame.enemies.Zombie;
import cegepst.finalGame.player.Player;
import cegepst.engine.entities.SpawnPoint;
import cegepst.finalGame.world.World;

public class DungeonCrawlerGame extends Game {

    private ImageLoader imageLoader;
    private GamePad gamePad;
    private Player player;
    private World world;
    private Hud hud;

    @Override
    protected void initialize() {
        imageLoader = new ImageLoader();
        EnemyRepository.getInstance().loadAnimations(imageLoader);
        SpellRepository.getInstance().loadAnimations(imageLoader);
        initializePlayer();
//        initializeEnemies();
        initializeWorld();
        Music.WIND_BACKGROUND.play();

        //RenderingEngine.getInstance().getScreen().fullScreen();
        RenderingEngine.getInstance().getScreen().hideCursor();
        Camera.getInstance().setMainEntity(player);
    }

    @Override
    protected void update() {
        updateInputs();
        player.update();
        Camera.getInstance().update(world.getDimension());
        updateSpells();
        updateSpwanPoints();
        updateEnemies();
        updateGameContinuation();
    }

    @Override
    protected void drawOnBuffer(Buffer buffer) {
        Camera.getInstance().draw(buffer, world.getBackground());
        drawEnemies(buffer);
        world.drawBlockades(buffer);
        hud.draw(buffer, player);
    }

    private void updateGameContinuation() {
        if (player.getHurtState() == HurtState.Dead) {
            Music.WIND_BACKGROUND.stop();
            stop();
        }
        if (Score.getInstance().getScore() >= 1000) {
            victory();
            stop();
        }
    }

    private void updateInputs() {
        if (gamePad.isQuitPressed()) {
            Music.WIND_BACKGROUND.stop();
            stop();
        }
        if (gamePad.isTogglePressed()) {
            RenderingEngine.getInstance().getScreen().toggleFullScreen();
        }
        if (gamePad.isMusicPressed()) {
            Music.WIND_BACKGROUND.toggle();
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

    private void initializePlayer() {
        gamePad = new GamePad();
        gamePad.useWASDMovement();
        hud = new Hud();
        player = new Player(gamePad);
        player.load(imageLoader);
        player.teleport(120, 120);
    }

    private void initializeEnemies() {
        new Zombie(player, 300, 500);
        new Zombie(player, 600, 50);
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
