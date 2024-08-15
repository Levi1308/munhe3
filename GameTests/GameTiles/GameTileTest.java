package GameTiles;

import GameTiles.Utilis.Board;
import GameTiles.Utilis.Position;
import UI.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTileTest {

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
    }

    @Test
    void swapTiles() {
        // Create two GameTile instances at different positions
        GameTile tile1 = new Empty(new Position(5, 5));
        GameTile tile2 = new Empty(new Position(6, 6));

        // Swap the tiles
        tile1.swapTiles(tile2);

        // Assert that their positions have been swapped
        assertEquals(new Position(6, 6), tile1.getPosition());
        assertEquals(new Position(5, 5), tile2.getPosition());
    }

    @Test
    void range() {
        // Create two GameTile instances at specific positions
        GameTile tile1 = new Empty(new Position(0, 0));
        GameTile tile2 = new Empty(new Position(3, 4));

        // Calculate the distance between the two tiles
        double distance = tile1.range(tile2);

        // Assert the correct distance (5.0 in this case)
        assertEquals(5.0, distance);
    }

    @Test
    void getTile() {
        // Create a GameTile instance with a specific tile character
        GameTile tile = new Empty(new Position(5, 5));

        // Assert that the tile character is correct
        assertEquals('.', tile.getTile());
    }

    @Test
    void setTile() {
        // Create a GameTile instance
        GameTile tile = new Empty(new Position(5, 5));

        // Set a new tile character
        tile.setTile('#');

        // Assert that the tile character was updated correctly
        assertEquals('#', tile.getTile());
    }

    @Test
    void getPosition() {
        // Create a GameTile instance at a specific position
        Position position = new Position(5, 5);
        GameTile tile = new Empty(position);

        // Assert that the position is correct
        assertEquals(position, tile.getPosition());
    }

    @Test
    void setPosition() {
        // Create a GameTile instance at an initial position
        GameTile tile = new Empty(new Position(5, 5));

        // Set a new position
        Position newPosition = new Position(10, 10);
        tile.setPosition(newPosition);

        // Assert that the position was updated correctly
        assertEquals(newPosition, tile.getPosition());
    }

    @Test
    void stringTile() {
        // Create a GameTile instance with a specific tile character
        GameTile tile = new Empty(new Position(5, 5));

        // Assert that the string representation of the tile is correct
        assertEquals(".", tile.StringTile());
    }
}
