package TowerM;
import Enemy.GeneralEnemy;
import HelperMethod.LoadImages;
import Scene.Playing;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class TowerManager {
    private Playing playing;
    private BufferedImage [] towerImages;
    private ArrayList<Tower> towers = new ArrayList<>(); // Use ArrayList to manage towers
    private int towerAmount = 0; // Initialize tower amount

    public TowerManager(Playing playing) {
        this.playing = playing;

        loadTowerImages();
    }

    private void loadTowerImages() {
        BufferedImage atlas = LoadImages.getSpriteAtlas();
        towerImages = new BufferedImage[1];
        towerImages[0] = atlas.getSubimage(64*6, 64, 64, 64); 
    }
    public void update() {
        for (Tower t : towers) {
            t.update();
            attackEnemyIfClose(t);
        }
    }
    private void attackEnemyIfClose(Tower t) {
            for(GeneralEnemy e : playing.getEnemyManaging().getEnemies()) {
                if(e.isAlive())
                    if(isEnemyInRange(t, e)) {
                        if(t.isCoolDownOver()) {
                            playing.shootEnemy(t, e); 
                            t.resetCooldown();
                    } else{
                        //do nothing
                    }
                }
            }
        }
    private boolean isEnemyInRange(Tower t, GeneralEnemy e) {
        int range = HelperMethod.Utilz.GetHypoDistance(t.getX(), t.getY(), e.getX(), e.getY());
        return range < t.getRange();
    }

    public void draw(Graphics g) {
        for (Tower t : towers) {
            g.drawImage(towerImages[t.getTowerType()], t.getX(), t.getY(), null); 
        }
    }
    
    public BufferedImage[] getTowerImages(){
            return towerImages;
    }
    
     public Tower getTowerAt(int x, int y) {
        for(Tower t : towers) {
            if(t.getX() == x && t.getY() == y) {
                return t;
            }
        }
        return null;
    }
     
    public void addTower(Tower selectedTower, int xPos, int yPos) {
       towers.add(new Tower(xPos, yPos, towerAmount++, selectedTower.getTowerType()));
    }

    
}
