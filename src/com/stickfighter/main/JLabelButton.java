package com.stickfighter.main;

import com.stickfighter.enumStates.StateID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JLabelButton extends MouseAdapter {

    public final Game game;
    private JLabel label;
    public final StateID stateID;
    public boolean hover = false;
    public boolean clicked = false;

    public JLabelButton(Game game, Rectangle rect, StateID stateID) {
        this.game = game;
        this.stateID = stateID;
        label = new JLabel();
        label.setBounds(rect);
        label.addMouseListener(this);
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

    public void removeButton() {
        game.remove(label);
    }
    public void addButton() { game.add(label);}
}
