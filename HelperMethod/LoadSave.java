package HelperMethod;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static BufferedImage getSpriteAtlas(){
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("spritesheet.png");
        try {
            img = ImageIO.read(is); 
        } catch(IOException e){
            e.printStackTrace();
        }
        return img;
    }
}
