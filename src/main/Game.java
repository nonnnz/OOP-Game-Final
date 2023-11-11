package main;

import entities.Player;
import gamesState.*;
import gamesState.Menu;

import java.awt.*;

public class Game implements Runnable{

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;

    private Playing playing;
    private Menu menu;
    private Player player;
    private BattleSelect battleSelect;
    private Level1 level1;
    private Won won;
    private Complete complete;
    private GameOver gameOver;

    public final static int GAME_WIDTH = 1280;
    public final static int GAME_HEIGHT = 960;
    public final static int SCALE = 3;
    private int Monster = 2;

    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClasses() {
        player = new Player(200,200);
        menu = new Menu(this);
        playing = new Playing(this);
        battleSelect = new BattleSelect(this);
        level1 = new Level1(this);
        won = new Won(this);
        complete = new Complete(this);
        gameOver = new GameOver(this);
    }


    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        switch (Gamestate.state) {
            case PLAYING:
                playing.update();
                break;
            case MENU:
                menu.update();
                break;
            case BATTLESELECT:
                battleSelect.update();
                break;
            case LEVEL1:
                level1.update();
                break;
            case WON:
                won.update();
                break;
            case COMPLETE:
                complete.update();
                break;
            case GAMEOVER:
                gameOver.update();
                break;
            case QUIT:
            default:
                System.exit(0);
                break;
        }
    }

    public void render(Graphics g) {

        switch (Gamestate.state) {
            case PLAYING:
                playing.draw(g);
                break;
            case MENU:
                menu.draw(g);
                break;
            case BATTLESELECT:
                battleSelect.draw(g);
                break;
            case LEVEL1:
                level1.draw(g);
                break;
            case WON:
                won.draw(g);
                break;
            case COMPLETE:
                complete.draw(g);
                break;
            case GAMEOVER:
                gameOver.draw(g);
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0  / FPS_SET;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();
        int frames = 0;
        long lastCheck = System.currentTimeMillis();
        while(true) {
            now = System.nanoTime();
            if(System.nanoTime() - lastFrame >= timePerFrame) {
                update();
                gamePanel.repaint();
                lastFrame = now;
                frames++;
            }
            if(System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
//                System.out.println("FPS: "+frames);
                frames=0;
            }
        }
    }

    public Menu getMenu() {
        return menu;
    }
    public Playing getPlaying() {
        return playing;
    }
    public BattleSelect getBattleSelect() {return battleSelect;}
    public Level1 getLevel1() {return level1;}

    public Won getWon() {
        return won;
    }

    public Complete getComplete() {
        return complete;
    }

    public GameOver getGameOver() {
        return gameOver;
    }

    public Player getPlayer() {
        return player;
    }

    public int getMonster() {
        return Monster;
    }

    public void setMonster(int monster) {
        this.Monster = monster;
    }
}
