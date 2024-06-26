package GameTiles.Unit.Player;

import GameTiles.GameTile;
import GameTiles.Unit.Enemy.Enemy;

import java.util.Random;

public class Warrior extends Player{
    public Integer ability_cooldown;
    public Integer remaining_cooldown=0;
    public Warrior(String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points
    , GameTile gameTile) {
        super(name, health_pool, health_amount, attack_points, defense_points,gameTile);
    }

    @Override
    public void LevelUp() {
        super.LevelUp();
        remaining_cooldown=0;
        setHealth_pool(getHealth_amount()+5*getLevel());
        setAttack_points(getAttack_points()+2*getLevel());
        setDefense_points(getDefense_points()+getLevel());
    }

    @Override
    public void OnAbilityCast() {
        this.remaining_cooldown=ability_cooldown;
        setHealth_amount(Math.min(getHealth_amount()+(10*getDefense_points()),getHealth_pool()));

    }
    public void OnGameTick()
    {
        remaining_cooldown--;
    }
    public double OnRange(Enemy enemy)
    {
        int myX=this.getGameTile().getPosition().getX();
        int myY=this.getGameTile().getPosition().getY();
        int otherX=enemy.getGameTile().getPosition().getX();
        int otherY=enemy.getGameTile().getPosition().getY();
        return Math.sqrt(Math.pow((myX-otherX),2)+Math.pow((myY-otherY),2));
    }
    @Override
    public String description() {
        return super.toString()+
                "Ability cooldown: "+ability_cooldown+"\n"+
                "Remaining cooldown: "+remaining_cooldown+"\n";

    }
}
