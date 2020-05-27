package com.stickfighter.enumStates;

import com.stickfighter.main.Game;

import java.awt.*;

public class GameOver extends GameStateManager{

    public void renderScreen(Graphics g) {
        Font font=new Font("arial",Font.BOLD,60);
        g.setFont(font);
        g.setColor(Color.PINK);
        g.drawString("Game Over", Game.WIDTH/3+36,100);

        Font fnt=new Font("arial",Font.PLAIN,30);
        g.setFont(fnt);
        g.setColor(Color.WHITE);
        Graphics2D g2d = (Graphics2D) g;
//        g2d.draw(play);
//        g2d.draw(quit);
        g.drawString("Press SPC Bar to Play Again", Game.WIDTH/2-190,350);
        g.drawString("Press 'ESC' to Quit", Game.WIDTH/2-125,450);

        //g.translate(Game.WIDTH/2, Game.HEIGHT/2);
        //g.setColor(Color.PINK);
        //g.fillRect(-3,-360,6,720);//line down the center of the screen, used for formatting
    }

    public void init() {

    }
}
