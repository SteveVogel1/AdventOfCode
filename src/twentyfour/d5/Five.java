package twentyfour.d5;

import twentytwo.helper.DailyTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Five implements DailyTask {
    public static void main(String[] args) {
        Five day = new Five();
        day.run();
    }

    public String taskA(List<String> input){
        long result = 0;

        int inputSplitIndex = input.indexOf("");
        List<int[]> rules = readRules(input, inputSplitIndex);
        List<String> pages = getPages(input, inputSplitIndex);

        for(String line : pages){
            String[] values = line.split(",");
            if(checkRules(values, rules)){
                result += Long.parseLong(values[(values.length/2)]);
            }
        }

        return String.valueOf(result);
    }

    public String taskB(List<String> input){
        long result = 0;
        return String.valueOf(result);
    }

    private boolean checkRules(String[] pages, List<int[]> rules){
        Map<Integer, Integer> lookup = new HashMap<>();
        for(int i = 0; i < pages.length; i++){
            lookup.put(Integer.parseInt(pages[i]), i);
        }

        for(int[] rule: rules){
            Integer indexA = lookup.get(rule[0]);
            Integer indexB = lookup.get(rule[1]);
            if(indexA == null || indexB == null){
                continue;
            }
            if(indexA > indexB) {
                return false;
            }

        }

        return true;
    }

    private List<int[]> readRules(List<String> input, int splitIndex){
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < splitIndex; i++){
            String[] tmp =  input.get(i).split("\\|");
            int[] rule = new int[]{ Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])};
            list.add(rule);
        }
        return list;
    }

    private List<String> getPages(List<String> input, int splitIndex){
        return input.subList(splitIndex+1, input.size());
    }
}