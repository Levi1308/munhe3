package GameTiles.Unit.Enemy;


import GameTiles.GameTile;
import GameTiles.Utilis.Position;
import GameTiles.Unit.Unit;
import GameTiles.Unit.Player.Player;


public abstract class Enemy extends Unit{
    private Integer experience_value;
    public static Player player;

    public Enemy(char tile, Position p, String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points, Integer experience_value ) {
        super(tile, p, name, health_pool,health_amount, attack_points, defense_points);
        this.experience_value = experience_value;
        addToEnemy(this);
    }
    public int GetExperiance() {
        return this.experience_value;
    }
    public abstract void on_GameTick();


    public void interact(Player player){
        manager.sendMessage(getName() + " engaged in combat with " + player.getName() + '.');
        int rand_att = random_Attack();
        int rand_def = player.random_Defense();
        if (rand_att - rand_def > 0){
            manager.sendMessage(getName() + " dealt " + (rand_att - rand_def) + " damage to " + player.getName() + ".");
            player.lose_health(rand_att-rand_def);
        }
        else {
            manager.sendMessage(getName() + " dealt " + 0 + " to " + player.getName() + ".");
        }
        manager.sendMessage(description());
    }

    public void interact(Enemy enemy){}

    public void lose_health(int num){
        this.setHealth_amount(getHealth_amount() - num);
        if (isDead()){
            player.swapTiles(this);
            manager.sendMessage(getName() + " died. " + player.getName() + " gained " + experience_value + " experience.");
            player.addExp(this.experience_value);
            manager.removeEnemy(this);
        }
    }
    public void interact(GameTile tile) {
        tile.interact(this);
    }

}
