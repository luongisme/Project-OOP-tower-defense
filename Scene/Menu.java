package Scene;

import Main.Game;
import static Main.GameStates.*;
import UI.MyButton;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

public class Menu extends GameScene implements SceneMethods{
    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();//add or remove
    private Random random;
    private BufferedImage menuImage;
    private MyButton bPlaying, bSettings, bQuit;

    public Menu(Game game) {
        super(game);
        random = new Random();
        importImg();
        LoadSprite();
        loadMenuImage(); // Ensure menu image is loaded
        initButtons();
    }

    private void initButtons() {// set up all the button location
        int w = 325;
        int h = w / 3;
        int x = 1280 / 2 - w / 2;
        int y = 175;
        int yOffset = 200;
        bPlaying = new MyButton("Play", x, y, w, h);
        bSettings = new MyButton("Settings", x, y + yOffset, w, h);
        bQuit = new MyButton("Quit", x, y + yOffset * 2, w, h);
    }

    @Override
    public void render(Graphics g) {
        if (menuImage == null) {
            loadMenuImage();
        }
        if (menuImage != null) {
            // Scale the menu image to fit the panel size
            g.drawImage(menuImage, 0, 0, g.getClipBounds().width, g.getClipBounds().height, null);
        }
        drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            SetGameState(PLAYING);
        } else if (bSettings.getBounds().contains(x, y)) {
            SetGameState(SETTINGS);
        } else if (bQuit.getBounds().contains(x, y))
            System.exit(0);
    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.setMouseOver(false);
        bSettings.setMouseOver(false);
        bQuit.setMouseOver(false);

        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMouseOver(true);
        } else if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMouseOver(true);
        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMousePressed(true);
        } else if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMousePressed(true);
        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    private void resetButtons() {
        bPlaying.resetBooleans();
        bSettings.resetBooleans();
        bQuit.resetBooleans();
    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bSettings.draw(g);
        bQuit.draw(g);
    }

    public void importImg(){
        InputStream is = getClass().getResourceAsStream("/spritesheet.png");//InputSteam: class to read image
        try {
            img = ImageIO.read(is);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void loadMenuImage() {
        // Load the menu image
        try {
            menuImage = ImageIO.read(getClass().getResource("/Menu.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void LoadSprite() {// Take the image by block for certain objects
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                sprites.add(img.getSubimage(x * 32, y * 32, 32, 32));
            }
        }
    }

    private int getRndInt() {
        return random.nextInt(100);
    }
}
