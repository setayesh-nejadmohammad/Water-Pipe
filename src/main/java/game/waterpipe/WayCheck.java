package game.waterpipe;

public class WayCheck {
    private Pipe[][] pipe;
    private int level;
    public WayCheck(Pipe[][] pipe, int level) {
        this.pipe = pipe;
        this.level = level;
    }

    public boolean stupidCheck(){
        boolean result = true; // برنده میشه مگر اینکه خلافش ثابت شه :)
        if(level == 1){
            if(pipe[1][0].getNum() != 3 ||
                    pipe[1][1].getNum() != 2 ||
                    pipe[1][2].getNum() != 2 ||
                    pipe[1][3].getNum() != 5 ||
                    pipe[2][0].getNum() != 4 ||
                    pipe[2][1].getNum() != 2 ||
                    pipe[2][2].getNum() != 2 ||
                    pipe[2][3].getNum() != 6 ||
                    pipe[3][0].getNum() != 1 ||
                    pipe[4][0].getNum() != 3 ||
                    pipe[4][1].getNum() != 2 ||
                    pipe[4][2].getNum() != 2 ||
                    pipe[4][3].getNum() != 2) result = false;
        }
        else if(level == 2){
            if(pipe[1][0].getNum() != 1 ||
                    pipe[2][0].getNum() != 1 ||
                    pipe[3][0].getNum() != 3 ||
                    pipe[3][1].getNum() != 2 ||
                    pipe[3][2].getNum() != 2 ||
                    pipe[3][3].getNum() != 2 ||
                    pipe[3][4].getNum() != 5 ||
                    pipe[4][1].getNum() != 4 ||
                    pipe[4][2].getNum() != 2 ||
                    pipe[4][3].getNum() != 2 ||
                    pipe[4][4].getNum() != 6 ||
                    pipe[5][1].getNum() != 1 ||
                    pipe[6][1].getNum() != 3 ||
                    pipe[6][2].getNum() != 2 ||
                    pipe[6][3].getNum() != 2 ||
                    pipe[6][4].getNum() != 2 ||
                    pipe[6][5].getNum() != 2) result = false;
        }
        return result;
    }
}
