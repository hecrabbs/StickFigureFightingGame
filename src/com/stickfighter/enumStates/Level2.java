package com.stickfighter.enumStates;

import com.stickfighter.graphics.Assets;
import com.stickfighter.main.Game;

import java.awt.*;

public class Level2 extends GameState {

    public Level2() {
        this.image = Assets.level2;
    }

    public void render(Graphics g) {
        makeLevelFromImage(handler);
    }

    public void init() {
    }
}
