package com.stickfighter.pickUps;

import com.stickfighter.main.Player;

import java.awt.*;

public abstract class Items {
    protected int dam;
    protected int xPos,yPos;
    protected boolean used,equipped;
    protected ItemID itemID;

    public Items(int xPos, int yPos, ItemID itemID){
        this.xPos=xPos;
        this.yPos=yPos;
        this.itemID=itemID;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public ItemID getItemID(){ return itemID; }

    public boolean isUsed() { return used; }

    public boolean isEquipped() { return equipped; }

    public int getxPos() { return xPos; }

    public int getyPos() { return yPos; }

    public abstract void useItem(Player player);

    public abstract Rectangle shoot();

    public abstract Rectangle hit();
}
