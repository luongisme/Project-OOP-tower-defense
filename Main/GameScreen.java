package Main;

import Input.KeyboardListener;
import Input.MyMouseListener;
import java.awt.*;
import javax.swing.*;

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
        size = new Dimension(1280,868); //each pixel is 32, therefore 32x24 896
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        //make sure when extends the screen, there is no gi do du thua
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.getRender().render(g);
    }

}