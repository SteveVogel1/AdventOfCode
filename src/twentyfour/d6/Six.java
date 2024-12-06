package twentyfour.d6;

import twentytwo.helper.DailyTask;

import java.util.List;

public class Six implements DailyTask {
    private static final boolean DEBUGGING = false;
    public static void main(String[] args) {
        Six day = new Six();
        day.run();
    }

    public String taskA(List<String> input){
        long result = 0;
        int xCurrent = 0;
        int yCurrent = 0;
        char[][] grid = new char[input.size()][input.getFirst().length()];
        for(int i = 0; i < input.size(); i++){
            grid[i] = input.get(i).toCharArray();
            if(input.get(i).contains("^")){
                yCurrent = i;
                xCurrent = input.get(i).indexOf('^');
                grid[yCurrent][xCurrent] = '.';
            }
        }

        int xNext;
        int yNext;

        Direction currentDirection = Direction.UP;

        outerLoop:
        while(true) {
            if(grid[yCurrent][xCurrent] == '.') {
                grid[yCurrent][xCurrent] = 'X';
                result++;
            }


            do {
                yNext = yCurrent;
                xNext = xCurrent;
                switch (currentDirection) {
                    case UP -> yNext = yCurrent - 1;
                    case DOWN -> yNext = yCurrent + 1;
                    case RIGHT -> xNext = xCurrent + 1;
                    case LEFT -> xNext = xCurrent - 1;
                }
                if(!inBound(grid, xNext, yNext)){
                    break outerLoop;
                }
                if (isBlocked(grid, xNext, yNext)) {
                    currentDirection = Direction.getNextDirection(currentDirection);
                }
            } while(isBlocked(grid, xNext, yNext));
            yCurrent = yNext;
            xCurrent = xNext;
        }

        if(DEBUGGING) {
            printGrid(grid);
        }

// up - width
// down + width
//left - width
// right + width

        return String.valueOf(result);
    }

    private boolean isBlocked(char[][] grid, int x, int y){
        return grid[y][x] == '#';
    }
    private boolean inBound(char[][] grid, int x, int y){
        return !(x < 0 | y < 0) && x < grid[0].length && y < grid.length;
    }

    public String taskB(List<String> input){
        long result = 0;

        return String.valueOf(result);
    }

    private void printGrid(char[][] grid){
        for (char[] chars : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(chars[j]);
            }
            System.out.println();
        }

    }


    enum Direction {
        UP, RIGHT, DOWN, LEFT;

        static Direction getNextDirection(Direction old){
            return switch (old){
                case UP -> RIGHT;
                case RIGHT -> DOWN;
                case DOWN -> LEFT;
                case LEFT -> UP;
            };
        }
    }
}