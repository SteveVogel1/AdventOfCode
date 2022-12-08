package twentytwo.dayEight;

import twentytwo.helper.DailyTask;

import java.util.List;

public class dayEight implements DailyTask {

    public static void main(String[] args) {
        dayEight day = new dayEight();
        day.run();
    }

    public String taskA(List<String> input){
        int[][] grid = createGrid(input);

        int counter = 0;
        for(int i = 0; i < input.size(); i++){
            for(int x = 0; x < input.get(i).length(); x++){
                if(isVisible(grid, i, x)){
                    counter++;
                }
            }
        }
        return String.valueOf(counter);
    }

    private static int[][] createGrid(List<String> input) {
        int[][] grid = new int[input.size()][input.get(0).length()];
        for(int i = 0; i < input.size(); i++){
            for(int x = 0; x < input.get(i).length(); x++){
                grid[i][x] = Integer.parseInt(String.valueOf(input.get(i).charAt(x)));
            }
        }
        return grid;
    }

    private boolean isVisible(int[][] grid, int i, int x){
        if(i == 0 || i == grid.length -1 || x == 0 || x == grid[0].length -1){
            return true;
        }

        int baseHeight = grid[i][x];
        for(int j = i-1; j >= 0; j--){
            if(grid[j][x] >= baseHeight){
                break;
            }
            if(j == 0) return true;
        }
        for(int j = i+1; j < grid.length; j++){
            if(grid[j][x] >= baseHeight){
                break;
            }
            if(j == grid.length - 1){
                return true;
            }
        }
        for(int j = x-1; j >= 0; j--){
            if(grid[i][j] >= baseHeight){
                break;
            }
            if(j == 0) return true;
        }
        for(int j = x+1; j < grid[i].length; j++){
            if(grid[i][j] >= baseHeight){
                break;
            }
            if(j == grid[0].length - 1) {
                return true;
            }
        }
        return false;
    }

    private int getScore(int[][] grid, int i, int x){
        int score = 1;
        int baseHeight = grid[i][x];
        int localScore = 0;
        for(int j = i-1; j >= 0; j--){
            localScore++;
            if(grid[j][x] >= baseHeight){
                break;
            }
        }
        score *= localScore;
        localScore = 0;
        for(int j = i+1; j < grid.length; j++){
            localScore++;
            if(grid[j][x] >= baseHeight){
                break;
            }
        }
        score *= localScore;
        localScore = 0;
        for(int j = x-1; j >= 0; j--){
            localScore++;
            if(grid[i][j] >= baseHeight){
                break;
            }
        }
        score *= localScore;
        localScore = 0;
        for(int j = x+1; j < grid[i].length; j++){
            localScore++;
            if(grid[i][j] >= baseHeight){
                break;
            }
        }
        score *= localScore;

        return score;
    }


    public String taskB(List<String> input){
        int[][] grid = createGrid(input);
        int maxScore = 0;
        for(int i = 0; i < input.size(); i++){
            for(int x = 0; x < input.get(i).length(); x++){
                int score = getScore(grid, i, x);
                if(score > maxScore){
                    maxScore = score;
                }
            }
        }
        return String.valueOf(maxScore);
    }
}
