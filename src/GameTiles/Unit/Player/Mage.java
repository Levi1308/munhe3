package GameTiles.Unit.Player;

import GameTiles.Unit.Enemy.Enemy;
import GameTiles.GameTile;
import GameTiles.Unit.Unit;
import GameTiles.Unit.Player.Player;

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
    public  Mage(String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points
            , GameTile gameTile,Integer manaPool,Integer mana_cost, Integer hits_count,
                 Integer ability_range) {
        super(name, health_pool, health_amount, attack_points, defense_points,gameTile);
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

    @Override
    public void OnAbilityCast() {

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


    public void interact(Unit unit) {
        unit.interact(this);
    }


}
