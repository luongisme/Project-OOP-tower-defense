package TowerM;
import HelperMethod.LoadSave;
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
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        towerImages = new BufferedImage[1];
        towerImages[0] = atlas.getSubimage(64*6, 64, 64, 64); 
    }
    public void update() {
        
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
