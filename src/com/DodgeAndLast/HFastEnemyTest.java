package com.DodgeAndLast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HFastEnemyTest {

    private HFastEnemy hFastEnemy;
    private Handler handler;

    @BeforeEach
    void setUp() {
        handler = new Handler();
        hFastEnemy = new HFastEnemy(100, 100, ID.HFastEnemy, handler);
    }

    @Test
    void testGetBounds() {
        Rectangle bounds = hFastEnemy.getBounds();
        assertEquals(100, bounds.x);
        assertEquals(100, bounds.y);
        assertEquals(16, bounds.width);
        assertEquals(16, bounds.height);
    }

    @Test
    void testTickMovement() {
        hFastEnemy.tick();

        assertEquals(111, hFastEnemy.getX());
        assertEquals(101, hFastEnemy.getY());
    }

    @Test
    void testTickBounceY() {
        hFastEnemy.setY(0);
        hFastEnemy.tick();

        assertTrue(hFastEnemy.getVelY() > 0);

        hFastEnemy.setY(Game.height - 60);
        hFastEnemy.tick();

        assertTrue(hFastEnemy.getVelY() < 0);
    }

    @Test
    void testTickBounceX() {
        hFastEnemy.setX(0);
        hFastEnemy.tick();

        assertTrue(hFastEnemy.getVelX() > 0);

        hFastEnemy.setX(Game.width - 33);
        hFastEnemy.tick();

        assertTrue(hFastEnemy.getVelX() < 0);
    }

    @Test
    void testRender() {
        Graphics g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();

        hFastEnemy.render(g);

        Color color = new Color(((Graphics2D) g).getBackground().getRGB());
        assertEquals(Color.CYAN, color);
    }
}
