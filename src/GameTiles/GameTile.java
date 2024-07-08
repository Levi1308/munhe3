
package GameTiles;
import GameTiles.Unit.Visited;
import GameTiles.Utilis.Board;
import GameTiles.Utilis.Position;
import UI.Manager;
import GameTiles.Unit.Visitor;

public abstract class GameTile implements  Visitor, Visited {

    private char tile;
    private Position position;
    public static Board board;
    public static Manager manager;

    public GameTile(char tile, Position position) {
        this.tile = tile;
        this.position=position;
        manager.addToTiles(this);
        board.addToBoard(this);
    }

    public void swapTiles(GameTile tile){
        board.swapTiles(this, tile);
        int x1 = this.position.getX();
        int y1 = this.position.getY();
        int x2 = tile.position.getX();
        int y2 = tile.position.getY();
        this.position.setX(x2);
        this.position.setY(y2);
        tile.position.setX(x1);
        tile.position.setY(y1);

    }

    public double range(GameTile tile) {
        int x2 = tile.position.getX();
        int y2 = tile.position.getY();
        return Math.sqrt(Math.pow(x2 - position.getX(),2) + Math.pow(y2 - position.getY(),2));
    }

    public char getTile() {
        return tile;
    }

    public void setTile(char tile) {
        this.tile = tile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String StringTile() {
        return tile+"";
    }

    public void accept(Visitor visitor){
        visitor.interact(this);
    }

    public String toString() {
        //return tile+"   "+position.getX()+","+ position.getY();
        return "";
    }
}
