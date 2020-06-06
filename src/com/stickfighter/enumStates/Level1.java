package com.stickfighter.enumStates;

import com.stickfighter.graphics.Assets;

import java.awt.*;

public class Level1 extends GameState {

    public Level1() {
        this.image = Assets.level1;
    }

    public void render(Graphics g) {
        makeLevelFromImage(handler);
    }

    public void init() {
    }

}
