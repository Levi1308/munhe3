package GameTiles;

import GameTiles.Unit.Player.Player;
import GameTiles.Unit.Player.Warrior;
import GameTiles.Utilis.Board;
import GameTiles.Utilis.Position;
import UI.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmptyTest {
    private Empty emptyTile;
    private Position position;

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
            manager.create_player('1', new Position(5,5));
        position = new Position(5, 5);
        emptyTile = new Empty(position);
    }

    @Test
    void interact_Unit() {
        Player player = new Warrior('@', new Position(6, 5), "Jon Snow", 100, 100, 10, 5,4);
        emptyTile.interact(player);
        assertEquals(5, player.getPosition().getX());
        assertEquals(5, player.getPosition().getY());
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
