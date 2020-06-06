package com.stickfighter.enumStates;

import com.stickfighter.main.Game;
import com.stickfighter.main.JLabelButton;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Rectangle;

public class Paused extends GameState {

    private int w = 400;
    private int h = 74;

    private final JLabelButton playLabel;
    private final JLabelButton helpLabel;
    private final JLabelButton quitLabel;

    private final Rectangle playRect = new Rectangle(Game.WIDTH/2-200,300,400,74);
    private final Rectangle helpRect = new Rectangle(Game.WIDTH/2-150,400,300,74);
    private final Rectangle quitRect = new Rectangle(Game.WIDTH/2-150,500,300,74);

    public Paused(Game game) {
        playLabel = new JLabelButton(game, playRect, StateID.Play);
        helpLabel = new JLabelButton(game, helpRect, StateID.Help);
        quitLabel = new JLabelButton(game, quitRect, null);
        this.buttons.add(playLabel);
        this.buttons.add(helpLabel);
        this.buttons.add(quitLabel);
    }

    public void init() {
        for (JLabelButton button : buttons) {
            button.addButton();
        }
    }

    public void render(Graphics g){
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
        if (playLabel.hover) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.ORANGE);
        }
        g2d.draw(playRect);
        g.drawString("Press SPC Bar to Resume", Game.WIDTH/2-170,350);
        if (helpLabel.hover) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.ORANGE);
        }
        g2d.draw(helpRect);
        g.drawString("Press 'H' for Help", Game.WIDTH/2-115,450);//Need to Implement
        if (quitLabel.hover) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.ORANGE);
        }
        g2d.draw(quitRect);
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
