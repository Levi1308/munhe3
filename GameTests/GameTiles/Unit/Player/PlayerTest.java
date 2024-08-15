package GameTiles.Unit.Player;

import GameTiles.Unit.Enemy.Enemy;
import GameTiles.Unit.Enemy.Monster;
import GameTiles.Utilis.Board;
import GameTiles.Utilis.Position;
import UI.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Position p;
    private Player player1;
    private Player player2;

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
        p = new Position(5, 5);
        player1 = new Warrior('@', p, "Levi", 220, 220, 30, 2, 6);
        player2 = new Warrior('@', p, "Saar", 300, 300, 30, 1, 9);
    }

    @Test
    void levelUp() {
        player1.addExp(50); // Adding enough experience to level up
        assertEquals(2, player1.getLevel());
        assertEquals(250, player1.getHealth_pool());
        assertEquals(240, player1.getHealth_amount());
        assertEquals(42, player1.getAttack_points());
        assertEquals(6, player1.getDefense_points());
    }


    @Test
    void getLevel() {
        assertEquals(1, player1.getLevel());
    }

    @Test
    void setLevel() {
        player1.setLevel(3);
        assertEquals(3, player1.getLevel());
    }

    @Test
    void getExperience() {
        assertEquals(0, player1.getExperience());
    }

    @Test
    void addExp() {
        player1.addExp(50);
        assertEquals(0, player1.getExperience()); // Should reset after level up
        assertEquals(2, player1.getLevel());
    }

    @Test
    void castAbility() {
        player1.castAbility();
        // Add assertions based on the expected behavior of castAbility
    }

    @Test
    void interact() {
        Enemy enemy = new Monster('E', new Position(1, 1), "Orc", 100, 100, 10, 5, 20,10);
        player1.interact(enemy);
        // Add assertions based on the interaction outcome
    }

    @Test
    void testInteract() {
        // Test a different interaction scenario if needed
    }

    @Test
    void battle() {
        Enemy enemy = new Monster('E', new Position(1, 1), "Orc", 100, 100, 10, 5, 20,10);
        player1.battle(enemy);
        // Add assertions based on the expected battle outcome
    }

    @Test
    void lose_health() {
        player1.lose_health(50);
        assertEquals(170, player1.getHealth_amount());
        player1.lose_health(170);
        assertEquals(0, player1.getHealth_amount());
        // Check if the player is marked as dead or any game over message is sent
    }

    @Test
    void moveUp() {
        // Setup player at position (5,5)
        player1.moveUp();
        // After moving up, the X coordinate should decrease by 1
        assertEquals(4, player1.getPosition().getX());
        assertEquals(5, player1.getPosition().getY());
    }

    @Test
    void moveDown() {
        // Setup player at position (5,5)
        player1.moveDown();
        // After moving down, the X coordinate should increase by 1
        assertEquals(6, player1.getPosition().getX());
        assertEquals(5, player1.getPosition().getY());
    }

    @Test
    void moveLeft() {
        // Setup player at position (5,5)
        player1.moveLeft();
        // After moving left, the Y coordinate should decrease by 1
        assertEquals(5, player1.getPosition().getX());
        assertEquals(4, player1.getPosition().getY());
    }

    @Test
    void moveRight() {
        // Setup player at position (5,5)
        player1.moveRight();
        // After moving right, the Y coordinate should increase by 1
        assertEquals(5, player1.getPosition().getX());
        assertEquals(6, player1.getPosition().getY());
    }


}
