package TowerM;

import Scene.Playing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import HelperMethod.LoadSave;
import Tower.Tower;
import TowerM.Tower;
import java.util.ArrayList;

import static HelperMethod.Constant.Towers;.*;

public class TowerManager {
    private Playing playing;
    private BufferedImage [] towerImages;
    private Tower tower;

    public TowerManager(Playing playing) {
        this.playing = playing;

        loadTowerImages();
        initTower();
    }


    private void initTower() {
      tower = new Tower(0, 0, 0, TOWER); // Initialize with default values
    }


    private void loadTowerImages() {
        BufferedImage atlas=LoadSave.getSpriteAtlas();
        towerImages = new BufferedImage[0];
        towerImages[0]=LoadSave.getSpriteAtlas().getSubimage(64*7, 64, 64, 64);
    }
    public void update() {
        
    }
    public void draw(Graphics g) {
            g.drawImage(towerImages[TOWER], tower.getX(), tower.getY(), null);
    }

}
    */