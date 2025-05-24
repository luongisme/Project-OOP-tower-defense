package Tile;

import HelperMethod.LoadSave;
import Tile.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {
    public Tile GRASS,WATER,ROAD;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();
    public static final int TILE_SIZE=64;
    public static final int SPRITE_SIZE=32;

    public TileManager(){
        loadAtlas();
        createTiles();
    }

    private void createTiles() {
        int id = 0;
        tiles.add(GRASS = new Tile(getSprite(8, 1), id++, "Grass"));
        tiles.add(WATER = new Tile(getSprite(0, 6), id++, "Water"));
        tiles.add(ROAD = new Tile(getSprite(9, 0), id++, "Road"));
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }
    public BufferedImage getSprite(int id){
        return tiles.get(id).getSprite();
    }
    
    
    private BufferedImage getSprite(int xCord, int yCord){
        return atlas.getSubimage(xCord * SPRITE_SIZE, yCord * SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE);
    }
    //chang to Tile_size later when find the get sprite
    public Tile getTile(int id){    
        return tiles.get(id);
    }
}
