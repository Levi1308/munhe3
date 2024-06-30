package GameTiles.Unit.Enemy;

import GameTiles.GameTile;
import GameTiles.Unit.Unit;

public class Trap extends Enemy{
    private int visibility_time;
    private int invisibility_time;
    private int tick_count;
    private char original_tile;
    private boolean visible;

    public Trap( String name, Integer health_pool,Integer health_amount, Integer attack_points, Integer defense_points,GameTile gameTile, Integer experience_value, int visibility_time, int invisibility_time) {
        super(name, health_pool,health_amount, attack_points, defense_points,gameTile, experience_value);
        this.visible = true;
        this.tick_count = 0;
        this.visibility_time = visibility_time;
        this.invisibility_time = invisibility_time;
    }

    public void setVisible(boolean bool){
        visible = bool;
    }



    @Override
    public void on_GameTick() {
        this.visible = this.tick_count < this.visibility_time;////visible or not
        if (this.visible) {
            this.setTile(this.original_tile);
        }
        else {
            this.setTile('.');
        }

        if (this.tick_count == this.visibility_time + this.invisibility_time) {///tick_count
            this.tick_count = 0;
        }
        else {
            this.tick_count = tick_count + 1;
        }

        if (range(player) < 2) {///attack
            this.interact(player);
        }
    }

    public void interact(GameTile tile) {

    }

    public void interact(Unit unit) {
        unit.interact(this);
    }



    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
