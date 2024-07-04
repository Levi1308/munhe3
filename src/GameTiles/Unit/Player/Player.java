package GameTiles.Unit.Player;



import GameTiles.Empty;
import GameTiles.GameTile;
import GameTiles.Utilis.Position;
import GameTiles.Unit.Unit;
import GameTiles.Unit.Enemy.*;
import GameTiles.Unit.Visited;
import GameTiles.Unit.HeroicUnit;
import GameTiles.Wall;


public abstract class Player extends Unit implements HeroicUnit{
    private Integer experience = 0;
    private Integer level = 1;

    public Player(char tile, Position p, String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points) {
        super(tile,p,name, health_pool, health_amount, attack_points, defense_points);
    }
    public void levelUp()
    {
        experience+=50*level;
        level++;
        setHealth_pool(getHealth_pool()+10*level);
        setHealth_amount(getHealth_pool());
        setAttack_points(getAttack_points()+4*level);
        setDefense_points(getDefense_points()+level);
    }
    public abstract void onGameTick();
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExperience() {
        return experience;
    }

    public void addExp(int experience) {
        this.experience = this.experience + experience;
        while (this.experience >= (level * 50)) {
            levelUp();
        }
    }

    public String description(){
        return super.description()+
                "experience: " + experience + "\n" +
                "level: " + level;

    }

    public abstract void castAbility();
    @Override
    public void interact(Enemy enemy) {
        this.Battle(enemy);
    }
    @Override
    public void interact(Player player) {}
    @Override
    public void interact(Empty empty) {
        this.swapTiles(empty);
    }
    @Override
    public void interact(Wall wall) {
        wall.interact(this);
    }
    public void Battle(Enemy enemy){
        int attack=random_Attack();
        int defence=random_Defense();
        if(attack-defence>0)
        {
            enemy.setHealth_amount(enemy.getHealth_amount()-(attack-defence));
            if(enemy.isDead())
            {
               this.addExp(enemy.GetExperiance());
                enemy.setTile('.');
                this.swapTiles(enemy);
            }
        }
    }


    public void lose_health(int damage){
        setHealth_amount(getHealth_amount() - damage);
        if (isDead()) {
            setTile('X');
            manager.sendMessage("Game is over, you lost :(");
            manager.is_GameOver();
        }
    }

    public void moveUp() {
        Visited tile = (Visited) board.getTile(getPosition().getX() - 1, getPosition().getY());  //move up
        tile.accept(this);
    }

    public void moveDown() {
        Visited tile = (Visited) board.getTile(getPosition().getX() + 1, getPosition().getY());  //move down
        tile.accept(this);
    }

    public void moveLeft() {
        Visited tile = (Visited) board.getTile(getPosition().getX(), getPosition().getY() - 1); //move left
        tile.accept(this);
    }

    public void moveRight() {
        Visited tile = (Visited) board.getTile(getPosition().getX(), getPosition().getY() + 1); //move right
        tile.accept(this);
    }
}

