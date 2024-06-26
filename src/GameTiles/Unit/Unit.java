package GameTiles.Unit;

import GameTiles.GameTile;

public abstract class Unit {
    private String name;
    private Integer health_pool;
    private Integer health_amount;
    private Integer attack_points;
    private Integer defense_points;
    private GameTile gameTile;
    public Unit(String name, Integer health_pool, Integer health_amount, Integer attack_points, Integer defense_points
    ,GameTile gameTile) {
        this.name = name;
        this.health_pool = health_pool;
        this.health_amount = health_amount;
        this.attack_points = attack_points;
        this.defense_points = defense_points;
        this.gameTile=gameTile;
    }

    // Getters
    public String getName() {
        return name;
    }

    public Integer getHealth_pool() {
        return health_pool;
    }

    public Integer getHealth_amount() {
        return health_amount;
    }

    public Integer getAttack_points() {
        return attack_points;
    }

    public Integer getDefense_points() {
        return defense_points;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setHealth_pool(Integer health_pool) {
        this.health_pool = health_pool;
    }

    public void setHealth_amount(Integer health_amount) {
        this.health_amount = health_amount;
    }

    public void setAttack_points(Integer attack_points) {
        this.attack_points = attack_points;
    }

    public void setDefense_points(Integer defense_points) {
        this.defense_points = defense_points;
    }

    public GameTile getGameTile() {
        return gameTile;
    }

    public void setGameTile(GameTile gameTile) {
        this.gameTile = gameTile;
    }

    public String description() {
        return "Description:\n"+
                "Name: "+getName()+"\n"+
                "Health pool: "+this.getHealth_pool()+"\n"+
                "Health amount: "+this.getHealth_amount()+"\n"+
                "Attack points: "+getAttack_points()+"\n"+
                "Defence points: "+getDefense_points()+"\n";
    }

}
