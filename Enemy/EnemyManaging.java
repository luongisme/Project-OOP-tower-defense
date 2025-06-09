package Enemy;
import static HelperMethod.Constant.Direction.*;
import static HelperMethod.Constant.Enemies.*;
import static HelperMethod.Constant.Tiles.ROAD_TILE;
import HelperMethod.LoadSave;
import Scene.Playing;
import Wave.WaveManger;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

    public class EnemyManaging {
        private Playing playing;
        private BufferedImage[][] enemyImages; // [enemyType][frame]
        private WaveManger wave;

        public EnemyManaging(Playing playing){
            this.playing=playing;
            enemyImages= new BufferedImage[4][10]; // 4 enemy types, 10 frames each
            wave = new WaveManger();
            loadEnemyImages();
        }

        public void update(){
            wave.update();
            for (GeneralEnemy e : wave.getEnemies()) {
                if(e.isAlive){                    
                    e.updateAnimation(); // update animation frame
                    isNextTileRoad(e);
                }
            }
        }   

        public void isNextTileRoad(GeneralEnemy e){
            int newX = (int) (e.getX() + getSpeedX(e.getLastDirection(), e));
            int newY = (int) (e.getY() + getSpeedY(e.getLastDirection(), e));
            if  (getTileType(newX, newY) == ROAD_TILE){
                e.move(e.getSpeed(), e.getLastDirection());
            }
            else if(isAtEnd(e)){
            }
            else{
                setNewDirectionAndMove(e);
            }
        }

        public void setNewDirectionAndMove(GeneralEnemy e){
            int direction = e.getLastDirection();

            int xCord = (int) (e.getX() / 64);
            int yCord = (int) (e.getY() / 64);

            fixEnemyOffsetTile(e, direction, xCord, yCord);
            // Try all directions to find a valid road tile

            if (direction == LEFT || direction == RIGHT) {
                int newY = (int) (e.getY() + getSpeedY(UP, e));

                if (getTileType((int) e.getX(), newY) == ROAD_TILE) {
                    e.move(e.getSpeed(), UP);
                    e.lastDirection = UP;

                } else if (getTileType((int) e.getX(), (int) (e.getY() + getSpeedY(DOWN, e))) == ROAD_TILE) {
                    e.move(e.getSpeed(), DOWN);
                    e.lastDirection = DOWN;

                }
            } else {

                int newX = (int) (e.getX() + getSpeedX(RIGHT, e));

                if (getTileType(newX, (int) e.getY()) == ROAD_TILE) {
                    e.move(e.getSpeed(), RIGHT);
                    e.lastDirection = RIGHT;

                } else if (getTileType((int) (e.getX() + getSpeedX(LEFT, e)), (int) e.getY()) == ROAD_TILE) {
                    e.move(e.getSpeed(), LEFT);
                    e.lastDirection = LEFT;

                }
            }
        }

        private void fixEnemyOffsetTile(GeneralEnemy e, int direction, int xCord, int yCord) {
		switch (direction) {
		case RIGHT:
			if (xCord < 19)
				xCord++;
			break;
		case DOWN:
			if (yCord < 12)
				yCord++;
			break;
		}

		e.setPos(xCord * 64, yCord * 64);
	}

        public boolean isAtEnd(GeneralEnemy e){
            return false;
        }

        private int getTileType(int x, int y){
            return playing.getTileType(x, y);
        }

        private float getSpeedX(int direction, GeneralEnemy e){
            float s = e.getSpeed();
            if (direction == LEFT) return -s;
            if (direction == RIGHT) return s+64;
            return 0;
        }

        private float getSpeedY(int direction, GeneralEnemy e){
            float s = e.getSpeed();
            if (direction == UP) return -s;
            if (direction == DOWN) return s+64;// this help fixing the offset problem
            return 0;
        }

        public void addEnemy(int enemyType){
            switch (enemyType) {
                case SKELETON:
                    wave.getEnemies().add(new Skeleton(0,64*11,SKELETON));
                    break;
                case BEATLE:
                    wave.getEnemies().add(new Beatle(0,64*11,BEATLE));
                    break;
                case ORC:
                    wave.getEnemies().add(new Orc(0,64*11,ORC));
                    break;
            }
        }

        public void draw(Graphics g){
            for (GeneralEnemy e: wave.getEnemies()){
                if(e.isAlive){
                    drawEnemyImages(e, g);
                    e.drawHealthBar(g);
                }
            }
        }

        public void loadEnemyImages(){
            //  load 10 frames for each enemy type 
            for (int type = 0; type < 4; type++) {
                for (int frame = 0; frame < 10; frame++) {
                    enemyImages[type][frame] = LoadSave.getSpriteAtlas().getSubimage(64 * frame, 64 * (2 + type * 5), 64, 64);
                }
            }
        }

        public void drawEnemyImages(GeneralEnemy e, Graphics g){
            int type = e.getID();
            int frame = e.getAnimationIndex();
            g.drawImage(enemyImages[type][frame], (int)e.getX(), (int)e.getY(), null);
        }

        public ArrayList<GeneralEnemy> getEnemies() {
            return wave.getEnemies();
        }
        /* 
        public void drawHealthBar(GeneralEnemy e, Graphics g){//draw health bar method
            g.setColor(Color.red);
            g.fillRect((int)e.getX() + 32 - (HPbarWidth / 2),(int)e.getY() - 10, HPbarWidth, 3);
        }
        public int getNewBarWidth(GeneralEnemy e) {
            return (int) ((int) HPbarWidth * e.getHealthBarFloat());
        }
            */
}
