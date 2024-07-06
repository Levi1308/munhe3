package GameTiles.Unit.Enemy;

import java.util.Random;

import GameTiles.Utilis.Position;
import GameTiles.Unit.HeroicUnit;
import GameTiles.Unit.Unit;


public class Boss extends Monster implements HeroicUnit{
    private int ability_frequency;
    private int combat_tick;

    public Boss(char tile, Position p,String name, int health_pool, int health_amount, int attack_points, int defense_points, int experience, int vision_range, int ability_frequency){
        super(tile, p, name, health_pool,health_amount, attack_points, defense_points, vision_range, experience);
        this.ability_frequency = ability_frequency;
        this.combat_tick = 0;
    }


    @Override
    public void onGameTick() {
        if (range(player) < getVision_range()){
            if (combat_tick == ability_frequency){
                combat_tick = 0;
                this.castAbility();
            }
            else {
                combat_tick = combat_tick + 1;
                this.move();
            }
        }
        else {
            combat_tick = 0;
            this.move();
        }
    }

    @Override
    public void castAbility() {
        Random random = new Random();
        int defense = random.nextInt(player.getDefense_points());
        manager.sendMessage(player.getName() + " rolled " + defense + " defense points.");
        int damage = getAttack_points() - defense;
        if (damage > 0) {
            manager.sendMessage(getName() + " shot " + player.getName() + ", dealing " + damage + " ability damage.");
            player.lose_health(damage);
        }
        else {
            manager.sendMessage(getName() + " shot " + player.getName() + ", dealing 0" + " ability damage.");
        }
    }
}

