package GameTiles.Unit.Enemy;

import java.util.Random;
import GameTiles.GameTile;
import GameTiles.Utilis.Position;

public class Monster extends Enemy {

    private Integer vision_range;

    public Monster(char tile, Position p, String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points, Integer vision_range, Integer experience_value) {
        super(tile, p, name, health_pool, health_amount, attack_points, defense_points, experience_value);
        this.vision_range = vision_range;
    }

    public void on_GameTick() {
        move();
    }

    public void move() {
        if (this.range(player) < (double) this.vision_range) {
            int dx = this.getPosition().getX() - player.getPosition().getX();
            int dy = this.getPosition().getY() - player.getPosition().getY();
            GameTile tile;
            if (Math.abs(dy) > Math.abs(dx)) {
                if (dy > 0) {
                    tile = board.getTile(getPosition().getX(), getPosition().getY() - 1); // move left
                } else {
                    tile = board.getTile(getPosition().getX(), getPosition().getY() + 1); //move right
                }
                //tile.accept(this);
            } else {
                if (dx > 0) {
                    tile = board.getTile(getPosition().getX() - 1, getPosition().getY());  //move up
                } else {
                    tile = board.getTile(getPosition().getX() + 1, getPosition().getY());  // move down
                }
                //tile.accept(this);
            }
        }
        else
        {
            GameTile[] arr = {board.getTile(getPosition().getX(), getPosition().getY() - 1), board.getTile(getPosition().getX(), getPosition().getY() + 1), board.getTile(getPosition().getX() - 1, getPosition().getY()), board.getTile(getPosition().getX() + 1, getPosition().getY()), board.getTile(getPosition().getX(), getPosition().getY())};
            Random r = new Random();
            int random_number = r.nextInt(5);
            GameTile tile1 = arr[random_number];
            //tile1.accept(this);
        }
    }

    public Integer getVision_range() {
        return vision_range;
    }

    public void interact(GameTile tile) {
        tile.interact(this);
    }

    public void interact(Enemy enemy){}
}
