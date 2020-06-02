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
    public static boolean pause = false;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (Game.getState() == GameState.Menu) {
            switch (key) {
                case KeyEvent.VK_H -> Game.setState(GameState.Help);
                case KeyEvent.VK_SPACE -> Game.setState(GameState.Play);
                case KeyEvent.VK_ESCAPE -> System.exit(1);
            }
        } else if (Game.getState() == GameState.Paused) {
            switch (key) {
                case KeyEvent.VK_H -> Game.setState(GameState.Help);
                case KeyEvent.VK_SPACE -> Game.setState(GameState.Play);
                case KeyEvent.VK_ESCAPE -> System.exit(1);
            }
        } else if (Game.getState() == GameState.Help) {
            switch (key) {
                case KeyEvent.VK_SPACE -> Game.setState(GameState.Play);
                case KeyEvent.VK_ESCAPE -> System.exit(1);
            }
        } else if (Game.getState() == GameState.GameOver) {
            switch (key) {
                case KeyEvent.VK_SPACE -> Game.restartGame();//Game.handler.addLevel1(); //Game.setState(GameState.Play);
                case KeyEvent.VK_ESCAPE -> System.exit(1);
            }
        } else if (Game.getState() == GameState.Play) {
            for (int i = 0; i < handler.gameObjects.size(); i++) {
                GameObject tempObject = handler.gameObjects.get(i);
                if (tempObject.getID() == ID.Player && !tempObject.knockback) {
                    switch (key) {
                        case KeyEvent.VK_SPACE:
                            if (!tempObject.jumping) {
                                tempObject.jumping = true;
                                tempObject.setVelY(-20);
                            }
                            break;
                        case KeyEvent.VK_A:
                            tempObject.facingRight = false;
                            tempObject.movingLeft = true;
                            tempObject.setVelX(-8);
                            break;
                        case KeyEvent.VK_D:
                            tempObject.facingRight = true;
                            tempObject.movingRight = true;
                            tempObject.setVelX(8);
                            break;
                        case KeyEvent.VK_J:
                            tempObject.isAttacking = true;
                            break;
                        case KeyEvent.VK_B:
                            tempObject.shooting=true;
                            Bullet b=new Bullet((int) tempObject.getX(),(int) (tempObject.getY()+32),ID.Bullet);
                            if(tempObject.facingRight){ b.setVelocity(5); }
                            else{ b.setVelocity(-5); }
                            handler.addObject(b);
                            break;
//                      case KeyEvent.VK_W:
//                        up = true;
//                        tempObject.setVelY(-5);
//                        break;
//                      case KeyEvent.VK_S:
//                        down = true;
//                        tempObject.setVelY(5);
//                        break;
                    }
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (Game.getState() == GameState.Play) {
            switch (key) {
                case KeyEvent.VK_P -> Game.setState(GameState.Paused);
                case KeyEvent.VK_ESCAPE -> System.exit(0);
            }
            for (int i = 0; i < handler.gameObjects.size(); i++) {
                GameObject tempObject = handler.gameObjects.get(i);
                //player movement
                if (tempObject.getID() == ID.Player && !tempObject.knockback) {
                    switch (key) {
                        case KeyEvent.VK_A:
                            tempObject.movingLeft = false;
                            if (tempObject.movingRight) {
                                tempObject.setVelX(8);
                            } else {
                                tempObject.setVelX(0);
                            }
                            break;
                        case KeyEvent.VK_D:
                            tempObject.movingRight = false;
                            if (tempObject.movingLeft) {
                                tempObject.setVelX(-8);
                            } else {
                                tempObject.setVelX(0);
                            }
                            break;
                        case KeyEvent.VK_J:
//                            tempObject.isAttacking=false;
                            break;
                        case KeyEvent.VK_B:
                            tempObject.shooting=false;
                            break;
//                      case KeyEvent.VK_W:
//                          up = false;
//                          if (down) {
//                            tempObject.setVelY(5);
//                          } else {
//                            tempObject.setVelY(0);
//                          }
//                          break;
//                    case KeyEvent.VK_S:
//                        down = false;
//                        if (up) {
//                            tempObject.setVelY(-5);
//                        } else {
//                            tempObject.setVelY(0);
//                        }
//                        break;
                    }
                }
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

}