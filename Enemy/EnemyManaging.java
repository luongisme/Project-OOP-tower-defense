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
        private BufferedImage[][] enemyImages; // [enemyType][frame]
        ArrayList<GeneralEnemy> enemies=new ArrayList<>();
        private float speed=0.6f;

        public EnemyManaging(Playing playing){
            this.playing=playing;
            enemyImages= new BufferedImage[4][10]; // 4 enemy types, 10 frames each
            addEnemy();
            loadEnemyImages();
        }

        public void update(){
            for (GeneralEnemy e:enemies ){
                e.updateAnimation(); // update animation frame
                isNextTileRoad(e);
            }
        }   

        public void isNextTileRoad(GeneralEnemy e){
            int newX = (int) (e.getX() + getSpeedX(e.getLastDirection()));
            int newY = (int) (e.getY() + getSpeedY(e.getLastDirection()));
            if  (getTileType(newX, newY) == ROAD_TILE){
                e.move(speed, e.getLastDirection());
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
                int newY = (int) (e.getY() + getSpeedY(UP));

                if (getTileType((int) e.getX(), newY) == ROAD_TILE) {
                    e.move(speed, UP);
                    e.lastDirection = UP;

                } else if (getTileType((int) e.getX(), (int) (e.getY() + getSpeedY(DOWN))) == ROAD_TILE) {
                    e.move(speed, DOWN);
                    e.lastDirection = DOWN;

                }
            } else {

                int newX = (int) (e.getX() + getSpeedX(RIGHT));

                if (getTileType(newX, (int) e.getY()) == ROAD_TILE) {
                    e.move(speed, RIGHT);
                    e.lastDirection = RIGHT;

                } else if (getTileType((int) (e.getX() + getSpeedX(LEFT)), (int) e.getY()) == ROAD_TILE) {
                    e.move(speed, LEFT);
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
            // Example: add a type 0 enemy
            enemies.add(new GeneralEnemy(0, 64*11, 0));
        }

        public void draw(Graphics g){
            for (GeneralEnemy e: enemies){
                drawEnemyImages(e, g);
                e.drawHealthBar(g);
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
            int type = e.getType();
            int frame = e.getAnimationIndex();
            g.drawImage(enemyImages[type][frame], (int)e.getX(), (int)e.getY(), null);
        }

}
