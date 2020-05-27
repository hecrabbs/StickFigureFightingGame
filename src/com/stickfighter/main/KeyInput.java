package com.stickfighter.main;

import com.stickfighter.enumStates.GameState;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    public static boolean pause=false;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.gameObjects.size(); i++) {
            GameObject tempObject = handler.gameObjects.get(i);
            //adding conditional for if the game state is "Play".
            if(Game.getState()==GameState.Play) {
                //player movement.  Could add another player with different ID but would need multiple threads for them to move at the same time.
                if (tempObject.getID() == ID.Player) {
                    switch (key) {
                        case KeyEvent.VK_P:
                            pause = !pause;
                            break;
                        case KeyEvent.VK_SPACE://For Jumping
                            if (!tempObject.jumping && !tempObject.falling) {
                                tempObject.setVelY(-30);
                                tempObject.jumping = true;
                                tempObject.falling = true;
                            }
                            break;
                        case KeyEvent.VK_ESCAPE:
                            System.exit(1);
                            break;
//                    case KeyEvent.VK_W:
//                        up = true;
//                        tempObject.setVelY(-5);
//                        break;
                        case KeyEvent.VK_A:
                            if(!tempObject.knockback) {
                                left = true;
                                tempObject.setVelX(-8);
                            }
                            else if(tempObject.knockback){
                                left=true;
                                tempObject.jumping=true;
                                //tempObject.setVelX(0);
                            }
                            break;
//                    case KeyEvent.VK_S:
//                        down = true;
//                        tempObject.setVelY(5);
//                        break;
                        case KeyEvent.VK_D:
                            if(!tempObject.knockback){
                                right = true;
                                tempObject.setVelX(8);
                            }
                            else if(tempObject.knockback) {
                                right = true;
                                tempObject.jumping=true;
                                //tempObject.setVelX(0);
                            }
                            break;
                    }
                } else if (key == KeyEvent.VK_P) {
                    Game.setState(GameState.Paused);
                }
            }
            //Enhanced Switch Statements. Similar to pattern matching in OCaml.
            else if(Game.getState()==GameState.Menu){
                switch (key) {
                    case KeyEvent.VK_H -> Game.setState(GameState.Help);
                    case KeyEvent.VK_SPACE -> Game.setState(GameState.Play);
                    case KeyEvent.VK_ESCAPE -> System.exit(1);
                }
            }
            else if(Game.getState()==GameState.Paused){
                switch (key) {
                    case KeyEvent.VK_H -> Game.setState(GameState.Help);
                    case KeyEvent.VK_SPACE -> Game.setState(GameState.Play);
                    case KeyEvent.VK_ESCAPE -> System.exit(1);
                }
            }
            else if(Game.getState()==GameState.Help){
                switch (key) {
                    case KeyEvent.VK_SPACE -> Game.setState(GameState.Play);
                    case KeyEvent.VK_ESCAPE -> System.exit(1);
                }
            }
            else if(Game.getState()==GameState.GameOver) {
                switch (key) {
                    case KeyEvent.VK_SPACE -> Game.setState(GameState.Play);
                    case KeyEvent.VK_ESCAPE -> System.exit(1);
                }
            }

        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.gameObjects.size(); i++) {
            GameObject tempObject = handler.gameObjects.get(i);
            //player movement
            if (tempObject.getID() == ID.Player) {
                switch (key) {
                    case KeyEvent.VK_SPACE:
                        tempObject.jumping = false;
                        break;
//                    case KeyEvent.VK_W:
//                        up = false;
//                        if (down) {
//                            tempObject.setVelY(5);
//                        } else {
//                            tempObject.setVelY(0);
//                        }
//                        break;
                    case KeyEvent.VK_A:
                        left = false;
                        if (right) {
                            tempObject.setVelX(8);
                        } else {
                            tempObject.setVelX(0);
                        }
                        break;
//                    case KeyEvent.VK_S:
//                        down = false;
//                        if (up) {
//                            tempObject.setVelY(-5);
//                        } else {
//                            tempObject.setVelY(0);
//                        }
//                        break;
                    case KeyEvent.VK_D:
                        right = false;
                        if (left) {
                            tempObject.setVelX(-8);
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