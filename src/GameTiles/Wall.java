package GameTiles;
import GameTiles.Unit.Enemy.Enemy;
import GameTiles.Unit.Player.Player;
import GameTiles.Unit.Unit;

public class Wall extends GameTile{
    public Wall (int x, int y){
        super('#', new Position(x,y));
    }

    public void interact(Empty empty) {

    }

    public void interact(Wall wall) {

    }

    @Override
    public void interact(Enemy enemy) {

    }

    @Override
    public void interact(Player player) {

    }
}
