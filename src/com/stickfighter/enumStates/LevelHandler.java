package com.stickfighter.enumStates;

import com.stickfighter.main.Game;
import com.stickfighter.main.Handler;

import java.awt.*;
import java.util.LinkedList;

public class LevelHandler {

    private final Graphics g;
    private final Handler handler;
    private final LinkedList<GameState> levels = new LinkedList<>();
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
        handler.clear();
        handler.addObject(Game.p1);
        currentLevel++;
        levels.get(currentLevel).render(g);
    }

    public LinkedList<GameState> getLevels() {
        return levels;
    }

    public GameState getLevel(int i) {
        return levels.get(i);
    }

    public void restart() {
        levels.get(currentLevel).render(g);
    }

}
