package GameTiles.Unit.Player;

import GameTiles.Empty;
import GameTiles.Position;
import GameTiles.Unit.Enemy.Enemy;
import GameTiles.GameTile;
import GameTiles.Unit.Unit;
import GameTiles.Unit.Player.Player;
import GameTiles.Wall;

import java.util.List;
import java.util.Random;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mage extends Player{
    private Integer mana_pool;
    private Integer current_mana;
    private Integer mana_cost;
    private Integer spell_power;
    private Integer hits_count;
    private Integer ability_range;
    public  Mage(char tile, Position p, String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points
            , Integer manaPool, Integer mana_cost, Integer hits_count,
                 Integer ability_range) {
        super(tile,p,name, health_pool, health_amount, attack_points, defense_points);
        this.mana_pool=manaPool;
        this.current_mana=this.mana_pool/4;
        this.mana_cost=mana_cost;
        this.hits_count=hits_count;
        this.ability_range=ability_range;


    }

    @Override
    public void levelUp() {
        super.levelUp();
        mana_pool=mana_pool+25*getLevel();
        current_mana=Math.min(current_mana+mana_pool/4,mana_pool);
        spell_power=spell_power+10*getLevel();
    }

    public void OnGameTick()
    {
        current_mana=current_mana-mana_cost;
    }
    public String description()
    {
        return super.description()+
                "mana_pool: " + mana_pool + "\n" +
                "current_mana: " + current_mana + "\n" +
                "mana_cost: " + mana_cost + "\n" +
                "spell_power: " + spell_power + "\n" +
                "hits_count: " + hits_count + "\n" +
                "ability_range: " + ability_range+"\n";
    }

    @Override
    public void castAbility() {
        if(current_mana>=mana_cost)
        {
            this.current_mana-=mana_cost;
            int hits=0;
            List<Enemy> enemies=this.enemyList_byRange(ability_range);
            while(hits<hits_count && enemies!=null)
            {
                Random rand=new Random();
                int index=rand.nextInt(1,enemies.size());
                Enemy battleEnemy=enemies.get(index);
                int lost=this.spell_power-battleEnemy.getDefense_points();
                if(lost>0) {
                    battleEnemy.setHealth_amount(battleEnemy.getHealth_amount()-lost);
                    if(battleEnemy.isDead()) {
                        this.addExp(battleEnemy.GetExperiance());
                        battleEnemy.setTile('.');
                    }
                }
                hits++;
            }
        }
        else
        {
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
