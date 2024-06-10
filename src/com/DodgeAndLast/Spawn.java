package com.DodgeAndLast;

import java.util.Random;

public class Spawn {

    private Handler handler;
    private HUD hud;
    private Random r = new Random();
    private int scoreKeep = 0;

    public Spawn (Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }

    public void tick(){
        // score adding up infinitely as long as player is alive
        scoreKeep++;

        // every 500 score add a level and reset "scoreKeep"
        if (scoreKeep >= 500){
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            // spawn enemies accordingly to the level
            if (hud.getLevel() == 2){
                handler.addObject(new BasicEnemy(r.nextInt(Game.width -50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));
            }else if (hud.getLevel() == 3){
                handler.addObject(new HFastEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.HFastEnemy, handler));
            }
            else if (hud.getLevel() == 4){
                handler.addObject(new BasicEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));
            }
            else if (hud.getLevel() == 5) {
                handler.addObject(new VFastEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.VFastEnemy, handler));
            }
            else if (hud.getLevel() == 10){
                    handler.clearEnemies();
                    handler.addObject(new SideBoss(-50, 0, ID.SideBoss, handler));
                    handler.addObject(new SideBoss2(Game.width - 10, 0, ID.SideBoss2, handler));
            }
        }
    }
}
