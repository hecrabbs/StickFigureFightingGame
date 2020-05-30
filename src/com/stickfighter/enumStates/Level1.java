package com.stickfighter.enumStates;

import com.stickfighter.graphics.Assets;
import com.stickfighter.main.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Level1 extends GameStateManager {

    private static BufferedImage image;

    public Level1() {
        image = Assets.level1;
    }

    public void renderScreen(Graphics g) {

    }

    public void init() {
    }

    public static void makeLevelFromImage(Handler handler) {
        int w = image.getWidth();
        int h = image.getHeight();

        for (int x = 0; x < h; x++) {
            for (int y = 0; y < w; y++) {
                Color c = new Color(image.getRGB(x,y));
                if (c.getRed() == 255 && c.getGreen() == 255 && c.getBlue() == 255) {
                    handler.addObject(new Platform(x * 32, y * 32, 32, 32, ID.Platform));
                }
                if (c.getRed() == 0 && c.getGreen() == 0 && c.getBlue() == 255) {
                    Game.p1.setX(x*32);
                    Game.p1.setY(y*32);
                }
                if (c.getRed() == 255 && c.getGreen() == 0 && c.getBlue() == 0) {
                    handler.addObject(new Enemy(x*32, y*32, handler, ID.Enemy, Game.p1));
                }
            }
        }
    }
}
