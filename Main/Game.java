package Main;


import Scene.Menu;
import Scene.Playing;
import Scene.Settings;
import Tile.TileManager;
import Input.KeyboardListener;
import Input.MyMouseListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame implements Runnable {
        private GameScreen gameScreen;

        private int updates;
        private long lastTimeUPS;

        private Thread gameThread;

        private final double FPS_SET = 120.0;
        private final double UPS_SET = 60.0;

        private Render render;
        private Menu menu;
        private Playing playing;
        private Settings settings;

    public Game(){

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        initClasses();
        add(gameScreen);
        pack();
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    private void initClasses() {
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);
    }


    private void start() {
        gameThread = new Thread(this){};
        gameThread.start();
    }

    private void callUPS() {
        if(System.currentTimeMillis() - lastTimeUPS >= 1000){
            System.out.println("UPS: " + updates);
            updates = 0;
            lastTimeUPS = System.currentTimeMillis();
        }
    }

    private void updateGame() {
        playing.update();
    }

    public static void main(String[] args){
        Game game = new Game();
        game.gameScreen.initInputs();
        game.start();
    }


    @Override
    public void run(){

        double timePerFrame = 1000000000.0/FPS_SET;//16.6667 nano secs = 0.016666 secs per frame
        double timePerUdpdate = 1000000000.0/UPS_SET;
        long lastFrame = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();
        long lastUpdate = System.nanoTime();

        int frames = 0;
        int updates = 0;

        long now;

        while(true) {
            now = System.nanoTime();
            //render
            if (now - lastFrame >= timePerFrame) {
                repaint();
                lastFrame = now;
                frames++;
            }
            //update
            if(now - lastUpdate >= timePerUdpdate){
                updateGame();
                lastUpdate = now;
                updates++;
            }

            if(System.currentTimeMillis() - lastTimeCheck >= 1000){
                System.out.println("FPS: " + frames + "| UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }
    public Render getRender(){
        return render;
    }
    public Menu getMenu(){
        return menu;
    }
    public Playing getPlaying(){
        return playing;
    }
    public Settings getSettings(){
        return settings;
    }

    public TileManager getTileManager() {
		return playing.getTileManager();
	}

}
