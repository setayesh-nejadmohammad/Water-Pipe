package game.waterpipe;

import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class Pipe {
    private int num;
    private pipeState state = pipeState.MOVABLE;  // defult is MOVABLE مگر اینکه خلافش ثابت شه :)
    public Image pipe1 = loadImage("pics/pipe1.png");
    public Image pipe2 = loadImage("pics/pipe2.png");
    public Image pipe3 = loadImage("pics/pipe3.png");
    public Image pipe4 = loadImage("pics/pipe4.png");
    public Image pipe5 = loadImage("pics/pipe5.png");
    public Image pipe6 = loadImage("pics/pipe6.png");
    public Image pipe7 = loadImage("pics/pipe7.png");
    public Image pipe8 = loadImage("pics/pipe8.png");
    public Image staticPipe1 = loadImage("pics/static pipe1.png");
    public Image staticPipe2 = loadImage("pics/static pipe2.png");
    public Image staticPipe3 = loadImage("pics/static pipe3.png");
    public Image staticPipe4 = loadImage("pics/static pipe4.png");
    public Image staticPipe5 = loadImage("pics/static pipe5.png");
    public Image staticPipe6 = loadImage("pics/static pipe6.png");

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
        if(num == 1 && state == Pipe.pipeState.MOVABLE){
            return pipe1;
        }
        else if(num == 1 && state == pipeState.STATIC){
            return staticPipe1;
        }
        else if(num == 2 && state == Pipe.pipeState.MOVABLE){
            return pipe2;
        }
        else if(num == 2 && state == pipeState.STATIC){
            return staticPipe2;
        }
        else if(num == 3 && state == Pipe.pipeState.MOVABLE){
            return pipe3;
        }
        else if(num == 3 && state == pipeState.STATIC){
            return staticPipe3;
        }
        else if(num == 4 && state == Pipe.pipeState.MOVABLE){
            return pipe4;
        }
        else if(num == 4 && state == pipeState.STATIC){
            return staticPipe4;
        }
        else if(num == 5 && state == Pipe.pipeState.MOVABLE){
            return pipe5;
        }
        else if(num == 5 && state == pipeState.STATIC){
            return staticPipe5;
        }
        else if(num == 6 && state == Pipe.pipeState.MOVABLE){
            return pipe6;
        }
        else if(num == 6 && state == pipeState.STATIC){
            return staticPipe6;
        }
        else if(num == 7){
            return pipe7;
        }
        else if(num == 8){
            return pipe8;
        }
        return null;
    }

    public enum pipeState{
        MOVABLE, STATIC;
    }


    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public pipeState getState(){
        return state;
    }
    public void setState(pipeState state){
        this.state = state;
    }
}
