package com.DodgeAndLast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class BasicEnemyTest {

    private BasicEnemy basicEnemy;
    private Handler handler;

    @BeforeEach
    void setUp() {
        handler = new Handler();
        basicEnemy = new BasicEnemy(100, 100, ID.BasicEnemy, handler);
    }

    @Test
    void testGetBounds() {
        Rectangle bounds = basicEnemy.getBounds();
        assertEquals(100, bounds.x);
        assertEquals(100, bounds.y);
        assertEquals(16, bounds.width);
        assertEquals(16, bounds.height);
    }

    @Test
    void testTickMovement() {
        basicEnemy.tick();

        assertEquals(105, basicEnemy.getX());
        assertEquals(105, basicEnemy.getY());
    }

    @Test
    void testTickBounceY() {
        basicEnemy.setY(0);
        basicEnemy.tick();

        assertTrue(basicEnemy.getVelY() > 0);

        basicEnemy.setY(Game.height - 60);
        basicEnemy.tick();

        assertTrue(basicEnemy.getVelY() < 0);
    }

    @Test
    void testTickBounceX() {
        basicEnemy.setX(0);
        basicEnemy.tick();

        assertTrue(basicEnemy.getVelX() > 0);

        basicEnemy.setX(Game.width - 33);
        basicEnemy.tick();

        assertTrue(basicEnemy.getVelX() < 0);
    }

    @Test
    void testRender() {
        Graphics g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();

        basicEnemy.render(g);

        Color color = new Color(((Graphics2D) g).getBackground().getRGB());
        assertEquals(Color.RED, color);
    }

    @Test
    void testTrailAdded() {
        int initialSize = handler.object.size();
        basicEnemy.tick();
        assertEquals(initialSize + 1, handler.object.size());
        assertTrue(handler.object.getLast() instanceof Trail);
    }

    @Test
    void testCollisionWithPlayer() {
        Player player = new Player(100, 100, ID.Player, handler);
        handler.addObject(player);

        basicEnemy.tick();
        assertEquals(98, HUD.health);
    }

    @Test
    void testRemoveEnemy() {
        handler.addObject(basicEnemy);
        handler.removeObject(basicEnemy);
        assertFalse(handler.object.contains(basicEnemy));
    }

    @Test
    void testHandlerSizeAfterMultipleTicks() {
        handler.addObject(basicEnemy);
        int initialSize = handler.object.size();
        for (int i = 0; i < 10; i++) {
            basicEnemy.tick();
        }
        assertTrue(handler.object.size() > initialSize);
    }

    @Test
    void testHandlerContainsBasicEnemy() {
        handler.addObject(basicEnemy);
        assertTrue(handler.object.contains(basicEnemy));
    }
}
