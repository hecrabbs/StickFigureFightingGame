package com.stickfighter.enumStates;

import com.stickfighter.main.Game;
import com.stickfighter.main.JLabelButton;

import java.awt.*;

public class Help extends GameState {

    private final JLabelButton playLabel;
    private final JLabelButton quitLabel;

    private final Rectangle playRect = new Rectangle(Game.WIDTH/2-200,500,400,74);
    private final Rectangle quitRect = new Rectangle(Game.WIDTH/2-150,600,300,74);

    public Help(Game game) {
        playLabel = new JLabelButton(game, playRect, StateID.Play);
        quitLabel = new JLabelButton(game, quitRect, null);
        this.buttons.add(playLabel);
        this.buttons.add(quitLabel);
    }

    public void init() {
        for (JLabelButton button : buttons) {
            button.addButton();
        }
    }

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
        Graphics2D g2d = (Graphics2D) g;
        if (playLabel.hover) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.ORANGE);
        }
        g2d.draw(playRect);
        g.drawString("Press SPC Bar to Resume", Game.WIDTH/2-170,550);
        if (quitLabel.hover) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.ORANGE);
        }
        g2d.draw(quitRect);
        g.drawString("Press 'ESC' to Quit", Game.WIDTH/2-125,650);

        /*g.translate(Game.WIDTH/2, Game.HEIGHT/2);
        g.setColor(Color.PINK);
        g.fillRect(-3,-360,6,720);//line down the center of the screen, used for formatting
         */
    }
}
