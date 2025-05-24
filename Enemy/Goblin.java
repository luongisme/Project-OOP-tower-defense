package Enemy;

import java.awt.image.BufferedImage;
import java.awt.image.TileObserver;

import Tile.TileManager;

public class Goblin extends GeneralEnemy {
    private BufferedImage sprite;
    private BufferedImage[] animationFrames;
    private int currentFrame;
    private int delayAnimation;
    private int delay;
    private TileManager tileManager;
    private int row;

    int[][] lvl=HelperMethod.LevelBuild.getLevelOneData();

    public Goblin(int hp, int speed, TileManager tileManager){
        super(10,1);
        this.tileManager=tileManager;
    }
    
}
