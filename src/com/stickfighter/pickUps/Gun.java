package com.stickfighter.pickUps;

import com.stickfighter.main.Player;

import java.awt.*;

public class Gun extends Items {

    public Gun(int xPos, int yPos, int damage, ItemID itemID) {
        super(xPos, yPos, itemID);
        this.dam=damage;
    }

    public void tick() {

    }

    public void render(Graphics g) {

    }

    public void useItem(Player player) { }//not used for weapons

    public Rectangle shoot() {
        return null;
    }

    public Rectangle hit() { return null; }
}
