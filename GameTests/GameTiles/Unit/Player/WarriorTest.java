package GameTiles.Unit.Player;

import GameTiles.Unit.Enemy.Enemy;
import GameTiles.Unit.Enemy.Monster;
import GameTiles.Utilis.Board;
import GameTiles.Utilis.Position;
import UI.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {

    private Warrior warrior1;

    @BeforeEach
    public void setUp() {
        Manager manager = new Manager();
        Board board = new Board(30, 30);
        manager.setBoard(board);
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                Position position = new Position(i, j);
                manager.initializer('.', position);
            }
        }
        manager.create_player('1', new Position(5, 5));
        Position p = new Position(0, 0);
        warrior1 = new Warrior('@', p, "The Hound", 400, 400, 20, 6, 5);
    }

    @Test
    void levelUp() {
        warrior1.addExp(50); // Trigger level up
        assertEquals(2, warrior1.getLevel());
        assertEquals(430, warrior1.getHealth_pool());
        assertEquals(420, warrior1.getHealth_amount());
        assertEquals(32, warrior1.getAttack_points()); // Attack points increase by 2 per level
        assertEquals(10, warrior1.getDefense_points()); // Defense points increase by 1 per level
        assertEquals(0, warrior1.getRemaining_cooldown()); // Cooldown should be reset
    }

    @Test
    void onGameTick() {
        warrior1.setRemaining_cooldown(5);
        warrior1.onGameTick();
        assertEquals(4, warrior1.getRemaining_cooldown()); // Remaining cooldown should decrease by 1

        // Decrement remaining cooldown to 0 and ensure it doesn’t go below 0
        warrior1.setRemaining_cooldown(0);
        warrior1.onGameTick();
        assertEquals(0, warrior1.getRemaining_cooldown());
    }

    @Test
    void description() {
        String description = warrior1.description();
        assertTrue(description.contains("Ability cooldown: 5"));
        assertTrue(description.contains("Remaining cooldown: 0"));
    }

    @Test
    void castAbility() {
        // Setup an enemy in range
        Enemy enemy = new Monster('E', new Position(1, 1), "Goblin", 100, 100, 10, 5, 20,10);
        warrior1.setRemaining_cooldown(0); // Ensure ability is off cooldown
        warrior1.castAbility();
        // Check that ability affects an enemy in range, adjusts health, and handles cooldown
        assertTrue(warrior1.getRemaining_cooldown() > 0); // Ensure cooldown is applied
        // You may need to mock or simulate actual enemy behavior to test this fully
    }

    @Test
    void getAbility_cooldown() {
        assertEquals(5, warrior1.getAbility_cooldown());
    }

    @Test
    void setAbility_cooldown() {
        warrior1.setAbility_cooldown(7);
        assertEquals(7, warrior1.getAbility_cooldown());
    }

    @Test
    void getRemaining_cooldown() {
        assertEquals(0, warrior1.getRemaining_cooldown());
    }

    @Test
    void setRemaining_cooldown() {
        warrior1.setRemaining_cooldown(3);
        assertEquals(3, warrior1.getRemaining_cooldown());
    }
}
