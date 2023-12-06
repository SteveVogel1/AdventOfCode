package twentythree.d6;

import twentytwo.helper.DailyTask;

import java.util.*;

public class DaySix implements DailyTask {

    public static void main(String[] args) {
        DaySix dayOne = new DaySix();
        dayOne.run();
    }

    public String getFileName() {
        return "src/twentythree/d6/input.txt";
    }


    public String taskA(List<String> input){

        String[] durations =  input.get(0).substring(11).trim().replace("  ", " ").replace("  ", " ").replace("  ", " ").replace("  ", " ").split(" ");;
        String[] distances = input.get(1).substring(11).trim().replace("  ", " ").replace("  ", " ").replace("  ", " ").replace("  ", " ").split(" ");;

        long sum = 1L;
        for(int r = 0; r < durations.length; r++){
            int count = 0;
            int duration = Integer.parseInt(durations[r]);
            for(int i = 1; i < duration; i++){
                int d = (duration - i) * (i);
                if(d > Long.parseLong(distances[r])){
                    count++;
                }
            }
            sum *= count;
        }

        return String.valueOf(sum);
    }

    public String taskB(List<String> input){
        String durations =  input.get(0).substring(11).trim().replace(" ", "");
        String distances = input.get(1).substring(11).trim().replace(" ", "");

        long sum = 1L;

        long count = 0;
        long duration = Long.parseLong(durations);
        for(int i = 1; i < duration; i++){
            long d = (duration - i) * (i);
            if(d > Long.parseLong(distances)){
                count++;
            }
        }
        sum *= count;

        return String.valueOf(sum);
    }

}
