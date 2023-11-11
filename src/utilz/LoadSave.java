package utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static final String PLAYER_ATLAS = "Player-1.png";
    public static final String MENU_BUTTONS = "button_atlas.png";
    public static final String LOGO = "logo.png";
    public static final String MENU_BACKGROUND = "menu-bg.png";
    public static final String DASHBOARD_BG = "dashboard-bg.png";
    public static final String BATTESC_BG = "battleSC.png";
    public static final String BATTLE_BG = "battle-bg.png";
    public static final String BAT = "Bat_Sprites.png";
    public static final String WON = "won.png";
    public static final String GAMEOVER = "gameover.png";
    public static final String COMPLETE = "complete.png";
    public static final String MINOTAUR = "Minotaur.png";
    public static final String ICON = "icon.png";

    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/res/" + fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
}
