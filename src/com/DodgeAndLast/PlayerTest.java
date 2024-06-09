package com.DodgeAndLast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;
    private Handler handler;
    private LinkedList<GameObject> objects;

    @BeforeEach
    void setUp() {
        handler = new Handler();
        player = new Player(100, 100, ID.Player, handler);
        objects = handler.object;
    }

    @Test
    void testGetBounds() {
        Rectangle bounds = player.getBounds();
        assertEquals(100, bounds.x);
        assertEquals(100, bounds.y);
        assertEquals(32, bounds.width);
        assertEquals(32, bounds.height);
    }

    @Test
    void testTickMovement() {
        player.setVelX(5);
        player.setVelY(5);
        player.tick();

        assertEquals(105, player.getX());
        assertEquals(105, player.getY());
    }

    @Test
    void testTickBorderConstraints() {
        player.setX(-10);
        player.setY(-10);
        player.tick();

        assertEquals(0, player.getX());
        assertEquals(0, player.getY());

        player.setX(Game.width);
        player.setY(Game.height);
        player.tick();

        assertEquals(Game.width - 49, player.getX());
        assertEquals(Game.height - 71, player.getY());
    }

    @Test
    void testTickAddTrail() {
        player.tick();
        assertFalse(objects.isEmpty());
        assertTrue(objects.getLast() instanceof Trail);
    }

    @Test
    void testTickCollision() {
        GameObject enemy = new BasicEnemy(100, 100, ID.BasicEnemy, handler);
        handler.addObject(enemy);

        HUD.health = 100;
        player.tick();
        assertEquals(98, HUD.health); // Assuming health is initially 100
    }

    @Test
    void testRender() {
        Graphics g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();

        player.render(g);

        Color color = new Color(((Graphics2D) g).getBackground().getRGB());
        assertEquals(Color.WHITE, color);
    }

    @Test
    void testSetVelX() {
        player.setVelX(10);
        assertEquals(10, player.getVelX());
    }

    @Test
    void testSetVelY() {
        player.setVelY(15);
        assertEquals(15, player.getVelY());
    }

    @Test
    void testPlayerHealthReductionOnCollision() {
        GameObject enemy = new BasicEnemy(100, 100, ID.BasicEnemy, handler);
        handler.addObject(enemy);

        HUD.health = 100;
        player.tick();
        assertEquals(98, HUD.health);
    }

    @Test
    void testPlayerDoesNotMoveBeyondLeftBoundary() {
        player.setX(-5);
        player.tick();
        assertEquals(0, player.getX());
    }

    @Test
    void testPlayerDoesNotMoveBeyondRightBoundary() {
        player.setX(Game.width);
        player.tick();
        assertEquals(Game.width - 49, player.getX());
    }

    @Test
    void testPlayerDoesNotMoveBeyondTopBoundary() {
        player.setY(-5);
        player.tick();
        assertEquals(0, player.getY());
    }

    @Test
    void testPlayerDoesNotMoveBeyondBottomBoundary() {
        player.setY(Game.height);
        player.tick();
        assertEquals(Game.height - 71, player.getY());
    }
}
