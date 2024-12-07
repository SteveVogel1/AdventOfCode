package twentyfour.d7;

import twentytwo.helper.DailyTask;

import java.util.List;

public class Seven implements DailyTask {
    public static void main(String[] args) {
        Seven day = new Seven();
        day.run();
    }

    public String taskA(List<String> input){
        long result = 0;

        for(String line : input) {
            String[] values = line.split(" ");
            long expected = Long.parseLong(values[0].substring(0, values[0].length()-1));
            if(check(expected, 0, false, List.of(values).subList(1, values.length).stream().map(Integer::valueOf).toList())){
                result += expected;
            }
        }

        return String.valueOf(result);
    }

    private boolean check(long expected, long current, boolean concat, List<Integer> values){
        if(expected == current){
            return true;
        }
        if(values.isEmpty() || current > expected){
            return false;
        }

        return check(expected, current + values.getFirst(), concat, values.subList(1,values.size())) || check(expected, current * values.getFirst(), concat, values.subList(1,values.size()))
                || (concat);
    }

    public String taskB(List<String> input){
        long result = 0;
        for(String line : input) {
            String[] values = line.split(" ");
            long expected = Long.parseLong(values[0].substring(0, values[0].length()-1));
            if(check(expected, 0, true, List.of(values).subList(1, values.length).stream().map(Integer::valueOf).toList())){
                result += expected;
            }
        }
        return String.valueOf(result);
    }
}