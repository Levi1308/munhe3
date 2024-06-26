package GameTiles.Unit.Player;

import GameTiles.GameTile;
import GameTiles.Unit.Unit;
public abstract class Player extends Unit {
    private Integer experience=0;
    private Integer level=1;

    public Player(String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points,GameTile gameTile) {
        super(name, health_pool, health_amount, attack_points, defense_points, gameTile);
    }
    public void LevelUp()
    {
        experience+=50*level;
        level++;
        setHealth_pool(getHealth_pool()+10*level);
        setHealth_amount(getHealth_pool());
        setAttack_points(getAttack_points()+4*level);
        setDefense_points(getDefense_points()+level);
    }
    abstract public void OnAbilityCast();

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }
    public String description(){
        return super.description()+
                "experience: " + experience + "\n" +
                "level: " + level;

    }
}
