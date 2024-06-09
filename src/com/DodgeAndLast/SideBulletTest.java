package com.DodgeAndLast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SideBulletTest {

    private SideBullet sideBullet;
    private Handler handler;
    private Random random;

    @BeforeEach
    void setUp() {
        handler = new Handler();
        random = new Random();
        sideBullet = new SideBullet(100, 100, ID.SideBullet, handler);
    }

    @Test
    void testGetBounds() {
        Rectangle bounds = sideBullet.getBounds();
        assertEquals(100, bounds.x);
        assertEquals(100, bounds.y);
        assertTrue(bounds.width > 0 && bounds.width < 50);
        assertTrue(bounds.height > 0 && bounds.height < 50);
    }

    @Test
    void testTick() {
        sideBullet.tick();

        assertEquals(100 + sideBullet.getVelX(), sideBullet.getX());
        assertEquals(100, sideBullet.getY());

        sideBullet.setX(Game.width + 1);
        sideBullet.tick();
        assertTrue(handler.object.isEmpty());
    }

    @Test
    void testRender() {
        Graphics g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();

        sideBullet.render(g);

        Color color = new Color(((Graphics2D) g).getBackground().getRGB());
        assertEquals(Color.RED, color);
    }
}
