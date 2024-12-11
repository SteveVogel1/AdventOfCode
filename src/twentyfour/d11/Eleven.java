package twentyfour.d11;

import twentytwo.helper.DailyTask;

import java.util.*;

public class Eleven implements DailyTask {
    public static void main(String[] args) {
        Eleven day = new Eleven();
        day.run();
    }

    public String taskA(List<String> input){
        long result = 0;

        int numbersOfBlink = 25;
        List<String> line = createStones(input);

        result = getLastLine(numbersOfBlink, line).size();

        return String.valueOf(result);
    }

    public String taskB(List<String> input){
        long result = 0;

        int numbersOfBlink = 75;
        List<String> line = createStones(input);

        result = getLastLine(numbersOfBlink, line).size();

        return String.valueOf(result);
    }

    private List<String> createStones(List<String> input) {
        return new ArrayList<>(Arrays.asList(input.getFirst().split(" ")));
    }

    private List<String>  getLastLine(int numbersOfBlink, List<String> line) {
        for(int i = 0; i < numbersOfBlink; i++){
            List<String> nextLine = new ArrayList<>();
            for(String stone: line){
                if(stone.equals("0")){
                    nextLine.add("1");
                }else if(stone.length() % 2 == 0){
                    nextLine.add(removeLeadingZeros(stone.substring(0, stone.length() / 2)));
                    nextLine.add(removeLeadingZeros(stone.substring(stone.length() / 2)));
                }else{
                    nextLine.add(String.valueOf(Long.parseLong(stone) * 2024));
                }
            }
            line = nextLine;
        }
        return line;
    }

    private String removeLeadingZeros(String input){
        return input.replaceAll("^0+(?!$)", "");
    }
}