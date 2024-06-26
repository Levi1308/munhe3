package GameTiles.Unit.Player;

import GameTiles.GameTile;
import GameTiles.Unit.Unit;

public class Hunter extends Player {
    private Integer range;
    private Integer arrows_count;
    private Integer ticks_count=0;
    public Hunter(String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points
            , GameTile gameTile,Integer range,Integer arrows_count) {
        super(name, health_pool, health_amount, attack_points, defense_points, gameTile);
        this.range=range;
        this.arrows_count=arrows_count;
    }

    @Override
    public void LevelUp() {
        super.LevelUp();
        arrows_count=arrows_count+10*getLevel();
        setAttack_points(getAttack_points()+2*getLevel());
        setDefense_points(getDefense_points()+getLevel());
    }
    public void OnGameTick()
    {
        if(ticks_count==10)
        {
            arrows_count--;
            ticks_count=0;
        }
        else
            ticks_count++;
    }
    @Override
    public void OnAbilityCast() {

    }
    public String description() {
        return super.description()+
                "range: " + range + "\n" +
                "arrows_count: " + arrows_count + "\n" +
                "ticks_count: " + ticks_count+"\n";
    }

}
