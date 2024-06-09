package com.DodgeAndLast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WindowTest {

    private Game game;
    private Window window;

    @BeforeEach
    void setUp() {
        game = new Game();
        window = new Window(800, 600, "Test Game", game);
    }

    @Test
    void testWindowSetup() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(window);

        assertNotNull(frame);
        assertEquals("Test Game", frame.getTitle());
        assertEquals(new Dimension(800, 600), frame.getPreferredSize());
        assertEquals(new Dimension(800, 600), frame.getMaximumSize());
        assertEquals(new Dimension(800, 600), frame.getMinimumSize());
        assertFalse(frame.isResizable());
        assertTrue(frame.isVisible());
    }

    @Test
    void testWindowProperties() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(window);

        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        assertEquals(false, frame.isResizable());
        assertTrue(frame.isVisible());
    }

    @Test
    void testWindowSize() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(window);

        assertEquals(800, frame.getWidth());
        assertEquals(600, frame.getHeight());
    }

    @Test
    void testWindowTitle() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(window);

        assertEquals("Test Game", frame.getTitle());
    }

    @Test
    void testWindowContainsGame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(window);

        assertTrue(frame.getContentPane().getComponentCount() > 0);
        assertTrue(frame.getContentPane().getComponent(0) instanceof Game);
    }

    @Test
    void testWindowDimensionLimits() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(window);

        assertEquals(new Dimension(800, 600), frame.getPreferredSize());
        assertEquals(new Dimension(800, 600), frame.getMaximumSize());
        assertEquals(new Dimension(800, 600), frame.getMinimumSize());
    }
}
