package com.stickfighter.enumStates;

import com.stickfighter.main.Game;
import com.stickfighter.main.Handler;

import java.awt.*;
import java.util.LinkedList;

public class LevelHandler {

    private final Graphics g;
    private final Handler handler;
    LinkedList<GameState> levels = new LinkedList<>();
    public int currentLevel = 0;

    public LevelHandler(Graphics g, Handler handler) {
        levels.add(null);
        GameState l1 = new Level1();
        levels.add(l1);
        GameState l2 = new Level2();
        levels.add(l2);
        this.g = g;
        this.handler = handler;
    }

    public void renderNextLevel() {
        handler.getGameObjects().clear();
        handler.addObject(Game.p1);
        currentLevel++;
        levels.get(currentLevel).render(g);
    }

    public LinkedList<GameState> getLevels() {
        return levels;
    }

    public void restart() {
        levels.get(1).render(g);
    }

}
