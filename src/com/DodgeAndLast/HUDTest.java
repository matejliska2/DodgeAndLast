package com.DodgeAndLast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class HUDTest {

    private HUD hud;
    private Graphics graphics;

    @BeforeEach
    void setUp() {
        hud = new HUD();
        graphics = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();
    }

    @Test
    void testTick() {
        HUD.health = 105;
        hud.tick();
        assertEquals(100, HUD.health);

        HUD.health = -5;
        hud.tick();
        assertEquals(0, HUD.health);

        int initialScore = hud.getScore();
        hud.tick();
        assertEquals(initialScore + 1, hud.getScore());
    }

    @Test
    void testRender() {
        hud.render(graphics);

        Color initialColor = new Color(((Graphics2D) graphics).getBackground().getRGB());

        hud.render(graphics);

        Color afterRenderColor = new Color(((Graphics2D) graphics).getBackground().getRGB());
        assertNotEquals(initialColor, afterRenderColor);
    }

    @Test
    void testRenderScore() {
        hud.setScore(50);
        hud.render(graphics);

        String scoreText = "Score: 50";
        Color initialColor = new Color(((Graphics2D) graphics).getBackground().getRGB());

        hud.render(graphics);

        Color afterRenderColor = new Color(((Graphics2D) graphics).getBackground().getRGB());
        assertNotEquals(initialColor, afterRenderColor);
    }

    @Test
    void testRenderLevel() {
        hud.setLevel(5);
        hud.render(graphics);

        String levelText = "Level: 5";
        Color initialColor = new Color(((Graphics2D) graphics).getBackground().getRGB());

        hud.render(graphics);

        Color afterRenderColor = new Color(((Graphics2D) graphics).getBackground().getRGB());
        assertNotEquals(initialColor, afterRenderColor);
    }

    @Test
    void testHealthBoundary() {
        HUD.health = 150;
        hud.tick();
        assertEquals(100, HUD.health);

        HUD.health = -20;
        hud.tick();
        assertEquals(0, HUD.health);
    }

    @Test
    void testHealthReduction() {
        HUD.health = 100;
        HUD.health -= 20;
        hud.tick();
        assertEquals(80, HUD.health);
    }

    @Test
    void testScoreIncrement() {
        int initialScore = hud.getScore();
        hud.tick();
        assertEquals(initialScore + 1, hud.getScore());
    }

    @Test
    void testSetScore() {
        hud.setScore(75);
        assertEquals(75, hud.getScore());
    }

    @Test
    void testSetLevel() {
        hud.setLevel(3);
        assertEquals(3, hud.getLevel());
    }
}
