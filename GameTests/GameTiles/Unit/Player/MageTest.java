package GameTiles.Unit.Player;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import GameTiles.Utilis.Position;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MageTest {
    static Mage player1;
    static Mage player2;

    @BeforeAll
    static void Initialize() {
        player1 = new Mage('@', new Position(9, 1), "Melisandre", 100, 100, 5, 1, 300, 30, 15, 5, 6);
        player2 = new Mage('@', new Position(15, 8), "Thoros of Myr", 250, 250, 25, 4, 150, 20, 20, 3, 4);
    }

    @Test
    void levelUp() {
        player1.levelUp();
        assertEquals(100 + 25 * player1.getLevel(), (int) player1.getMana_pool());
        assertEquals(Math.min(100 + player1.getLevel(), player1.getMana_pool()), (int) player1.getCurrent_mana());
        assertEquals(15 + 10 * player1.getLevel(), (int) player1.getSpell_power());
    }

    @Test
    void onGameTick() {
        int initialMana = player1.getCurrent_mana();
        player1.onGameTick();
        assertEquals(Math.min(initialMana + player1.getLevel(), player1.getMana_pool()), (int) player1.getCurrent_mana());
    }

    @Test
    void description() {
        String desc = player1.description();
        assertNotNull(desc);
        assertTrue(desc.contains("Mana: " + player1.getCurrent_mana() + "/" + player1.getMana_pool()));
        assertTrue(desc.contains("Spell power: " + player1.getSpell_power()));
    }

    @Test
    void castAbility() {
        int initialMana = player1.getCurrent_mana();
        if (initialMana > player1.getMana_cost()) {
            player1.castAbility();
            assertTrue(player1.getCurrent_mana() < initialMana); // Mana should decrease after casting ability
        } else {
            player1.castAbility();
            assertEquals(initialMana, (int) player1.getCurrent_mana()); // Mana should remain the same if not enough
        }
    }

    @Test
    void interact() {
        player1.interact(player2);
        assertNotNull(player1);
        assertNotNull(player2);
    }
}
