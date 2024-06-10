package com.DodgeAndLast;

import java.awt.*;
import java.util.Random;

public class SideBullet2 extends GameObject{

    private Handler handler;
    Random r = new Random();

    private int height = r.nextInt(35);
    private int width = r.nextInt(35);

    // velocity / movement of the boss bullets2
    public SideBullet2(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = (r.nextInt(8 - -8) + -8);
        velY = 0;
    }

    // hitbox of the box bullets2
    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if(x >=  Game.width) handler.removeObject(this);

    }

    // color and size of the boss bullets2
    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }
}
