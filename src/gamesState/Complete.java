package gamesState;

import entities.Monster;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Complete extends State implements StateMethods{
    private BufferedImage backgroundImg;
    private int bgX, bgY, bgW, bgH;

    public Complete(Game game) {
        super(game);
        loadBackground();
    }

    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.COMPLETE);
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

        //info
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 34));
        Monster[] monsters = game.getLevel1().getMonsters();
        int index = 0;
        for (Monster monster : monsters) {
            g.drawString(""+monster.getDeath()+"x "+monster.getMonsterName(),174,393+(40*index));
            index++;
        }


        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("Time: "+game.getPlayer().getEndTimeFormat(), 832,568);

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
            case KeyEvent.VK_F:
                Gamestate.state = Gamestate.PLAYING;
                Monster[] monsters = game.getLevel1().getMonsters();
                for (Monster monster : monsters) {
                    monster.reset();
                    monster.resetDeath();
                }
                game.getPlayer().reset();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
