package com.stickfighter.main;

import java.util.LinkedList;
import java.awt.Graphics;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    //updates all game objects
    public void tick(double delta) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.tick(delta);
        }

    }

    //renders all game objects
    public void render(Graphics g, double delta) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.render(g, delta);
        }

    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

}