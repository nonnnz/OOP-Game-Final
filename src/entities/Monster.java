package entities;

import java.awt.*;

public abstract class Monster extends Entity{
    // stat
    protected int HP, ATK;
    protected int MAX_HP;
    protected int death_count = 0;
    protected String MonsterName;

    public Monster(float x, float y, int ATK, int MAX_HP) {
        super(x, y);
        this.ATK = ATK;
        this.MAX_HP = MAX_HP;
        this.HP = MAX_HP;
    }

    public void update() {}
    public void render(Graphics g) {}
    public void setAction(int e) {}
    private void updateAnimationTick() {}
    private void setAnimations() {}
    private void resetAniTrick() {}
    private void updatePos() {}
    private void loadAnimaitons(){}
    private void reward() {}

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getATK() {
        return ATK;
    }

    public void setATK(int ATK) {
        this.ATK = ATK;
    }

    public int getMAX_HP() {
        return MAX_HP;
    }

    public void setMAX_HP(int MAX_HP) {
        this.MAX_HP = MAX_HP;
    }
    public void reset(){
        this.HP = MAX_HP;
    }
    public void setDeath_count(int death_count) {}

    public String getMonsterName() {return this.MonsterName;}
    public void increaseDeathCount() {
        death_count++;
    }
    public void resetDeath() {
        this.death_count = 0;
    }

    public int getDeath() {
        return death_count;
    }
}
