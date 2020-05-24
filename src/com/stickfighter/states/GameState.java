package com.stickfighter.states;

import com.stickfighter.main.KeyInput;

import java.awt.*;

public abstract class GameState {
    private GameStateManager gsm;

    public void GameState(GameStateManager gsm){
        this.gsm=gsm;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public abstract void input(KeyInput key);//might need a mouse input later, we'll figure it out, we're smart.

}
