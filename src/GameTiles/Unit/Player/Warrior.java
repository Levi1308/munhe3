package GameTiles.Unit.Player;

import GameTiles.Empty;
import GameTiles.Utilis.Position;
import GameTiles.Unit.Enemy.Enemy;
import GameTiles.Unit.Unit;

import GameTiles.Wall;

import java.util.List;
import java.util.Random;

public class Warrior extends Player {
    public Integer ability_cooldown;
    public Integer remaining_cooldown;

    public Warrior(char tile, Position p, String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points, Integer ability_cooldown) {
        super(tile, p, name, health_pool, health_amount, attack_points, defense_points);
        this.ability_cooldown = ability_cooldown;
        this.remaining_cooldown = 0;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        remaining_cooldown = 0;
        setHealth_pool(getHealth_amount() + 5 * getLevel());
        setAttack_points(getAttack_points() + 2 * getLevel());
        setDefense_points(getDefense_points() + getLevel());
    }

    @Override
    public void onGameTick() {
        remaining_cooldown = Math.max(remaining_cooldown - 1, 0);
    }

    @Override
    public String description() {
        return super.toString() +
                "Ability cooldown: " + ability_cooldown + "\n" +
                "Remaining cooldown: " + remaining_cooldown + "\n";

    }

    @Override
    public void castAbility() {
        if (remaining_cooldown == 0) {
            remaining_cooldown = ability_cooldown;
            setHealth_amount(Math.min(getHealth_amount() + (10 * getDefense_points()), getHealth_pool()));
            manager.sendMessage(getName() + " used Avenger's Shield");
            List<Enemy> enemies_list = getEnemyList();
            if (enemies_list.size() != 0) {
                List<Enemy> enemies_inRange = enemyList_byRange(3);
                if (enemies_inRange.size() != 0) {
                    Random random = new Random();
                    Enemy random_enemy = enemies_inRange.get(random.nextInt(enemies_inRange.size()));
                    int defense = random_enemy.random_Defense();
                    manager.sendMessage(getName() + " hit " + random_enemy.getName() + " for " + ((getHealth_pool() / 10) + -defense) + " ability damage");
                    random_enemy.lose_health((getHealth_pool() / 10) - defense);
                }
            }
        } else {
            manager.sendMessage(getName() + " tried to cast Avenger's Shield, but there is a cooldown: " + remaining_cooldown);
        }
    }
    public void interact(Unit unit) {
        unit.interact(this);
    }
}
