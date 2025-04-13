package game.waterpipe;

import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class Pipe {
    private int num;
    public Image pipe1 = loadImage("pics/pipe1.png");
    public Image pipe2 = loadImage("pics/pipe2.png");
    public Image pipe3 = loadImage("pics/pipe3.png");
    public Image pipe4 = loadImage("pics/pipe4.png");
    public Image pipe5 = loadImage("pics/pipe5.png");
    public Image pipe6 = loadImage("pics/pipe6.png");
    public Image pipe7 = loadImage("pics/pipe7.png");
    public Image pipe8 = loadImage("pics/pipe8.png");

    public Pipe(int num){
        this.num = num;
    }

    private Image loadImage(String path) {
        try {
            return new Image(getClass().getResourceAsStream(path));
        } catch (Exception e) {
            System.err.println("Error loading image: " + path);
            return null;
        }
    }

    public Image getImage() {
        if(num == 1){
            return pipe1;
        }
        else if(num == 2){
            return pipe2;
        }
        else if(num == 3){
            return pipe3;
        }
        else if(num == 4){
            return pipe4;
        }
        else if(num == 5){
            return pipe5;
        }
        else if(num == 6){
            return pipe6;
        }
        else if(num == 7){
            return pipe7;
        }
        else if(num == 8){
            return pipe8;
        }
        return null;
    }
    public int getNum() {
        return num;
    }

}
