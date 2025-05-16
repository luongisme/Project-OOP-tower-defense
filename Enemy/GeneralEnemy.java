package Enemy;

import java.awt.Rectangle;

public abstract  class GeneralEnemy {
    private float x,y;
    protected int hp;
    protected int speed;
    protected boolean isAlive;
    protected boolean isHit;
    protected boolean reachEnd;
    protected int barWidth;// only modify barwidth if the enemy get hit
    protected int barLength=5;
    protected Direction firstDirection;

    protected Rectangle hitbox;

    public enum Direction{
        UP, DOWN, LEFT, RIGHT
    }

    public GeneralEnemy(int hp, int speed){
        this.hp=hp;
        this.speed=speed;
        this.isAlive=true;
        this.isHit=false;
        this.reachEnd=false;
        this.barWidth=4*hp;
        this.hitbox= new Rectangle((int)x, (int) y, 32, 32);
        this.firstDirection=Direction.RIGHT;//when the game start, the enemy set default to go right
    }

    public float getX(){
        return x;   
    }

    public float getY(){
        return y;
    }

    public Rectangle getHitBox(){
        return hitbox;
    }

    public int getHP(){
        return hp;
    }
    

}
