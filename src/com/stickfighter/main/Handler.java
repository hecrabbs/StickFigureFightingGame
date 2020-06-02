package com.stickfighter.main;

import javax.swing.*;
import java.util.LinkedList;
import java.awt.Graphics;

public class Handler {

    LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();

    //updates all game gameObjects
    public void tick() {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);

            tempObject.tick();
        }
    }

    //renders all game gameObjects
    public void render(Graphics g) {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);

            tempObject.render(g);
        }
    }

    public void addObject(GameObject gameObjects) {
        this.gameObjects.add(gameObjects);
    }

    public void removeObject(GameObject gameObjects) {
        this.gameObjects.remove(gameObjects);
    }

    public void removeAll(){
        for(int i=0;i<this.gameObjects.size();i++){// Time Complexity: O(n).
            this.gameObjects.remove(i); }
//    this.gameObjects.clear(); replace this method with built in clear() method
    }


    public LinkedList<GameObject> getObject() {
        return gameObjects;
    }
}