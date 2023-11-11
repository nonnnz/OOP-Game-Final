package main;

import utilz.LoadSave;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameWindow {
    BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.ICON);
    private JFrame jframe;
    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame();
        jframe.add(gamePanel);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        jframe.setTitle("FromWeakToStrong");
        jframe.setIconImage(img);
    }
}
