package GameTiles;

import GameTiles.Unit.Enemy.Enemy;
import GameTiles.Unit.Player.Player;
import GameTiles.Unit.Player.Warrior;
import GameTiles.Unit.Unit;
import GameTiles.Utilis.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {
    private Wall wallTile;
    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position(5, 5);
        wallTile = new Wall(position);
    }

    @Test
    void interact_Empty() {
        Empty emptyTile = new Empty(new Position(6, 5));
        wallTile.interact(emptyTile);
        // No specific interaction defined, test if no exception is thrown
        assertTrue(true);
    }

    @Test
    void testInteract_Wall() {
        Wall anotherWall = new Wall(new Position(6, 5));
        wallTile.interact(anotherWall);
        // No specific interaction defined, test if no exception is thrown
        assertTrue(true);
    }

    @Test
    void testInteract_Unit() {
        Player player = new Warrior('@', new Position(6, 5), "Player", 100, 100, 10, 5,4);
        wallTile.interact(player);
        // No specific interaction defined, test if no exception is thrown
        assertTrue(true);
    }
}
