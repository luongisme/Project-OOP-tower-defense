package Scene;

import Enemy.EnemyManaging;
import Enemy.GeneralEnemy;
import static HelperMethod.Constant.Tiles.GRASS_TOWER_TILE;
import HelperMethod.LevelBuild;
import Main.Game;
import Projectile.ProjectileManager;
import Tile.Tile;
import Tile.TileManager;
import TowerM.Tower;
import TowerM.TowerManager;
import UI.BottomBar;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Playing extends GameScene implements SceneMethods{
    private int[][] lvl;

    private TileManager tileManager;

    private BottomBar bottomBar;

    private Tile selectedTile;

    private int mouseX, mouseY;

    private boolean drawSelect = false;

    private int lastTileX, lastTileY, lastTileId;

    private EnemyManaging enemyManaging;

    private TowerManager towerManager;

    private Tower selectedTower;

    private ProjectileManager projectileManager;

    private int animationIndex;
    
    private int tick;

    

    public Playing(Game game) {
        super(game);
        lvl = LevelBuild.getLevelOneData();
        tileManager = new TileManager();
        bottomBar = new BottomBar(0, 740, 1024, 150, this);//starting at x = 0, y =740(screen)
        enemyManaging = new EnemyManaging(this);
        towerManager = new TowerManager(this);
        projectileManager = new ProjectileManager(this);
    }


    
    @Override
    public void render(Graphics g) {
        drawLevel(g); // Use the animated drawLevel method
        bottomBar.draw(g);
        drawSelectedTile(g);
        enemyManaging.draw(g);
        updateTick();
        towerManager.draw(g);
        projectileManager.draw(g);

        drawSelectedTower(g);
        drawHighlight(g);
    }

    private void drawHighlight(Graphics g) {
        g.setColor(Color.WHITE);
       g.drawRect(mouseX, mouseY, 64, 64);
    }



    private void drawSelectedTower(Graphics g){
        if(selectedTower != null){
            g.drawImage(towerManager.getTowerImages()[selectedTower.getTowerType()], mouseX, mouseY, null);
    }
    }
    public void update(){
        enemyManaging.update();
        towerManager.update();
        projectileManager.update();
    }

    public void updateTick(){
        tick++;
        if (tick>=20){
            tick=0;
            animationIndex++;
            if (animationIndex>=10){
                animationIndex=0;
            }
        }
    }

    private void drawSelectedTile(Graphics g) {
        if(selectedTile != null && drawSelect){
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 64, 64, null);
        }
    }

    public void setSelectedTower(Tower selectedTower){
        this.selectedTower = selectedTower;
    }

    public void drawLevel(Graphics g){
        // go through all the tile
        for (int y=0;y<lvl.length;y++){
            for (int x=0;x<lvl[y].length;x++){
                int id=lvl[y][x];
                if (checkAnimation(id)){
                    g.drawImage(getSprite(id,animationIndex), x*64, y*64, null);
                }
                else{
                    g.drawImage(getSprite(id), x*64, y*64, null);
                }
            }
        }
    }

    public BufferedImage getSprite(int spriteID){
        return tileManager.getSprite(spriteID);
    }

    public BufferedImage getSprite(int spriteID, int animationIndex){
        return tileManager.getSpriteAnimation(spriteID, animationIndex);
    }

    private boolean checkAnimation(int spriteID){
        return getTileManager().checkSpriteAnimation(spriteID);
    }

    public TileManager getTileManager(){
        return tileManager;
    }
 private void changeTile(int x, int y) {

        if(selectedTile != null){
            int tileX = x / 64;
            int tileY = y / 64;
            if(lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId())
                return;
            lastTileX = tileX;
            lastTileY = tileY;
            lastTileId = selectedTile.getId();
            // if one of the three is wrong , cll func

            lvl[tileY][tileX] = selectedTile.getId();
        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(y >= 740){
            bottomBar.mouseClicked(x, y);
        }else{
            if(selectedTower != null){
                if(isTilePlaceable(mouseX, mouseY)){
                    if(getTowerAt(mouseX, mouseY) == null){
                        towerManager.addTower(selectedTower, mouseX, mouseY);
                        selectedTower = null;
                    }
                }
            }
        }
    }
    
     private Tower getTowerAt(int x, int y) {
        return towerManager.getTowerAt(x, y);
    }
     
    
     public Boolean isTilePlaceable(int x, int y){
        int id = lvl[y/64][x/64];
        int tileType = getGame().getTileManager().getTile(id).getTileType();
        return tileType ==  GRASS_TOWER_TILE;
     }
     /* 
     public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            selectedTower = null; // Deselect tower
        }
    }
        */
    @Override
    public void mouseMoved(int x, int y) {
        if(y >= 740){
            bottomBar.mouseMoved(x, y);
            drawSelect = false;
        }else{
            drawSelect = true;
            mouseX = (x / 64) * 64;
            mouseY = (y / 64) * 64;

        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(y >= 740){
            bottomBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
            bottomBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
        if(y >= 740){

        }else{
            changeTile(x, y);
        }
    }

    public void setLevel(int[][] lvl){
        this.lvl=lvl;
    }

    public int[][] getLevel() {
        return lvl;
    }

    public int getTileType(int x, int y){
        int id = lvl[y/64][x/64];
        return getGame().getTileManager().getTile(id).getTileType();
    }

    public TowerManager getTowerManager() {
        return towerManager;
    }
    public EnemyManaging getEnemyManaging() {
        return enemyManaging;
    }



    public void shootEnemy(Tower t, GeneralEnemy e) {
        projectileManager.newProjectile(t, e);
    }
}
