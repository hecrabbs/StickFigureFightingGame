package com.stickfighter.graphics;

import com.stickfighter.main.Game;
import com.stickfighter.main.Player;

import java.awt.*;

public class HUD {

    private int width = 300;
    private int height = 40;
    private int x = Game.WIDTH-width-30;
    private int y = 50;
    private int healthWidth;

    public void tick() {
        if(Player.health <= 0) {
            healthWidth = 0;
        } else {
            healthWidth = Player.health*width/100;
        }
    }

    public void render(Graphics g) {

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, width, height);
        g.setColor(Color.getHSBColor( (1f * Player.health) / 360, 1f, 1f));
        g.fillRect(x, y, healthWidth, height);
        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);
        g.setColor(Color.GREEN);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Health", x, y);
    }
}
