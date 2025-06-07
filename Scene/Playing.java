package Scene;

import Enemy.EnemyManaging;
import HelperMethod.LevelBuild;
import Main.Game;
import Tile.Tile;
import Tile.TileManager;
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

    private int animationIndex;
    
    private int tick;

    private int ANIMATION_SPEED=10;

    public Playing(Game game) {
        super(game);
        lvl = LevelBuild.getLevelOneData();
        tileManager = new TileManager();
        bottomBar = new BottomBar(0, 740, 1024, 150, this);//starting at x = 0, y =740(screen)
        enemyManaging = new EnemyManaging(this);
        towerManager = new TowerManager(this);
    }


    
    @Override
    public void render(Graphics g) {
        drawLevel(g); // Use the animated drawLevel method
        bottomBar.draw(g);
        drawSelectedTile(g);
        enemyManaging.draw(g);
        updateTick();
        towerManager.draw(g);
    }

    public void update(){
        enemyManaging.update();
        towerManager.update();
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

    public void setSelectedTile(Tile tile){
        this.selectedTile = tile;
        drawSelect = true;
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


    @Override
    public void mouseClicked(int x, int y) {
        if(y >= 740){
            bottomBar.mouseClicked(x, y);
        }else{
            changeTile(mouseX, mouseY);
        }
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
}
