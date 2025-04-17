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

    public boolean SMARTcheck() {
        System.out.println("SMARTcheck in ON!");
        for(int i = 0; i < pipe.length; i++){
            for(int j = 0; j < pipe[i].length; j++){
                if(pipe[i][j] !=  null){
                    System.out.print(pipe[i][j].getNum() + " ");
                }
            }
            System.out.println();
        }
        boolean result = true;

        // The start position
        int x = 1;
        int y = 0;

        while(!(x == 6 && y == 6) && result && pipe[x][y] != null){
            //System.out.println("pipe[x][y] = "+pipe[x][y].getNum());
            if(pipe[x-1][y].getNum() == 8 && pipe[x][y].getNum() != 3 && pipe[x][y].getNum() != 1 && pipe[x][y].getNum() != 7){
                result = false;
            }
            else if(pipe[x][y].getNum() == 1){
                if(x+1<7 && pipe[x+1][y].getNum() != 1 && pipe[x+1][y].getNum() != 3 && pipe[x+1][y].getNum() != 7){
                    result = false;
                }
                else x++;
            }
            else if(pipe[x][y].getNum() == 2){
                if(y+1<6 && pipe[x][y+1].getNum() != 2 && pipe[x][y+1].getNum() != 5 && pipe[x][y+1].getNum() != 7){
                    result = false;
                }
                else y++;
            }
            else if(pipe[x][y].getNum() == 7){
                if(x+1<7 && pipe[x+1][y].getNum() != 1 && pipe[x+1][y].getNum() != 3 && pipe[x+1][y].getNum() != 7
                && y+1<6 && pipe[x][y+1].getNum() != 2 && pipe[x][y+1].getNum() != 5 && pipe[x][y+1].getNum() != 7){
                    result = false;
                }
                else if(pipe[x+1][y].getNum() == 1 || pipe[x+1][y].getNum() == 3 || pipe[x+1][y].getNum() == 7){
                    x++;
                }
                else y++;
            }
            else if(pipe[x][y].getNum() == 3){
                if(y+1<6 && pipe[x][y+1].getNum() != 2 && pipe[x][y+1].getNum() != 5 && pipe[x][y+1].getNum() != 7){
                    result = false;
                }
                else y++;
            }
            else if(pipe[x][y].getNum() == 5){
                if(x+1<7 && pipe[x+1][y].getNum() != 1 && pipe[x+1][y].getNum() != 3 && pipe[x+1][y].getNum() != 7){
                    result = false;
                }
                else x++;
            }
        }
        System.out.println("The result is: " + result);
        return result;
    }
}
