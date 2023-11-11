package entities;

import gamesState.Gamestate;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.BatConstants.*;

public class Bat extends Monster{
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int Action = IDEL;
    private boolean attacking = false;
    private float E_SCALE = 1f;
    private int pW = 64, pH = 64;

    String monsterName = "Bat";
    public Bat(float x, float y) {
        this(x, y, 2, 30);
    }
    public Bat(float x, float y, int ATK, int MAX_HP) {
        super(x, y, ATK, MAX_HP);
        loadAnimaitons();
    }
    public void update() {
        updateAnimationTick();
        setAnimations();
        updatePos();
    }
    public void render(Graphics g) {
        g.drawImage(animations[Action][aniIndex], (int)x, (int) y, ((int) (pW * E_SCALE)), ((int) (pH * E_SCALE)), null);
    }

    private void loadAnimaitons() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.BAT);
        animations = new BufferedImage[3][5];

        for(int j = 0; j < animations.length; j++)
            for(int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i*16,j*20,16,20);
    }
    public void setAction(int e) {
        switch (e) {
            case 1:
                Action = IDEL;
                break;
            case 2:
                Action = ATTACK;
                break;
            case 3:
                Action = HIT;
                break;
        }
    }
    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(Action)) {
                aniIndex = 0;
                attacking = false;
            }

        }
    }
    private void setAnimations() {
        int startAni = Action;
        if (attacking)
            Action = ATTACK;
        else
            Action = IDEL;
        if(startAni != Action)
            resetAniTrick();
    }
    private void resetAniTrick() {
        aniTick =0;
        aniIndex =0;
    }
    private void updatePos() {
        if (Gamestate.state == Gamestate.PLAYING) {
            this.x = 616 / 2 + 180;
            this.y = 571 / 2 + 25;
            this.E_SCALE = 2.5f;
        } else if (Gamestate.state == Gamestate.LEVEL1) {
            this.x = 808;
            this.y = 150;
            this.E_SCALE = 4f;
        }
    }
    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
//        resetAniTrick();
    }

    public String getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }
}
