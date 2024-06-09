package com.DodgeAndLast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class TrailTest {

    private Trail trail;
    private Handler handler;

    @BeforeEach
    void setUp() {
        handler = new Handler();
        trail = new Trail(100, 100, ID.Trail, Color.white, 32, 32, 0.05f, handler);
    }

    @Test
    void testTick() {
        float initialAlpha = trail.alpha;

        // Simulate several ticks
        for (int i = 0; i < 10; i++) {
            trail.tick();
        }

        assertTrue(trail.alpha < initialAlpha);

        trail.alpha = trail.life;
        trail.tick();

        assertTrue(handler.object.isEmpty());
    }

    @Test
    void testRender() {
        Graphics g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();

        trail.render(g);

        Color color = new Color(((Graphics2D) g).getBackground().getRGB());
        assertEquals(Color.WHITE, color);
    }

    @Test
    void testGetBounds() {
        assertNull(trail.getBounds());
    }
}
