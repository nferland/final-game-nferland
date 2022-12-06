package cegepst.finalGame;

import cegepst.engine.entities.Dimension;
import cegepst.engine.entities.EnemyRepository;
import cegepst.engine.entities.stateMachines.HurtState;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.Game;
import cegepst.engine.graphics.Camera;
import cegepst.engine.graphics.ImageLoader;
import cegepst.engine.graphics.RenderingEngine;
import cegepst.finalGame.audio.Music;
import cegepst.engine.entities.Enemy;
import cegepst.finalGame.enemies.Zombie;
import cegepst.finalGame.player.Player;
import cegepst.finalGame.world.SpawnPoint;
import cegepst.finalGame.world.World;

import java.util.ArrayList;

public class DungeonCrawlerGame extends Game {

    private ImageLoader imageLoader;
    private GamePad gamePad;
    private Player player;
    private World world;
    private Hud hud;

    @Override
    protected void initialize() {
        imageLoader = new ImageLoader();
        EnemyRepository.getInstance().loadZombieAnimation();
        SpellRepository.getInstance().loadAnimations(imageLoader);
        initializePlayer();
        initializeEnemies();
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
        updateSpwanPoints();
        updateEnemies();
        updateGameContinuation();
    }

    @Override
    protected void drawOnBuffer(Buffer buffer) {
        Camera.getInstance().draw(buffer, world.getBackground());
        drawEnemies(buffer);
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
            player.cast(imageLoader);
        }
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
        player.teleport(400, 300);
    }

    private void initializeEnemies() {
        Zombie zombie = new Zombie(player, 300, 500);
        EnemyRepository.getInstance().registerEntity(zombie);
        Zombie zombie1 = new Zombie(player, 600, 50);
        EnemyRepository.getInstance().registerEntity(zombie1);
    }

    private void initializeWorld() {
        world = new World(new Dimension(2400, 960));
        world.load(imageLoader);
    }

    private void drawEnemies(Buffer buffer) {
        for (Enemy enemy :
                EnemyRepository.getInstance()) {
            enemy.draw(buffer);
        }
    }
}
