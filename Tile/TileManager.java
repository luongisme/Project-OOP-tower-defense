package Tile;

import HelperMethod.LoadSave;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static HelperMethod.Constant.Tiles.*;

public class TileManager {
    public Tile GRASS,TREE,ROAD,CAMP,TOWER,ARCHER,ORC,SHARK,WATER,GRASS_TOWER,HOME;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public TileManager(){
        loadAtlas();
        createTiles();
    }

    private void createTiles() {
        int id = 0;
        tiles.add(GRASS = new Tile(getSprite(3, 6), id++, GRASS_TILE,false));
        tiles.add(TREE = new Tile(getSprite(0, 0), id++, TREE_TILE,false));
        tiles.add(ROAD = new Tile(getSprite(8, 0), id++, ROAD_TILE,false));
        tiles.add(CAMP = new Tile(getSprite(0,6), id++,CAMP_TILE,false));
        tiles.add(TOWER = new Tile(getSprite(6, 1), id++,TOWER_TILE,false));
        //these enemy doesnt need to appear in the bottom bar
        //tiles.add(ARCHER = new Tile(getSprite(0, 16), id++,"Archer"));
        //tiles.add(ORC = new Tile(getSprite(0, 17), id++,"Orc"));
        //tiles.add(SHARK = new Tile(getSprite(0, 8), id++,"Shark"));
        tiles.add(WATER = new Tile(getSpriteAnimations(0,15), id++,WATER_TILE ,false));
        tiles.add(GRASS_TOWER = new Tile(getSprite(3,6),id++,GRASS_TOWER_TILE,true));
        tiles.add(HOME = new Tile(getSprite(2,6),id++,HOME_TILE,false));
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }
    public BufferedImage getSprite(int id){
        return tiles.get(id).getSprite();
    }

    public BufferedImage getSpiteAnimation(int id, int animationIndex){
        return tiles.get(id).getSprite(animationIndex);
    }

    public BufferedImage getSpriteAnimation(int id, int animationIndex) {
        return tiles.get(id).getSprite(animationIndex);
    }

    private BufferedImage[] getSpriteAnimations(int xCord, int yCord){
        BufferedImage[] arr = new BufferedImage[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = getSprite(xCord + i, yCord); 
        }
        return arr;
    }

    
    public boolean checkSpriteAnimation(int spriteID){
        return tiles.get(spriteID).checkAnimation();
    }

    private BufferedImage getSprite(int xCord, int yCord){
        return atlas.getSubimage(xCord * 64, yCord * 64, 64, 64);
    }
    public Tile getTile(int id){
        return tiles.get(id);
    }
}
