package game.waterpipe;

import java.util.Random;

public class RandomWay {
    int[][] pipes3Array;

    public RandomWay(int[][] pipes3Array) {
        this.pipes3Array = pipes3Array;
    }

    public static int[][] generateRandomWay() {
        int[][] pipes3Arr = {
                {8, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 9}
        };

        Random random = new Random();
        int downCount = 0;
        int rightCount = 0;
        int pointX = 1;
        int pointY = 0;
        boolean flag = false;
        int randomNum;
        for(int j = 0; j < 10; j++){
            if(rightCount < 6 && downCount < 6){
                randomNum = random.nextInt(2);
            }
            else if(rightCount >= 5){
                randomNum = 1;
            }
            else{
                randomNum = 0;
            }
            if(randomNum == 0){
                pointX += 1;
                downCount += 1;
            }
            else if(randomNum == 1){
                pointY += 1;
                rightCount += 1;
            }
            if(pointX >= 6) pointX = 6;
            if(pointY >= 6) pointY = 5;
            pipes3Arr[pointX][pointY] = 1;
        }
        for(int j = 0; j < 7; j++){
            for(int k = 0; k < 7; k++){
                System.out.print(pipes3Arr[j][k] + " ");
            }
            System.out.println();
        }
        System.out.println();

        int x = 1; int y = 0;

        while (!(x == 6 && y == 6)) {
            if(y == 0){
                if(x+1 < 7 && pipes3Arr[x+1][y] != 0){
                    pipes3Arr[x][y] = 1;
                    x++;
                }
                else if(y+1 < 7 && pipes3Arr[x][y+1] != 0){
                    pipes3Arr[x][y] = 3;
                    y++;
                }
            }
            else if(x == 6){
                if(y-1>=0 && pipes3Arr[x][y-1] != 0){
                    pipes3Arr[x][y] = 2;
                }
                else if(x-1>0 && pipes3Arr[x-1][y] != 0){
                    pipes3Arr[x][y] = 3;

                }
                y++;
            }
            else if(y == 5 && x != 6){
                if(y-1>=0 && pipes3Arr[x][y-1] != 0){
                    pipes3Arr[x][y] = 5;
                }
                else if(x-1>0 && pipes3Arr[x-1][y] != 0){
                    pipes3Arr[x][y] = 1;
                }
                x++;
            }
            else {
                if(y+1<7 && pipes3Arr[x][y+1] != 0 && y-1>=0 && pipes3Arr[x][y-1] != 0){
                    pipes3Arr[x][y] = 2;
                    y++;
                }
                else if(y+1<7 && pipes3Arr[x][y+1] != 0 && x-1>0 && pipes3Arr[x-1][y] != 0){
                    pipes3Arr[x][y] = 3;
                    y++;
                }
                else if(x+1<7 && pipes3Arr[x+1][y] != 0 && y-1>=0 && pipes3Arr[x][y-1] != 0){
                    pipes3Arr[x][y] = 5;
                    x++;
                }
                else if(x+1<7 && pipes3Arr[x+1][y] != 0 && x-1>0 && pipes3Arr[x-1][y] != 0){
                    pipes3Arr[x][y] = 1;
                    x++;
                }
            }

        }
        for(int j = 0; j < 7; j++){
            for(int k = 0; k < 7; k++){
                System.out.print(pipes3Arr[j][k] + " ");
            }
            System.out.println();
        }
        System.out.println();
        return pipes3Arr;
    }
}
