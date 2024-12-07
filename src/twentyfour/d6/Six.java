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
        String[][] grid = new String[input.size()][input.getFirst().length()];
        for(int i = 0; i < input.size(); i++){
            grid[i] = input.get(i).split("");
            if(input.get(i).contains("^")){
                yCurrent = i;
                xCurrent = input.get(i).indexOf('^');
                grid[yCurrent][xCurrent] = ".";
            }
        }

        int xNext;
        int yNext;

        Direction currentDirection = Direction.UP;

        outerLoop:
        while(true) {
            if(grid[yCurrent][xCurrent].equals(".")) {
                grid[yCurrent][xCurrent] = "X";
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
        return String.valueOf(result);
    }

    public String taskB(List<String> input){
        long result = 0;
        for(int y = 0; y < input.size(); y++) {
            for(int x = 0; x <input.getFirst().length(); x++){
                if (checkForLoop(input, x, y)) {
                    if(DEBUGGING) {
                        System.out.println("Result Nr. " + result);
                        System.out.println("X: " + x + " Y: " + y);
                    }
                    result++;
                }
            }
        }
        return String.valueOf(result);
    }

    private boolean isBlocked(String[][] grid, int x, int y){
        return hasChar(grid, "#", x, y);
    }

    private boolean hasChar(String[][] grid, String c, int x, int y){
        return grid[y][x].equals(c);
    }

    private boolean inBound(String[][] grid, int x, int y){
        return !(x < 0 | y < 0) && x < grid[0].length && y < grid.length;
    }

    private void printGrid(String[][] grid){
        System.out.println("-------------------------------------");
        System.out.println("");
        for (String[] chars : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(chars[j]);
            }
            System.out.println();
        }
        System.out.println("");
    }

    private boolean checkForLoop(List<String> input, int x, int y){
        int xCurrent = 0;
        int yCurrent = 0;
        String[][] grid = new String[input.size()][input.getFirst().length()];
        for(int i = 0; i < input.size(); i++){
            grid[i] = input.get(i).split("");
            if(input.get(i).contains("^")){
                yCurrent = i;
                xCurrent = input.get(i).indexOf('^');
                grid[yCurrent][xCurrent] = ".";
            }
        }

        grid[y][x] = "#";

        int xNext;
        int yNext;

        int xLastTurn = -1;
        int yLastTurn = -1;

        Direction currentDirection = Direction.UP;

        int change = 0;
        outerLoop:
        while(true) {
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
                    if(!hasChar(grid, ".", xCurrent, yCurrent) && !(xLastTurn == xCurrent && yLastTurn == yCurrent)){
                        if(DEBUGGING) {
                            printGrid(grid);
                        }
                        return true;
                    }

                    xLastTurn = xCurrent;
                    yLastTurn = yCurrent;
                    grid[yCurrent][xCurrent] = String.valueOf(change);
                    change++;
                }
            } while(isBlocked(grid, xNext, yNext));
            yCurrent = yNext;
            xCurrent = xNext;
        }
        return false;
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