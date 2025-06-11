package UI;

import Main.GameStates;
import static Main.GameStates.MENU;
import static Main.GameStates.SetGameState;
import Scene.Playing;
import TowerM.Tower;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BottomBar {
    private int x, y , width, height;
    private MyButton bMenu;
    private Playing playing;

    

    //private ArrayList<MyButton> tileButtons = new ArrayList<>();
    private MyButton[] towerButtons;
    private Tower selectedTower;

    private int lives = 1;
// can you modify the bottom bar for me,need some update on the tower place
    public  BottomBar(int x, int y, int width, int height, Playing playing){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playing = playing;

        initButtons();
        
    }

    public void draw(Graphics g){
        //background
        g.setColor(new Color(220, 123, 15));
        g.fillRect(x, 770, 1280, height); // Set width to 1280 to fit the screen

        //button
        drawButtons(g);
        g.setColor(Color.black);
        g.drawString("Lives: " + lives, 10, 790);
    }
       
        
    private void initButtons() {

        bMenu = new MyButton("Menu", 2, 772, 100, 65);
        int w = 65;
        int h = 65;
        int xStart = 110;
        int yStart = 772;
        int xOffset = (int)(w * 1.1);
        int i = 0;
        towerButtons = new MyButton[1];

        //for(Tile tile : playing.getTileManager().tiles){
            //g.setColor(Color.GRAY);
            //g.fillRect(xStart + xOffset * i, yStart, w, h);
            //towerButtons.add(new MyButton(tile.getName(), xStart + xOffset * i, yStart , w, h, i));
            towerButtons[i] = new MyButton("", xStart+ xOffset * i, yStart, w, h, i);
            i++;
        //}
    }
    private void drawButtons(Graphics g) {
        
        
        //drawTileButtons(g);
        bMenu.draw(g);
        for(MyButton b : towerButtons){
            g.setColor(Color.GRAY);
            g.fillRect(b.x, b.y, b.width, b.height);
            g.drawImage(playing.getTowerManager().getTowerImages()[0], b.x, b.y, b.width, b.height, null);
        }
        //ORIGINAL CODE drawSelectedTile(g);
        //drawSelectedTower(g);
    }
    

    /* ORIGINAL CODE
    private void drawSelectedTile(Graphics g) {
        if(selectedTile != null){
            g.drawImage(selectedTile.getSprite(), 1200, 772, 65, 65, null);
            g.setColor(Color.black);
            g.drawRect(1200, 772, 65,65);
        }
    }
    */
    
       

    private void drawTileButtons(Graphics g) {
        //for(MyButton b : tileButtons){
        for(MyButton b : towerButtons){    
            //Sprite
            g.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height, null);

            //mouseOver
            if(b.isMouseOver()){
                g.setColor(Color.WHITE);
            }else{
                g.setColor(Color.BLACK);
            }

            //border
            g.drawRect(b.x, b.y, b.width, b.height);

            //mousePressed
            if(b.isMousePressed()){
                g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
                g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
            }


        }
    }

    public BufferedImage getButtImg(int id){
        return playing.getTileManager().getSprite(id);
    }


    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            SetGameState(MENU);
        else{
            for(MyButton b : towerButtons){
            //for(MyButton b : tileButtons){
                if(b.getBounds().contains(x, y)){
                    selectedTower = new Tower(0,0,-1,b.getId());
                    playing.setSelectedTower(selectedTower); 
                    return;
                    /* 
                    selectedTile =  playing.getTileManager().getTile(b.getId());
                    playing.setSelectedTile(selectedTile);
                    return;
                    */
                }
            }
        }
    }


    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        for(MyButton b : towerButtons)
        //for (MyButton b : tileButtons)
            b.setMouseOver(false);
        if (bMenu.getBounds().contains(x, y))
                bMenu.setMouseOver(true);
        else {
            for(MyButton b : towerButtons){
            //for (MyButton b : tileButtons) {
                if(b.getBounds().contains(x, y)){
                    b.setMouseOver(true);
                    return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
        else{
            for(MyButton b : towerButtons){
            //for(MyButton b : tileButtons){
                if(b.getBounds().contains(x, y)){
                    b.setMousePressed(true);
                    return;
                }
            }
        }
    }


    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        for(MyButton b : towerButtons)
        //for(MyButton b: tileButtons)
            b.resetBooleans();
    }

    public int getLives() {
        return lives;
    }
    public void removeOneLife(){
        lives--;
        if(lives <= 0){
            SetGameState(GameStates.GAME_OVER);
        }
    }

}
