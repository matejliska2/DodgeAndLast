package com.DodgeAndLast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SideBullet2Test {

    private SideBullet2 sideBullet2;
    private Handler handler;

    @BeforeEach
    void setUp() {
        handler = new Handler();
        sideBullet2 = new SideBullet2(100, 100, ID.SideBullet2, handler);
    }

    @Test
    void testGetBounds() {
        Rectangle bounds = sideBullet2.getBounds();
        assertEquals(100, bounds.x);
        assertEquals(100, bounds.y);
        assertTrue(bounds.width > 0 && bounds.width < 35);
        assertTrue(bounds.height > 0 && bounds.height < 35);
    }

    @Test
    void testTick() {
        sideBullet2.tick();

        assertEquals(100 + sideBullet2.getVelX(), sideBullet2.getX());
        assertEquals(100, sideBullet2.getY());

        sideBullet2.setX(Game.width + 1);
        sideBullet2.tick();
        assertTrue(handler.object.isEmpty());
    }

    @Test
    void testRender() {
        Graphics g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();

        sideBullet2.render(g);

        Color color = new Color(((Graphics2D) g).getBackground().getRGB());
        assertEquals(Color.RED, color);
    }
}
