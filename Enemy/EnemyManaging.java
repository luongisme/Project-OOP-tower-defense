package Enemy;
import Scene.Playing;
import Enemy.GeneralEnemy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class EnemyManaging {
    private Playing playing;
    private BufferedImage[] enemyImages;
    private GeneralEnemy testEnemy; 

    public EnemyManaging(Playing playing){
        this.playing=playing;
        enemyImages= new BufferedImage[4];
        testEnemy=new GeneralEnemy (32*3,32*9,0,0);
        loadEnemyImages();
        
    }

    public void update(){

    }

    public void draw(Graphics g){

    }

    public void loadEnemyImages(){
        
    }

    public void drawEnemyImages(GeneralEnemy e, Graphics g){
        g.drawImage(enemyImages[0],(int)e.getX(),(int)e.getY(),null);
    }



}
