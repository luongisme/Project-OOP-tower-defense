package Tile;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage[] sprite;
    private int id,tileType;
    private String name;
    private boolean canPlaceTower;

    public Tile(BufferedImage sprite, int id, int tileType,boolean canPlaceTower) {
        this.sprite = new BufferedImage[1];
        this.sprite[0]=sprite;
        this.id = id;
        this.tileType=tileType;
        this.canPlaceTower=canPlaceTower;
    }

    public Tile(BufferedImage[] sprite, int id, int tileType,boolean canPlaceTower){
        this.sprite=sprite;
        this.id=id;
        this.tileType=tileType;
        this.canPlaceTower=canPlaceTower;
    }

    //this work as a slide that transition from picture to picture of an object
    public BufferedImage getSprite(int animationIndex){
        return sprite[animationIndex];
    }

    public Boolean checkAnimation(){
        return sprite.length>1;
    }

    public int getTileType(){
        return tileType;
    }

    public BufferedImage getSprite() {
        return sprite[0];
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
