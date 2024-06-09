package com.DodgeAndLast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    private Menu menu;
    private Game game;
    private Handler handler;
    private HUD hud;

    @BeforeEach
    void setUp() {
        game = new Game();
        handler = new Handler();
        hud = new HUD();
        menu = new Menu(game, handler, hud);
    }

    @Test
    void testMousePressedPlayButton() {
        game.gameState = Game.State.Menu;
        MouseEvent playEvent = new MouseEvent(new Component(){}, 0, 0, 0, (Game.width / 2) - 50, 100, 1, false);

        menu.mousePressed(playEvent);

        assertEquals(Game.State.Game, game.gameState);
        assertFalse(handler.object.isEmpty());
        assertTrue(handler.object.get(0) instanceof BasicEnemy);
        assertTrue(handler.object.get(1) instanceof Player);
    }

    @Test
    void testMousePressedHelpButton() {
        game.gameState = Game.State.Menu;
        MouseEvent helpEvent = new MouseEvent(new Component(){}, 0, 0, 0, (Game.width / 2) - 50, 250, 1, false);

        menu.mousePressed(helpEvent);

        assertEquals(Game.State.Help, game.gameState);
    }

    @Test
    void testMousePressedQuitButton() {
        game.gameState = Game.State.Menu;
        MouseEvent quitEvent = new MouseEvent(new Component(){}, 0, 0, 0, (Game.width / 2) - 50, 350, 1, false);

    }

    @Test
    void testMousePressedBackButton() {
        game.gameState = Game.State.Help;
        MouseEvent backEvent = new MouseEvent(new Component(){}, 0, 0, 0, (Game.width) - 100, 380, 1, false);

        menu.mousePressed(backEvent);

        assertEquals(Game.State.Menu, game.gameState);
    }

    @Test
    void testMousePressedTryAgainButton() {
        game.gameState = Game.State.End;
        MouseEvent tryAgainEvent = new MouseEvent(new Component(){}, 0, 0, 0, (Game.width / 2) - 40, 350, 1, false);

        menu.mousePressed(tryAgainEvent);

        assertEquals(Game.State.Game, game.gameState);
        assertEquals(0, hud.getScore());
        assertEquals(1, hud.getLevel());
        assertFalse(handler.object.isEmpty());
        assertTrue(handler.object.get(0) instanceof BasicEnemy);
        assertTrue(handler.object.get(1) instanceof Player);
    }

    @Test
    void testMouseOver() {
        assertTrue(menu.mouseOver(50, 50, 0, 0, 100, 100));
        assertFalse(menu.mouseOver(150, 150, 0, 0, 100, 100));
    }

    @Test
    void testTick() {
        menu.tick();
    }

    @Test
    void testRender() {
        Graphics g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();
        game.gameState = Game.State.Menu;

        menu.render(g);

        assertNotNull(g);
    }
}
