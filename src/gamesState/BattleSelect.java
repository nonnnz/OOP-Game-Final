package gamesState;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class BattleSelect extends State implements StateMethods {
    private BufferedImage backgroundImg;
    private int bgX, bgY, bgW, bgH;


    public BattleSelect(Game game) {
        super(game);
        loadBackground();
    }
    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.BATTESC_BG);
        bgW = (int) (backgroundImg.getWidth());
        bgH = (int) (backgroundImg.getHeight());
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = Game.GAME_HEIGHT / 2 - bgH / 2;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                Gamestate.state = Gamestate.LEVEL1;
                game.setMonster(1);
                break;
            case KeyEvent.VK_A:
                Gamestate.state = Gamestate.LEVEL1;
                game.setMonster(2);
                break;
            case KeyEvent.VK_ESCAPE:
                Gamestate.state = Gamestate.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
