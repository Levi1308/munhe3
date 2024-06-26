package GameTiles;

public class GameTile {
    private char tile;
    private Position position;
    public GameTile(char tile,Position position)
    {
        this.tile=tile;
        this.position=position;
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
}
