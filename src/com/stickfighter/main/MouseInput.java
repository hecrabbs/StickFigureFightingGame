package com.stickfighter.main;

import com.stickfighter.enumStates.GameState;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {


    public Boolean hover;

    public MouseInput(Boolean hover) {
        this.hover = hover;

    }

    public void mouseEntered(MouseEvent e) {
       this.hover = true;
       System.out.println("HOVER");
    }

    public void mouseExited(MouseEvent e) {
        this.hover = false;
    }

}