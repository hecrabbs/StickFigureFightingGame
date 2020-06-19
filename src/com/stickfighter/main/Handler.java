package com.stickfighter.main;

import java.util.LinkedList;
import java.awt.Graphics;

public class Handler {

    private final LinkedList<GameObject> gameObjects = new LinkedList<>();

    public Handler() {
    }

    //updates all game gameObjects
    public void tick(Double dt) {
        //DO NOT replace with enhanced for loop. Will cause ConcurrentModificationException
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).tick(dt);
        }
    }

    //renders all game gameObjects
    public void render(Graphics g) {
        //DO NOT replace with enhanced for loop. Will cause ConcurrentModificationException
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).render(g);
        }
    }

    public void addObject(GameObject gameObjects) {
        this.gameObjects.add(gameObjects);
    }

    public void removeObject(GameObject gameObjects) {
        this.gameObjects.remove(gameObjects);
    }

    public void clear() {
        this.gameObjects.clear();
    }

    public int size() {
        return this.gameObjects.size();
    }

    public LinkedList<GameObject> getGameObjects() {
        return gameObjects;
    }
}