package com.DodgeAndLast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class SideBossTest {

    private SideBoss sideBoss;
    private Handler handler;

    @BeforeEach
    void setUp() {
        handler = new Handler();
        sideBoss = new SideBoss(100, 100, ID.SideBoss, handler);
    }

    @Test
    void testGetBounds() {
        Rectangle bounds = sideBoss.getBounds();
        assertEquals(100, bounds.x);
        assertEquals(100, bounds.y);
        assertEquals(96, bounds.width);
        assertEquals(96, bounds.height);
    }

    @Test
    void testTick() {
        sideBoss.tick();

        assertEquals(102, sideBoss.getX());
        assertEquals(100, sideBoss.getY());

        sideBoss.timer = 0;
        sideBoss.tick();
        assertEquals(0, sideBoss.velX);

        assertFalse(handler.object.isEmpty());
        assertTrue(handler.object.getLast() instanceof SideBullet);
    }

    @Test
    void testRender() {
        Graphics g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();

        sideBoss.render(g);

        Color color = new Color(((Graphics2D) g).getBackground().getRGB());
        assertEquals(Color.RED, color);
    }
}
