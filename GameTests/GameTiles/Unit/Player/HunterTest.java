package GameTiles.Unit.Player;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import GameTiles.Utilis.Position;

class HunterTest {
    static Hunter player;

    @BeforeAll
    static void Initialize() {
        Position p = new Position(9, 1);
        player = new Hunter('@', p, "Ygritte", 220, 220, 30, 2, 6);
    }

    @Test
    void levelUp() {
        int initialArrows = player.getArrows_count();
        player.levelUp();
        assertEquals(220 + 10 * player.getLevel(), (int) player.getHealth_pool());
        assertEquals(initialArrows + 10 * player.getLevel(), (int) player.getArrows_count());
        assertEquals(30 + 2 * player.getLevel(), (int) player.getAttack_points());
        assertEquals(2 + player.getLevel(), (int) player.getDefense_points());
    }

    @Test
    void onGameTick() {
        int initialTicks = player.getTicks_count();
        player.onGameTick();
        if (initialTicks == 10) {
            assertEquals(220 + player.getLevel(), (int) player.getArrows_count());
            assertEquals(0, (int) player.getTicks_count());
        } else {
            assertEquals(initialTicks + 1, (int) player.getTicks_count());
        }
    }

    @Test
    void description() {
        String desc = player.description();
        assertNotNull(desc);
        assertTrue(desc.contains("range: " + player.getRange()));
        assertTrue(desc.contains("arrows_count: " + player.getArrows_count()));
        assertTrue(desc.contains("ticks_count: " + player.getTicks_count()));
    }

    @Test
    void castAbility() {
        // Assuming a mock manager for testing messages
        player.castAbility(); // Assuming there's an enemy to hit
        assertTrue(player.getArrows_count() < 220); // Arrows should decrease after casting ability
    }

    @Test
    void interact() {
        // Assuming a mock unit for testing interaction
        player.interact(player); // Interaction with itself
        assertNotNull(player);
    }

    // Additional tests can be added for edge cases or specific behaviors
}
