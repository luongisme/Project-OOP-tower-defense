package HelperMethod;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LoadImages {
    public static BufferedImage getSpriteAtlas(){
        BufferedImage img = null;
        InputStream is = LoadImages.class.getClassLoader().getResourceAsStream("spritesheet.png");
        try {
            img = ImageIO.read(is); 
        } catch(IOException e){
            e.printStackTrace();
        }
        return img;
    }
}
