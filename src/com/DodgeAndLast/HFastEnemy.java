package com.DodgeAndLast;

import java.awt.*;

public class HFastEnemy extends GameObject{

    private Handler handler;
    // velocity / movement of the horizontal fast enemy
    public HFastEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 11;
        velY = 1;
    }
    // hitboxes of the horizontal enemy
    public Rectangle getBounds(){
        return new Rectangle(x, y, 16, 16);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        // border for the horizontal fast enemy
        if(y <= 0 || y >= Game.height - 60) velY *= -1;
        if(x <= 0 || x >= Game.width - 33) velX *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, Color.cyan, 16, 16, 0.02f, handler));
    }

    // color and size of the horizontal fast enemy
    @Override
    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(x, y, 16, 16);
    }
}
