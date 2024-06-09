package com.DodgeAndLast;

import java.awt.*;
import java.util.Random;

public class SideBullet extends GameObject{

    private Handler handler;
    Random r = new Random();

    private int height = r.nextInt(50);
    private int width = r.nextInt(50);

    // velocity / movement of the boss bullets
    public SideBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = (r.nextInt(8 - -8) + -8);
        velY = 0;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if(x >=  Game.width) handler.removeObject(this);

    }

    // color and size of the boss bullets
    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }
}
