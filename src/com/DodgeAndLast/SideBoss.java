package com.DodgeAndLast;

import java.awt.*;
import java.util.Random;

public class SideBoss extends GameObject{

    private Handler handler;
    Random r = new Random();

    public int timer = 20;

    // velocity / movement of the side boss
    public SideBoss(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 2;
        velY = 0;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 96, 96);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if (timer <= 0){
            velX = 0;
        }else timer--;


        int spawn = r.nextInt(10);
        if (spawn == 0) handler.addObject(new SideBullet(x, (r.nextInt(Game.height - -Game.height) + -Game.height), ID.SideBullet, handler));
    }

    // color and size of the side boss
    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 48, Game.height);
    }
}
