package com.stickfighter.main;

import com.stickfighter.enumStates.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class JLabelButton extends MouseAdapter {

    private final Game game;
    private JLabel label;
    public final GameState gameState;
    public boolean hover = false;
    public boolean clicked = false;

    public JLabelButton(Game game, Rectangle rect, GameState gameState) {
        this.game = game;
        this.gameState = gameState;
        label = new JLabel();
        label.setBounds(rect);
        label.addMouseListener(this);
        game.add(label);
    }

    public void mouseEntered(MouseEvent e) {
        hover = true;
    }

    public void mouseExited(MouseEvent e) {
        hover = false;
    }

    public void mouseClicked(MouseEvent e) {
        clicked = true;
    }

    public void mouseReleased(MouseEvent e) {
        clicked = false;
    }

    public void removeButton() {
        game.remove(label);
    }
}
