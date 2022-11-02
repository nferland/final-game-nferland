package cegepst.finalGame;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.Game;
import cegepst.engine.graphics.ImageLoader;
import cegepst.engine.graphics.RenderingEngine;
import cegepst.finalGame.audio.Music;
import cegepst.finalGame.world.Tree;
import cegepst.finalGame.world.World;

public class DungeonCrawlerGame extends Game {

    private ImageLoader imageLoader;
    private GamePad gamePad;
    private Player player;
    private World world;
    private Tree tree;
    private Hud hud;

    @Override
    protected void initialize() {
        imageLoader = new ImageLoader();
        gamePad = new GamePad();
        gamePad.useWASDMovement();
        hud = new Hud();
        player = new Player(gamePad);
        player.load(imageLoader);
        world = new World();
        world.load(imageLoader);
        player.teleport(200, 200);
        tree = new Tree(300, 350);
        tree.load(imageLoader);

        Music.WIND_BACKGROUND.play();

        //RenderingEngine.getInstance().getScreen().fullScreen();
        RenderingEngine.getInstance().getScreen().hideCursor();
    }

    @Override
    protected void update() {
        updateInputs();
        player.update();
        if (player.getY() < tree.getY() + 52) {
            tree.blockadeFromTop();
        } else {
            tree.blockadeFromBottom();
        }
    }

    @Override
    protected void drawOnBuffer(Buffer buffer) {
        world.draw(buffer);
        // 80 (tree height) - 28 (max for layer switch)
        if (player.getY() < tree.getY() + 52) {
            player.draw(buffer);
            tree.draw(buffer);
        } else {
            tree.draw(buffer);
            player.draw(buffer);
        }

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
}
