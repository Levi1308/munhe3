package GameTiles.Unit.Enemy;

import GameTiles.Unit.Enemy.Monster;
import GameTiles.Unit.Enemy.Trap;
import GameTiles.Unit.Player.Hunter;
import GameTiles.Unit.Player.Mage;
import GameTiles.Unit.Player.Rouge;
import GameTiles.Unit.Player.Warrior;
import GameTiles.Utilis.Board;
import GameTiles.Utilis.Position;
import GameTiles.Wall;
import GameTiles.Unit.Unit;
import UI.Manager;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class MainTest {
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
    }



    @Test
    void MonsterMove() {
        Monster MonsterTestUp = new Monster('H', new Position(9,5), "MonsterTest", 100,100, 30, 5, 5, 50);
        Monster MonsterTestDown = new Monster('H', new Position(1,5), "MonsterTest", 100,100, 30, 5, 5, 50);
        Monster MonsterTestLeft = new Monster('H', new Position(5,9), "MonsterTest", 100,100, 30, 5, 5, 50);
        Monster MonsterTestRight = new Monster('H', new Position(5,1), "MonsterTest", 100,100, 30, 5, 5, 50);
        MonsterTestUp.move();
        MonsterTestDown.move();
        MonsterTestLeft.move();
        MonsterTestRight.move();
        assertEquals(8, MonsterTestUp.getPosition().getX());
        assertEquals(5, MonsterTestUp.getPosition().getY());
        assertEquals(2, MonsterTestDown.getPosition().getX());
        assertEquals(5, MonsterTestDown.getPosition().getY());
        assertEquals(5, MonsterTestLeft.getPosition().getX());
        assertEquals(8, MonsterTestLeft.getPosition().getY());
        assertEquals(5, MonsterTestRight.getPosition().getX());
        assertEquals(2, MonsterTestRight.getPosition().getY());
    }


    @Test
    void battle() {
        Monster MonsterTest = new Monster('H', new Position(7,7), "MonsterTest", 100,100, 30, 1, 5, 50);
        Rouge RougeTest = new Rouge('@', new Position(7,6), "RougeTest", 100, 100,40, 1, 12);
        RougeTest.interact(MonsterTest);
        assertTrue(MonsterTest.getHealth_amount()<100);
        MonsterTest.interact(RougeTest);
        assertTrue(RougeTest.getHealth_amount()<100);
    }

    @Test
    void Trap() {
        Trap TrapTest = new Trap('T',new Position(2,2),"TrapTest",1000,1000,20,1,20,2,2);
        assertTrue(TrapTest.getVisible());
        TrapTest.onGameTick();
        assertTrue(TrapTest.getVisible());
        TrapTest.onGameTick();
        assertTrue(TrapTest.getVisible());
        TrapTest.onGameTick();
        assertFalse(TrapTest.getVisible());
        TrapTest.onGameTick();
        assertFalse(TrapTest.getVisible());
        TrapTest.onGameTick();
        TrapTest.onGameTick();
        assertTrue(TrapTest.getVisible());
        TrapTest.onGameTick();
        TrapTest.onGameTick();
        TrapTest.onGameTick();
        assertFalse(TrapTest.getVisible());
        Warrior warrior = new Warrior('@',new Position(3,2), "warrior1", 100,100, 70, 20, 5);
        warrior.moveUp();
        assertTrue(TrapTest.getHealth_amount()<1000);



    }

    @Test
    void specialAbilities() {
        Warrior warrior = new Warrior('@',new Position(1,29), "warrior1", 100,100, 10, 20, 5);
        Mage mage = new Mage('@',new Position(5,20), "mage1", 100,100, 40, 15, 100, 20, 20, 4, 4);
        Hunter hunter = new Hunter('@',new Position(15,13), "hunter", 40,40, 20, 30, 12);
        Rouge rogue = new Rouge('@',new Position(22,2),"rogue1", 40,40, 30, 50, 70);
        Monster monster1 = new Monster('H', new Position(1,28), "monster1", 100,100, 30, 5, 2, 50);
        Monster monster2 = new Monster('H', new Position(5,19), "monster2", 100,100, 30, 5, 2, 50);
        Monster monster3 = new Monster('H', new Position(22,3), "monster3", 100,100, 30, 5, 2, 50);
        Monster monster4 = new Monster('H', new Position(15,12), "monster4", 100,100, 30, 5, 2, 50);
        Monster monster5 = new Monster('H', new Position(15,11), "monster5", 100,100, 30, 5, 2, 50);

        warrior.castAbility();
        assertTrue(monster1.getHealth_amount() < 100);
        mage.castAbility();
        assertTrue(monster2.getHealth_amount() < 100);
        int health = monster2.getHealth_amount();
        mage.castAbility();
        assertEquals(monster2.getHealth_amount(), health);
        rogue.castAbility();
        assertTrue(monster3.getHealth_amount() < 100);
        int health2 = monster3.getHealth_amount();
        rogue.castAbility();
        assertEquals(monster3.getHealth_amount(), health2);
        hunter.castAbility();
        assertTrue(monster4.getHealth_amount() < 100);
        assertEquals(monster5.getHealth_amount(), 100); //The hunter should hit only monster4 because he is the closest
    }
}