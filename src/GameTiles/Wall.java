package GameTiles;
import GameTiles.Unit.Enemy.Enemy;
import GameTiles.Unit.Player.Player;
import GameTiles.Utilis.Position;
import GameTiles.Unit.Unit;


public class Wall extends GameTile{
    public Wall (Position p){
        super('#', p);
    }

    public void interact(Empty empty) {

    }

    public void interact(Wall wall) {

    }
    public void interact(GameTile tile) {

    }
    public void interact(Unit unit) {
    }
}
