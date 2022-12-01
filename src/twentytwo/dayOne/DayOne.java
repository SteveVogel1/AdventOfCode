package twentytwo.dayOne;

import twentytwo.helper.Helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DayOne {

    public static void main(String[] args) {
        DayOne dayOne = new DayOne();
        dayOne.run();
    }


    private void run(){
        taskA();
        taskB();
    }

    private void taskA(){
        List<String> input = new Helper().readFile("src/twentytwo/dayOne/input.txt");
        List<Long> calories = new ArrayList<>();
        Long calory = 0L;
        for(String line : input){
            if(line.isEmpty()){
                calories.add(calory);
                calory = 0L;
            }else {
                calory += Long.valueOf(line);
            }
        }
        calories.add(calory);

        System.out.println(calories.stream().max(Comparator.comparingLong(value -> value)).get());
    }

    private void taskB(){
        List<String> input = new Helper().readFile("src/twentytwo/dayOne/input.txt");
        List<Long> calories = new ArrayList<>();
        Long calory = 0L;
        for(String line : input){
            if(line.isEmpty()){
                calories.add(calory);
                calory = 0L;
            }else {
                calory += Long.valueOf(line);
            }
        }
        calories.add(calory);

        System.out.println(calories.stream().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.summingLong(Long::longValue)));
    }
}
