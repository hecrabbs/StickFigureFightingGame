package com.stickfighter.main;

import com.stickfighter.gameObjects.GameObject;

public class Camera {

    private float x, y;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject player) {
        x = -player.getX() + Game.WIDTH / 2.0f;
        y = -player.getY() + Game.HEIGHT / 2.0f;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
