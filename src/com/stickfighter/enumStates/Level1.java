package com.stickfighter.enumStates;

import com.stickfighter.graphics.Assets;
import com.stickfighter.main.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Level1 extends GameStateManager {

    private static BufferedImage image;
    private static int w = 32, h = 32;

    public Level1() {
        image = Assets.level1;
    }

    public void renderScreen(Graphics g) {
    }

    public void init() {
    }

    private static boolean isBlock(int x, int y) {
        Color c = new Color(image.getRGB(x, y));
        return c.getRed() == 255 && c.getGreen() == 255 && c.getBlue() == 255;
    }

    public static void makeLevelFromImage(Handler handler) {
        int imageW = image.getWidth();
        int imageH = image.getHeight();

        for (int x = 0; x < imageH; x++) {
            for (int y = 0; y < imageW; y++) {
                Color c = new Color(image.getRGB(x, y));
                if (isBlock(x, y)) {
                    if (x == 0) {
                        if (y == 0) {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 1, 4));
                        } else if (y == imageH - 1) {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 3, 4));
                        } else {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 2, 4));
                        }
                    } else if (x == imageW - 1) {
                        if (y == 0) {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 1, 2));
                        } else if (y == imageH - 1) {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 2, 3));
                        } else {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 2, 4));
                        }
                    } else if (y == 0 || y == imageH - 1) {
                        handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 1, 3));
                    } else {
                        if (isBlock(x, y - 1) && isBlock(x + 1, y) && isBlock(x, y + 1) && isBlock(x - 1, y)) {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 0));
                        } else if (isBlock(x + 1, y) && isBlock(x, y + 1) && isBlock(x - 1, y)) {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 1));
                        } else if (isBlock(x, y + 1) && isBlock(x - 1, y) && isBlock(x, y - 1)) {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 2));
                        } else if (isBlock(x - 1, y) && isBlock(x, y - 1) && isBlock(x + 1, y)) {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 3));
                        } else if (isBlock(x, y - 1) && isBlock(x + 1, y) && isBlock(x, y + 1)) {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 4));
                        } else if (isBlock(x, y + 1) && isBlock(x - 1, y)) {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 1, 2));
                        } else if (isBlock(x - 1, y) && isBlock(x, y - 1)) {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 2, 3));
                        } else if (isBlock(x, y - 1) && isBlock(x + 1, y)) {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 3, 4));
                        } else if (isBlock(x + 1, y) && isBlock(x, y + 1)) {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 1, 4));
                        } else if (isBlock(x + 1, y) && isBlock(x - 1, y)) {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 1, 3));
                        } else if (isBlock(x, y + 1) && isBlock(x, y - 1)) {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 2, 4));
                        } else if (isBlock(x - 1, y)) {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 1, 2, 3));
                        } else if (isBlock(x, y - 1)) {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 2, 3, 4));
                        } else if (isBlock(x + 1, y)) {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 3, 4, 1));
                        } else if (isBlock(x, y + 1)) {
                            handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 4, 1, 2));
                        }
                    }
                }

                if (c.getRed() == 0 && c.getGreen() == 0 && c.getBlue() == 255) {
                    Game.p1.setX(x * 32);
                    Game.p1.setY(y * 32);
                }

                if (c.getRed() == 255 && c.getGreen() == 0 && c.getBlue() == 0) {
                    handler.addObject(new Enemy(x * 32, y * 32, handler, ID.Enemy, Game.p1));
                }
            }
        }
    }
}
