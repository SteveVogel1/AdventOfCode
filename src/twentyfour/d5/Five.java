package twentyfour.d5;

import twentytwo.helper.DailyTask;

import java.util.*;

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

        int inputSplitIndex = input.indexOf("");
        List<int[]> rules = readRules(input, inputSplitIndex);
        List<String> pages = getPages(input, inputSplitIndex);

        for(String line : pages){
            String[] values = line.split(",");
            if(!checkRules(values, rules)){
                String[] orderValues = orderValues(values, rules);
                result += Long.parseLong(orderValues[(orderValues.length/2)]);
            }
        }
        return String.valueOf(result);
    }

    private String[] orderValues(String[] pages, List<int[]> rules){
        Map<Integer, Integer> lookup = new HashMap<>();
        for(int i = 0; i < pages.length; i++){
            lookup.put(Integer.parseInt(pages[i]), i);
        }

        for(int i = 0; i < rules.size(); i++){
            int[] rule = rules.get(i);
            Integer indexA = lookup.get(rule[0]);
            Integer indexB = lookup.get(rule[1]);
            if(indexA == null || indexB == null){
                continue;
            }
            if(indexA > indexB) { //SWAP POSITIONS and recheck all rules
                lookup.put(rule[0], indexB);
                lookup.put(rule[1], indexA);
                i = -1;
            }
        }

        String[] ordered = new String[pages.length];
        lookup.forEach( (value, index) -> ordered[index] = String.valueOf(value));
        return ordered;
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