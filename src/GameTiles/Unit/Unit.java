package GameTiles.Unit;

import GameTiles.GameTile;
import GameTiles.Utilis.Position;
import GameTiles.Unit.Enemy.*;
import GameTiles.Unit.Player.*;
import GameTiles.Empty;
import GameTiles.Wall;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Unit extends GameTile implements Visitor{
    private String name;
    private Integer health_pool;
    private Integer health_amount;
    private Integer attack_points;
    private Integer defense_points;
    protected static ArrayList<Enemy> enemyList = new ArrayList<>();


    public Unit(char tile, Position p,String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points) {
        super(tile,p);
        this.name = name;
        this.health_pool = health_pool;
        this.health_amount = health_amount;
        this.attack_points = attack_points;
        this.defense_points = defense_points;
        manager.setEnemies(enemyList);
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setHealth_pool(Integer health_pool) {
        this.health_pool = health_pool;
    }

    public void setHealth_amount(Integer health_amount) {
        this.health_amount = Math.max(health_amount,0);
    }

    public void setAttack_points(Integer attack_points) {
        this.attack_points = attack_points;
    }

    public void setDefense_points(Integer defense_points) {this.defense_points = defense_points;}

   public Integer getHealth_pool() {
        return health_pool;
    }

    public Integer getHealth_amount() {
        return health_amount;
    }

    public Integer getAttack_points() {
        return attack_points;
    }

    public Integer getDefense_points() {
        return defense_points;
    }

    public static List<Enemy> getEnemyList() {
        return enemyList;
    }

    public String getName() {
        return name;
    }

    public void addToEnemy(Enemy enemy) {
        enemyList.add(enemy);
    }

    public String description() {
        return "Description:\n"+
                "Name: "+getName()+"\n"+
                "Health pool: "+this.getHealth_pool()+"\n"+
                "Health amount: "+this.getHealth_amount()+"\n"+
                "Attack points: "+getAttack_points()+"\n"+
                "Defence points: "+getDefense_points()+"\n";
    }

    public int random_Defense(){
        Random random = new Random();
        int defense = random.nextInt(defense_points);
        manager.sendMessage(getName() + " rolled " + defense + " defense points");
        return defense;
    }

    public int random_Attack(){
        Random random = new Random();
        int attack = random.nextInt(attack_points);
        manager.sendMessage(getName() + " rolled " + attack + " defense points");
        return attack;
    }

    public void interact(Player player){
        player.interact(this);
    }

    public void interact(Enemy enemy){
        enemy.interact(this);
    }

    public void interact(GameTile tile){
        tile.interact(this);
    }

    public void interact(Empty empty){
        this.swapTiles(empty);
    }

    public void interact(Wall wall){
        wall.interact(this);
    }
    public boolean isDead(){
        return health_amount <= 0;
    }

    public List<Enemy> enemyList_byRange(int range){
        List<Enemy> enemyList1 = enemyList;
        List<Enemy> enemyList3 = new ArrayList<>();
        for (Enemy e: enemyList1) {
            if(e.range(this) <= range) {
                enemyList3.add(e);
            }
        }
        return enemyList3;
    }
    public Enemy NearestEnemy(List<Enemy> enemies)
    {
        Enemy closeEnemy=null;
        double mindistance=100;//default number
        for(Enemy enemy:enemies){
            double distance=range(enemy);
            if(distance<mindistance)
            {
                closeEnemy=enemy;
                mindistance=distance;
            }
        }
        return closeEnemy;
    }
}
