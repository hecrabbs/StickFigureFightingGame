package com.stickfighter.main;

import java.awt.*;

public class Flag extends GameObject {

    public Flag(int x, int y, ID id) {
        super(x, y, id);
        this.width = 32;
        this.height = 32;
    }

    public void tick() {
    }


    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int) x, (int) y,width,height);
    }
}
