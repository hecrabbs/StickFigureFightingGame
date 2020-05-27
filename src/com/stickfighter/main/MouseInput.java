package com.stickfighter.main;

import com.stickfighter.enumStates.GameState;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

//    private Handler handler;
//
//    public MouseInput(Handler handler) {
//        this.handler = handler;
//    }
    public MouseInput() {

    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("Entered");
    }

}