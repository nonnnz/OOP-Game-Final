package gamesState;

import entities.Bat;
import entities.Minotaur;
import entities.Monster;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Level1 extends State implements StateMethods {
    private BufferedImage backgroundImg;
    private int bgX, bgY, bgW, bgH;
    Bat bat;
    Minotaur minotaur;
    Monster[] monsters;
    String[] gameMessages;

    public Level1(Game game) {
        super(game);
        initClasses();
        loadBackground();
    }
    public void initClasses() {
        monsters = new Monster[2];
        monsters[0] = new Bat(200,200);
        monsters[1] = new Minotaur(200,200);
        gameMessages = new String[]{"", ""};
    }
    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.BATTLE_BG);
        bgW = (int) (backgroundImg.getWidth());
        bgH = (int) (backgroundImg.getHeight());
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = Game.GAME_HEIGHT / 2 - bgH / 2;
    }

    public Bat getBat() {
        return bat;
    }

    public Monster[] getMonsters () {
        return monsters;
    }

    public Minotaur getMinotaur() {
        return minotaur;
    }

    @Override
    public void update() {
        game.getPlayer().update();
        monsters[game.getMonster()-1].update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);
        game.getPlayer().render(g);
        monsters[game.getMonster()-1].render(g);
        // name
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 34));
        g.drawString(""+monsters[game.getMonster()-1].getMonsterName(), 42, 136);
        g.drawString("Player", 762, 537);

        // status
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("HP: "+monsters[game.getMonster()-1].getHP()+"/"+monsters[game.getMonster()-1].getMAX_HP(), 287, 208);

        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("HP: "+game.getPlayer().getHP()+"/"+game.getPlayer().getMAX_HP(),1024,597 );
        g.drawString("Armor: "+game.getPlayer().getARMOR()+"/"+game.getPlayer().getMAX_ARMOR(),1028,661);

        // mes
        g.drawString(""+gameMessages[0],71,821 );
        g.drawString(""+gameMessages[1],66,896);
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
            case KeyEvent.VK_Q:
                System.out.println("fight");
                game.getPlayer().setAttacking(true);
                fight();
                break;
            case KeyEvent.VK_W:
                shield();
                break;
        }
    }
    public void won() {
        gameMessages[0] = "";
        gameMessages[1] = "";
        for (Monster monster : monsters) {
            monster.reset();
        }
        if(game.getMonster() == 2) {
            game.getPlayer().setEndTimeFormat();
            int monsterIndex = game.getMonster() - 1;
            monsters[monsterIndex].increaseDeathCount();
            Gamestate.state = Gamestate.COMPLETE;
            return;
        }
        int monsterIndex = game.getMonster() - 1;
        monsters[monsterIndex].increaseDeathCount();

        int newCoin = game.getPlayer().getCOIN() + 10;

        int newStat = game.getPlayer().getSTAT_POINT() + 2;

        game.getPlayer().setSTAT_POINT(newStat);
        game.getPlayer().setCOIN(newCoin);
        Gamestate.state = Gamestate.WON;
    }

    public void defeat() {
        gameMessages[0] = "";
        gameMessages[1] = "";
        for (Monster monster : monsters) {
            monster.reset();
            monster.resetDeath();
        }

        Gamestate.state = Gamestate.GAMEOVER;
    }

    public void fight() {
        int playerATK = game.getPlayer().getATK();
        int monsterIndex = game.getMonster() - 1;
        Monster currentMonster = monsters[monsterIndex];

        Random random = new Random();
        int minDamage = playerATK / 2;
        int playerDamage = random.nextInt(playerATK - minDamage + 1) + minDamage;


        int monsterHP = currentMonster.getHP();
        int monsterATK = currentMonster.getATK();
        int minMonsterATK = monsterATK / 2;
        int monsterDamage = random.nextInt(monsterATK - minMonsterATK + 1) + minMonsterATK;

        // Check for critical hit
        boolean isCritical = false;
        double playerHPPct = (double) game.getPlayer().getHP() / game.getPlayer().getMAX_HP();
        System.out.println(playerHPPct);
        if (playerHPPct < 0.4) {
            isCritical= true;
            int randomCritical = random.nextInt(2) + 1;
            playerDamage *= randomCritical;
        }


        int newMonsterHP = monsterHP - playerDamage;
        int newPlayerHP = game.getPlayer().getHP() - monsterDamage;

        if (newMonsterHP <= 0) {
            won();
            return;
        }


        if (newPlayerHP <= 0) {
            defeat();
            return;
        }
        System.out.println(isCritical);
        currentMonster.setHP(newMonsterHP);
        game.getPlayer().setHP(newPlayerHP);
        gameMessages[1] = currentMonster.getMonsterName() + " deals " + monsterDamage + " damage!!!";
        gameMessages[0] = currentMonster.getMonsterName() + " HP - " + playerDamage;
        if(isCritical) gameMessages[0] = currentMonster.getMonsterName() + " HP - " + playerDamage + " by critical hit!!!";
    }


    public void shield() {
        int armor = game.getPlayer().getARMOR();
        int monsterIndex = game.getMonster() - 1;
        Monster currentMonster = monsters[monsterIndex];
        Random random = new Random();
        int blockChance = 10;
        boolean blockSuccessful = random.nextInt(100) < blockChance;
        if (blockSuccessful) {
            gameMessages[0] = "Block!";
            gameMessages[1] = currentMonster.getMonsterName() + " deals 0 damage!!!";
        } else {
            int monsterATK = currentMonster.getATK();
            int damage = armor - monsterATK;
            System.out.println(damage);
            if(damage < 0) {
                game.getPlayer().setARMOR(0);
                int newHP = game.getPlayer().getHP();
                int newPlayerHP = newHP + damage;
                if (newPlayerHP <= 0) {
                    System.out.println("defeat");
                    defeat();
                    return;
                }
                game.getPlayer().setHP(newPlayerHP);
                return;
            }
            game.getPlayer().setARMOR(damage);
            gameMessages[0] = "Can't Block!";
            gameMessages[1] = currentMonster.getMonsterName() + " deals " + monsterATK + " damage!!!";
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
