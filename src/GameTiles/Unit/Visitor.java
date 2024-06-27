package GameTiles.Unit;

import GameTiles.Empty;
import GameTiles.GameTile;
import GameTiles.Wall;

public interface Visitor {
    public void interact(GameTile tile);
    public void interact(Unit unit);
    public void interact(Empty empty);
    public void interact(Wall wall);
}
