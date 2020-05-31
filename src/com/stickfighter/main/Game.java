package com.stickfighter.main;

import com.stickfighter.enumStates.*;
import com.stickfighter.graphics.Assets;
import com.stickfighter.graphics.HUD;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.LinkedList;

//Run from src directory with: javac ./com/stickfighter/main/*.java && java com.stickfighter.main.Game
public class Game extends JPanel implements Runnable {

    public static final int WIDTH = 1280;//changed from 1920
    public static final int HEIGHT = 720;//change from 1080

    private static double delta = 0;
    public static double dt = 0;
    public static int FPS = 60;
    private static int frames = 0;

    //States declared here
    private static GameState state = GameState.Menu;
    private GameOver gameOver;
    private static GameMenu menu;
    private static Paused pause;
    private static Help help;

    private Thread thread;
    private boolean running = false;

    public static Handler handler;
    public static Camera cam;
    public static Player p1;
    public static LinkedList<GameObject> gameObjects;
    private HUD hud;

    private BufferedImage image;
    private Graphics g;

    public Game() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        Assets.init();
        init();
        new Level1();
        Level1.makeLevelFromImage(handler);
        addKeyListener(new KeyInput(handler));

        //This image contains everything being rendered to the screen
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
        //This is the graphics of the image that we draw things to the image with
        g = image.createGraphics();

        new Window(WIDTH, HEIGHT, "Stick Fighter", this);
    }

    public void init() {
        handler = new Handler();
        p1 = new Player(64, 0, handler, ID.Player);
        cam = new Camera(0, 0);
        hud = new HUD();
        menu = new GameMenu(this);
        menu.init();
        pause = new Paused(this);
        help = new Help(this);
        gameOver = new GameOver();
        gameObjects = handler.getObject();

        handler.addObject(p1);
//        for (int i = 0; i < 1; i++) {
//            handler.addObject((new Enemy(WIDTH / 2, 0, handler, ID.Enemy, p1)));
//        }
//        handler.addObject((new Platform(0, HEIGHT - 50, WIDTH, 20, ID.Platform)));
//        handler.addObject((new Platform(0, 2 * HEIGHT / 3, WIDTH / 5, 50, ID.Platform)));
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
            //amount of time passed since this line was last run, divided by max time allowed
            dt = now - lastTime;
            delta += (now - lastTime) / timePerTick;
            lastTime = now;
            /* once the time that has passed is greater than or equal to the max time allowed
             * tick, render, and start delta timer over */
            if (delta >= 1) {
                tick();
                render();
                repaint();
                frames++;
                delta--;
            }
            //Prints fps to console
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
        if (image != null) {
            g2.drawImage(image, 0, 0, this);
        }
    }

    private void tick() {
        //GameState becomes 'Play' when the user: either exits the main menu or pause screen.
        //Refer to KeyInput class to see how the key input changes the game state.
        if (state == GameState.Play) {
            handler.tick();
            cam.tick(p1);
            hud.tick();
        } else if (state == GameState.Menu) {
            menu.tick();
        } else if (state == GameState.Paused) {
            pause.tick();
        } else if (state == GameState.Help) {
            help.tick();
        }
    }

    private void render() {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if (state == GameState.Play) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            //Set origin at the camera position
            g2d.translate(cam.getX(), cam.getY()); //start camera.
            handler.render(g);
            //reset origin back to normal
            g2d.translate(-cam.getX(), -cam.getY()); //end camera. Everything after this will move with camera
            hud.render(g);
        } else if (state == GameState.Menu) {
            menu.renderScreen(g);
        } else if (state == GameState.Paused) {
            pause.renderScreen(g);
        } else if (state == GameState.Help) {
            help.renderScreen(g);
        } else if (state == GameState.GameOver) {
            gameOver.renderScreen(g);
        }
    }

    public static GameState getState() {
        return state;
    }

    public static void setState(GameState gameState) {
        state = gameState;
        switch (gameState) {
            case Menu -> menu.init();
            case Help -> help.init();
            case Paused -> pause.init();
        }
    }

    public static void restartGame(){
        handler.removeAll();// Preps handler for re-initialization.
        p1.revive();// Restores player health to 75.
        handler.addObject(p1);// Adding all of the game components again.
        new Level1();// As we add levels we should keep track of what level is currently being played.
        Level1.makeLevelFromImage(Game.handler);
        setState(GameState.Play);
    }

    public static void main(String[] args) {
        //ThreadPool pool=new ThreadPool(2);//Using this to add music
        new Game();
    }
}