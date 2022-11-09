package cegepst.finalGame;

import cegepst.engine.entities.Dimension;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.Game;
import cegepst.engine.graphics.Camera;
import cegepst.engine.graphics.ImageLoader;
import cegepst.engine.graphics.RenderingEngine;
import cegepst.finalGame.audio.Music;
import cegepst.finalGame.player.Player;
import cegepst.finalGame.world.Tree;
import cegepst.finalGame.world.World;

public class DungeonCrawlerGame extends Game {

    private ImageLoader imageLoader;
    private GamePad gamePad;
    private Player player;
    private Camera camera;
    private World world;
    private Tree tree;
    private Hud hud;

    @Override
    protected void initialize() {
        imageLoader = new ImageLoader();
        initializePlayer();
        camera = new Camera(player, new Dimension(800, 600));
        initializeWorld();
        initializeTree();
        Music.WIND_BACKGROUND.play();

        //RenderingEngine.getInstance().getScreen().fullScreen();
        RenderingEngine.getInstance().getScreen().hideCursor();
    }

    @Override
    protected void update() {
        updateInputs();
        player.update();
        camera.update();
        updateTreeBlockade();
    }

    @Override
    protected void drawOnBuffer(Buffer buffer) {
        world.draw(buffer, camera);
        // 80 (tree height) - 28 (max for layer switch)
        drawTreeLayer(buffer);

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

    private void initializePlayer() {
        gamePad = new GamePad();
        gamePad.useWASDMovement();
        hud = new Hud();
        player = new Player(gamePad);
        player.load(imageLoader);
        player.teleport(400, 300);
    }

    private void initializeWorld() {
        world = new World();
        world.load(imageLoader);
    }

    private void initializeTree() {
        tree = new Tree(300, 350);
        tree.load(imageLoader);
    }

    private void updateTreeBlockade() {
        if (player.getY() < tree.getY() + 52) {
            tree.blockadeFromTop();
        } else {
            tree.blockadeFromBottom();
        }
    }

    private void drawTreeLayer(Buffer buffer) {
        if (player.getY() < tree.getY() + 52) {
            player.draw(buffer, camera);
            tree.draw(buffer, camera);
        } else {
            tree.draw(buffer, camera);
            player.draw(buffer, camera);
        }
    }
}
