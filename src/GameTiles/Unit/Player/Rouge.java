package GameTiles.Unit.Player;


import GameTiles.Empty;
import GameTiles.Utilis.Position;
import GameTiles.Unit.Enemy.Enemy;
import java.util.List;
import GameTiles.Unit.Unit;

import GameTiles.Wall;

public class Rouge extends Player{
    private Integer cost;
    private Integer current_energy;

    public Rouge(char tile, Position p, String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points
            , Integer cost ) {
        super(tile, p,name, health_pool, health_amount, attack_points, defense_points);
        this.cost=cost;
        this.current_energy= 100;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        current_energy=100;
        setAttack_points(getAttack_points()+3*getLevel());
    }

    @Override
    public void onGameTick()
    {
        current_energy=Math.min(current_energy + 10, 100);
    }

    public String description()
    {
        return super.description()+"\n"+
                "cost: " + cost + "\n" +
                "current_energy: " + current_energy+"\n";
    }



    @Override
    public void castAbility() {
        if (current_energy > cost) {
            manager.sendMessage(getName() + " used Fan of Knives");
            current_energy = current_energy - cost;
            List<Enemy> enemies_inRange = enemyList_byRange(2);
            for (Enemy e : enemies_inRange){
                int defense = e.random_Defense();
                manager.sendMessage(getName() + " attacked " + e.getName() + " for " + (getAttack_points() - defense) + " ability damage.");
                e.lose_health(getAttack_points() - defense);
            }
        }
        else {
            manager.sendMessage(getName() + " tried to cast Fan of Knives, but there was not enough energy: " + current_energy + '/' + cost);
        }
    }

    public void interact(Unit unit) {
        unit.interact(this);
    }
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCurrent_energy() {
        return current_energy;
    }

    public void setCurrent_energy(Integer current_energy) {
        this.current_energy = current_energy;
    }

}
