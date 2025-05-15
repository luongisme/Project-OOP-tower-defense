package main;

import inputs.KeyboardListener;
import inputs.MyMouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JPanel {
    private Game game;
    //private int[] numbers;exactly the number
    private Dimension size;

    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;



    public GameScreen(Game game) {
        this.game = game;

        setPanelSize();

    }

    public void initInputs(){
        myMouseListener = new MyMouseListener(game);
        keyboardListener = new KeyboardListener();

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);

        addKeyListener(keyboardListener);

        requestFocus();//focus on particular component, make sure no weird bugs(no much need bc of JFrame and JPanel)
    }

    private void setPanelSize() {
        size = new Dimension(640, 740); //each pixel is 32, therefore 32x20 640
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        //make sure when extends the screen, there is no gi do du thua
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.getRender().render(g);
        //g.drawImage(sprites.get(19), 0, 0 , null); // equal to g.drawImage(img.getSubimage(32 * 9, 32, 32, 32), 0, 0, null);
        //g.drawImage(img.getSubimage(32 * 9, 32, 32, 32), 0, 0, null);
        //g.drawRect(50 , 50, 100, 100);
        //g.fillRect(0,0,32,32);//at x = 0, y = 0, width = 32(ngang), 32(doc) = 1 pixel at (0,0)
        //g.fillRect(32,0,32,32);//x=32 => cach 1 pixel
    }

}