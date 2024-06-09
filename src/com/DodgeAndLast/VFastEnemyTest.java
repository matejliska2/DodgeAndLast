package com.DodgeAndLast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class VFastEnemyTest {

    private VFastEnemy vFastEnemy;
    private Handler handler;

    @BeforeEach
    void setUp() {
        handler = new Handler();
        vFastEnemy = new VFastEnemy(100, 100, ID.VFastEnemy, handler);
    }

    @Test
    void testGetBounds() {
        Rectangle bounds = vFastEnemy.getBounds();
        assertEquals(100, bounds.x);
        assertEquals(100, bounds.y);
        assertEquals(16, bounds.width);
        assertEquals(16, bounds.height);
    }

    @Test
    void testTickMovement() {
        vFastEnemy.tick();

        assertEquals(101, vFastEnemy.getX());
        assertEquals(111, vFastEnemy.getY());
    }

    @Test
    void testTickBounceY() {
        vFastEnemy.setY(0);
        vFastEnemy.tick();

        assertTrue(vFastEnemy.getVelY() > 0);

        vFastEnemy.setY(Game.height - 60);
        vFastEnemy.tick();

        assertTrue(vFastEnemy.getVelY() < 0);
    }

    @Test
    void testTickBounceX() {
        vFastEnemy.setX(0);
        vFastEnemy.tick();

        assertTrue(vFastEnemy.getVelX() > 0);

        vFastEnemy.setX(Game.width - 33);
        vFastEnemy.tick();

        assertTrue(vFastEnemy.getVelX() < 0);
    }

    @Test
    void testTickAddTrail() {
        vFastEnemy.tick();
        assertFalse(handler.object.isEmpty());
        assertTrue(handler.object.getLast() instanceof Trail);
    }

    @Test
    void testRender() {
        Graphics g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();

        vFastEnemy.render(g);

        Color color = new Color(((Graphics2D) g).getBackground().getRGB());
        assertEquals(Color.CYAN, color);
    }
}
