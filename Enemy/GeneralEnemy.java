package Enemy;

import java.awt.Color;
import java.awt.Graphics;
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

    protected Rectangle hitbox;// act as the hitbox of the enemy

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

    public void drawHealthBar(Graphics g){//draw health bar method
        int barX=(int) x;
        int barY=(int) y-10;

        g.fillRect(barX, barY,barWidth,barLength);
        if(barWidth <= 30 && barWidth >= 20){
            g.setColor(Color.YELLOW);
        }
        else if(barWidth <= 20 && barWidth >= 10){
            g.setColor(Color.ORANGE);
        }
        else if(barWidth <= 10 && barWidth>=0){
            g.setColor(Color.RED);
        }
        else{
            g.setColor(Color.GREEN);
        }
    }

    public void receiveDamage(int damage){
        this.isHit=true;
        this.hp -=damage;

        if(this.hp<=0){
            this.isAlive=false;
            this.hp=0;
        }
        barWidth -=4*damage;
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

    public int getBarWidth(){
        return barWidth;
    }

    public int getBarLength(){
        return barLength;
    }
    public abstract void draw(Graphics g);

}
