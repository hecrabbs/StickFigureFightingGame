package com.stickfighter.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    private boolean jumping=false,falling=false;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }
    //jumping is buggy
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            //player movement.  Could add another player with different ID but would need multiple threads for them to move at the same time.
            if (tempObject.getID() == ID.Player) {
                switch (key) {
                    case KeyEvent.VK_SPACE://For Jumping
                        tempObject.setVelY(-5);
                        jumping=true;
                        break;
                    case KeyEvent.VK_ESCAPE:
                        System.exit(1);
                        break;
                    case KeyEvent.VK_W:
                        up = true;
                        tempObject.setVelY(-5);
                        break;
                    case KeyEvent.VK_A:
                        left = true;
                        tempObject.setVelX(-5);
                        break;
                    case KeyEvent.VK_S:
                        down = true;
                        tempObject.setVelY(5);
                        break;
                    case KeyEvent.VK_D:
                        right = true;
                        tempObject.setVelX(5);
                        break;
                }
            }
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            //player movement
            if (tempObject.getID() == ID.Player) {
                switch (key) {
                    case KeyEvent.VK_SPACE:
                        //For Jumping. This is buggy, guy jumps up but does not fall back down
                        while(jumping || falling){
                            tempObject.setVelY(tempObject.getVelY()+1);
                            if(tempObject.getVelY()==0){
                                jumping=false;falling=false;
                            }
                        }
                        break;
                    case KeyEvent.VK_W:
                        up = false;
                        if (down) {
                            tempObject.setVelY(5);
                        } else {
                            tempObject.setVelY(0);
                        }
                        break;
                    case KeyEvent.VK_A:
                        left = false;
                        if (right) {
                            tempObject.setVelX(5);
                        } else {
                            tempObject.setVelX(0);
                        }
                        break;
                    case KeyEvent.VK_S:
                        down = false;
                        if (up) {
                            tempObject.setVelY(-5);
                        } else {
                            tempObject.setVelY(0);
                        }
                        break;
                    case KeyEvent.VK_D:
                        right = false;
                        if (left) {
                            tempObject.setVelX(-5);
                        } else {
                            tempObject.setVelX(0);
                        }
                        break;
                }
            }
        }

    }

    public void keyTyped(KeyEvent e) {

    }

}