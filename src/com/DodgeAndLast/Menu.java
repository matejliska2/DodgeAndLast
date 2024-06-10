package com.DodgeAndLast;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    Random r = new Random();
    private Game game;
    private Handler handler;

    private HUD hud;

    public Menu(Game game, Handler handler, HUD hud){
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();


        if (game.gameState == Game.State.Menu) {
            // play button
            if (mouseOver(mx, my, (Game.width / 2) - 100, 90, 200, 75)) {
                game.gameState = Game.State.Game;
                handler.addObject(new BasicEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));
                handler.addObject(new Player(Game.width / 2 - 32, Game.height / 2 - 32, ID.Player, handler));
            }

            // Help button
            if (mouseOver(mx, my, (Game.width / 2) - 100, 205, 200, 75)) {
                game.gameState = Game.State.Help;
            }

            // quit button
            if (mouseOver(mx, my, (Game.width / 2) - 100, 320, 200, 75)) {
                System.exit(1);
            }
        }

        // Help back button
        if (game.gameState == Game.State.Help) {
                if (mouseOver(mx, my, (Game.width) - 150, 370, 100, 50)) {
                    game.gameState = Game.State.Menu;
                    return;
                }
        }

        // Try again button
        if (game.gameState == Game.State.End){
            if(mouseOver(mx, my, (Game.width / 2) - 80, 320, 170, 65)){
                game.gameState = Game.State.Game;
                hud.setScore(0);
                hud.setLevel(1);
                handler.addObject(new BasicEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));
                handler.addObject(new Player(Game.width / 2 - 32, Game.height / 2 - 32, ID.Player, handler));
            }
        }
    }
    public void mouseReleased(MouseEvent e){

    }

    // hitboxes for the buttons
    boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if (mx > x && mx < x + width){
            if (my > y && my < y + height) {
                return true;
            }else return false;
        }else return false;
    }

    public void tick(){

    }

    public void render(Graphics g){

        // Menu
        if(game.gameState == Game.State.Menu) {
            Font font = new Font("arial", 1, 50);
            Font font2 = new Font("arial", 1, 35);

            g.setFont(font);
            g.setColor(Color.white);
            g.drawString("Menu", (Game.width / 2) - 65, 60);

            g.setFont(font2);
            g.setColor(Color.white);
            g.drawRect((Game.width / 2) - 100, 90, 200, 75);
            g.drawString("Play", (Game.width / 2) - 40, 140);

            g.setColor(Color.white);
            g.drawRect((Game.width / 2) - 100, 205, 200, 75);
            g.drawString("Help", (Game.width / 2) - 40, 255);

            g.setColor(Color.white);
            g.drawRect((Game.width / 2) - 100, 320, 200, 75);
            g.drawString("Quit", (Game.width / 2) - 40, 370);

        // help menu
        }else if (game.gameState == Game.State.Help){
            Font font = new Font("arial", 1, 50);
            Font font2 = new Font("arial", 1, 25);
            Font font3 = new Font("arial", 1, 15);

            g.setFont(font);
            g.setColor(Color.white);
            g.drawString("Help", 40, 60);

            g.setFont(font3);
            g.drawString("Use WSAD to dodge enemies and last as long as you can", 60, 100);

            g.setFont(font2);
            g.setColor(Color.white);
            g.drawRect((Game.width) - 150, 370, 100, 50);
            g.drawString("Back", (Game.width) - 130, 405);

        // end game menu
        }else if (game.gameState == Game.State.End){
        Font font = new Font("arial", 1, 50);
        Font font2 = new Font("arial", 1, 25);
        Font font3 = new Font("arial", 1, 15);

        g.setFont(font);
        g.setColor(Color.white);
            g.drawString("Game over", (Game.width / 2) - 120, 60);

        g.setFont(font3);
        g.drawString("You lost with a score of: " + hud.getScore(), (game.width / 2)- 100, 100);

        // try again button
        g.setFont(font2);
        g.setColor(Color.white);
        g.drawRect((Game.width / 2) - 80, 320, 170, 65);
        g.drawString("Try Again", (Game.width / 2) - 55, 360);
    }
    }
}
