package com.stickfighter.main;

import java.awt.Graphics;
import java.awt.Color;

public class Enemy extends GameObject {

    private GameObject player;

    public Enemy(int x, int y, ID id, GameObject player) {
        super(x, y, id);
        this.player = player;
        this.width = 25;
        this.height = 25;

        this.velX = 2;
        this.velY = 2;

    }

    public void tick(double delta) {
        follow(this.player, delta);
        // if (detectCollision(this.player)) {
        //     collide(this.player);
        // }

    }

    public void render(Graphics g, double delta) {
        g.setClip(this.x - this.width / 2, this.y - height / 2, this.width, this.height);
        this.clip = g.getClipBounds();
        g.setColor(Color.RED);
        g.fillRect(this.x - this.width / 2, this.y - height / 2, this.width, this.height);

    }

}