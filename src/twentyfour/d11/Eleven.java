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
        Map<String, Long> line = createStones(input);

        var x = getLastLine(numbersOfBlink, line).values();
        result = x.stream().mapToLong(Long::longValue).sum();

        return String.valueOf(result);
    }

    public String taskB(List<String> input){
        long result = 0;

        int numbersOfBlink = 75;
        Map<String, Long> line = createStones(input);

        var x = getLastLine(numbersOfBlink, line).values();
        result = x.stream().mapToLong(Long::longValue).sum();

        return String.valueOf(result);
    }

    private Map<String, Long> createStones(List<String> input) {
        Map<String, Long> stoneMap = new HashMap<>();
        for(String stone: input.getFirst().split(" ")){
            stoneMap.put(stone, stoneMap.getOrDefault(stone, 0L) + 1);
        }
        return stoneMap;
    }

    private Map<String, Long>  getLastLine(int numbersOfBlink, Map<String, Long> line) {
        for(int i = 0; i < numbersOfBlink; i++){
            Map<String, Long> nextLine = new HashMap<>();
            for(String stone: line.keySet()){
                long count = line.get(stone);
                if(stone.equals("0")){
                    nextLine.put("1", nextLine.getOrDefault("1", 0L) + count);
                }else if(stone.length() % 2 == 0){
                    String leftValue = removeLeadingZeros(stone.substring(0, stone.length() / 2));
                    nextLine.put(leftValue, nextLine.getOrDefault(leftValue, 0L) + count);
                    String rightValue = removeLeadingZeros(stone.substring(stone.length() / 2));
                    nextLine.put(rightValue, nextLine.getOrDefault(rightValue, 0L) + count);
                }else{
                    String value = String.valueOf(Long.parseLong(stone) * 2024);
                    nextLine.put(value, nextLine.getOrDefault(value, 0L) + count);
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