package GameTiles.Unit.Player;



import GameTiles.Empty;
import GameTiles.GameTile;
import GameTiles.Unit.Visitor;
import GameTiles.Utilis.Position;
import GameTiles.Unit.Unit;
import GameTiles.Unit.Enemy.*;
import GameTiles.Unit.Visited;
import GameTiles.Unit.HeroicUnit;
import GameTiles.Wall;


public abstract class Player extends Unit implements HeroicUnit{
    private Integer experience;
    private Integer level;

    public Player(char tile, Position p, String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points) {
        super('@',p,name, health_pool, health_amount, attack_points, defense_points);
        Enemy.player = this;
        manager.setPlayer(this);
        this.level = 1;
        this.experience = 0;
    }
    public void levelUp()
    {
        experience = experience - (level * 50);
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
    @Override
    public String description() {
        return super.description() + "\n" +
                "experience: " + experience + "\n" +
                "level: " + level;

    }

    public abstract void castAbility();

    @Override
    public void interact(Enemy enemy) {
        this.battle(enemy);
    }
    @Override
    public void interact(Player player) {

    }

    protected void battle(Enemy enemy){
        manager.sendMessage(getName() + " engaged in combat with " + enemy.getName() + '.');
        int rand_att = random_Attack();
        int rand_def = enemy.random_Defense();
        if (rand_att - rand_def > 0){
            manager.sendMessage(getName() + " dealt " + (rand_att-rand_def) + " damage to " + enemy.getName() +".");
            enemy.lose_health(rand_att-rand_def);
        }
        else {
            manager.sendMessage(getName() + " dealt " + 0 + " to " + enemy.getName() + ".");
        }
        manager.sendMessage(enemy.description());
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
        Visited tile = board.getTile(getPosition().getX() - 1, getPosition().getY());  //move up
        tile.accept(this);
    }

    public void moveDown() {
        Visited tile =  board.getTile(getPosition().getX() + 1, getPosition().getY());  //move down
        tile.accept(this);
    }

    public void moveLeft() {
        Visited tile =  board.getTile(getPosition().getX(), getPosition().getY() - 1); //move left
        tile.accept(this);
    }

    public void moveRight() {
        Visited tile =  board.getTile(getPosition().getX(), getPosition().getY() + 1); //move right
        tile.accept(this);
    }
    @Override
    public void accept(Visitor visitor) {
        if(visitor instanceof Player)
            level=0;
        visitor.interact(this);
    }
}

