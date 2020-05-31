package com.stickfighter.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Assets {

    private static final int width = 64, height = 64;
    private static final int rows = 2, columns = 12;
    private static int x=0,y=0;

    public static BufferedImage[] playerRight = new BufferedImage[columns];
    public static BufferedImage[] playerLeft = new BufferedImage[columns];
    public static BufferedImage[] pAttackR = new BufferedImage[3];
    public static BufferedImage level1;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(loadImage("./res/textures/playerSheet.png"));
        for (int i = 0; i < columns; i++ ) {
            playerRight[i] = sheet.crop(x,y,width,height);
            x+=width;
        }
        y=height;
        x=width*(columns-1);
        for (int i = 0; i < columns; i++ ) {
            playerLeft[i] = sheet.crop(x,y,width,height);
            x-=width;
        }
        y=height*2;
        x=0;
        for (int i=0;i<pAttackR.length;i++) {
            pAttackR[i] = sheet.crop(x,y,width,height);
            x+=width;
        }

        level1 = loadImage("./res/levels/level1.png ");
    }

    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
