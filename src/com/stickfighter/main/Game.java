package com.stickfighter.main;

import com.stickfighter.enumStates.*;
import com.stickfighter.graphics.HUD;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import  java.util.LinkedList;

//Run from src directory with: javac ./com/stickfighter/main/*.java && java com.stickfighter.main.Game
public class Game extends JPanel implements Runnable {

    public static final int WIDTH = 1280;//changed from 1920
    public static final int HEIGHT = 720;//change from 1080

    private static double delta = 0;
    public static int FPS = 60;
    private static int frames = 0;

    //States declared here
    private static GameState state = GameState.Menu;
    private static GameMenu menu;
    private static Paused pause;
    private static Help help;

    private Thread thread;
    private boolean running = false;

    private Random r;
    public static Handler handler;
    public static Player p1;
    public static LinkedList<GameObject> gameObjects;
    private HUD hud;

    private BufferedImage image;
    private Graphics g;

    public Game() {
        super();
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        requestFocus();
        init();
        addKeyListener(new KeyInput(handler));

        //This image contains everything being rendered to the screen
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
        //This is the graphics of the image that we draw things to the image with
        g = image.createGraphics();

        new Window(WIDTH, HEIGHT, "Stick Fighter", this);
    }

    public void init() {
        handler = new Handler();
        hud = new HUD();
        menu = new GameMenu(this);
        pause = new Paused(this);
        help = new Help();
        gameObjects = handler.getObject();

//        Assets.init();

        r = new Random();

        p1=new Player(10, 10, handler, ID.Player);
        handler.addObject(p1);
        for (int i = 0; i < 1; i++) {
            handler.addObject((new Enemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), handler, ID.Enemy, p1)));
        }
        handler.addObject((new Platform(0, HEIGHT-50, WIDTH, 20, ID.Platform)));
        handler.addObject((new Platform(0, 2*HEIGHT/3, WIDTH/3, 50, ID.Platform)));
        handler.addObject((new Platform(WIDTH/2, HEIGHT-110, 50, 80, ID.Platform)));
    }

    public synchronized void start() {
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        /* 1 billion nano seconds per second divided by frames per second = nanoSec/frames
         * timePerTick is max amount of time allowed to run tick and render methods per 1 frame
         */
        double timePerTick = 1e9 / FPS;
        long now;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        while (running) {
            now = System.nanoTime();
            /* amount of time passed since this line was last run, divided by max time allowed
             */
            delta += (now - lastTime) / timePerTick;
            lastTime = now;

            /* once the time that has passed is greater than or equal to the max time allowed
             * tick, render, and start delta timer over
             */
            if (delta >= 1) {
                tick();
                render();
                repaint();
                frames++;
                delta--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    @Override
    protected void paintComponent(Graphics g2) {
        super.paintComponent(g2);
        if(image != null) {
            g2.drawImage(image, 0, 0, this);
        }
    }

    private void tick() {
        //GameState becomes 'Play' when the user: either exits the main menu or pause screen.
        //Refer to KeyInput class to see how the key input changes the game state.
        if(state== GameState.Play){
            handler.tick();
            hud.tick();
        } else if (state == GameState.Menu) {
            menu.tick();
        } else if (state == GameState.Paused) {
            pause.tick();
        }
    }

    private void render() {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if(state==GameState.Play) {
            handler.render(g);
            hud.render(g);
        }
        else if(state==GameState.Menu){
            menu.renderScreen(g);
        }
        else if(state==GameState.Paused){
            pause.renderScreen(g);
        }
        else if(state==GameState.Help){
            help.renderScreen(g);
        }
    }

    public static GameState getState() { return state; }

    public static void setState(GameState gameState) {
        state = gameState;
        switch (gameState) {
            case Menu -> menu.init();
            case Help -> help.init();
            case Paused -> pause.init();
        }
    }

    public static void main(String[] args) {
        //ThreadPool pool=new ThreadPool(2);//Using this to add music
        new Game();
    }
}