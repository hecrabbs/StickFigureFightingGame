package com.stickfighter.enumStates;
import java.awt.*;
import com.stickfighter.main.Game;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Rectangle;

public class Menu {
    //adding buttons to the screen
    Rectangle play=new Rectangle(Game.WIDTH/2-80,300,100,74);
    Rectangle help=new Rectangle(Game.WIDTH/2-80,400,100,74);
    Rectangle quit=new Rectangle(Game.WIDTH/2-80,500,100,74);

    public void renderScreen(Graphics g){
        Font font=new Font("arial",Font.BOLD,60);
        g.setFont(font);
        g.setColor(Color.PINK);
        g.drawString("Stick Figure Fighting", Game.WIDTH/4,100);

        Font fnt=new Font("arial",Font.PLAIN,30);
        g.setFont(fnt);
        g.setColor(Color.ORANGE);
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(play);
        g2d.draw(help);
        g2d.draw(quit);
        g.drawString("Play", Game.WIDTH/2-60,350);//Press space to play
        g.drawString("Help", Game.WIDTH/2-60,450);//Need to Implement
        g.drawString("Quit", Game.WIDTH/2-60,550);//Press ESC to quit
    }

}
