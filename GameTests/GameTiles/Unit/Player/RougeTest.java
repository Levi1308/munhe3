package GameTiles.Unit.Player;

import GameTiles.Unit.Enemy.Enemy;
import GameTiles.Unit.Enemy.Monster;
import GameTiles.Utilis.Board;
import GameTiles.Utilis.Position;
import UI.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RougeTest {

    private Rouge rouge1;
    private Rouge rouge2;

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
        rouge1 = new Rouge('@', p, "Arya Stark", 150, 150, 40, 2, 20);
        rouge2 = new Rouge('@', p, "Bronn", 150, 150, 10, 6, 10);
    }

    @Test
    void levelUp() {
        rouge1.addExp(50); // Adding experience to level up
        assertEquals(2, rouge1.getLevel()); // Assuming the initial level was 1
        assertEquals(170, rouge1.getHealth_pool());
        assertEquals(170, rouge1.getHealth_amount());
        assertEquals(54, rouge1.getAttack_points()); // Attack points should increase by 3 per level
        assertEquals(4, rouge1.getDefense_points()); // Defense points should increase by 1 per level
        assertEquals(100, rouge1.getCurrent_energy()); // Energy should be reset to 100
    }

    @Test
    void onGameTick() {
        rouge1.onGameTick();
        assertEquals(100, rouge1.getCurrent_energy()); // Should remain at max value 100

        rouge1.setCurrent_energy(50); // Setting energy to a value less than max
        rouge1.onGameTick();
        assertEquals(60, rouge1.getCurrent_energy()); // Should increase by 10
    }

    @Test
    void castAbility() {
        Enemy enemy = new Monster('E', new Position(1, 1), "Goblin", 100, 100, 10, 5, 20,5);
        rouge1.addExp(50); // Level up to have enough attack points
        rouge1.setCurrent_energy(30); // Ensure enough energy for ability

        rouge1.castAbility();
        // Assert that the ability affects enemies in range, send appropriate messages, and deduct energy
        // This requires mocking or capturing the output messages from the manager for validation
    }

    @Test
    void interact() {
        Enemy enemy = new Monster('M', new Position(1, 1), "Orc", 100, 100, 10, 5, 20,5);
        rouge1.interact(enemy);
        // Check the interaction outcome, which should involve combat between the rouge and the enemy
    }

    @Test
    void getCost() {
        assertEquals(20, rouge1.getCost());
    }

    @Test
    void setCost() {
        rouge1.setCost(25);
        assertEquals(25, rouge1.getCost());
    }

    @Test
    void getCurrent_energy() {
        assertEquals(100, rouge1.getCurrent_energy());
    }

    @Test
    void setCurrent_energy() {
        rouge1.setCurrent_energy(75);
        assertEquals(75, rouge1.getCurrent_energy());
    }
}
