package com.DodgeAndLast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class SideBoss2Test {

    private SideBoss2 sideBoss2;
    private Handler handler;

    @BeforeEach
    void setUp() {
        handler = new Handler();
        sideBoss2 = new SideBoss2(100, 100, ID.SideBoss2, handler);
    }

    @Test
    void testGetBounds() {
        Rectangle bounds = sideBoss2.getBounds();
        assertEquals(100, bounds.x);
        assertEquals(100, bounds.y);
        assertEquals(96, bounds.width);
        assertEquals(96, bounds.height);
    }

    @Test
    void testTick() {
        sideBoss2.tick();

        assertEquals(98, sideBoss2.getX());
        assertEquals(100, sideBoss2.getY());

        sideBoss2.timer = 0;
        sideBoss2.tick();
        assertEquals(0, sideBoss2.velX);

        assertFalse(handler.object.isEmpty());
        assertTrue(handler.object.getLast() instanceof SideBullet2);
    }

    @Test
    void testRender() {
        Graphics g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();

        sideBoss2.render(g);

        Color color = new Color(((Graphics2D) g).getBackground().getRGB());
        assertEquals(Color.RED, color);
    }
}
