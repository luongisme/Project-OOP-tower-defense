package Enemy;
    import Scene.Playing;
    import HelperMethod.LoadSave;

    import java.awt.Graphics;
    import java.awt.image.BufferedImage;
    import java.util.ArrayList;

    public class EnemyManaging {
        private Playing playing;
        private BufferedImage[] enemyImages;
        ArrayList<GeneralEnemy> enemies=new ArrayList<>();

        public EnemyManaging(Playing playing){
            this.playing=playing;
            enemyImages= new BufferedImage[4];
            addEnemy();
            loadEnemyImages();
        }

        public void update(){
            int[][] lvl = playing.getLevel();
            for (GeneralEnemy e : enemies) {
                // Use the correct move method for pathfinding
                moveEnemyAlongPath(e, lvl);
            }
        }   

        public void addEnemy(){
            // Place the enemy at the first PATH (2) tile in the level
            int[][] lvl = playing.getLevel();
            for (int y = 0; y < lvl.length; y++) {
                for (int x = 0; x < lvl[y].length; x++) {
                    if (lvl[y][x] == 2) {
                        enemies.add(new GeneralEnemy(x * GeneralEnemy.TILE_SIZE, y * GeneralEnemy.TILE_SIZE));
                        return;
                    }
                }
            }
            // fallback if no PATH tile found
            enemies.add(new GeneralEnemy(0, 0));
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

        // Move the enemy along the path (only on PATH tiles)
        private void moveEnemyAlongPath(GeneralEnemy e, int[][] lvl) {
            if (!e.isAlive) return;
            int xTile = (int) (e.getX() / GeneralEnemy.TILE_SIZE);
            int yTile = (int) (e.getY() / GeneralEnemy.TILE_SIZE);
            boolean canMoveRight = xTile + 1 < lvl[0].length && lvl[yTile][xTile + 1] == 2;
            boolean canMoveLeft = xTile - 1 >= 0 && lvl[yTile][xTile - 1] == 2;
            boolean canMoveUp = yTile - 1 >= 0 && lvl[yTile - 1][xTile] == 2;
            boolean canMoveDown = yTile + 1 < lvl.length && lvl[yTile + 1][xTile] == 2;

            // If aligned to tile, pick new direction
            if (((int)e.getX()) % GeneralEnemy.TILE_SIZE == 0 && ((int)e.getY()) % GeneralEnemy.TILE_SIZE == 0) {
                if (canMoveRight && e.lastDirection != GeneralEnemy.Direction.LEFT.ordinal()) {
                    e.lastDirection = GeneralEnemy.Direction.RIGHT.ordinal();
                } else if (canMoveDown && e.lastDirection != GeneralEnemy.Direction.UP.ordinal()) {
                    e.lastDirection = GeneralEnemy.Direction.DOWN.ordinal();
                } else if (canMoveLeft && e.lastDirection != GeneralEnemy.Direction.RIGHT.ordinal()) {
                    e.lastDirection = GeneralEnemy.Direction.LEFT.ordinal();
                } else if (canMoveUp && e.lastDirection != GeneralEnemy.Direction.DOWN.ordinal()) {
                    e.lastDirection = GeneralEnemy.Direction.UP.ordinal();
                }
            }
            // Move in the chosen direction
            switch (e.lastDirection) {
                case 3: // RIGHT
                    if (canMoveRight) e.move(e.speed, 0);
                    break;
                case 2: // LEFT
                    if (canMoveLeft) e.move(-e.speed, 0);
                    break;
                case 0: // UP
                    if (canMoveUp) e.move(0, -e.speed);
                    break;
                case 1: // DOWN
                    if (canMoveDown) e.move(0, e.speed);
                    break;
            }
            e.updateHitBox();
            // Check if reached end
            if (e.ReachedEnd(lvl)) {
                e.reachEnd = true;
                e.kill();
            }
        }
        
}
