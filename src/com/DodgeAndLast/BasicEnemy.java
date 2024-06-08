package com.DodgeAndLast;

import java.awt.*;

public class BasicEnemy extends GameObject{

    // velocity / movement of the basic enemy
    public BasicEnemy(int x, int y, ID id) {
        super(x, y, id);
        velX = 5;
        velY = 5;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        // border for the basic enemy
        if(y <= 0 || y >= Game.height - 60) velY *= -1;
        if(x <= 0 || x >= Game.width - 33) velX *= -1;
    }

    // color and size of the basic enemy
    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 16, 16);
    }
}
