package com.stickfighter.main;

import java.util.LinkedList;
import java.awt.Graphics;

public class Handler {

    LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();

    //updates all game gameObjects
    public void tick(double delta) {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);

            tempObject.tick(delta);
        }

    }

    //renders all game gameObjects
    public void render(Graphics g, double delta) {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);

            tempObject.render(g, delta);
        }

    }

    public void addObject(GameObject gameObjects) {
        this.gameObjects.add(gameObjects);
    }

    public void removeObject(GameObject gameObjects) {
        this.gameObjects.remove(gameObjects);
    }

    public LinkedList<GameObject> getObject() {
        return gameObjects;
    }
}