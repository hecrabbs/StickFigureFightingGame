package com.stickfighter.enumStates;

import java.awt.*;
import com.stickfighter.main.Game;

public class Help extends GameStateManager {

    Rectangle play=new Rectangle(Game.WIDTH/2-200,500,400,74);
    Rectangle quit=new Rectangle(Game.WIDTH/2-150,600,300,74);

    public void renderScreen(Graphics g){
        Font font=new Font("arial",Font.BOLD,60);
        g.setFont(font);
        g.setColor(Color.PINK);
        g.drawString("Help", Game.WIDTH/2-60,100);
        g.setColor(Color.WHITE);
        g.drawString("Controls:", Game.WIDTH/6,150);
        g.drawString("MISC:", Game.WIDTH-450,150);

        Font fnt=new Font("arial",Font.PLAIN,30);
        g.setFont(fnt);
        g.setColor(Color.WHITE);
        g.drawString("Movement:", Game.WIDTH/5,200);
        g.drawString("A: Stride Left", Game.WIDTH/5,230);
        g.drawString("D: Stride Right", Game.WIDTH/5,260);
        g.drawString("SPC: Jump", Game.WIDTH/5,290);
        g.drawString("P: Pause", Game.WIDTH-450,200);
        //g.drawString("Press 'ESC' to Quit", Game.WIDTH/2-125,650);
        g.setColor(Color.ORANGE);
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(play);
        g2d.draw(quit);
        g.drawString("Press SPC Bar to Resume", Game.WIDTH/2-170,550);
        g.drawString("Press 'ESC' to Quit", Game.WIDTH/2-125,650);

        //g.translate(Game.WIDTH/2, Game.HEIGHT/2);
        //g.setColor(Color.PINK);
        //g.fillRect(-3,-360,6,720);//line down the center of the screen, used for formatting
    }

}
