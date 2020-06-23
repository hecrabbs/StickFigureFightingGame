package com.stickfighter.main;

import com.stickfighter.enumStates.StateID;
import com.stickfighter.gameObjects.Bullet;
import com.stickfighter.gameObjects.GameObject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private final Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (Game.getState() == StateID.Menu) {
            switch (key) {
                case KeyEvent.VK_H -> Game.setState(StateID.Help);
                case KeyEvent.VK_SPACE -> Game.setState(StateID.Play);
                case KeyEvent.VK_ESCAPE -> System.exit(1);
            }
        } else if (Game.getState() == StateID.Paused) {
            switch (key) {
                case KeyEvent.VK_H -> Game.setState(StateID.Help);
                case KeyEvent.VK_SPACE -> Game.setState(StateID.Play);
                case KeyEvent.VK_ESCAPE -> System.exit(1);
            }
        } else if (Game.getState() == StateID.Help) {
            switch (key) {
                case KeyEvent.VK_SPACE -> Game.setState(StateID.Play);
                case KeyEvent.VK_ESCAPE -> System.exit(1);
            }
        } else if (Game.getState() == StateID.GameOver) {
            switch (key) {
                case KeyEvent.VK_SPACE -> Game.restartGame();
                case KeyEvent.VK_ESCAPE -> System.exit(1);
            }
        } else if (Game.getState() == StateID.Play) {
            for (int i = 0; i < handler.size(); i++) {
                GameObject tempObject = handler.get(i);
                if (tempObject.getID() == ID.Player && !tempObject.isKnockback()) {
                    switch (key) {
                        case KeyEvent.VK_SPACE:
                            if (!tempObject.isJumping()) {
                                tempObject.setJumping(true);
                                tempObject.setVelY(-20);
                            }
                            break;
                        case KeyEvent.VK_A:
                            tempObject.setFacingRight(false);
                            tempObject.setMovingLeft(true);
                            tempObject.setVelX(-8);
                            break;
                        case KeyEvent.VK_D:
                            tempObject.setFacingRight(true);
                            tempObject.setMovingRight(true);
                            tempObject.setVelX(8);
                            break;
                        case KeyEvent.VK_J:
                            tempObject.setAttacking(true);
                            break;
                        case KeyEvent.VK_B:
                            if (tempObject.isHasGun() && tempObject.ammo > 0) {
                                tempObject.setShooting(true);
                                tempObject.ammo--;
                                Bullet b = new Bullet((int) tempObject.getX(), (int) (tempObject.getY() + 32), handler, ID.Bullet);
                                if (tempObject.isFacingRight()) {
                                    b.setVelocity(16);
                                } else {
                                    b.setVelocity(-16);
                                }
                                handler.addObject(b);
                            }
                            System.out.println("Ammo " + tempObject.ammo);
                            tempObject.setShooting(false);
                            break;
                        case KeyEvent.VK_R:
                            Game.restartGame();
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

        if (Game.getState() == StateID.Play) {
            switch (key) {
                case KeyEvent.VK_P -> Game.setState(StateID.Paused);
                case KeyEvent.VK_ESCAPE -> System.exit(0);
            }
            for (int i = 0; i < handler.size(); i++) {
                GameObject tempObject = handler.get(i);
                //player movement
                if (tempObject.getID() == ID.Player && !tempObject.isKnockback()) {
                    switch (key) {
                        case KeyEvent.VK_A:
                            tempObject.setMovingLeft(false);
                            if (tempObject.isMovingRight()) {
                                tempObject.setFacingRight(true);
                                tempObject.setVelX(8);
                            } else {
                                tempObject.setVelX(0);
                            }
                            break;
                        case KeyEvent.VK_D:
                            tempObject.setMovingRight(false);
                            if (tempObject.isMovingLeft()) {
                                tempObject.setFacingRight(false);
                                tempObject.setVelX(-8);
                            } else {
                                tempObject.setVelX(0);
                            }
                            break;
                        case KeyEvent.VK_J:
//                            tempObject.isAttacking=false;
                            break;
                        case KeyEvent.VK_B:
                            tempObject.setShooting(false);
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