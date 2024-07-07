package GameTiles;

import GameTiles.Unit.Player.Player;
import GameTiles.Unit.Player.Warrior;
import GameTiles.Utilis.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmptyTest {
    private Empty emptyTile;
    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position(5, 5);
        emptyTile = new Empty(position);
    }

    @Test
    void interact_Unit() {
        Player player = new Warrior('@', new Position(6, 5), "Jon Snow", 100, 100, 10, 5,4);
        emptyTile.interact(player);
        assertEquals(position, player.getPosition());
    }

    @Test
    void testInteract_Empty() {
        Empty anotherEmpty = new Empty(new Position(6, 6));
        emptyTile.interact(anotherEmpty);
        // No specific interaction defined, test if no exception is thrown
        assertTrue(true);
    }

    @Test
    void testInteract_Wall() {
        Wall wall = new Wall(new Position(6, 6));
        emptyTile.interact(wall);
        // No specific interaction defined, test if no exception is thrown
        assertTrue(true);
    }

}
