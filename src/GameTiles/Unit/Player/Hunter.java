package GameTiles.Unit.Player;

import GameTiles.GameTile;

import GameTiles.Unit.Enemy.Enemy;
import java.util.List;

import GameTiles.Unit.Unit;

public class Hunter extends Player {
    private Integer range;
    private Integer arrows_count;
    private Integer ticks_count=0;

    public Hunter(int x, int y, String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points) {
        super(x,y,name, health_pool, attack_points, defense_points);

        this.range=range;
        this.arrows_count=arrows_count;
    }


    public void LevelUp() {
        super.levelUp();
        arrows_count=arrows_count+10*getLevel();
        setAttack_points(getAttack_points()+2*getLevel());
        setDefense_points(getDefense_points()+getLevel());
    }

    public void On_GameTick()

    {
        if(ticks_count==10)
        {
            arrows_count--;
            ticks_count=0;
        }
        else
            ticks_count++;
    }
    @Override

    public void interact(Unit unit) {
        unit.interact(this);

    public String description() {
        return super.description()+
                "range: " + range + "\n" +
                "arrows_count: " + arrows_count + "\n" +
                "ticks_count: " + ticks_count+"\n";
    }

    @Override
    public void castAbility() {
        if (arrows_count > 0) {
            arrows_count = arrows_count - 1;
            List<Enemy> enemies_inRange = enemyList_byRange(range);
            if (enemies_inRange.size() > 0){
                Enemy e = enemies_inRange.get(0);
                double minRange = range(e);
                for (Enemy enemy: enemies_inRange) {
                    if(range(enemy) < minRange) {
                        e = enemy;
                        minRange = range(enemy);
                    }
                }
                manager.sendMessage(getName() + " shot " + e.getName());
                int defense = e.random_Defense();
                manager.sendMessage(getName() + " hit " + e.getName() + " for " + (getAttack_points() - defense) + " ability damage.");
                e.lose_health(getAttack_points() - defense);
            }
        }
        else {
            manager.sendMessage(getName() + " tried to shoot, but there were not enough arrows.");
        }

    }


}
