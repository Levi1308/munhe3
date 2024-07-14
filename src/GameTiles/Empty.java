package GameTiles;

import GameTiles.Unit.Enemy.Enemy;
import GameTiles.Unit.Player.Player;
import GameTiles.Unit.Unit;
import GameTiles.Unit.Visitor;
import GameTiles.Utilis.Position;


public class Empty extends GameTile {

    public Empty(Position p) {
        super('.', p);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.interact(this);
    }

    public void interact(Empty empty) {
    }

    public void interact(Wall wall) {
    }

    @Override
    public void interact(Enemy enemy) {
        enemy.swapTiles(this);
    }

    @Override
    public void interact(Player player) {
        player.swapTiles(this);
    }

}