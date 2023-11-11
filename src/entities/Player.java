package entities;

import gamesState.Gamestate;
import utilz.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.PlayerConstants.GetSpriteAmount;
import static utilz.Constants.PlayerConstants.*;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDEL;
    private boolean attacking = false;
    private float E_SCALE = 1f;
    private int pW = 120, pH = 140;
    // stat
    private int HP, ARMOR, ATK, DEF, COIN;
    private int MAX_HP, MAX_ARMOR, STAT_POINT;
    // game stat
    private long startTime;
    private String endTimeFormat;

    public Player(float x, float y) {
        super(x, y);
        loadAnimaitons();
        this.MAX_HP = 25;
        this.HP = MAX_HP;
        this.MAX_ARMOR = 3;
        this.ARMOR = MAX_ARMOR;
        this.ATK = 2;
        this.DEF = 2;
        this.COIN = 0;
        this.STAT_POINT = 0;
        this.startTime = System.currentTimeMillis();
    }

    public String getEndTimeFormat() {
        return endTimeFormat;
    }

    public void setEndTimeFormat() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;
        long elapsedSeconds = elapsedTime / 1000;
        int hours = (int) (elapsedSeconds / 3600);
        int minutes = (int) ((elapsedSeconds % 3600) / 60);
        int seconds = (int) (elapsedSeconds % 60);
        this.endTimeFormat = String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void update() {
//        System.out.println(playerAction);
        updateAnimationTick();
        setAnimations();
        updatePos();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int)x, (int) y, ((int) (pW * E_SCALE)), ((int) (pH * E_SCALE)), null);
    }

    public void setAction(int e) {
        switch (e) {
            case 1:
                playerAction = IDEL;
                break;
            case 2:
                playerAction = ATTACK;
                break;
            case 3:
                playerAction = JUMP;
                break;
            case 4:
                playerAction = HIT;
                break;
            default:
                playerAction = IDEL;
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }

        }
    }

    private void setAnimations() {
        int startAni = playerAction;
        if (attacking)
            playerAction = ATTACK;
        else
            playerAction = IDEL;
        if(startAni != playerAction)
            resetAniTrick();
    }

    private void resetAniTrick() {
        aniTick =0;
        aniIndex =0;
    }

    private void updatePos() {
        if(Gamestate.state == Gamestate.PLAYING) {
            this.x = 616/2 + 180;
            this.y = 571/2 + 10;
            this.E_SCALE = 2.5f;
        }
        else if (Gamestate.state == Gamestate.LEVEL1) {
            this.x = 113;
            this.y = 250;
            this.E_SCALE = 4f;
        }

    }
    private void loadAnimaitons() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        animations = new BufferedImage[14][13];

        for(int j = 0; j < animations.length; j++)
            for(int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i*32,j*32,32,32);
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
        resetAniTrick();
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getARMOR() {
        return ARMOR;
    }

    public void setARMOR(int ARMOR) {
        this.ARMOR = ARMOR;
    }

    public int getATK() {
        return ATK;
    }

    public void setATK(int ATK) {
        if(STAT_POINT != 0) {
            this.ATK += ATK;
            STAT_POINT--;
        }
    }

    public int getDEF() {
        return DEF;
    }

    public void setDEF(int DEF) {
        if(STAT_POINT != 0) {
            this.DEF += DEF;
            STAT_POINT--;
            this.MAX_HP = 12 + this.DEF/2;
            this.MAX_ARMOR = 1 + this.DEF;
        }
    }

    public int getMAX_HP() {
        return MAX_HP;
    }

    public void setMAX_HP(int MAX_HP) {
        this.MAX_HP = MAX_HP;
    }

    public int getMAX_ARMOR() {
        return MAX_ARMOR;
    }

    public void setMAX_ARMOR(int MAX_ARMOR) {
        this.MAX_ARMOR = MAX_ARMOR;
    }

    public int getCOIN() {
        return COIN;
    }

    public void setCOIN(int COIN) {
        this.COIN = COIN;
    }

    public int getSTAT_POINT() {
        return STAT_POINT;
    }

    public void setSTAT_POINT(int STAT_POINT) {
        this.STAT_POINT = STAT_POINT;
    }

    public void buyPotion() {
        if(COIN >= 10) {
            COIN-=10;
            HP+=10;
            if(HP > MAX_HP) HP = MAX_HP;
        }
    }
    public void buyArmor() {
        if(COIN >= 5) {
            COIN-=5;
            ARMOR+=10;
            if(ARMOR > MAX_ARMOR) ARMOR = MAX_ARMOR;
        }

    }
    public void reset() {
        this.HP = 11 + (2/2);
        this.ARMOR = 1 + 2;
        this.ATK = 10;
        this.DEF = 2;
        this.MAX_HP = 12;
        this.MAX_ARMOR = 3;
        this.COIN = 0;
        this.STAT_POINT = 0;
        this.startTime = System.currentTimeMillis();
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

}
