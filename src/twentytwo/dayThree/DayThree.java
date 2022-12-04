package twentytwo.dayThree;

import twentytwo.helper.DailyTask;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class DayThree implements DailyTask {

    public static void main(String[] args) {
        DayThree dayThree = new DayThree();
        dayThree.run();
    }

    public String getFileName() {
        return "src/twentytwo/dayThree/input.txt";
    }

    public String taskA(List<String> input){
       long points = 0;
       for(String line: input){
           String start = line.substring(0, line.length() / 2);
           String end = line.substring(line.length() / 2);
           for(char letter : start.toCharArray()){
                if(end.contains(String.valueOf(letter))){
                    points += getPriority(letter);
                    break;
                }
           }
       }
       return String.valueOf(points);
    }

    private int getPriority(char character){
        int value = character;
        if(value > 95) {
            value -= 96;
        }else{
            value -= 38;
        }
        return value;
    }

    public String taskB(List<String> input){
        long points = 0;
        for(int i = 0; i < input.size() / 3; i++){
            HashSet<String> setA = new HashSet<>( Arrays.asList(input.get(i*3).split("")));
            HashSet<String> setB = new HashSet<>( Arrays.asList(input.get((i*3)+1).split("")));
            HashSet<String> setC = new HashSet<>( Arrays.asList(input.get((i*3)+2).split("")));

            setA.retainAll(setB);
            setA.retainAll(setC);

            if(setA.size() == 1){
                points += getPriority(((String)setA.toArray()[0]).charAt(0));
            }
        }
        return String.valueOf(points);

    }
}
