package twentyfour.d4;

import twentytwo.helper.DailyTask;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Four implements DailyTask {
    public static void main(String[] args) {
        Four day = new Four();
        day.run();
    }

    public String taskA(List<String> input){
        char[][] grid = getGrid(input);
        long result = findAllSolutions(grid,"XMAS");

        return String.valueOf(result);
    }

    public String taskB(List<String> input){
        char[][] grid = getGrid(input);
        long result = 0; //findAllSolutions(grid, "XMAS");

        return String.valueOf(result);
    }


    private char[][] getGrid(List<String> input){
        char[][] grid = new char[input.size()][input.get(0).length()];
        for(int i = 0; i < input.size(); i++){
            grid[i] = input.get(i).toCharArray();
        }
        return grid;
    }

    private long findAllSolutions(char[][] grid, String lookUp){
        long counter = 0;
        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[y].length; x++){
                counter += hasXmas(grid, x, y, lookUp);
            }
        }
        return counter;
    }

    private int hasXmas(char[][] grid, int posX, int posY, String lookUp){
        int counter = 0;
        for(int[] direction : directions){
            for(int i = 0; i < 4; i++){
                int newPosY = posY + i*direction[0];
                int newPosX = posX + i*direction[1];
                if(newPosY < 0 || newPosY >= grid.length
                        || newPosX < 0 || newPosX >= grid[0].length
                        || grid[newPosY][newPosX] != lookUp.toCharArray()[i]){
                    i = 5;
                    continue;
                }
                if(i == 3){
                    counter++;
                }
            }
        }

        return counter;
    }

    int[][] directions = new int[][]{
            //Y,X
            {0,1},
            {1,1},
            {1,0},
            {1,-1},
            {0,-1},
            {-1,-1},
            {-1,0},
            {-1,1},
    };

}