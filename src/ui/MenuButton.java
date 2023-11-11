package ui;

import gamesState.Gamestate;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButton {
    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = 366/2;
    private Gamestate state;
    private BufferedImage[] imgs;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;
    private static int B_WIDTH = 366, B_HEIGHT = (int) (242/2.0);
    private static int B_WIDTH_DEFAULT = 366, B_HEIGHT_DEFAULT = (int) (242/2.0) ;
    private static int SCALE = 10;

    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
        this.xPos =xPos;
        this.yPos =yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos-xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
    }

    private void loadImgs() {
        imgs = new BufferedImage[2];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(0,i*B_HEIGHT,B_WIDTH_DEFAULT,B_HEIGHT);

    }

    public void draw(Graphics g) {
        g.drawImage(imgs[rowIndex], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);

    }

    public void update() {
        index = 0;
        if(mouseOver)
            index = 1;
        if(mousePressed)
            index = 2;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void applyGamestate() {
        Gamestate.state = state;
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

}
