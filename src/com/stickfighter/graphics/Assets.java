package com.stickfighter.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Assets {

    public static BufferedImage[] pMoveR = new BufferedImage[12];
    public static BufferedImage[] pMoveL = new BufferedImage[12];
    public static BufferedImage[] pAttackR = new BufferedImage[6];
    public static BufferedImage[] pAttackL = new BufferedImage[6];
    public static BufferedImage[] pIdle = new BufferedImage[6];
    public static BufferedImage level1;

    public static void init() {
        loadPlayerImages();
//        level1 = loadImage("./res/levels/level1.png ");
        level1 = loadImage("./res/levels/level1.png ");
    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static void loadPlayerImages() {
        SpriteSheet sheet = new SpriteSheet(loadImage("./res/textures/playerSheet.png"));
        int w = 64, h = 64;
        int x = 0, y = 0;
        int r = 3, c = 12;
        for (int i = 0; i < pMoveR.length; i++) {
            pMoveR[i] = sheet.crop(x, y, w, h);
            x += w;
        }
        y = h;
        x = w * (pMoveL.length - 1);
        for (int i = 0; i < pMoveL.length; i++) {
            pMoveL[i] = sheet.crop(x, y, w, h);
            x -= w;
        }
        y = h * 2;
        x = 0;
        for (int i = 0; i < pAttackR.length; i++) {
            pAttackR[i] = sheet.crop(x, y, w, h);
            x += w;
        }
        x = w * (c - 1);
        for (int i = 0; i < pAttackL.length; i++) {
            pAttackL[i] = sheet.crop(x, y, w, h);
            x -= w;
        }
        y = h * 3;
        x = 0;
        for (int i = 0; i < pIdle.length; i++) {
            pIdle[i] = sheet.crop(x, y, w, h);
            x += w;
        }
    }

}
