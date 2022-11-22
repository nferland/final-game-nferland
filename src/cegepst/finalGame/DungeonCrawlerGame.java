package cegepst.finalGame;

import cegepst.engine.entities.Dimension;
import cegepst.engine.entities.physic.CollidableRepository;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.Game;
import cegepst.engine.graphics.Camera;
import cegepst.engine.graphics.ImageLoader;
import cegepst.engine.graphics.RenderingEngine;
import cegepst.finalGame.audio.Music;
import cegepst.finalGame.enemies.Enemy;
import cegepst.finalGame.enemies.Zombie;
import cegepst.finalGame.player.Player;
import cegepst.finalGame.world.Tree;
import cegepst.finalGame.world.World;

import java.util.ArrayList;

public class DungeonCrawlerGame extends Game {

    private ImageLoader imageLoader;
    private ArrayList<Enemy> enemies;
    private GamePad gamePad;
    private Player player;
    private World world;
    private Hud hud;

    @Override
    protected void initialize() {
        imageLoader = new ImageLoader();
        initializePlayer();
        initializeEnemies();
        initializeWorld();
        Music.WIND_BACKGROUND.play();

        //RenderingEngine.getInstance().getScreen().fullScreen();
        RenderingEngine.getInstance().getScreen().hideCursor();
    }

    @Override
    protected void update() {
        updateInputs();
        player.update();
        Camera.getInstance().update(player, world.getDimension());
        updateEnemies();
    }

    @Override
    protected void drawOnBuffer(Buffer buffer) {
        Camera.getInstance().draw(buffer, world.getBackground());
        hud.draw(buffer);
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
    }

    private void updateEnemies() {
        for (Enemy enemy : enemies) {
            enemy.update();
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
        enemies = new ArrayList<>();
        Zombie zombie = new Zombie(player, 300, 500);
        zombie.load(imageLoader);
        enemies.add(zombie);
    }

    private void initializeWorld() {
        world = new World(new Dimension(2400, 960));
        world.load(imageLoader);
    }
}
