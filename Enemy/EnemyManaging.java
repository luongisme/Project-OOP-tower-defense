package Enemy;
import Scene.Playing;
import Enemy.GeneralEnemy;
import HelperMethod.LoadSave;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class EnemyManaging {
    private Playing playing;
    private BufferedImage[] enemyImages;
    private GeneralEnemy testEnemy; 

    public EnemyManaging(Playing playing){
        this.playing=playing;
        enemyImages= new BufferedImage[4];
        testEnemy=new GeneralEnemy (32*0,32*10);
        loadEnemyImages();
        
    }

    public void update(){

    }

    public void draw(Graphics g){
        drawEnemyImages(testEnemy, g);
    }

    public void loadEnemyImages(){
        BufferedImage atlas=LoadSave.getSpriteAtlas();
        enemyImages[0]=LoadSave.getSpriteAtlas().getSubimage(32*9, 32, 32, 32);
        enemyImages[1]=LoadSave.getSpriteAtlas().getSubimage(32, 32, 32, 32);
        enemyImages[2]=LoadSave.getSpriteAtlas().getSubimage(32*2, 32, 32, 32);
        enemyImages[3]=LoadSave.getSpriteAtlas().getSubimage(32*3, 32, 32, 32);
    }

    public void drawEnemyImages(GeneralEnemy e, Graphics g){
        g.drawImage(enemyImages[0],(int)e.getX(),(int)e.getY(),null);
    }



}
