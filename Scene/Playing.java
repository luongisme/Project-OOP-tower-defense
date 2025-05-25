package Scene;

import UI.BottomBar;
import UI.MyButton;
import HelperMethod.LevelBuild;
import Main.Game;
import Tile.TileManager;
import Tile.Tile;

import java.awt.*;

import Enemy.EnemyManaging;

import static Main.GameStates.MENU;
import static Main.GameStates.SetGameState;

public class Playing extends GameScene implements SceneMethods{
    private int[][] lvl;

    private TileManager tileManager;

    private BottomBar bottomBar;

    private Tile selectedTile;

    private int mouseX, mouseY;

    private boolean drawSelect = false;

    private int lastTileX, lastTileY, lastTileId;

    private EnemyManaging enemyManaging;

    public Playing(Game game) {
        super(game);
        //the lvl
        //tileManager
        lvl = LevelBuild.getLevelOneData();
        tileManager = new TileManager();
        bottomBar = new BottomBar(0, 640, 640, 100, this);//starting at x = 0, y =640(screen)
        enemyManaging=new EnemyManaging(this);
    }



    @Override
    public void render(Graphics g) {
        for(int y = 0; y < lvl.length; y++){
            for(int x = 0; x < lvl[y].length; x++){
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
            }
        }

        bottomBar.draw(g);
        drawSelectedTile(g);
        enemyManaging.draw(g);
    }

    private void drawSelectedTile(Graphics g) {
        if(selectedTile != null && drawSelect){
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }

    public void setSelectedTile(Tile tile){
        this.selectedTile = tile;
        drawSelect = true;
    }

    public TileManager getTileManager(){
        return tileManager;
    }


    @Override
    public void mouseClicked(int x, int y) {
        if(y >= 640){
            bottomBar.mouseClicked(x, y);
        }else{
            changeTile(mouseX, mouseY);
        }
    }

    private void changeTile(int x, int y) {

        if(selectedTile != null){
            int tileX = x / 32;
            int tileY = y / 32;
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
        if(y >= 640){
            bottomBar.mouseMoved(x, y);
            drawSelect = false;
        }else{
            drawSelect = true;
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;

        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(y >= 640){
            bottomBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
            bottomBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
        if(y >= 640){

        }else{
            changeTile(x, y);
        }
    }
}
