package com.stickfighter.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;
import  java.util.LinkedList;

//Run from src directory with: javac ./com/stickfighter/main/*.java && java com.stickfighter.main.Game
public class Game extends Canvas implements Runnable {

    //generated serial version UID (whatever that is)
    private static final long serialVersionUID = 1856390909208917103L;

    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;

    //need to implement maxfps
    private int FPS = 60;

    private Thread thread;
    private boolean running = false;

    private Random r;
    private Handler handler;

    public static Player p1;
    public static LinkedList<GameObject> gameObjects;

    //initializing thins ing constructor rn. Add init() method??
    public Game() {
        handler = new Handler();
        gameObjects=handler.getObject();


        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "Stick Fighter", this);

        r = new Random();
        p1=new Player(r.nextInt(WIDTH), r.nextInt(HEIGHT),handler, ID.Player);
        handler.addObject(p1);
        for (int i = 0; i < 1; i++) {
            handler.addObject((new Enemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Enemy, handler.object.getFirst())));
        }
        handler.addObject((new Platform(0,1020,ID.Platform)));
        System.out.println(gameObjects.size());

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
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        //reusable game loop
        /*"lastTime", "now," and "ns" are used to calculate "delta."
        amountOfTicks is the amount of tics/second, and ns is the amount of nanoseconds/tick.
        When delta is calculated, you have (now-lastTime)/(ns/tick),
        but now and lastTime  are in nanoseconds, so it has units "tick".
        We then add this to delta, and keep going.
        Whenever delta+=1, one tick has passed, and we therefore call the command tick()
        [[[which is explained in the video]]],
        and reset delta to 0 in the while(delta>=1) loop.
        the if(running) loop updates the window (by rendering again),
        and increases the frames with 1.
        the if(System.currentTimeMillis()-timer>1000) loop writes out the FPS once per second by
        checking if the current time is more than 1000 milliseconds (1 second) larger than "timer" was.
        IF so, we update "timer" to be 1 second later (timer+=1000;),
        and print the amount of frames that have passed, and set frames to 0.
        Since this event happens once every second, the value "frames" is the frames per second.
        stop() stops the game.*/
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick(delta);
                delta--;
            }
            if (running) {
                render(delta);
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(double delta) {
        handler.tick(delta);

    }

    private void render(double delta) {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g, delta);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game();
    }
}