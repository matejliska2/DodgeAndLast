package com.DodgeAndLast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpawnTest {

    private Spawn spawn;
    private Handler handler;
    private HUD hud;

    @BeforeEach
    void setUp() {
        handler = new Handler();
        hud = new HUD();
        spawn = new Spawn(handler, hud);
    }

    @Test
    void testTickLevelUp() {
        hud.setLevel(1);
        // Simulate 250 ticks
        for (int i = 0; i < 250; i++) {
            spawn.tick();
        }
        assertEquals(2, hud.getLevel());
        assertFalse(handler.object.isEmpty());
        assertTrue(handler.object.getLast() instanceof BasicEnemy);
    }

    @Test
    void testTickLevel3() {
        hud.setLevel(2);
        // Simulate 250 ticks
        for (int i = 0; i < 250; i++) {
            spawn.tick();
        }
        assertEquals(3, hud.getLevel());
        assertFalse(handler.object.isEmpty());
        assertTrue(handler.object.getLast() instanceof HFastEnemy);
    }

    @Test
    void testTickLevel5() {
        hud.setLevel(4);
        // Simulate 250 ticks
        for (int i = 0; i < 250; i++) {
            spawn.tick();
        }
        assertEquals(5, hud.getLevel());
        assertFalse(handler.object.isEmpty());
        assertTrue(handler.object.getLast() instanceof VFastEnemy);
    }

    @Test
    void testTickLevel10() {
        hud.setLevel(9);
        // Simulate 250 ticks
        for (int i = 0; i < 250; i++) {
            spawn.tick();
        }
        assertEquals(10, hud.getLevel());
        assertTrue(handler.object.size() >= 2);
        assertTrue(handler.object.get(handler.object.size() - 2) instanceof SideBoss);
        assertTrue(handler.object.getLast() instanceof SideBoss2);
    }
}
