package GameTiles;

import GameTiles.Unit.Enemy.*;
import GameTiles.Unit.Enemy.Monster;
import GameTiles.Unit.Player.*;
import GameTiles.Unit.Enemy.Enemy;
import GameTiles.Unit.Player.Rouge;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    private Board board;
    private Player player;
    private boolean gameOver = false;
    private int gameLevel = 1;
    private CLI cli;
    private ArrayList<Enemy> enemies;
    private ArrayList<GameTile> tiles;
    private String path;

    public Manager(){
    }
