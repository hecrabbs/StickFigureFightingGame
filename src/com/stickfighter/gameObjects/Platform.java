package com.stickfighter.gameObjects;

import com.stickfighter.graphics.Assets;
import com.stickfighter.main.ID;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Platform extends GameObject {

    private final int[] sides;
    private final List<Integer> sidesList;

    public Platform(int x, int y, int width, int height, ID id, int... sides) {
        super(x, y, id);
        this.width = width;
        this.height = height;
        this.sides = sides;

        sidesList = new ArrayList<>(sides.length);
        for (int side : sides) {
            sidesList.add(side);
        }
    }

    public void tick(Double dt) {
        //Only need to update platform here if it is moving.
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int) this.x, (int) this.y, this.width, this.height);
        g.setColor(Color.BLACK);
        for (int i = 0; i < sidesList.size(); i++) {
            switch (sides[i]) {
                case 1 -> g.drawLine((int) this.x, (int) this.y, (int) this.x + this.width, (int) this.y);
                case 2 -> g.drawLine((int) this.x + this.width, (int) this.y, (int) this.x + this.width, (int) this.y + this.height);
                case 3 -> g.drawLine((int) this.x, (int) this.y + this.height, (int) this.x + this.width, (int) this.y + this.height);
                case 4 -> g.drawLine((int) this.x, (int) this.y, (int) this.x, (int) this.y + this.height);
                case 5 -> g.drawImage(Assets.grass[0], (int) this.x, (int) this.y, this.width, this.height, null);
            }
        }
    }

    public void handleCollisions(Rectangle r, Rectangle oldR, GameObject temp) {

    }

}

