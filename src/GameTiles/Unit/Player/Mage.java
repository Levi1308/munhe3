package GameTiles.Unit.Player;

import GameTiles.Empty;
import GameTiles.Utilis.Position;
import GameTiles.Unit.Enemy.Enemy;
import GameTiles.Wall;
import GameTiles.Unit.Unit;

import java.util.List;
import java.util.Random;

public class Mage extends Player{
    private Integer mana_pool;
    private Integer current_mana;
    private Integer mana_cost;
    private Integer spell_power;
    private Integer hits_count;
    private Integer ability_range;

    public Mage(char tile, Position p, String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points
            , Integer manaPool, Integer mana_cost,Integer spellPower, Integer hits_count,
                 Integer ability_range) {
        super(tile,p,name, health_pool, health_amount, attack_points, defense_points);
        this.mana_pool=manaPool;
        this.current_mana=this.mana_pool/4;
        this.mana_cost=mana_cost;
        this.hits_count=hits_count;
        this.ability_range=ability_range;
        this.spell_power=spellPower;

    }

    @Override
    public void levelUp() {
        super.levelUp();
        mana_pool = mana_pool + (25 * getLevel());
        current_mana = Math.min(current_mana + (mana_pool / 4), mana_pool);
        spell_power = spell_power + (10 * getLevel());
    }

    @Override
    public void onGameTick()
    {
      current_mana=Math.min(mana_pool,current_mana+getLevel());
    }

    public String description(){
        return super.description() + ", Mana: " + current_mana +'/' + mana_pool + ", Spell power: " + spell_power;
    }

    @Override
    public void castAbility() {
        if (current_mana > mana_cost) {
            current_mana = current_mana - mana_cost;
            int hits = 0;
            while (hits < hits_count) {
                List<Enemy> enemies_inRange = enemyList_byRange(ability_range);
                if (enemies_inRange.size() != 0) {
                    Random random = new Random();
                    Enemy random_enemy = enemies_inRange.get(random.nextInt(enemies_inRange.size()));
                    int defense = random_enemy.random_Defense();
                    manager.sendMessage(getName() + " hit " + random_enemy.getName() + " for " + (spell_power - defense) + " ability damage.");
                    random_enemy.lose_health(spell_power - defense);
                    hits += 1;
                }
                else {
                    break;
                }
            }
        }
        else {
            manager.sendMessage(getName() + " tried to cast Blizzard, but there was not enough mana: " + current_mana + '/' + mana_cost);
        }
    }

    public void interact(Unit unit) {
        unit.interact(this);
    }
}
