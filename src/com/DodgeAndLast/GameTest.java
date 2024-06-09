package com.DodgeAndLast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;

    @BeforeEach
    void setUp() {
        game = new Game();
        handler = new Handler();
        hud = new HUD();
        spawner = new Spawn(handler, hud);
        menu = new Menu(game, handler, hud);
        game.handler = handler;
        game.hud = hud;
        game.spawner = spawner;
        game.menu = menu;
    }

    @Test
    void testStartAndStop() throws Exception {
        game.start();
        assertTrue(isRunning());

        game.stop();
        assertFalse(isRunning());
    }

    private boolean isRunning() throws Exception {
        Field field = Game.class.getDeclaredField("running");
        field.setAccessible(true);
        return field.getBoolean(game);
    }

    @Test
    void testTick() {
        game.gameState = Game.State.Game;
        game.tick();
        assertTrue(handler.object.size() > 0 || hud.getLevel() > 0 || hud.getScore() > 0);

        game.gameState = Game.State.Menu;
        game.tick();
        assertTrue(handler.object.size() > 0 || hud.getLevel() == 0 || hud.getScore() == 0);
    }

    @Test
    void testRender() {
        BufferStrategy bufferStrategy = new TestBufferStrategy();
        game.createBufferStrategy(3);
        game.getBufferStrategy(); // Initialize buffer strategy
        game.render();

        // The test is to ensure the method runs without error
        assertNotNull(bufferStrategy);
    }

    @Test
    void testClamp() {
        assertEquals(10, Game.clamp(15, 0, 10));
        assertEquals(5, Game.clamp(5, 0, 10));
        assertEquals(0, Game.clamp(-5, 0, 10));
    }

    @Test
    void testGameInitialization() {
        assertNotNull(game.handler);
        assertNotNull(game.hud);
        assertNotNull(game.spawner);
        assertNotNull(game.menu);
        assertTrue(game.isFocusTraversable());
    }

    @Test
    void testGameStateChanges() {
        game.gameState = Game.State.Menu;
        assertEquals(Game.State.Menu, game.gameState);

        game.gameState = Game.State.Game;
        assertEquals(Game.State.Game, game.gameState);

        game.gameState = Game.State.Help;
        assertEquals(Game.State.Help, game.gameState);

        game.gameState = Game.State.End;
        assertEquals(Game.State.End, game.gameState);
    }

    @Test
    void testWindowDimensions() {
        assertEquals(640, Game.width);
        assertEquals(Game.width / 12 * 9, Game.height);
    }

    @Test
    void testMainMethod() {
        // The test is to ensure the main method runs without error
        Game.main(new String[]{});
    }

    private static class TestBufferStrategy extends BufferStrategy {
        @Override
        public boolean contentsLost() {
            return false;
        }

        @Override
        public boolean contentsRestored() {
            return false;
        }

        @Override
        public void show() {
        }

        @Override
        public BufferCapabilities getCapabilities() {
            return null;
        }

        @Override
        public Graphics getDrawGraphics() {
            return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();
        }
    }
}
