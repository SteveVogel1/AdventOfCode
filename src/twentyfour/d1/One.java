package twentyfour.d1;

import twentytwo.helper.DailyTask;

import java.util.*;

public class One implements DailyTask {

    public static void main(String[] args) {
        One day = new One();
        day.run();
    }

    public String taskA(List<String> input){

        List<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();
        for(String line : input){
            String[] values = line.split("   ");
            leftList.add(Integer.valueOf(values[0]));
            rightList.add(Integer.valueOf(values[1]));
        }

        Collections.sort(leftList);
        Collections.sort(rightList);

        int distance = 0;
        for(int i = 0; i < leftList.size(); i++){
            int a = leftList.get(i);
            int b = rightList.get(i);
            distance +=  Math.abs(a - b);
        }

        return String.valueOf(distance);
    }

    public String taskB(List<String> input){
        List<Integer> leftList = new ArrayList<>();
        Map<Integer, Integer> rightList = new HashMap<>();
        for(String line : input){
            String[] values = line.split("   ");
            leftList.add(Integer.valueOf(values[0]));

            Integer rightKey = Integer.valueOf(values[1]);
            if(rightList.containsKey(rightKey)){
                rightList.put(rightKey, rightList.get(rightKey) + 1);
            }else {
                rightList.put(rightKey, 1);
            }
        }

        long value = 0;
        for(Integer i : leftList){
            value += (long) i * rightList.getOrDefault(i, 0);
        }
        return String.valueOf(value);
    }
}
