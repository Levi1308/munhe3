package GameTiles.Utilis;


public class Position {
    private Integer x;
    private Integer y;
    public Position(int x,int y)
    {
        this.x=x;
        this.y=y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Position)
            return ((Position) obj).x==getX()&&((Position) obj).y==getY();
        else
            return false;
    }
}

