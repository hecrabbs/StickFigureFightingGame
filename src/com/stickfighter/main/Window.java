package com.stickfighter.main;

import com.stickfighter.enumStates.GameMenu;
import com.stickfighter.enumStates.GameStateManager;

import java.awt.*;

import javax.swing.*;

public class Window {
    public static JFrame frame;
//    public static JLabel playLabel;
    private int WIDTH = Game.WIDTH;
    private int HEIGHT = Game.HEIGHT;

    public Window(int width, int height, String title, Game game) {
        frame = new JFrame(title);
//        playLabel = new JLabel();
        frame.setSize(new Dimension(WIDTH,HEIGHT));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.setLayout(null);
        frame.add(game);


        game.start();

    }

    public static void main(String[] args) {

    }

}