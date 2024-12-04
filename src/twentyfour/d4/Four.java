package twentyfour.d4;

import twentytwo.helper.DailyTask;

import java.util.List;

public class Four implements DailyTask {
    final boolean DEBUGGING = false;
    public static void main(String[] args) {
        Four day = new Four();
        day.run();
    }

    public String taskA(List<String> input){
        char[][] grid = getGrid(input);
        long result = findAllSolutions(grid);

        return String.valueOf(result);
    }

    public String taskB(List<String> input){
        char[][] grid = getGrid(input);
        long result = findAllSolutionsB(grid);

        return String.valueOf(result);
    }


    private char[][] getGrid(List<String> input){
        char[][] grid = new char[input.size()][input.get(0).length()];
        for(int i = 0; i < input.size(); i++){
            grid[i] = input.get(i).toCharArray();
        }
        return grid;
    }

    private long findAllSolutions(char[][] grid){
        long counter = 0;
        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[y].length; x++){
                counter += hasXmas(grid, x, y);
            }
        }
        return counter;
    }

    private int hasXmas(char[][] grid, int posX, int posY){
        int counter = 0;
        for(int[] direction : directions){
            for(int i = 0; i < "XMAS".length(); i++){
                int newPosY = posY + i*direction[0];
                int newPosX = posX + i*direction[1];
                if(newPosY < 0 || newPosY >= grid.length
                        || newPosX < 0 || newPosX >= grid[0].length
                        || grid[newPosY][newPosX] != "XMAS".toCharArray()[i]){
                    i = 5;
                    continue;
                }
                if(i == "XMAS".length() -1){
                    counter++;
                }
            }
        }

        return counter;
    }

    private long findAllSolutionsB(char[][] grid){
        long counter = 0;
        for(int y = 1; y < grid.length-1; y++){
            for(int x = 1; x < grid[y].length-1; x++){
                counter += lookUp(grid, x, y);
            }
        }

        return counter;
    }

    private int lookUp(char[][] grid, int posX, int posY){
        if(grid[posY][posX] == 'A'){
            int counter = 0;
            for(int i = 1; i < directions.length; i+=2) {
                int[] direction = directions[i];
                if (grid[posY+ direction[0]][posX+ direction[1]] =='M'
                        && grid[posY- direction[0]][posX- direction[1]] =='S'){
                    counter++;
                }
            }

            if(counter > 1){
                printFound(grid, posX, posY);
                return 1;
            }

        }
        return 0;
    }

    private void printFound(char[][] grid, int posX, int posY){
        if(DEBUGGING) {
            System.out.println("X: " + posX + " Y: " + posY);
            System.out.println("" + grid[posY - 1][posX - 1] + grid[posY - 1][posX] + grid[posY - 1][posX + 1]);
            System.out.println("" + grid[posY][posX - 1] + grid[posY][posX] + grid[posY][posX + 1]);
            System.out.println("" + grid[posY + 1][posX - 1] + grid[posY + 1][posX] + grid[posY + 1][posX + 1]);
        }
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