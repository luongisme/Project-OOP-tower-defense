package Input;

import Main.GameStates;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Main.GameStates.*;
import Main.GameStates;
public class KeyboardListener implements KeyListener {
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
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
