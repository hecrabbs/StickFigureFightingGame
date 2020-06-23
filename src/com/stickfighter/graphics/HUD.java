package com.stickfighter.graphics;

import com.stickfighter.main.Game;

import java.awt.*;

public class HUD {

    private int width = 300;
    private int height = 40;
    private int x = Game.WIDTH - width - 30;
    private int y = 50;
    private int healthWidth;

    public void tick() {
        if (Game.p1.getHealth() <= 0) {
            healthWidth = 0;
        } else {
            healthWidth = Game.p1.getHealth() * width / 100;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, width, height);
        g.setColor(Color.getHSBColor((1f * Game.p1.getHealth()) / 360, 1f, 1f));
        g.fillRect(x, y, healthWidth, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Health", x, y - 10);
    }
}
