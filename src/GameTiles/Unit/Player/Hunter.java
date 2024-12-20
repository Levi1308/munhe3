package GameTiles.Unit.Player;

import GameTiles.Utilis.Position;
import GameTiles.Unit.Enemy.Enemy;
import GameTiles.Unit.Unit;

import java.util.List;

public class Hunter extends Player {
    private Integer range;
    private Integer arrows_count;
    private Integer ticks_count;

    public Hunter(char tile, Position p, String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points
            , Integer range) {
        super(tile, p, name, health_pool, health_amount, attack_points, defense_points);
        this.range = range;
        this.arrows_count = 10 * getLevel();
        this.ticks_count = 0;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        arrows_count = arrows_count + 10 * getLevel();
        setAttack_points(getAttack_points() + 2 * getLevel());
        setDefense_points(getDefense_points() + getLevel());
    }

    @Override
    public void onGameTick() {
        if (ticks_count == 10) {
            arrows_count += getLevel();
            ticks_count = 0;
        } else
            ticks_count++;
    }

    public String description() {
        return super.description() + "\n" +
                "range: " + range + "\n" +
                "arrows_count: " + arrows_count + "\n" +
                "ticks_count: " + ticks_count + "\n";
    }

    @Override
    public void castAbility() {
        if (arrows_count > 0) {
            arrows_count = arrows_count - 1;
            List<Enemy> enemies_inRange = enemyList_byRange(range);
            if (!enemies_inRange.isEmpty()) {
                Enemy e = enemies_inRange.get(0);
                double minRange = range(e);
                for (Enemy enemy : enemies_inRange) {
                    if (range(enemy) < minRange) {
                        e = enemy;
                        minRange = range(enemy);
                    }
                }
                manager.sendMessage(getName() + " shot " + e.getName());
                int defense = e.random_Defense();
                manager.sendMessage(getName() + " hit " + e.getName() + " for " + (getAttack_points() - defense) + " ability damage.");
                e.lose_health(getAttack_points() - defense);
            }
        } else {
            manager.sendMessage(getName() + " tried to shoot, but there were not enough arrows.");
        }
    }


    // Getters and Setters
    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public Integer getArrows_count() {
        return arrows_count;
    }

    public void setArrows_count(Integer arrows_count) {
        this.arrows_count = arrows_count;
    }

    public Integer getTicks_count() {
        return ticks_count;
    }

    public void setTicks_count(Integer ticks_count) {
        this.ticks_count = ticks_count;
    }
}
