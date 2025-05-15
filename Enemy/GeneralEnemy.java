package Enemy;

import java.awt.Rectangle;

public abstract  class GeneralEnemy {
    protected int hp;
    protected int speed;
    protected boolean isAlive;
    protected boolean isHit;
    protected boolean reachEnd;
    protected int barWidth;// only modify barwidth if the enemy get hit
    protected int barLength=5;

    protected Rectangle hitbox;

    public GeneralEnemy(int hp, int speed){
        this.hp=hp;
        this.speed=speed;
        this.isAlive=true;
        this.isHit=false;
        this.reachEnd=false;
        this.barWidth=4*hp;
        this.hitbox= new Rectangle();
    }


}
