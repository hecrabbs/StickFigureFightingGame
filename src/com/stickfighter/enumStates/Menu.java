package com.stickfighter.enumStates;
import java.awt.*;
import com.stickfighter.main.Game;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Rectangle;

public class Menu {
    //adding buttons to the screen
    Rectangle play=new Rectangle(Game.WIDTH/2-100,400,128,74);
    Rectangle quit=new Rectangle(Game.WIDTH/2-150,600,300,74);

    public void renderScreen(Graphics g){
        Font font=new Font("arial",Font.BOLD,60);
        g.setFont(font);
        g.setColor(Color.PINK);
        g.drawString("Stick Figure Fighting", Game.WIDTH/3,100);

        Font fnt=new Font("arial",Font.PLAIN,30);
        g.setFont(fnt);
        g.setColor(Color.ORANGE);
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(play);
        g2d.draw(quit);
        g.drawString("Press Spc Bar to Play", Game.WIDTH/2-64,450);
        g.drawString("Press 'ESC' to Quit", Game.WIDTH/2-130,650);
    }

}
