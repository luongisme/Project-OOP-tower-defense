package Enemy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import static HelperMethod.Constant.Direction.*;

public class GeneralEnemy {
    private float x,y;
    protected int hp;
    protected int speed;
    protected boolean isAlive;
    protected boolean isHit;
    protected boolean reachEnd;
    protected int lastDirection;
    protected int barWidth;// only modify barwidth if the enemy get hit
    protected int barLength=5;
    protected Direction firstDirection;

    protected Rectangle hitbox;// act as the hitbox of the enemy

    protected int animationIndex = 0;
    protected int animationTick = 0;
    protected int animationSpeed = 10; // Lower is faster
    protected int maxAnimationFrames = 10; // Set this to the number of frames per enemy

    public enum Direction{
        UP, DOWN, LEFT, RIGHT
    }

    public static final int TILE_SIZE=64;

    public GeneralEnemy(float x,float y){
        this.x=x;
        this.y=y;
        this.hp=10;
        this.lastDirection=1; // RIGHT
        this.speed=1;
        this.isAlive=true;
        this.isHit=false;
        this.reachEnd=false;
        this.barWidth=4*hp;
        this.hitbox= new Rectangle((int)x, (int) y, 64, 64);
        this.firstDirection=Direction.RIGHT;//when the game start, the enemy set default to go right
    }

    public void drawHealthBar(Graphics g){//draw health bar method
        int barX=(int) x;
        int barY=(int) y-10;
        barWidth=50;

        
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
        g.fillRect(barX+5, barY+15,barWidth,barLength);
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

    public void setPos(int x, int y) {

		this.x = x;
		this.y = y;
	}

    public void move(float speed,int Direction){
        switch (Direction) {
            case LEFT:
                this.x-=speed;
                break;
            case RIGHT:
                this.x+=speed;
                break;
            case DOWN:
                this.y+=speed;
                break;
            case UP:
                this.y-=speed;
                break;
        }
    }
    // get start health
    public void updateHitBox(){
        hitbox.x=(int)x;
        hitbox.y=(int)y;
    }

    public boolean ReachedEnd(int[][] lvl){
        int xTile=(int) x/TILE_SIZE;
        return xTile>=lvl[0].length-1;
    }

    public void kill(){
        isAlive=false;
        hp=0;
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

    public int getLastDirection(){
        return lastDirection;
    }

    

    public void updateAnimation() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= maxAnimationFrames) {
                animationIndex = 0;
            }
        }
    }

    public int getAnimationIndex() {
        return animationIndex;
    }

}
