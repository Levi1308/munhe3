package GameTiles.Unit.Enemy;

import GameTiles.GameTile;
import GameTiles.Utilis.Position;
import GameTiles.Unit.Unit;

public class Trap extends Enemy {
    private int visibility_time;
    private int invisibility_time;
    private int tick_count;
    private char original_tile;
    private boolean visible;

    public Trap(char tile, Position p, String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points, Integer experience_value, int visibility_time, int invisibility_time) {
        super(tile, p, name, health_pool, health_amount, attack_points, defense_points, experience_value);
        this.visible = true;
        this.tick_count = 0;
        this.visibility_time = visibility_time;
        this.invisibility_time = invisibility_time;
        this.original_tile = tile;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public void onGameTick() {
        this.visible = this.tick_count < this.visibility_time; // Set visibility based on tick count
        if (this.visible) {
            this.setTile(this.original_tile); // Set tile to original when visible
        } else {
            this.setTile('.'); // Set tile to '.' when invisible
        }

        if (this.tick_count == this.visibility_time + this.invisibility_time) {
            this.tick_count = 0; // Reset tick count after visibility and invisibility time
        } else {
            this.tick_count++; // Increment tick count
        }

        if (range(player) < 2) { // Attack player if within range
            this.interact(player);
        }
    }


    // Getters and Setters
    public int getVisibility_time() {
        return visibility_time;
    }

    public void setVisibility_time(int visibility_time) {
        this.visibility_time = visibility_time;
    }

    public int getInvisibility_time() {
        return invisibility_time;
    }

    public void setInvisibility_time(int invisibility_time) {
        this.invisibility_time = invisibility_time;
    }

    public int getTick_count() {
        return tick_count;
    }

    public void setTick_count(int tick_count) {
        this.tick_count = tick_count;
    }

    public char getOriginal_tile() {
        return original_tile;
    }

    public void setOriginal_tile(char original_tile) {
        this.original_tile = original_tile;
    }

    public boolean getVisible() {
        return visible;
    }
}
