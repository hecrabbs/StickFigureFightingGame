package com.stickfighter.enumStates;
import java.awt.*;
import com.stickfighter.main.Game;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Rectangle;

public class Paused extends GameStateManager{
    Rectangle play=new Rectangle(Game.WIDTH/2-200,300,400,74);
    Rectangle help=new Rectangle(Game.WIDTH/2-150,400,300,74);
    Rectangle quit=new Rectangle(Game.WIDTH/2-150,500,300,74);

    public void renderScreen(Graphics g){
        g.setColor(Color.WHITE);
        g.drawOval(80, 70, 150, 150);
        g.setColor(Color.YELLOW);
        g.fillOval(80, 70, 150, 150);
        g.setColor(Color.BLACK);
        g.fillOval(120, 120, 15, 25);
        g.fillOval(170, 120, 15, 25);
        g.drawArc(120, 160, 70, 40, 180, 180);

        Font font=new Font("arial",Font.BOLD,60);
        g.setFont(font);
        g.setColor(Color.PINK);
        g.drawString("Game Paused", Game.WIDTH/3+18,100);

        Font fnt=new Font("arial",Font.PLAIN,30);
        g.setFont(fnt);
        g.setColor(Color.WHITE);
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(play);
        g2d.draw(help);
        g2d.draw(quit);
        g.drawString("Press SPC Bar to Resume", Game.WIDTH/2-170,350);
        g.drawString("Press 'H' for Help", Game.WIDTH/2-115,450);//Need to Implement
        g.drawString("Press 'ESC' to Quit", Game.WIDTH/2-125,550);

        /* Moving the origin to the center of the screen
        //g.translate(Game.WIDTH/2, Game.HEIGHT/2);
        //g.setColor(Color.PINK);
        //g.fillRect(-3,-360,6,720);//line down the center of the screen, used for formatting

        //Font font=new Font("arial",Font.BOLD,60);//in Case we want to offset the Pause Screen Header.
        //g.setFont(font);
        //g.setColor(Color.PINK);
        //g.drawString("Game Paused", -Game.WIDTH/3+40,-250);
         */
    }
}
