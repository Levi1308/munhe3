package GameTiles;
import GameTiles.Unit.Enemy.Enemy;
import GameTiles.Unit.Player.Player;
import GameTiles.Utilis.Position;

public class Wall extends GameTile{
    public Wall (Position p){
        super('#', p);
    }

    public void interact(Empty empty) {

    }

    public void interact(Wall wall) {

    }
    public void interact(GameTile tile) {
        tile.interact(this);
    }
    @Override
    public void interact(Enemy enemy) {

    }

    @Override
    public void interact(Player player) {

    }
}
