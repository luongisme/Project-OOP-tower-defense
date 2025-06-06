    package Enemy;
    import Scene.Playing;
    import Enemy.GeneralEnemy;
    import HelperMethod.LoadSave;
    import static HelperMethod.Constant.Direction.*;

    import java.awt.Graphics;
    import java.awt.image.BufferedImage;
    import java.util.ArrayList;

    public class EnemyManaging {
        private Playing playing;
        private BufferedImage[] enemyImages;
        ArrayList<GeneralEnemy> enemies=new ArrayList<>();
        private float speed=0.5f;

        public EnemyManaging(Playing playing){
            this.playing=playing;
            enemyImages= new BufferedImage[4];
            addEnemy();
            loadEnemyImages();
        }

        public void update(){
            for (GeneralEnemy e:enemies ){
                e.move(0.5f,0);
                if (isNextTileRoad(e)){

                }
            }
        }   

        public boolean isNextTileRoad(GeneralEnemy e){
            int newX=(int) (e.getX()+getSpeedX(e.getLastDirection()));
            int newY=(int) (e.getY()+getSpeedY(e.getLastDirection()));
            return false;
        }

        private float getSpeedX(int Direction){
            if (Direction==UP){
                return -speed;
            }
            else if(Direction==DOWN){
                return speed;
            }
            return 0;
        }

        private float getSpeedY(int Direction){
            if (Direction==LEFT){
                return -speed;
            }
            else if(Direction==RIGHT){
                return speed;
            }
            return 0;
        }

        public void addEnemy(){
            enemies.add(new GeneralEnemy(0,64*6));// the enemy doesnt walk right on the path so i did a little adjustment
        }

        public void draw(Graphics g){
            for (GeneralEnemy e: enemies){
                drawEnemyImages(e, g);
                e.drawHealthBar(g);
            }
        }

        public void loadEnemyImages(){
            BufferedImage atlas=LoadSave.getSpriteAtlas();
            enemyImages[0]=LoadSave.getSpriteAtlas().getSubimage(0, 64*2, 64, 64);
            enemyImages[1]=LoadSave.getSpriteAtlas().getSubimage(64, 64, 64, 64);
            enemyImages[2]=LoadSave.getSpriteAtlas().getSubimage(64*2, 64, 64, 64);
            enemyImages[3]=LoadSave.getSpriteAtlas().getSubimage(64*3, 64, 64, 64);
        }

        public void drawEnemyImages(GeneralEnemy e, Graphics g){
            g.drawImage(enemyImages[0],(int)e.getX(),(int)e.getY(),null);
        }

        public void enemyMove(GeneralEnemy e){
            //apply all the method about movement
        }
        
}
