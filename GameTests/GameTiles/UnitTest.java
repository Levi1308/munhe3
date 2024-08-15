package GameTiles;

import GameTiles.Unit.Enemy.Enemy;
import GameTiles.Unit.Enemy.Monster;
import GameTiles.Unit.Player.Warrior;
import GameTiles.Unit.Unit;
import GameTiles.Utilis.Board;
import GameTiles.Utilis.Position;
import UI.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    private Unit unit;

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
        Position p = new Position(5, 5);
        unit = new Warrior('@', p, "The Hound", 400, 400, 20, 6, 5);
    }

    @Test
    void setName() {
        unit.setName("Arya Stark");
        assertEquals("Arya Stark", unit.getName());
    }

    @Test
    void setHealth_pool() {
        unit.setHealth_pool(500);
        assertEquals(500, unit.getHealth_pool());
    }

    @Test
    void setHealth_amount() {
        unit.setHealth_amount(350);
        assertEquals(350, unit.getHealth_amount());
    }

    @Test
    void setAttack_points() {
        unit.setAttack_points(30);
        assertEquals(30, unit.getAttack_points());
    }

    @Test
    void setDefense_points() {
        unit.setDefense_points(10);
        assertEquals(10, unit.getDefense_points());
    }

    @Test
    void getHealth_pool() {
        assertEquals(400, unit.getHealth_pool());
    }

    @Test
    void getHealth_amount() {
        assertEquals(400, unit.getHealth_amount());
    }

    @Test
    void getAttack_points() {
        assertEquals(20, unit.getAttack_points());
    }

    @Test
    void getDefense_points() {
        assertEquals(6, unit.getDefense_points());
    }

    @Test
    void getName() {
        assertEquals("The Hound", unit.getName());
    }

    @Test
    void addToEnemy() {
        // Assuming there is an Enemy class that extends Unit for testing purposes
        Enemy enemy = new Monster('@', new Position(6, 6), "Enemy", 200, 200, 15, 5, 3,6);
        unit.addToEnemy(enemy);
        assertTrue(Unit.getEnemyList().contains(enemy));
    }

    @Test
    void random_Defense() {
        int defense = unit.random_Defense();
        assertTrue(defense >= 0 && defense <= unit.getDefense_points());
    }

    @Test
    void random_Attack() {
        int attack = unit.random_Attack();
        assertTrue(attack >= 0 && attack <= unit.getAttack_points());
    }

    @Test
    void isDead() {
        assertFalse(unit.isDead());
        unit.setHealth_amount(0);
        assertTrue(unit.isDead());
    }

    @Test
    void enemyList_byRange() {
        // Assuming there are other enemies on the board for testing purposes
        Enemy enemy1 = new Monster('@', new Position(6, 5), "Enemy1", 200, 200, 15, 5, 3,4);
        Enemy enemy2 = new Monster('@', new Position(8, 8), "Enemy2", 200, 200, 15, 5, 3,7);
        unit.addToEnemy(enemy1);
        unit.addToEnemy(enemy2);
        assertTrue(unit.enemyList_byRange(3).contains(enemy1));
        assertFalse(unit.enemyList_byRange(3).contains(enemy2));
    }
}
