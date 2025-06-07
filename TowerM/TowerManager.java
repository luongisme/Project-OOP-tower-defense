/* package TowerM;

import Scene.Playing;
import Tile.Tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import HelperMethod.LoadSave;
import Tower.Tower;
import TowerM.Tower;
import java.util.ArrayList;

public class TowerManager {
    private Playing playing;
    private BufferedImage [] towerImages;
    public ArrayList<Tower> towers = new ArrayList<>();
    public Tile ARCHER;

    public TowerManager() {
        this.playing = playing;

        loadTowerImages();
    }


    private void loadTowerImages() {
        BufferedImage atlas=LoadSave.getSpriteAtlas();
        towerImages = new BufferedImage[4];
        towerImages[0]=LoadSave.getSpriteAtlas().getSubimage(0, 64*2, 64, 64);
        towerImages[1]=LoadSave.getSpriteAtlas().getSubimage(64, 64, 64, 64);
        towerImages[2]=LoadSave.getSpriteAtlas().getSubimage(64*2, 64, 64, 64);
        towerImages[3]=LoadSave.getSpriteAtlas().getSubimage(64*3, 64, 64, 64);
    }
    public void update() {
        // Update logic for towers can be added here
    }
    public void drawTowerImages(Tower tower, Graphics g) {
        g.drawImage(towerImages[0], tower.getX(), tower.getY(), null);
    }

}
*/