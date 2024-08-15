package GameTiles.Unit.Player;

import GameTiles.Utilis.Board;
import GameTiles.Utilis.Position;
import UI.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HunterTest {

    Position p;
    Hunter hunter1;
    Hunter hunter2;

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
        p = new Position(0, 0);
        hunter1 = new Hunter('@', p, "Ygritte", 220, 220, 30, 2, 6);
        hunter2 = new Hunter('@', p, "Saar", 300, 300, 30, 1, 5);
    }

    @Test
    void levelUp() {
        int initialArrows = hunter1.getArrows_count();
        hunter1.levelUp();

        assertEquals(initialArrows + 10 * hunter1.getLevel(), hunter1.getArrows_count());
        assertEquals(42, hunter1.getAttack_points());
        assertEquals(6, hunter1.getDefense_points());
    }

    @Test
    void onGameTick() {
        for (int i = 0; i < 9; i++) {
            hunter1.onGameTick();
            assertEquals(i + 1, hunter1.getTicks_count());
        }

        hunter1.onGameTick();
        assertEquals(10, hunter1.getTicks_count());
    }
    @Test
    void castAbility() {
        hunter1.setArrows_count(1);
        hunter1.castAbility();
        assertEquals(0, hunter1.getArrows_count());

        hunter1.setArrows_count(0);
        hunter1.castAbility();
        assertEquals(0, hunter1.getArrows_count());
    }

    @Test
    void getRange() {
        assertEquals(6, hunter1.getRange());
    }

    @Test
    void setRange() {
        hunter1.setRange(7);
        assertEquals(7, hunter1.getRange());
    }

    @Test
    void getArrows_count() {
        assertEquals(10 * hunter1.getLevel(), hunter1.getArrows_count());
    }

    @Test
    void setArrows_count() {
        hunter1.setArrows_count(5);
        assertEquals(5, hunter1.getArrows_count());
    }

    @Test
    void getTicks_count() {
        assertEquals(0, hunter1.getTicks_count());
    }

    @Test
    void setTicks_count() {
        hunter1.setTicks_count(5);
        assertEquals(5, hunter1.getTicks_count());
    }
}
