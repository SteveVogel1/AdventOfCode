package twentyfour.d2;

import twentytwo.helper.DailyTask;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Two implements DailyTask {

    public static void main(String[] args) {
        Two day = new Two();
        day.run();
    }

    public String taskA(List<String> input){

        int safe = 0;
        for(String line : input){
            List<Integer> parsedLine = Arrays.stream(line.split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            if(checkLine(parsedLine)){
                safe++;
            }
        }

        return String.valueOf(safe);
    }

    private boolean checkLine(List<Integer> list){
        return correctJumps(list) && checkContinuity(list);
    }

    private boolean correctJumps(List<Integer> list){
        for(int i = 0; i < list.size() -1; i++){
            if(Objects.equals(list.get(i), list.get(i + 1)) || Math.abs(list.get(i) - list.get(i+1)) > 3){
                return false;
            }
        }
        return true;
    }

    private boolean checkContinuity(List<Integer> list){
        int step = list.get(0).compareTo(list.get(1));
        for(int i = 0; i < list.size() -1; i++){
            if(list.get(i).compareTo(list.get(i+1)) != step){
                return false;
            }
        }
        return true;
    }

    public String taskB(List<String> input){
        int safe = 0;
        for(String line : input){
            List<Integer> parsedLine = Arrays.stream(line.split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            for(int i = 0; i < parsedLine.size(); i++) {
                List<Integer> modifiedLine = Stream.concat(
                        parsedLine.subList(0, i).stream(),
                        parsedLine.subList(i+1, parsedLine.size())
                                .stream()).collect(Collectors.toList());

                if (checkLine(modifiedLine)) {
                    safe++;
                    break;
                }
            }
        }
        return String.valueOf(safe);
    }
}
