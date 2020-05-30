package com.stickfighter.enumStates;

import com.stickfighter.main.Game;
import com.stickfighter.main.JLabelButton;
import com.stickfighter.main.KeyInput;

import java.awt.*;
import java.util.LinkedList;

public abstract class GameStateManager {

    public final LinkedList<JLabelButton> buttons = new LinkedList<JLabelButton>();

    public abstract void renderScreen(Graphics g);

    public abstract void init();

    public void tick() {
        for (int i = 0; i < buttons.size(); i++) {
            JLabelButton tempButton = buttons.get(i);
            if (tempButton.clicked) {
                for (JLabelButton button : buttons) {
                    button.removeButton();
                }
                if (tempButton.gameState == null) {
                    System.exit(0);
                } else {
                    Game.setState(tempButton.gameState);
                    tempButton.clicked = false;
                    tempButton.hover = false;
                }
            }
        }
    }
}
