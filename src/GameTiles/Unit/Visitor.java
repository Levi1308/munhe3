package GameTiles.Unit;

import GameTiles.Empty;
import GameTiles.GameTile;
import GameTiles.Unit.Enemy.Enemy;
import GameTiles.Unit.Player.Player;
import GameTiles.Wall;

public interface Visitor {
    public void interact(Empty empty);
    public void interact(Wall wall);
    public void interact(Enemy enemy);
    public void interact(Player player);
    public void interact(GameTile gameTile);
}
