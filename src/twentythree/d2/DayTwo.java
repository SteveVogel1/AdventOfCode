package twentythree.d2;

import twentytwo.helper.DailyTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayTwo implements DailyTask {

    public static void main(String[] args) {
        DayTwo dayOne = new DayTwo();
        dayOne.run();
    }

    public String getFileName() {
        return "src/twentythree/d2/input.txt";
    }

    int maxBlue = 14;
    int maxRed = 12;
    int maxGreen = 13;
    public String taskA(List<String> input){

        int sum = 0;
        for(String line : input){
            String[] splitted = line.split(":");
            int gameIndex = Integer.parseInt(splitted[0].substring(5));

            if(!numbersExceeded(splitted[1])){
                sum += gameIndex;
            }

        }


    return String.valueOf(sum);
    }

    private boolean numbersExceeded(String input){
        String[] splitted = input.split(";");
        for(String game: splitted){
            String[] line = game.split(",");
            for(String outcomeLine : line){
                String[] outcom = outcomeLine.trim().split(" ");
                if(outcom[1].equals("blue")){
                    if(Integer.parseInt(outcom[0]) > maxBlue) return true;
                }else if(outcom[1].equals("green")){
                    if(Integer.parseInt(outcom[0]) > maxGreen) return true;
                }else if(outcom[1].equals("red")){
                    if(Integer.parseInt(outcom[0]) > maxRed) return true;
                }
            }
        }
        return false;
    }

    public String taskB(List<String> input){

        int sum = 0;
        for(String line : input){
            String[] splitted = line.split(":");
                sum += getMinNumbersMutliplied(splitted[1]);
        }
        return String.valueOf(sum);
    }

    private int getMinNumbersMutliplied(String input){
        String[] splitted = input.split(";");
        Map<String, Integer> maxNumbers = new HashMap<>();
        for(String game: splitted){
            String[] line = game.split(",");
            for(String outcomeLine : line){
                String[] outcom = outcomeLine.trim().split(" ");
                maxNumbers.put(outcom[1], Math.max(Integer.parseInt(outcom[0]), maxNumbers.getOrDefault(outcom[1], 0)));
            }
        }
        return maxNumbers.values().stream().reduce((a, b) -> a*b).orElse(0);
    }
}
