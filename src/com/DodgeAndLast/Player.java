package com.DodgeAndLast;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject{
    Handler handler;
    Random r = new Random();
    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 32, 32);
    }

    // collision
    private void collision(){
        for(int i =0; i < handler.object.size(); i++){

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.VFastEnemy || tempObject.getId() == ID.HFastEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.health -= 2;
                }
            }
        }
    }
    @Override
    public void tick() {

        // velocity / movement of the player
        x += velX;
        y += velY;

        // border for the player
        x = Game.clamp(x, 0, Game.width - 49);
        y = Game.clamp(y, 0, Game.height - 71);

        handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.05f, handler));

        collision();
    }



    // player´s color and size
    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);
    }

}
