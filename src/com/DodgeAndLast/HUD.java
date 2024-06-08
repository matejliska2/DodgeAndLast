package com.DodgeAndLast;

import java.awt.Graphics;
import java.awt.Color;


// player´s health bar
public class HUD {

    public static int health = 100;

    public void tick(){
        health = Game.clamp(health, 0, 100);
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(Game.width / 2 - 100, 10, 200, 30);
        g.setColor(Color.green);
        g.fillRect(Game.width / 2 - 100, 10, health * 2, 30);
        g.setColor(Color.white);
        g.drawRect(Game.width / 2 - 100, 10, 200, 30);
    }
}
