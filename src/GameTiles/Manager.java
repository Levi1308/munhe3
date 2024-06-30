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
        CLI cli = new CLI(this);
        tiles = new ArrayList<>();
        GameTile.manager = this;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void addToTiles(GameTile tile){
        tiles.add(tile);
    }



    public void removeEnemy(Enemy e){
        player.swapTiles(e);
        GameTile newTile = new Empty(e.getPosition());
        tiles.add(newTile);
        board.replaceTile(newTile);
        enemies.remove(e);
        if (enemies.size() == 0){
            sendMessage("You beat level " + gameLevel + ". Good job!");
            nextLevel();
        }
    }
    public void setCli(CLI cli) {
        this.cli = cli;
    }
    public void sendMessage(String msg) {
        this.cli.print(msg);
    }
    public boolean playerExists(){
        return player != null;
    }

    public void is_GameOver(){
        gameOver = true;
    }

    /*public void acceptInput(char c){
        if (c == 'e') {
            player.castAbility();
        }
        else {
            if (c == 'w'){
                player.moveUp();
            }
            else if (c == 's'){
                player.moveDown();
            }
            else if (c == 'a'){
                player.moveLeft();
            }
            else if (c == 'd'){
                player.moveRight();
            }
        }*/

    public void readFile(String path) throws FileNotFoundException {
        try {
            this.path = path;
            File nextBoard = new File(path + "\\level" + this.gameLevel + ".txt");
            Scanner readLines = new Scanner(nextBoard);
            int a = 0;
            int b = 0;
            readLines.useDelimiter("");

            while (readLines.hasNext() && readLines.next().equals("#")) {
                b++;
            }

            while (readLines.hasNextLine()) {
                a++;
                readLines.nextLine();
            }
            this.board = new Board(a, b);
            this.cli.setBoard(this.board);

            int x = 0;
            int y = 0;
            File nextLevel = new File(path + "\\level" + gameLevel + ".txt");
            Scanner scanner = new Scanner(nextLevel);
            while (scanner.hasNextLine()) {
                char[] chars = scanner.nextLine().toCharArray();
                for (char c : chars) {
                    initializer(c, x, y);
                    y += 1;
                }
                y = 0;
                x += 1;
            }
            cli.printBoard();
        } catch (FileNotFoundException var15) {
            this.sendMessage("You win!");
            this.is_GameOver();
        }
    }
    private void player_list() {
        sendMessage("");
        sendMessage("Warriors:");
        sendMessage("1. John Snow, Health: 300/300, Attack: 30, Defense: 4, Level: 1, Experience: 0/50, Cooldown: 0/3");
        sendMessage("2. The Hound, Health: 400/400, Attack: 20, Defense: 6, Level: 1, Experience: 0/50, Cooldown: 0/5");
        sendMessage("");
        sendMessage("Mages:");
        sendMessage("3. Melisandre, Health: 100/100, Attack: 5, Defense: 1, Level: 1, Experience: 0/50, Mana: 75/300, Spell Power: 15");
        sendMessage("4. Thoros of Myr, Health: 250/250, Attack: 25, Defense: 4, Level: 1, Experience: 0/50, Mana: 37/150, Spell Power: 20");
        sendMessage("");
        sendMessage("Rogues:");
        sendMessage("5. Arya stark, Health: 150/150, Attack: 40, Defense: 2, Level: 1, Experience: 50, Energy: 100");
        sendMessage("6. Bronn, Health: 250/250, Attack: 35, Defense: 3, Level: 1, Experience: 50, Energy: 100");
        sendMessage("");
        sendMessage("Hunter:");
        sendMessage("7. Ygritte, Health: 220/220, Attack: 30, Defense: 2, Level: 1, Experience: 50, Range: 6");
    }


    public void create_player(char c, Position p){
        if (c == '1'){
            new Warrior('@',p, "John Snow", 300, 30, 4, 3);
        }
        else if (c == '2'){
            new Warrior('@', p, "The Hound", 400, 20, 6, 5);
        }
        else if(c == '3'){
            new Mage('@',p, "Melisandre", 100, 5, 1, 300, 30, 15,5, 6 );
        }
        else if (c == '4'){
            new Mage('@',p, "Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4);
        }
        else if (c == '5'){
            new Rouge('@',p, "Arya Stark", 150, 40, 2, 20, 20);
        }
        else if (c == '6'){
            new Rouge('@', p, "Bronn", 250, 35, 3, 50, 50);
        }
        else if (c == '7'){
            new Hunter('@',p, "Ygritte", 220, 30, 30, 2, 6,1);
        }
    }


    public void initializer(char c,Position p){
        if (c == '.'){
            new Empty(p);
        }
        else if (c == '#'){
            new Wall(p);
        }
        else if (c == 's'){
            new Monster('s', p , "Lannister Soldier", 80, 80,8, 3, 3,25);
        }
        else if (c == 'k'){
            new Monster('k',p,"Lannister Knight", 200, 200, 14,8,4,50);
        }
        else if (c == 'q'){
            new Monster('q',p,"Queen's Guard", 400, 400, 20, 15, 5,100);
        }
        else if (c == 'z'){
            new Monster('z', p,"Wright" , 600, 600, 30, 15, 3,100);
        }
        else if (c == 'b'){
            new Monster('b',p, "Bear-Wright", 1000, 1000, 75,30,4, 250);
        }
        else if (c== 'g'){
            new Monster('g',p, "Giant-Wright", 1500, 1500,100, 40,5,500);
        }
        else if (c == 'w'){
            new Monster('w',p, "White Walker", 2000, 2000,150,50,6,1000);
        }
        else if (c == 'M'){
            new Boss('M', p, "The Mountain", 1000, 1000 ,60, 25,500,6,5);
        }
        else if (c == 'C'){
            new Boss('C',p, "Cersei", 100, 100, 10, 10,1000,1,8);
        }
        else if (c == 'K'){
            new Boss('K', p, "Night's King", 5000, 5000, 300, 150,5000,8,3);
        }
        else if (c == 'B'){
            new Trap('B', p, "Bonus Trap", 1, 1, 1, 1,250,1,5);
        }
        else if (c == 'Q'){
            new Trap('Q',p, "Queen's Trap", 250, 250,50,10, 100,3,1);
        }
        else if (c == 'D'){
            new Trap('D',p ,"Death Trap", 500, 500,100,20,250,1,10);
        }
        // Initializing the player
        else if (c == '@') {
            if (player == null) {
                sendMessage("Select Player:");
                player_list();
                char choice = cli.choose_player();
                create_player(choice, p);
                sendMessage("You chose: " + player.getName());
            }
            else {
                player.getPosition().setX(p.getX());
                player.getPosition().setY(p.getY());
                board.replaceTile(player);
            }
        }
    }

    public void nextLevel(){
        gameLevel += 1;
        try{
            readFile(this.path);
        } catch (FileNotFoundException e) {
            sendMessage("You win!");
        }
    }
}
