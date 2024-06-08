package com.DodgeAndLast;

import java.awt.Graphics;
import java.awt.Color;


// player´s health bar
public class HUD {

    public static int health = 100;

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(Game.width / 2 - 125, 10, 250, 30);
    }
}
