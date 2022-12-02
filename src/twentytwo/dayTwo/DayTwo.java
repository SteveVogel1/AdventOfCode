package twentytwo.dayTwo;

import twentytwo.helper.DailyTask;
import java.util.*;

public class DayTwo implements DailyTask {

    public static void main(String[] args) {
        DayTwo dayTwo = new DayTwo();
        dayTwo.run();
    }

    public String getFileName() {
        return "src/twentytwo/dayTwo/input.txt";
    }

    ArrayList<String> selections = new ArrayList<>(List.of("A", "B", "C"));
    ArrayList<String> selectionsUser = new ArrayList<>(List.of("X", "Y", "Z"));
    String[] beatenBy = new String[] {"Y", "Z", "X"};
    Map<String, Integer> points = Map.of("X", 1, "Y", 2, "Z", 3);

    public String taskA(List<String> input){
        long totalPoints = 0;
        for(String line : input){
            if(!line.isEmpty()){
                String[] inputs = line.split(" ");

                totalPoints += points.get(inputs[1]);
                if(inputs[0].equals(getConvertedInput(inputs[1]))){
                    totalPoints += 3;
                }else if(inputs[1].equals(beatenBy[selections.indexOf(inputs[0])])){
                    totalPoints += 6;
                }
            }
        }

        return String.valueOf(totalPoints);
    }

    private String getConvertedInput(String input){
        if(selectionsUser.contains(input)) {
            return selections.get(selectionsUser.indexOf(input));
        }else{
            return selectionsUser.get(selections.indexOf(input));
        }
    }

    public String taskB(List<String> input){
        Map<String, Integer> winningPoints = Map.of("X", 0, "Y", 3, "Z", 6);
        long totalPoints = 0;
        for(String line : input){
            if(!line.isEmpty()){
                String[] inputs = line.split(" ");

                totalPoints += winningPoints.get(inputs[1]);
                String response = getAction(inputs[0], inputs[1]);
                totalPoints += points.get(response);
            }
        }
        return String.valueOf(totalPoints);
    }

    private String getAction(String computerValue, String myAction){
        int index = selections.indexOf(computerValue);
        if(myAction.equals("X")){
            return beatenBy[(index + 1) % 3];
        }else if(myAction.equals("Y")){
            return getConvertedInput(computerValue);
        }else{
            return beatenBy[index];
        }
    }
}
