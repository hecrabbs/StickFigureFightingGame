/*package com.stickfighter.pickUps;

import com.stickfighter.gameObjects.Player;

import java.awt.*;

public class healthPack extends Items {

    public healthPack(int xPos, int yPos, ItemID itemID) {
        super(xPos, yPos, itemID);
    }

    public void tick() {
    }

    public void render(Graphics g) {

    }

    public void useItem(Player player) {
        if(player.getHealth()<100 && !this.used){
            this.used=true;
            player.setHealth(100);
        }
    }

}
*/