package com.DodgeAndLast;

import java.awt.Graphics;
import java.awt.Color;


// player´s health bar
public class HUD {

    public static int health = 100;

    private int score = 0;
    private int level = 1;

    public void tick(){
        health = Game.clamp(health, 0, 100);
        score++;
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(Game.width / 2 - 100, 10, 200, 30);
        g.setColor(Color.green);
        g.fillRect(Game.width / 2 - 100, 10, health * 2, 30);
        g.setColor(Color.white);
        g.drawRect(Game.width / 2 - 100, 10, 200, 30);

        // score and level
        g.drawString("Score: " + score, Game.width / 2 - 25, 60);
        g.drawString("Level: " + level, Game.width / 2 - 25, 75);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
