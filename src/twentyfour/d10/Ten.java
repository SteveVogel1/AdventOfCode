package twentyfour.d10;

import twentytwo.helper.DailyTask;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ten implements DailyTask {
    public static void main(String[] args) {
        Ten day = new Ten();
        day.run();
    }

    public String taskA(List<String> input){
        long result = 0;

        int[][] grid = getGrid(input);

        result = getResult(grid);

        return String.valueOf(result);
    }

    public String taskB(List<String> input){

        long result = 0;

        int[][] grid = getGrid(input);

        result = getAllResult(grid);

        return String.valueOf(result);
    }

    record Coordinate(int x, int y){}

    private long getResult(int[][] grid){
        long result = 0;
        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[0].length; x++){
                Set<Coordinate> resultCoordinates = new HashSet<>();
                possiblePaths(grid, x, y, -1, resultCoordinates);
                result += resultCoordinates.size();
            }
        }
        return result;
    }

    private long getAllResult(int[][] grid){
        long result = 0;
        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[0].length; x++){
                Set<Coordinate> resultCoordinates = new HashSet<>();
                result += possiblePaths(grid, x, y, -1, resultCoordinates);
            }
        }
        return result;
    }

    private long possiblePaths(int[][] grid, int x, int y, int currentValue, Set<Coordinate> resultCoordinates){
        if(x < 0 || x >= grid[0].length || y < 0 || y >= grid.length){
            return 0;
        }
        int nextValue = currentValue+1;
        if(nextValue != grid[y][x]){
            return 0;
        }
        if(nextValue == 9){
            resultCoordinates.add(new Coordinate(x, y));
            return 1;
        }

        return possiblePaths(grid, x+1, y, nextValue, resultCoordinates) + possiblePaths(grid, x-1, y, nextValue, resultCoordinates)
             + possiblePaths(grid, x, y+1, nextValue, resultCoordinates) + possiblePaths(grid, x, y-1, nextValue, resultCoordinates);
    }

    private int[][] getGrid(List<String> input){
        int[][] grid = new int[input.size()][input.getFirst().length()];
        for(int y = 0; y < grid.length; y++){
            String[] line = input.get(y).split("");
            for(int x = 0; x < grid[0].length; x++){
                if(line[x].equals(".")){
                    grid[y][x] = -1;
                }else {
                    grid[y][x] = Integer.parseInt(line[x]);
                }
            }
        }
        return grid;
    }
}