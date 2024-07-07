package GameTiles;

import GameTiles.Unit.Enemy.Enemy;
import GameTiles.Unit.Enemy.Monster;
import GameTiles.Unit.Player.Player;
import GameTiles.Unit.Player.Warrior;
import GameTiles.Unit.Unit;
import GameTiles.Utilis.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    private Unit unit;

    @BeforeEach
    void setUp() {
        // Initialize a unit for testing
        Position position = new Position(0, 0); // Example position
        unit = new Monster('w', position, "White Walker", 2000, 2000, 150, 50, 6, 1000);
    }

    @Test
    void setName() {
        unit.setName("NewName");
        assertEquals("NewName", unit.getName());
    }

    @Test
    void setHealth_pool() {
        unit.setHealth_pool(150);
        assertEquals(150, unit.getHealth_pool());
    }

    @Test
    void setHealth_amount() {
        unit.setHealth_amount(50);
        assertEquals(50, unit.getHealth_amount());
    }

    @Test
    void setAttack_points() {
        unit.setAttack_points(30);
        assertEquals(30, unit.getAttack_points());
    }

    @Test
    void setDefense_points() {
        unit.setDefense_points(15);
        assertEquals(15, unit.getDefense_points());
    }

    @Test
    void addToEnemy() {
        Enemy enemy = new Monster('w', new Position(1,2), "White Walker", 2000, 2000, 150, 50, 6, 1000);
        unit.addToEnemy(enemy);
        List<Enemy> enemies = Unit.getEnemyList();
        assertTrue(enemies.contains(enemy));
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
    void interact_with_Player() {
        Player player = new Warrior('P', new Position(2, 2), "Player", 200, 200, 30, 15, 0);
        unit.interact(player);
        // Add assertions to verify interaction results
    }

    @Test
    void interact_with_Enemy() {
        Enemy enemy = new Monster('w', new Position(1,7), "White Walker", 2000, 2000, 150, 50, 6, 1000);
        unit.interact(enemy);
        // Add assertions to verify interaction results
    }

    @Test
    void interact_with_Empty() {
        Empty emptyTile = new Empty(new Position(3, 3));
        unit.interact(emptyTile);
        // Add assertions to verify interaction results
    }

    @Test
    void interact_with_Wall() {
        Wall wall = new Wall(new Position(4, 4));
        unit.interact(wall);
        // Add assertions to verify interaction results
    }

    @Test
    void isDead() {
        unit.setHealth_amount(0);
        assertTrue(unit.isDead());
    }

    @Test
    void enemyList_byRange() {
        Enemy enemy1 = new Monster('w', new Position(1,4), "White Walker", 2000, 2000, 150, 50, 6, 1000);

        Enemy enemy2 = new Monster('w', new Position(5,8), "White Walker", 2000, 2000, 150, 50, 6, 1000);

        unit.addToEnemy(enemy1);
        unit.addToEnemy(enemy2);

        List<Enemy> nearbyEnemies = unit.enemyList_byRange(2); // Example range
        assertEquals(2, nearbyEnemies.size());
    }

    @Test
    void nearestEnemy() {
        Enemy enemy1 = new Monster('w', new Position(9,6), "White Walker", 2000, 2000, 150, 50, 6, 1000);
        Enemy enemy2 = new Monster('w', new Position(6,9), "White Walker", 2000, 2000, 150, 50, 6, 1000);

        unit.addToEnemy(enemy1);
        unit.addToEnemy(enemy2);

        List<Enemy> nearbyEnemies = unit.enemyList_byRange(2); // Example range
        Enemy nearestEnemy = unit.NearestEnemy(nearbyEnemies);
        assertEquals(enemy1, nearestEnemy);
    }
}
