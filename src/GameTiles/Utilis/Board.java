package GameTiles.Utilis;

import GameTiles.GameTile;

public class Board {
    private GameTile[][] gameTiles;

    public Board(int a, int b) {
        gameTiles = new GameTile[a][b];
        GameTile.board = this;
    }

    public void swapTiles(GameTile t1, GameTile t2) {
        int t1x = t1.getPosition().getX();
        int t1y = t1.getPosition().getY();
        int t2x = t2.getPosition().getX();
        int t2y = t2.getPosition().getY();
        gameTiles[t1x][t1y] = t2;
        gameTiles[t2x][t2y] = t1;
    }
    public int getWidth(){
        return this.gameTiles.length;
    }

    public int getLength(){
        return this.gameTiles[1].length;
    }

    public GameTile getTile(int x, int y){
        return gameTiles[x][y];
    }

    public void replaceTile(GameTile tile){
        int x = tile.getPosition().getX();
        int y = tile.getPosition().getY();
        gameTiles[x][y] = tile;
    }

    public void addToBoard(GameTile tile){
        gameTiles[tile.getPosition().getX()][tile.getPosition().getY()] = tile;
    }

}
