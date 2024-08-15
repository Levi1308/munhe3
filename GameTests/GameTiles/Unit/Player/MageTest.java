package GameTiles.Unit.Player;

import GameTiles.Utilis.Board;
import GameTiles.Utilis.Position;
import UI.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MageTest {

    Mage mage1;
    Mage mage2;

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
        mage1 = new Mage('@', p, "Melisandre", 100, 100, 5, 1, 300, 30, 15, 5, 6);
        mage2 = new Mage('@', p, "Levi", 100, 100, 5, 3, 300, 20, 15, 15, 46);
    }

    @Test
    void levelUp() {
        int initialManaPool = mage1.getMana_pool();
        int initialCurrentMana = mage1.getCurrent_mana();
        int initialDefense = mage1.getDefense_points();

        mage1.levelUp();

        assertEquals(initialManaPool + 25 * mage1.getLevel(), mage1.getMana_pool());
        assertEquals(Math.min(mage1.getMana_pool(), initialCurrentMana + (mage1.getMana_pool() / 4)), mage1.getCurrent_mana());
        assertEquals(13, mage1.getAttack_points());
        assertEquals(initialDefense + mage1.getLevel(), mage1.getDefense_points());
    }

    @Test
    void onGameTick() {
        for (int i = 0; i < 9; i++) {
            mage1.onGameTick();
        }
        mage1.onGameTick();
        assertEquals(85, mage1.getCurrent_mana());
    }
    @Test
    void castAbility() {
        mage1.castAbility();
        assertEquals(45, mage1.getCurrent_mana());

        mage1.setCurrent_mana(0);
        mage1.castAbility();
        assertEquals(0, mage1.getCurrent_mana());
    }

    @Test
    void getMana_pool() {
        assertEquals(300, mage1.getMana_pool());
    }

    @Test
    void setMana_pool() {
        mage1.setMana_pool(350);
        assertEquals(350, mage1.getMana_pool());
    }

    @Test
    void getCurrent_mana() {
        assertEquals(75, mage1.getCurrent_mana());
    }

    @Test
    void setCurrent_mana() {
        mage1.setCurrent_mana(40);
        assertEquals(40, mage1.getCurrent_mana());
    }

    @Test
    void getMana_cost() {
        assertEquals(30, mage1.getMana_cost());
    }

    @Test
    void setMana_cost() {
        mage1.setMana_cost(20);
        assertEquals(20, mage1.getMana_cost());
    }

    @Test
    void getSpell_power() {
        assertEquals(15, mage1.getSpell_power());
    }

    @Test
    void setSpell_power() {
        mage1.setSpell_power(10);
        assertEquals(10, mage1.getSpell_power());
    }

    @Test
    void getHits_count() {
        assertEquals(5, mage1.getHits_count());
    }

    @Test
    void setHits_count() {
        mage1.setHits_count(10);
        assertEquals(10, mage1.getHits_count());
    }

    @Test
    void getAbility_range() {
        assertEquals(6, mage1.getAbility_range());
    }

    @Test
    void setAbility_range() {
        mage1.setAbility_range(7);
        assertEquals(7, mage1.getAbility_range());
    }
}
