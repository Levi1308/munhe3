package GameTiles.Unit.Player;


import GameTiles.Empty;
import GameTiles.Utilis.Position;
import GameTiles.Unit.Enemy.Enemy;
import java.util.List;

import GameTiles.Wall;

public class Rouge extends Player{
    private Integer cost;
    private Integer current_energy=100;
    public Rouge(char tile, Position p, String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points
            , Integer cost ) {
        super(tile, p,name, health_pool, health_amount, attack_points, defense_points);
        this.cost=cost;
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
        current_energy=Math.min(current_energy+10,10100);
    }
    public String description()
    {
        return super.description()+
                "cost: " + cost + "\n" +
                "current_energy: " + current_energy+"\n";
    }
    @Override
    public void castAbility() {
        if (current_energy >= cost) {
            current_energy -= cost;
            List<Enemy> enemies = this.enemyList_byRange(2);
            for (Enemy enemy : enemies) {
                int lost=this.getAttack_points()-enemy.getDefense_points();
                if(lost>0)
                {
                    enemy.setHealth_amount(enemy.getHealth_amount()-lost);
                    if(enemy.isDead())
                    {
                        this.addExp(enemy.GetExperiance());
                        enemy.setTile('.');
                    }
                }
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
//undefined
    }
    @Override
    public void interact(Empty empty) {
    this.swapTiles(empty);
    }
    @Override
    public void interact(Wall wall) {
        wall.interact(this);
    }
}
