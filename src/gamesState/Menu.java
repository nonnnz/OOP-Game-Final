package gamesState;

import main.Game;
import ui.MenuButton;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements StateMethods {
    private MenuButton[] buttons = new MenuButton[2];
    private BufferedImage backgroundImg;
    private int menuX, menuY, menuWidth, menuHight;

    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
    }

    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
        menuWidth = (int) (backgroundImg.getWidth());
        menuHight = (int) (backgroundImg.getHeight());
        menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = Game.GAME_HEIGHT / 2 - menuHight / 2;
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH/2, (int) (150*Game.SCALE), 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH/2, (int) (220*Game.SCALE), 1, Gamestate.QUIT);
    }

    @Override
    public void update() {
        for(MenuButton mb : buttons)
            mb.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHight, null);
        for(MenuButton mb : buttons)
            mb.draw(g);
        BufferedImage logos = LoadSave.GetSpriteAtlas(LoadSave.LOGO);
        g.drawImage(logos, Game.GAME_WIDTH/2 - (725/2) , 10, 725, 388, null);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if(isIn(e,mb)) {
                mb.setMousePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if(isIn(e,mb)) {
                if(mb.isMousePressed())
                    mb.applyGamestate();
                break;
            }
        }
        resetButton();
    }

    private void resetButton() {
        for(MenuButton mb : buttons)
            mb.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb : buttons)
            mb.setMouseOver(false);
        for (MenuButton mb : buttons)
            if(isIn(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
            Gamestate.state = Gamestate.PLAYING;
        System.out.println(Gamestate.state);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
