package Scene;

import Main.Game;
import java.awt.image.BufferedImage;

public class GameScene {
    private Game game;
    public GameScene(Game game){
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

}
