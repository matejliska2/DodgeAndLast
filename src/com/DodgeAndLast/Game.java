package com.DodgeAndLast;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = -1493246767715141082L;
    public static final int width = 640, height = width / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private Random r;
    Handler handler;
    HUD hud;
    Spawn spawner;

    Menu menu;

    public enum State{
        Menu,
        Game,
        Help,
        End
    }

    public static State gameState = State.Menu;

    public Game(){

        hud = new HUD();
        handler = new Handler();
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        new Window(width, height, "DodgeAndLast", this);


        spawner = new Spawn(handler, hud);

        r = new Random();

        if (gameState == State.Game){
            handler.addObject(new BasicEnemy(r.nextInt(Game.width -50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));

            handler.addObject(new Player(width / 2 - 32, height / 2 - 32, ID.Player, handler));
        }


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


    void tick(){
        handler.tick();

        if (gameState == State.Game){

        hud.tick();
        spawner.tick();

        if (hud.health <= 0){
            hud.health = 100;
            gameState = State.End;
            handler.clearEnemies();
        }

    } else if (gameState == State.Menu || gameState == State.End){
            menu.tick();
        }
    }

    // render for the color of the window
    void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0 ,width, height);

        handler.render(g);
        if(gameState == State.Game){
            hud.render(g);
        } else if (gameState == State.Menu || gameState == State.Help || gameState == State.End) {
            menu.render(g);
        }

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
