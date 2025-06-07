package TowerM;

import Scene.Playing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import HelperMethod.LoadSave;
import TowerM.GeneralTower;
import java.util.ArrayList;

public class TowerManager {
    private Playing playing;
    private BufferedImage [] towerImages;
    private GeneralTower tower;

    public TowerManager(Playing playing) {
        this.playing = playing;

        loadTowerImages();
        initTower();
    }


    private void initTower() {
      tower = new GeneralTower(0, 0, 0, 0); // Use 0 as default type/index
    }


    private void loadTowerImages() {
        BufferedImage atlas=LoadSave.getSpriteAtlas();
        towerImages = new BufferedImage[1];
        towerImages[0]=atlas.getSubimage(64*6, 64, 64, 64); // Use index 0
    }
    public void update() {
        
    }
    public void draw(Graphics g) {
            g.drawImage(towerImages[0], tower.getX(), tower.getY(), null); // Use index 0
    }

}
