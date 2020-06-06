package com.stickfighter.enumStates;

import com.stickfighter.main.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public abstract class GameState {

    public final LinkedList<JLabelButton> buttons = new LinkedList<>();
    protected BufferedImage image;
    public static Handler handler = Game.handler;

    public abstract void render(Graphics g);

    public abstract void init();

    public void tick() {
        for (int i = 0; i < buttons.size(); i++) {
            JLabelButton tempButton = buttons.get(i);
            if (tempButton.clicked) {
                for (JLabelButton button : buttons) {
                    button.removeButton();
                }
                if (tempButton.stateID == null) {
                    System.exit(0);
                } else {
                    Game.setState(tempButton.stateID);
                    tempButton.clicked = false;
                    tempButton.hover = false;
                }
            }
        }
    }

    private boolean isBlock(int x, int y) {
        Color c = new Color(this.image.getRGB(x, y));
        return c.getRed() == 255 && c.getGreen() == 255 && c.getBlue() == 255;
    }

    public void makeLevelFromImage(Handler handler) {
        int w = 32, h = 32;
        int imageW = this.image.getWidth();
        int imageH = this.image.getHeight();

        for (int x = 0; x < imageH; x++) {
            for (int y = 0; y < imageW; y++) {
                Color c = new Color(this.image.getRGB(x, y));
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
                } else if (c.getRed() == 0 && c.getGreen() == 0 && c.getBlue() == 255) {
                    Game.p1.setX(x * 32);
                    Game.p1.setY(y * 32);
                } else if (c.getRed() == 255 && c.getGreen() == 0 && c.getBlue() == 0) {
                    handler.addObject(new Enemy(x * 32, y * 32, handler, ID.Enemy, Game.p1));
                } else if (c.getRed() == 255 && c.getGreen() == 255 && c.getBlue() == 0) {
                    handler.addObject(new Flag(x * 32, y * 32, ID.Flag));
                } else if (c.getRed() == 0 && c.getGreen() == 255 && c.getBlue() == 0) {
                    handler.addObject(new Platform(x * w, y * h, w, h, ID.Platform, 5));
                }

            }
        }
    }

}
