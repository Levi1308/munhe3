package GameTiles.Unit.Player;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import GameTiles.Utilis.Position;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RougeTest {
    static Rouge player1;
    static Rouge player2;

    @BeforeAll
    static void Initialize() {
        player1 = new Rouge('@', new Position(9, 1), "Arya Stark", 150, 150, 40, 2, 20);
        player2 = new Rouge('@', new Position(15, 8), "Bronn", 250, 250, 35, 3, 50);
    }

    @Test
    void levelUp() {
        player1.levelUp();
        assertEquals(100, (int) player1.getCurrent_energy());
        assertEquals(40 + 3 * player1.getLevel(), (int) player1.getAttack_points());
    }

    @Test
    void onGameTick() {
        int initialEnergy = player1.getCurrent_energy();
        player1.onGameTick();
        assertEquals(Math.min(initialEnergy + 10, 100), (int) player1.getCurrent_energy());
    }

    @Test
    void description() {
        String desc = player1.description();
        assertNotNull(desc);
        assertTrue(desc.contains("cost: " + player1.getCost()));
        assertTrue(desc.contains("current_energy: " + player1.getCurrent_energy()));
    }

    @Test
    void castAbility() {
        int initialEnergy = player1.getCurrent_energy();
        if (initialEnergy > player1.getCost()) {
            player1.castAbility();
            assertEquals(initialEnergy - player1.getCost(), (int) player1.getCurrent_energy());
        } else {
            player1.castAbility();
            assertEquals(initialEnergy, (int) player1.getCurrent_energy());
        }
    }

    @Test
    void interact() {
        player1.interact(player2);
        assertNotNull(player1);
        assertNotNull(player2);
    }
}
