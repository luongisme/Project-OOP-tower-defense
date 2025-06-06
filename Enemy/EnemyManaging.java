package Enemy;
    import Scene.Playing;
    import Enemy.GeneralEnemy;
    import HelperMethod.LoadSave;
    import static HelperMethod.Constant.Direction.*;
import static HelperMethod.Constant.Tiles.ROAD_TILE;

import java.awt.Graphics;
    import java.awt.image.BufferedImage;
    import java.util.ArrayList;

    public class EnemyManaging {
        private Playing playing;
        private BufferedImage[] enemyImages;
        ArrayList<GeneralEnemy> enemies=new ArrayList<>();
        private float speed=1f;

        public EnemyManaging(Playing playing){
            this.playing=playing;
            enemyImages= new BufferedImage[4];
            addEnemy();
            loadEnemyImages();
        }

        public void update(){
            for (GeneralEnemy e:enemies ){
                if (isNextTileRoad(e)){
                    e.move(speed, e.getLastDirection());
                }
                // else: do nothing, enemy stops
            }
        }   

        public boolean isNextTileRoad(GeneralEnemy e){
            int newX = (int) (e.getX() + getSpeedX(e.getLastDirection()));
            int newY = (int) (e.getY() + getSpeedY(e.getLastDirection()));
            if( getTileType(newX, newY) == ROAD_TILE){
                e.move(speed,e.getLastDirection());
            }
            else{
                setNewDirectionAndMove(e);
            }
            return false;
        }

        public void setNewDirectionAndMove(GeneralEnemy e){
            int direction=e.getLastDirection();

            if (direction==LEFT||direction==RIGHT){
                int newY = (int) (e.getY() + getSpeedY(UP));

                if (getTileType((int) e.getX(), newY)==ROAD_TILE){
                    e.move(speed,UP);
                }

                else{
                    e.move(speed,DOWN);
                }
                
            }

            else{
                int newX = (int) (e.getX() + getSpeedX(e.getLastDirection()));

                if(getTileType(newX,(int) e.getY())==ROAD_TILE){
                    e.move(speed, RIGHT);
                }
                else{
                    e.move(speed,LEFT);
                }
            }
        }

        public boolean isAtEnd(GeneralEnemy e){
            return false;
        }

        private int getTileType(int x, int y){
            return playing.getTileType(x, y);
        }

        private float getSpeedX(int direction){
            if (direction == LEFT) return -speed;
            if (direction == RIGHT) return speed+64;
            return 0;
        }

        private float getSpeedY(int direction){
            if (direction == UP) return -speed;
            if (direction == DOWN) return speed+64;
            return 0;
        }

        public void addEnemy(){
            enemies.add(new GeneralEnemy(0,64*11));// the enemy doesnt walk right on the path so i did a little adjustment
        }

        public void draw(Graphics g){
            for (GeneralEnemy e: enemies){
                drawEnemyImages(e, g);
                e.drawHealthBar(g);
            }
        }

        public void loadEnemyImages(){
            enemyImages[0]=LoadSave.getSpriteAtlas().getSubimage(0, 64*2, 64, 64);
            enemyImages[1]=LoadSave.getSpriteAtlas().getSubimage(64, 64, 64, 64);
            enemyImages[2]=LoadSave.getSpriteAtlas().getSubimage(64*2, 64, 64, 64);
            enemyImages[3]=LoadSave.getSpriteAtlas().getSubimage(64*3, 64, 64, 64);
        }

        public void drawEnemyImages(GeneralEnemy e, Graphics g){
            g.drawImage(enemyImages[0],(int)e.getX(),(int)e.getY(),null);
        }

}
