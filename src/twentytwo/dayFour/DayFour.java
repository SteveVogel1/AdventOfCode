package twentytwo.dayFour;

import twentytwo.helper.DailyTask;

import java.util.HashSet;
import java.util.List;

public class DayFour implements DailyTask {

    public static void main(String[] args) {
        DayFour day = new DayFour();
        day.run();
    }

    public String getFileName() {
        return "src/twentytwo/dayFour/input.txt";
    }

    private HashSet<String> getSetFromRange(String range){
        HashSet<String> set = new HashSet<>();
        String[] split = range.split("-");
        for(int i = Integer.parseInt(split[0]); i <= Integer.parseInt(split[1]); i++){
            set.add(String.valueOf(i));
        }
        return set;
    }

    public String taskA(List<String> input){
        long amount = 0;
        for(String i: input) {
            String[] line = i.split(",");
            String partA = line[0];
            String partB = line[1];
            HashSet<String> setA = getSetFromRange(partA);
            HashSet<String> setB = getSetFromRange(partB);

            if(setA.containsAll(setB) || setB.containsAll(setA)){
                amount++;
            }
        }
        return String.valueOf(amount);
    }

    public String taskB(List<String> input){
        long amount = 0;
        for(String i: input) {
            String[] line = i.split(",");
            String partA = line[0];
            String partB = line[1];
            HashSet<String> setA = getSetFromRange(partA);
            HashSet<String> setB = getSetFromRange(partB);
            setA.retainAll(setB);
            if(!setA.isEmpty()){
                amount++;
            }
        }
        return String.valueOf(amount);
    }
}
