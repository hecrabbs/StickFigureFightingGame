package com.stickfighter.main;

import java.awt.*;

public class Platform extends GameObject{
    private int w,h;

    public Platform(int x, int y, ID id) {
        super(x, y, id);
    }

    public void tick(double delta) {
        /*Don't know if this needs to be filled or not.*/
    }

    public void render(Graphics g, double delta) {
        g.setClip(x,y,1920,15);
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 1920,15);
        /*g.setClip(x,y,10,0);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, 10, 0);*/
    }

    public Rectangle getBounds() {
        /*
        The width and height is the same as the platform
        that was rendered in the above function.
        We need to find a way to make it universal for all platforms.
        */
        return new Rectangle(x,y,1920,15);
    }
}