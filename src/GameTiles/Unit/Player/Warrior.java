package GameTiles.Unit.Player;

import GameTiles.Empty;
import GameTiles.GameTile;
import GameTiles.Position;
import GameTiles.Unit.Enemy.Enemy;

import GameTiles.Unit.Unit;
import GameTiles.Wall;

import java.util.List;
import java.util.Random;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Warrior extends Player {
    public Integer ability_cooldown;
    public Integer remaining_cooldown = 0;

    public Warrior(char tile, Position p, String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points) {
        super(tile, p, name, health_pool, health_amount, attack_points, defense_points);
    }

    @Override
    public void levelUp() {
        super.levelUp();
        remaining_cooldown = 0;
        setHealth_pool(getHealth_amount() + 5 * getLevel());
        setAttack_points(getAttack_points() + 2 * getLevel());
        setDefense_points(getDefense_points() + getLevel());
    }

    public void OnGameTick() {
        remaining_cooldown--;
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
            List<Enemy> enemies = this.enemyList_byRange(3);
            if(enemies!=null) {
                Random rand = new Random();
                int index = rand.nextInt(1, enemies.size());
                Enemy battleEnemy = enemies.get(index);
                battleEnemy.setHealth_amount(this.getHealth_amount()-this.getHealth_pool()/10);
                if(battleEnemy.isDead())
                {
                    this.addExp(battleEnemy.GetExperiance());
                    battleEnemy.setTile('.');
                }
                this.remaining_cooldown = ability_cooldown;
                setHealth_amount(Math.min(getHealth_amount() + (10 * getDefense_points()), getHealth_pool()));
            }
            else
            {
                //add response
            }
            } else {
            //add response
        }
    }

    @Override
    public void interact(Enemy enemy) {
        this.Battle(enemy);
    }

    @Override
    public void interact(Player player) {
        //undefinded
    }

    @Override
    public void interact(Empty empty) {
        empty.swapTiles(this);
    }

    @Override
    public void interact(Wall wall) {
        wall.interact(this);
    }
}
