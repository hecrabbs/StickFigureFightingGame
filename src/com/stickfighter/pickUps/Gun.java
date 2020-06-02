/*package com.stickfighter.pickUps;

import com.stickfighter.main.Player;

import java.awt.*;

public class Gun extends Items {
    public int ammo=10;

    public Gun(int xPos, int yPos, int damage, ItemID itemID) {
        super(xPos, yPos, itemID);
        this.dam=damage;
    }

    public void tick() {

    }

    public void render(Graphics g) {

    }

    public void useItem(Player player) { }//not used for weapons

    public Bullet shoot(Player p) {
        //ammo--;
        Bullet b=new Bullet(p);
        if(p.isFacingRight()){
            //Bullet b=new Bullet(p);
            b.setVelocity(5);
            return b;
        } else{
            //Bullet b=new Bullet(p);
            b.setVelocity(-5);
            return b;
        }
        //return null;
    }

}*/
