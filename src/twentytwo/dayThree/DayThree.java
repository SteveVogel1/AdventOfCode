package twentytwo.dayThree;

import twentytwo.helper.DailyTask;
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
        return "";
    }
}
