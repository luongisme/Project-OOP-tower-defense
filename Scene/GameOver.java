package Scene;

import java.awt.Graphics;
import Main.Game;
import UI.MyButton;
import UI.MyButton;
import java.awt.Color;
import java.awt.Font;
import static Main.GameStates.*;

public class GameOver extends GameScene implements SceneMethods{
    private MyButton bQuit;


    public GameOver(Game game){
        super(game);
        initButtons();
    }

    private void initButtons() {
        int w = 300;
        int h = 200 / 3;
        int x = 1280 / 2 - w / 2;
        int y = 300;

        bQuit = new MyButton("Quit", x, y, w, h);
        
    }

    @Override
    public void render(Graphics g){
        g.setFont(new Font(null, Font.BOLD, 20));
        bQuit.draw(g);
        
        g.setFont(new Font(null, Font.BOLD, 50));
        g.setColor(Color.RED);
        g.drawString("Game Over!", 1280 / 2 - 150, 100);

    }

    @Override
    public void mouseClicked(int x, int y) {
        if(bQuit.getBounds().contains(x, y)){
            System.exit(0);
    }
}
    


    @Override
    public void mouseMoved(int x, int y) {
        bQuit.setMouseOver(false);

        if(bQuit.getBounds().contains(x, y)){
            bQuit.setMouseOver(true);
        }
    }
    @Override
    public void mousePressed(int x, int y) {
        if(bQuit.getBounds().contains(x, y)){
            bQuit.setMousePressed(true);;
        }
    }
    @Override
    public void mouseReleased(int x, int y) {
        bQuit.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {
        
    }

} 


