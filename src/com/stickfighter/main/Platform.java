package com.stickfighter.main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Platform extends GameObject {

    private int[] side;
    private List<Integer> list;

    public Platform(int x, int y, int width, int height, ID id, int... side) {
        super(x, y, id);
        this.width = width;
        this.height = height;
        this.side = side;

        list = new ArrayList<Integer>(side.length);
        for (int i : side) {
            list.add(i);
        }
    }

    public void tick() {
        //Only need to update platform here if it is moving.
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int) this.x, (int) this.y, this.width, this.height);
        g.setColor(Color.BLACK);
        for (int i = 0; i < list.size(); i++) {
            switch (side[i]) {
                case 1 -> g.drawLine((int) this.x, (int) this.y, (int) this.x + this.width, (int) this.y);
                case 2 -> g.drawLine((int) this.x + this.width, (int) this.y, (int) this.x + this.width, (int) this.y + this.height);
                case 3 -> g.drawLine((int) this.x, (int) this.y + this.height, (int) this.x + this.width, (int) this.y + this.height);
                case 4 -> g.drawLine((int) this.x, (int) this.y, (int) this.x, (int) this.y + this.height);
            }
        }

    }
}

