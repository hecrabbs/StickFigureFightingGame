package com.stickfighter.main;

import com.stickfighter.enumStates.GameState;
import com.stickfighter.enumStates.Menu;
import com.stickfighter.enumStates.Paused;
import com.stickfighter.graphics.Assets;
import com.stickfighter.graphics.HUD;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.Random;
import  java.util.LinkedList;

//Run from src directory with: javac ./com/stickfighter/main/*.java && java com.stickfighter.main.Game
public class Game extends Canvas implements Runnable {
    //generated serial version UID (whatever that is)
    private static final long serialVersionUID = 1856390909208917103L;

    public static final int WIDTH = 1280;//changed from 1920
    public static final int HEIGHT = 720;//change from 1080

    private static double delta = 0;
    public static int FPS = 60;
    private static int frames = 0;
    private double dt;
    //States declared here
    private static GameState state = GameState.Menu;
    private final Menu menu;
    private Paused pause;

    private Thread thread;
    private boolean running = false;

    private Random r;
    private Handler handler;
    public static Player p1;
    public static LinkedList<GameObject> gameObjects;
    private HUD hud;

    //initializing thins ing constructor rn. Add init() method??
    public Game() {
        handler = new Handler();
        hud = new HUD();
        menu=new Menu();
        pause=new Paused();
        gameObjects=handler.getObject();

        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "Stick Fighter", this);

        Assets.init();

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
        thread.start();
        running = true;
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
        this.requestFocus();
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
            dt = now - lastTime;
            delta += (now - lastTime) / timePerTick;
            lastTime = now;

            /* once the time that has passed is greater than or equal to the max time allowed
             * tick, render, and start delta timer over
             */
            if (delta >= 1) {
                tick(delta);
                render(delta);
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

    public static int getFrames() {
        return frames;
    }

    private void tick(double dt) {
        //Once ready to test states, un-comment lines below.
        if(state==GameState.Play){
            handler.tick(delta);
            hud.tick();
        }
    }

    private void render(double dt) {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        if(state==GameState.Play) {
            handler.render(g, delta);
            hud.render(g);
        }
        else if(state==GameState.Menu){
            menu.renderScreen(g);
        }
        else if(state==GameState.Paused){
            pause.renderPaused(g);
        }
        g.dispose();
        bs.show();
    }

    public static GameState getState() { return state; }

    public static void setState(GameState gameState) { state=gameState; }



    public static void main(String[] args) {
        new Game();
    }
}