package twentytwo.dayOne;

import twentytwo.helper.DailyTask;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DayOne implements DailyTask {

    public static void main(String[] args) {
        DayOne dayOne = new DayOne();
        dayOne.run();
    }


    public String taskA(List<String> input){
        List<Long> calories = new ArrayList<>();
        long calory = 0L;
        for(String line : input){
            if(line.isEmpty()){
                calories.add(calory);
                calory = 0L;
            }else {
                calory += Long.parseLong(line);
            }
        }
        calories.add(calory);

        return String.valueOf(calories.stream().max(Comparator.comparingLong(value -> value)).get());
    }

    public String taskB(List<String> input){
        List<Long> calories = new ArrayList<>();
        long calory = 0L;
        for(String line : input){
            if(line.isEmpty()){
                calories.add(calory);
                calory = 0L;
            }else {
                calory += Long.parseLong(line);
            }
        }
        calories.add(calory);

        return String.valueOf(
                calories.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToLong(Long::longValue)
                .sum());
    }
}
