package GameTiles;
import GameTiles.Unit.Unit;

public class Wall extends GameTiles{
    public Wall (int x, int y){
        super('#', x, y);
    }

    public void interact(GameTiles tile){
    }

    public void interact(Unit unit) {
    }

    public void interact(Empty empty) {

    }

    public void interact(Wall wall) {

    }
}
