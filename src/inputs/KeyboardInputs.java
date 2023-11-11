package inputs;

import gamesState.Gamestate;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU:
                System.out.println(e);
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            case BATTLESELECT:
                gamePanel.getGame().getBattleSelect().keyPressed(e);
                break;
            case LEVEL1:
                gamePanel.getGame().getLevel1().keyPressed(e);
                break;
            case WON:
                gamePanel.getGame().getWon().keyPressed(e);
                break;
            case COMPLETE:
                gamePanel.getGame().getComplete().keyPressed(e);
                break;
            case GAMEOVER:
                gamePanel.getGame().getGameOver().keyPressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            case BATTLESELECT:
                gamePanel.getGame().getBattleSelect().keyReleased(e);
                break;
            case LEVEL1:
                gamePanel.getGame().getLevel1().keyReleased(e);
                break;
            case WON:
                gamePanel.getGame().getWon().keyReleased(e);
                break;
            case COMPLETE:
                gamePanel.getGame().getComplete().keyReleased(e);
                break;
            case GAMEOVER:
                gamePanel.getGame().getGameOver().keyReleased(e);
                break;
            default:
                break;
        }
    }
}
