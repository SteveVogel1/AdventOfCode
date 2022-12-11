package twentytwo.dayTen;

import twentytwo.helper.DailyTask;

import java.util.List;

public class dayTen implements DailyTask {

    public static void main(String[] args) {
        dayTen day = new dayTen();
        day.run();
    }

    public String taskA(List<String> input){
        long counter = 0;
        long x = 1;
        long cycle = 1;
        for (String line : input) {
            String[] command = line.split(" ");

            if ((cycle + 20) % 40 == 0) {
                counter += x * cycle;
            }

            if (command[0].equals("addx")) {
                cycle++;
                if ((cycle + 20) % 40 == 0) {
                    counter += x * cycle;
                }
                x += Long.parseLong(command[1]);
            }
            cycle++;
        }
        return String.valueOf(counter);
    }

    public String taskB(List<String> input){
        boolean[][] grid = new boolean[6][40];
        int x = 1;
        int cycle = 0;
        for (String line : input) {
            String[] command = line.split(" ");

            if (cycle % 40 == x || cycle % 40 == x - 1 || cycle % 40 == x + 1) {
                grid[cycle / 40][cycle % 40] = true;
            }

            if (command[0].equals("addx")) {
                cycle++;
                if (cycle % 40 == x || cycle % 40 == x - 1 || cycle % 40 == x + 1) {
                    grid[cycle / 40][cycle % 40] = true;
                }
                x += Long.parseLong(command[1]);
            }
            cycle++;
        }

        printGrid(grid);
        return "";
    }

    private void printGrid(boolean[][] grid){
        for (boolean[] booleans : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(booleans[j] ? "#" : ".");
            }
            System.out.println();
        }

    }
}
