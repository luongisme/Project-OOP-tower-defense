package Input;

import Main.Game;
import Main.GameStates;
import static Main.GameStates.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyboardListener implements KeyListener {
    private Game game;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_M){
            GameStates.gameState = MENU;
        }
        else if (e.getKeyCode() == KeyEvent.VK_P){
            GameStates.gameState = PLAYING;
        }else if (e.getKeyCode() == KeyEvent.VK_S){
            GameStates.gameState = SETTINGS;
        }
        /* deselect tower when pressing esc
        else if(GameStates.gameState == PLAYING){
            game.getPlaying().keyPressed(e);
        }
        */
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
