package GameTiles;

import GameTiles.Utilis.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTileTest {
    private GameTile gameTile1;
    private GameTile gameTile2;

    @BeforeEach
    void setUp() {
        // Create GameTile instances for testing
        Position position1 = new Position(1, 1);
        Position position2 = new Position(2, 2);
        gameTile1 = new Empty(position1);
        gameTile2 = new Empty(position2);
    }

    @Test
    void swapTiles() {
        // Before swapping
        Position originalPosition1 = gameTile1.getPosition();
        Position originalPosition2 = gameTile2.getPosition();

        // Swap tiles
        gameTile1.swapTiles(gameTile2);

        // After swapping
        assertEquals(originalPosition1, gameTile2.getPosition());
        assertEquals(originalPosition2, gameTile1.getPosition());
    }

    @Test
    void range() {
        // Calculate range between two tiles
        double distance = gameTile1.range(gameTile2);

        // Assuming positions are (1,1) and (2,2), the distance should be sqrt(2)
        assertEquals(Math.sqrt(2), distance, 0.01);
    }

    @Test
    void setTile() {
        // Test setter for tile character
        gameTile1.setTile('X');
        assertEquals('X', gameTile1.getTile());
    }

    @Test
    void setPosition() {
        // Test setter for position
        Position newPosition = new Position(3, 3);
        gameTile1.setPosition(newPosition);
        assertEquals(newPosition, gameTile1.getPosition());
    }
}
