package com.stickfighter.main;

import java.awt.*;

public class Enemy extends GameObject {

    private GameObject player;

    public Enemy(int x, int y, ID id, GameObject player) {
        super(x, y, id);
        this.player = player;
        this.width = 25;
        this.height = 25;
    }

    public void tick(double delta) {
        follow(this.player, delta);
        if(this.getBounds().intersects(player.getBounds())) {
            this.velX = 0;
            this.velY = 0;
        } else {
            this.velX = 3;
            this.velY = 3;
        }
    }

    public void render(Graphics g, double delta) {
        g.setColor(Color.RED);
        g.fillRect(this.x, this.y, this.width, this.height);
    }

}