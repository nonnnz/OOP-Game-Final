package gamesState;

import entities.Player;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class Playing extends State implements StateMethods {
    private BufferedImage backgroundImg;
    private int bgX, bgY, bgW, bgH;

    public Playing(Game game) {
        super(game);
//        initClasses();
        loadBackground();
    }



    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.DASHBOARD_BG);
        bgW = (int) (backgroundImg.getWidth());
        bgH = (int) (backgroundImg.getHeight());
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = Game.GAME_HEIGHT / 2 - bgH / 2;
    }

    @Override
    public void update() {
        game.getPlayer().update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);
        game.getPlayer().render(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString(""+game.getPlayer().getHP()+"/"+game.getPlayer().getMAX_HP(),215,180 );
        g.drawString(""+game.getPlayer().getARMOR()+"/"+game.getPlayer().getMAX_ARMOR(),215,260 );
        //point
        g.setFont(new Font("Arial", Font.BOLD, 34));
        g.drawString("Stat point: "+game.getPlayer().getSTAT_POINT(),120,446 );
        g.drawString(""+game.getPlayer().getATK(),145,602 );
        g.drawString(""+game.getPlayer().getDEF(),145,792 );
        //coin
        g.setFont(new Font("Arial", Font.BOLD, 72));
        g.drawString("Coin: "+game.getPlayer().getCOIN(),849,107 );
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            game.getPlayer().setAttacking(true);
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
                game.getPlayer().setATK(1);
                break;
            case KeyEvent.VK_A:
                game.getPlayer().setDEF(1);
                break;
            case KeyEvent.VK_F:
                System.out.println("F -> BATTLESELECT");
                Gamestate.state = Gamestate.BATTLESELECT;
                break;
            case KeyEvent.VK_Z:
                game.getPlayer().buyPotion();
                break;
            case KeyEvent.VK_X:
                game.getPlayer().buyArmor();
                break;
            case KeyEvent.VK_ESCAPE:
                Gamestate.state = Gamestate.MENU;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

