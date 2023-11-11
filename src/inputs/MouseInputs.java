package inputs;

import main.Game;
import main.GamePanel;

import gamesState.Gamestate;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (Gamestate.state) {
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseClicked(e);
                System.out.println(e.getPoint());
                break;
            case LEVEL1:
                gamePanel.getGame().getLevel1().mouseClicked(e);
                System.out.println(e.getPoint());
                break;
            case WON:
                gamePanel.getGame().getWon().mouseClicked(e);
                System.out.println(e.getPoint());
                break;
            case COMPLETE:
                gamePanel.getGame().getComplete().mouseClicked(e);
                System.out.println(e.getPoint());
                break;
            case GAMEOVER:
                gamePanel.getGame().getGameOver().mouseClicked(e);
                System.out.println(e.getPoint());
                break;
            default:
                break;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().mousePressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mousePressed(e);
                break;
            case BATTLESELECT:
                gamePanel.getGame().getBattleSelect().mousePressed(e);
                break;
            case LEVEL1:
                gamePanel.getGame().getLevel1().mousePressed(e);
                break;
            case WON:
                gamePanel.getGame().getWon().mousePressed(e);
                break;
            case COMPLETE:
                gamePanel.getGame().getComplete().mousePressed(e);
                break;
            case GAMEOVER:
                gamePanel.getGame().getGameOver().mousePressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseReleased(e);
                break;
            case BATTLESELECT:
                gamePanel.getGame().getBattleSelect().mouseReleased(e);
                break;
            case LEVEL1:
                gamePanel.getGame().getLevel1().mouseReleased(e);
                break;
            case WON:
                gamePanel.getGame().getWon().mouseReleased(e);
                break;
            case COMPLETE:
                gamePanel.getGame().getComplete().mouseReleased(e);
                break;
            case GAMEOVER:
                gamePanel.getGame().getGameOver().mouseReleased(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseMoved(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseMoved(e);
                break;
            case LEVEL1:
                gamePanel.getGame().getLevel1().mouseMoved(e);
                break;
            case WON:
                gamePanel.getGame().getWon().mouseMoved(e);
                break;
            case COMPLETE:
                gamePanel.getGame().getComplete().mouseMoved(e);
                break;
            case GAMEOVER:
                gamePanel.getGame().getGameOver().mouseMoved(e);
                break;
            default:
                break;
        }
    }
}
