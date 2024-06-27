package GameTiles;

import GameTiles.Unit.Unit;



public class Empty extends GameTile {
    public Empty(int x, int y) {
        super('.', x, y);
    }

    public void interact(GameTile tile) {}

    public void interact(Unit unit){this.swapTiles(unit);}

    public void interact(Empty empty) {}

    public void interact(Wall wall) {}
}
