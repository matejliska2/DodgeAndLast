package com.DodgeAndLast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KeyInputTest {

    private KeyInput keyInput;
    private Handler handler;
    private GameObject player;

    @BeforeEach
    void setUp() {
        handler = new Handler();
        player = new Player(100, 100, ID.Player, handler);
        handler.addObject(player);
        keyInput = new KeyInput(handler);
    }

    @Test
    void testKeyPressed() {
        keyInput.keyPressed(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_D, 'D'));
        assertEquals(5, player.getVelX());

        keyInput.keyPressed(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_A, 'A'));
        assertEquals(-5, player.getVelX());

        keyInput.keyPressed(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_W, 'W'));
        assertEquals(-5, player.getVelY());

        keyInput.keyPressed(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_S, 'S'));
        assertEquals(5, player.getVelY());
    }

    @Test
    void testKeyReleased() {
        keyInput.keyPressed(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_D, 'D'));
        keyInput.keyReleased(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_D, 'D'));
        assertEquals(0, player.getVelX());

        keyInput.keyPressed(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_A, 'A'));
        keyInput.keyReleased(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_A, 'A'));
        assertEquals(0, player.getVelX());

        keyInput.keyPressed(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_W, 'W'));
        keyInput.keyReleased(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_W, 'W'));
        assertEquals(0, player.getVelY());

        keyInput.keyPressed(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_S, 'S'));
        keyInput.keyReleased(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_S, 'S'));
        assertEquals(0, player.getVelY());
    }
}
