package GameTiles.Unit.Player;


import GameTiles.Unit.Unit;
import GameTiles.Unit.Enemy.Enemy;
import java.util.List;

import GameTiles.GameTile;

import java.util.ArrayList;
import java.util.List;

public class Rouge extends Player{
    private Integer cost;
    private Integer current_energy=100;
    public Rouge(String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points
            , GameTile gameTile,Integer cost ) {
        super(name, health_pool, health_amount, attack_points, defense_points,gameTile);
        this.cost=cost;
    }

    @Override
    public void OnAbilityCast() {

    }

    @Override
    public void levelUp() {
        super.levelUp();
        current_energy=100;
        setAttack_points(getAttack_points()+3*getLevel());
    }
    public void OnGameTick()
    {
        current_energy=Math.min(current_energy+10,10100);
    }
    public String description()
    {
        return super.description()+
                "cost: " + cost + "\n" +
                "current_energy: " + current_energy+"\n";
    }

    public void interact(Unit unit) {
        unit.interact(this);
    }


}
