package Tile;

import HelperMethod.LoadSave;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {
    public Tile GRASS,TREE,ROAD,CAMP,TOWER,ARCHER;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public TileManager(){
        loadAtlas();
        createTiles();
    }

    private void createTiles() {
        int id = 0;
        tiles.add(GRASS = new Tile(getSprite(3, 6), id++, "Grass"));
        tiles.add(TREE = new Tile(getSprite(0, 0), id++, "Tree"));
        tiles.add(ROAD = new Tile(getSprite(8, 0), id++, "Road"));
        tiles.add(CAMP = new Tile(getSprite(0,6), id++, "Camp"));
        tiles.add(TOWER = new Tile(getSprite(8, 1), id++,"Tower"));
        tiles.add(ARCHER = new Tile(getSprite(0, 19), id++,"Archer"));
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }
    public BufferedImage getSprite(int id){
        return tiles.get(id).getSprite();
    }

    private BufferedImage getSprite(int xCord, int yCord){
        return atlas.getSubimage(xCord * 64, yCord * 64, 64, 64);
    }
    public Tile getTile(int id){
        return tiles.get(id);
    }
}
