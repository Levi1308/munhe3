package GameTiles;

import GameTiles.Unit.Enemy.Enemy;
import GameTiles.Unit.Player.Player;
import GameTiles.Unit.Unit;
import GameTiles.Utilis.Position;


public class Empty extends GameTile {

    public Empty(Position p) {
        super('.',p);
    }

    public void interact(Unit unit){this.swapTiles(unit);}

    public void interact(Empty empty) {}

    public void interact(Wall wall) {}

    public void interact(GameTile tile) {}

}
