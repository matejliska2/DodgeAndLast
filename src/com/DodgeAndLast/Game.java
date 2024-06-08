package com.DodgeAndLast;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = -1493246767715141082L;
    public static final int width = 640, height = width / 12 * 9;
    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private HUD hud;
    private Random r;

    public Game(){

        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Window(width, height, "DodgeAndLast", this);

        hud = new HUD();

        r = new Random();

        for (int i = 0; i < 5; i++) {
            handler.addObject(new BasicEnemy(r.nextInt(width), r.nextInt(height), ID.BasicEnemy, handler));
        }

        handler.addObject(new Player(width / 2 - 32, height / 2 - 32, ID.Player, handler));

    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    // this is a known game loop not made by me !!
    @Override
    public void run() {

        //focus on the executed window on run
        this.requestFocus();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if (running)
                render();
                frames++;

                if(System.currentTimeMillis() - timer > 1000){
                    timer += 1000;
                    System.out.println("FPS: " + frames);
                    frames = 0;
                }
            }
            stop();
        }


    private void tick(){
        handler.tick();
        hud.tick();
    }

    // render for the color of the window
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0 ,width, height);

        handler.render(g);

        hud.render(g);

        g.dispose();
        bs.show();
    }

    // setting border for the entities so that they don´t go out of bounds
    public static int clamp(int var, int min, int max){
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else return var;
    }

    // runs the game
    public static void main(String args[]){
        new Game();
    }
}
