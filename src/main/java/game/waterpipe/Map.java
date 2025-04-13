package game.waterpipe;

import java.util.Random;

public class Map {
    private int level;
    int SIZE = 5;

    private int[][] pipes1Arr = {
            {7, 0, 0, 0, 0},
            {6, 1, 1, 3, 0},
            {5, 1, 2, 4, 0},
            {1, 0, 0, 0, 0},
            {4, 2, 1, 1, 8}
    };
    private int[][] pipes2Arr = {
            {7, 0, 0, 0, 0, 0, 0},
            {2, 0, 0, 0, 0, 0, 0},
            {2, 0, 0, 0, 0, 0, 0},
            {4, 1, 2, 1, 6, 0, 0},
            {0, 3, 1, 2, 4, 0, 0},
            {0, 1, 0, 0, 0, 0, 0},
            {0, 4, 2, 1, 2, 1, 8}
    };

    private int[][] pipes3Arr = {
            {7, 0, 0, 0, 0, 0, 0},
            {2, 0, 0, 0, 0, 0, 0},
            {2, 0, 0, 0, 0, 0, 0},
            {4, 1, 2, 1, 6, 0, 0},
            {0, 3, 1, 2, 4, 0, 0},
            {0, 1, 0, 0, 0, 0, 0},
            {0, 4, 2, 1, 2, 1, 8}
    };

    private int[][] fixedPipe1 = {
            {7, 0, 0, 0, 0},
            {3, 2, 2, 5, 0},
            {4, 2, 2, 6, 0},
            {1, 0, 0, 0, 0},
            {3, 2, 2, 2, 8}
    };

    private int[][] fixedPipe2 = {
            {7, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0},
            {3, 2, 2, 2, 5, 0, 0},
            {0, 4, 2, 2, 6, 0, 0},
            {0, 1, 0, 0, 0, 0, 0},
            {0, 4, 2, 2, 2, 2, 8}
    };

    private int[][] pipes;
    private int[][] fixedPipes;

    public Map(int level) {
        this.level = level;
        if(level == 1){
            pipes = pipes1Arr;
            fixedPipes = fixedPipe1;
            SIZE = 5;
        }
        else if(level == 2){
            pipes = pipes2Arr;
            fixedPipes = fixedPipe2;
            SIZE = 7;
        }
        else if(level == 3){
            pipes = pipes3Arr;
            fixedPipes = fixedPipe2;
            SIZE = 7;
        }
        //Random(pipe);
    }

    private void RandomMap(int[][] pipes){
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                Random rand = new Random();
                if(pipes[i][j] == 0){
                    pipes[i][j] = rand.nextInt(7);
                }
                else if(pipes[i][j] == 1 || pipes[i][j] == 2){
                    pipes[i][j] = rand.nextInt(2)+1;
                }
                else if(pipes[i][j] >= 3 && pipes[i][j] <= 6){
                    pipes[i][j] = rand.nextInt(4)+3;
                }
            }
        }
    }

    private void RandomPipe(Pipe[][] pipe){
        Random rand = new Random();
        int[] n = {rand.nextInt(17), rand.nextInt(17), rand.nextInt(17), rand.nextInt(17)};
        int count = 0;
        for(int k = 0; k < 4; k++){  // define 4 random pos for STATIC
            count = 0;
            for(int i = 0; i < SIZE; i++){
                for(int j = 0; j < SIZE; j++){
                    if(pipe[i][j].getNum() != 0 && pipe[i][j].getNum() <= 6){
                        count++;
                    }
                    if(count == n[k]){
                        pipe[i][j].setNum(fixedPipes[i][j]);
                        pipe[i][j].setState(Pipe.pipeState.STATIC);
                    }
                }
            }
        }

        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(pipe[i][j].getNum() == 0 && pipe[i][j].getState() == Pipe.pipeState.MOVABLE){
                    pipe[i][j].setNum(rand.nextInt(7));
                }
                else if((pipe[i][j].getNum() == 1 || pipe[i][j].getNum() == 2) && pipe[i][j].getState() == Pipe.pipeState.MOVABLE){
                    pipe[i][j].setNum(rand.nextInt(2)+1);
                }
                else if(pipe[i][j].getNum() >= 3 && pipe[i][j].getNum() <= 6 && pipe[i][j].getState() == Pipe.pipeState.MOVABLE){
                    pipe[i][j].setNum(rand.nextInt(4)+3);
                }
            }
        }

    }


    public void fillPipes(Pipe[][] pipe) {
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                pipe[i][j] = new Pipe(pipes[i][j]);
            }
        }
        RandomPipe(pipe);
    }
}
