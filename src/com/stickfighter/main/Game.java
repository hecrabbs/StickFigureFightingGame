package com.stickfighter.main;

import com.stickfighter.enumStates.*;
import com.stickfighter.gameObjects.Bullet;
import com.stickfighter.gameObjects.Player;
import com.stickfighter.graphics.Assets;
import com.stickfighter.graphics.HUD;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game extends JPanel implements Runnable {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    private double delta = 0;
    private int frames = 0;

    //States declared here
    private static StateID state = StateID.Menu;
    private static GameOver gameOver;
    private static GameMenu menu;
    private static Paused pause;
    private static Help help;

    private Thread thread;
    private boolean running = false;

    public static Handler handler;
    public static LevelHandler levelHandler;
    public static Player p1;
    private Camera cam;
    private Bullet bullet;//idk if this should be here rn
    private HUD hud;

    private final BufferedImage image;
    private final Graphics g;

    public Game() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        Assets.init();
        init();
        addKeyListener(new KeyInput(handler));

        //This image contains everything being rendered to the screen
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
        //This is the graphics of the image that we draw things to the image with
        g = image.createGraphics();

        levelHandler.renderNextLevel();

        new Window(WIDTH, HEIGHT, "Stick Fighter", this);
    }

    public void init() {
        handler = new Handler();
        levelHandler = new LevelHandler(g, handler);
        p1 = new Player(384, 1856, ID.Player);
        cam = new Camera(0, 0);
        hud = new HUD();
        menu = new GameMenu(this);
        menu.init();
        pause = new Paused(this);
        help = new Help(this);
        gameOver = new GameOver();
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
        int FPS = 60;
        double timePerTick = 1e9 / FPS;
        long now;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        while (running) {
            now = System.nanoTime();
            //amount of time passed since this line was last run, divided by max time allowed
            double dt = now - lastTime;
            delta += (now - lastTime) / timePerTick;
            lastTime = now;
            /* once the time that has passed is greater than or equal to the max time allowed
             * tick, render, and start delta timer over */
            if (delta >= 1) {
                tick(dt);
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

    private void tick(Double dt) {
        //GameState becomes 'Play' when the user: either exits the main menu or pause screen.
        //Refer to KeyInput class to see how the key input changes the game state.
        if (state == StateID.Play) {
            handler.tick(dt);
            cam.tick(p1);
            hud.tick();
        } else if (state == StateID.Menu) {
            menu.tick();
        } else if (state == StateID.Paused) {
            pause.tick();
        } else if (state == StateID.Help) {
            help.tick();
        }
    }

    private void render() {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if (state == StateID.Play) {
            //Solid Background Color (moves with camera)
            g.setColor(new Color(181, 234, 255));
            g.fillRect(0, 0, WIDTH, HEIGHT);

            g2d.translate(cam.getX(), cam.getY());    //Set origin at the camera position

            g.drawImage(Assets.background, -640, -180, Assets.background.getWidth() * 2, Assets.background.getHeight() * 2, null); //Draw background image
            handler.render(g);

            g2d.translate(-cam.getX(), -cam.getY()); //end camera. reset origin back to normal

            hud.render(g);
        } else if (state == StateID.Menu) {
            menu.render(g);
        } else if (state == StateID.Paused) {
            pause.render(g);
        } else if (state == StateID.Help) {
            help.render(g);
        } else if (state == StateID.GameOver) {
            gameOver.render(g);
        }
    }

    public static StateID getState() {
        return state;
    }

    public static void setState(StateID stateID) {
        state = stateID;
        switch (stateID) {
            case Menu -> menu.init();
            case Help -> help.init();
            case Paused -> pause.init();
        }
    }

    public static void restartGame() {
        handler.clear();// Preps handler for re-initialization.
        p1.revive();// Restores player health to 75.
        handler.addObject(p1);// Adding all of the game components again.
        levelHandler.restart();
        setState(StateID.Play);
    }

    public static void main(String[] args) {
        //multiThread pool=new multiThread(2);//Using this to add music
        new Game();
        //MusicPlayer music=new MusicPlayer("8 bit");
        //pool.runTask(music);
        //pool.join();
    }
}