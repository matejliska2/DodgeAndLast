package com.DodgeAndLast;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    //loop through all game objects
    public void Tick(){
        for (int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }
    public void render(Graphics g){

    }
}
