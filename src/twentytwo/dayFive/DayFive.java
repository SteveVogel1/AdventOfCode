package twentytwo.dayFive;

import twentytwo.helper.DailyTask;

import java.util.*;
import java.util.stream.Collectors;

public class DayFive implements DailyTask {

    public static void main(String[] args) {
        DayFive day = new DayFive();
        day.run();
    }

    public String getFileName() {
        return "src/twentytwo/dayFive/input.txt";
    }



    public String taskA(List<String> input){
        int maxLength = input.stream().mapToInt(String::length).max().orElse(0) + 3;
        ArrayList<Stack<String>> stacks = new ArrayList<>();
        for(int i = 0; i < (maxLength / 4); i++){
            stacks.add(new Stack<>());
        }
        int inputSplitIndex = 0;
        //Parse init stack
        for(String line : input) {
            if(line.isEmpty() || line.contains("1")){
                inputSplitIndex = input.indexOf(line) + 1;
                break;
            }
            for (int i = 0; i < (line.length() + 3) / 4; i++) {
                String character = String.valueOf(line.charAt(i*4 + 1));
                if(!character.isBlank()) {
                    stacks.get(i).add(0,character);
                }
            }
        }

        //parse movements
        for(int i = inputSplitIndex+1; i < input.size(); i++){
            String line = input.get(i);
            line = line.replaceAll("[a-zA-Z]", "");
            line = line.replaceAll("  ", " ");
            String[] commands = line.trim().split(" ");
            for(int x = 0; x < Integer.parseInt(commands[0]); x++){
                String container = stacks.get(Integer.parseInt(commands[1])-1).pop();
                stacks.get(Integer.parseInt(commands[2])-1).push(container);
            }
        }
        return stacks.stream().map(Stack::peek).collect(Collectors.joining());
    }

    public String taskB(List<String> input){
        int maxLength = input.stream().mapToInt(String::length).max().orElse(0) + 3;
        ArrayList<Stack<String>> stacks = new ArrayList<>();
        for(int i = 0; i < (maxLength / 4); i++){
            stacks.add(new Stack<>());
        }
        int inputSplitIndex = 0;
        //Parse init stack
        for(String line : input) {
            if(line.isEmpty() || line.contains("1")){
                inputSplitIndex = input.indexOf(line) + 1;
                break;
            }
            for (int i = 0; i < (line.length() + 3) / 4; i++) {
                String character = String.valueOf(line.charAt(i*4 + 1));
                if(!character.isBlank()) {
                    stacks.get(i).add(0,character);
                }
            }
        }

        //parse movements
        for(int i = inputSplitIndex+1; i < input.size(); i++){
            String line = input.get(i);
            line = line.replaceAll("[a-zA-Z]", "");
            line = line.replaceAll("  ", " ");
            String[] commands = line.trim().split(" ");
            Stack<String> tmpStack = new Stack<>();
            for(int x = 0; x < Integer.parseInt(commands[0]); x++){
                String container = stacks.get(Integer.parseInt(commands[1])-1).pop();
                tmpStack.push(container);
            }
            while(!tmpStack.isEmpty()) {
                String container = tmpStack.pop();
                stacks.get(Integer.parseInt(commands[2]) - 1).push(container);
            }
        }
        return stacks.stream().map(Stack::peek).collect(Collectors.joining());
    }
}
