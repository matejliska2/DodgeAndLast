package com.DodgeAndLast;

import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class Player extends GameObject{

    Random r = new Random();
    public Player(int x, int y, ID id) {
        super(x, y, id);

    }

    @Override
    public void tick() {

        // velocity / movement of the player
        x += velX;
        y += velY;

        // border for the player
        x = Game.clamp(x, 0, Game.width - 49);
        y = Game.clamp(y, 0, Game.height - 71);
    }

    // player´s color and size
    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);
    }
}
