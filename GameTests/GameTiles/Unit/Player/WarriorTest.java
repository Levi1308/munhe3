package GameTiles.Unit.Player;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import GameTiles.Utilis.Position;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {
    static Warrior player1;
    static Warrior player2;

    @BeforeAll
    static void Initialize() {
        player1 = new Warrior('@', new Position(9, 1), "John Snow", 300, 300, 30, 4, 3);
        player2 = new Warrior('@', new Position(15, 8), "The Hound", 400, 400, 20, 6, 5);
    }

    @Test
    void levelUp() {
        long cooldown = player1.getRemaining_cooldown();
        long health_pool = player1.getHealth_amount() + 5 * player1.getLevel();
        long attack = player1.getAttack_points() + 2 * player1.getLevel();
        long defence = player1.getDefense_points() + player1.getLevel();
        player1.levelUp();
        assertEquals(cooldown, (long) player1.getRemaining_cooldown());
        assertEquals(health_pool, (long) player1.getHealth_pool());
        assertEquals(attack, (long) player1.getAttack_points());
        assertEquals(defence, (long) player1.getDefense_points());
    }

    @Test
    void onGameTick() {
        int initialCooldown = player1.getRemaining_cooldown();
        player1.onGameTick();
        assertEquals(Math.max(initialCooldown - 1, 0), player1.getRemaining_cooldown());
    }

    @Test
    void description() {
        String desc = player1.description();
        assertNotNull(desc);
        assertTrue(desc.contains("Ability cooldown: " + player1.getAbility_cooldown()));
        assertTrue(desc.contains("Remaining cooldown: " + player1.getRemaining_cooldown()));
    }

    @Test
    void castAbility() {
        int initialHealth = player1.getHealth_amount();
        int initialCooldown = player1.getRemaining_cooldown();
        if (initialCooldown == 0) {
            player1.castAbility();
            assertEquals(player1.getAbility_cooldown(), player1.getRemaining_cooldown());
            assertTrue(player1.getHealth_amount() >= initialHealth);
        } else {
            player1.castAbility();
            assertEquals(initialCooldown, player1.getRemaining_cooldown());
        }
    }

    @Test
    void interact() {
        player1.interact(player2);
        assertNotNull(player1);
        assertNotNull(player2);
    }
}
