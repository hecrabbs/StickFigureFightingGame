package com.stickfighter.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {
    private int index;
    private float interval;
    private long timer, now, lastTime;

    private BufferedImage[] images;
    private BufferedImage currentImg;
    public boolean done;

    public Animation(float interval, BufferedImage[] images) {
        this.interval = interval;
        this.images = images;
        index = 0;
        timer = 0;
        now = 0;
        lastTime = System.currentTimeMillis();
        done = false;
    }

    public void tick() {
        now = System.currentTimeMillis();
        timer += now - lastTime;
        lastTime = now;

        if (timer >= interval) {
            index++;
            timer = 0;

            if (index >= images.length) {
                done = true;
                index = 0;
            }
        }
    }

    public void render(Graphics g, int x, int y, int scaleX, int scaleY) {
        g.drawImage(images[index], x-15, y, scaleX, scaleY, null);
    }

//
//    public void renderEntireAnimation(Graphics g, int x, int y, int scaleX, int scaleY) {
//
//    }

}
