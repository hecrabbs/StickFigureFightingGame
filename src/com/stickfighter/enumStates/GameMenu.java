package com.stickfighter.enumStates;

import com.stickfighter.main.Game;
import com.stickfighter.main.JLabelButton;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Rectangle;


public class GameMenu extends GameState {

    private int w = 100;
    private int h = 74;

    private final JLabelButton playLabel;
    private final JLabelButton helpLabel;
    private final JLabelButton quitLabel;

    private final Rectangle playRect = new Rectangle(Game.WIDTH / 2 - 80, 300, w, h);
    private final Rectangle helpRect = new Rectangle(Game.WIDTH / 2 - 80, 400, w, h);
    private final Rectangle quitRect = new Rectangle(Game.WIDTH / 2 - 80, 500, w, h);

    public GameMenu(Game game) {
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

    public void render(Graphics g) {
        g.drawOval(80, 70, 150, 150);
        g.setColor(Color.YELLOW);
        g.fillOval(80, 70, 150, 150);
        g.setColor(Color.BLACK);
        g.fillOval(120, 120, 15, 25);
        g.fillOval(170, 120, 15, 25);
        g.drawArc(120, 160, 70, 40, 180, 180);

        Font font = new Font("arial", Font.BOLD, 60);
        g.setFont(font);
        g.setColor(Color.PINK);
        g.drawString("Trouble in ChinaTown", Game.WIDTH / 4, 100);

        Font fnt = new Font("arial", Font.PLAIN, 30);
        g.setFont(fnt);

        Graphics2D g2d = (Graphics2D) g;
        if (playLabel.hover) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.ORANGE);
        }
        g2d.draw(playRect);
        g.drawString("Play", Game.WIDTH / 2 - 60, 350);//Press space to play
        if (helpLabel.hover) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.ORANGE);
        }
        g2d.draw(helpRect);
        g.drawString("Help", Game.WIDTH / 2 - 60, 450);//Press H for help
        if (quitLabel.hover) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.ORANGE);
        }
        g2d.draw(quitRect);
        g.drawString("Quit", Game.WIDTH / 2 - 60, 550);//Press ESC to quit
    }
}
