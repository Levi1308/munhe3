package GameTiles.Unit.Player;

import GameTiles.Empty;

import GameTiles.Utilis.Position;
import GameTiles.Unit.Enemy.Enemy;
import java.util.List;

import GameTiles.Wall;

public class Hunter extends Player {
    private Integer range;
    private Integer arrows_count;
    private Integer ticks_count=0;
    public Hunter(char tile, Position p,String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points
            , Integer range, Integer arrows_count) {
        super(tile,p,name, health_pool, health_amount, attack_points, defense_points);
        this.range=range;
        this.arrows_count=arrows_count;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        arrows_count=arrows_count+10*getLevel();
        setAttack_points(getAttack_points()+2*getLevel());
        setDefense_points(getDefense_points()+getLevel());
    }
    @Override
    public void onGameTick()
    {
        if(ticks_count==10)
        {
            arrows_count--;
            ticks_count=0;
        }
        else
            ticks_count++;
    }
    public String description() {
        return super.description()+
                "range: " + range + "\n" +
                "arrows_count: " + arrows_count + "\n" +
                "ticks_count: " + ticks_count+"\n";
    }
    @Override
    public void castAbility() {
        List<Enemy> enemies=this.enemyList_byRange(range);
        if(arrows_count==0 && enemies!=null)
        {
            arrows_count--;
            Enemy battleEnemy=this.NearestEnemy(enemies);
            int lost=this.getAttack_points()-battleEnemy.getDefense_points();
            if(lost>0)
            {
                battleEnemy.setHealth_amount(battleEnemy.getHealth_amount()-lost);
                if(battleEnemy.isDead())
                {
                    this.addExp(battleEnemy.GetExperiance());
                    battleEnemy.setTile('.');
                }
            }

        }
        else {
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
        this.swapTiles(empty);
    }
    @Override
    public void interact(Wall wall) {
        wall.interact(this);
    }

}
