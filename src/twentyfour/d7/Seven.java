package twentyfour.d7;

import twentytwo.helper.DailyTask;

import java.util.List;

public class Seven implements DailyTask {
    public static void main(String[] args) {
        Seven day = new Seven();
        day.run();
    }

    public String taskA(List<String> input){
        long result = 0;

        for(String line : input) {
            String[] x = line.split(" ");

        }

        return String.valueOf(result);
    }

    public String taskB(List<String> input){
        long result = 0;

        return String.valueOf(result);
    }
}