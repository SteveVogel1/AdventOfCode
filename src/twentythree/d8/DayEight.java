package twentythree.d8;

import twentytwo.helper.DailyTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DayEight implements DailyTask {

    public static void main(String[] args) {
        DayEight dayOne = new DayEight();
        dayOne.run();
    }

    public String getFileName() {
        return "src/twentythree/d8/input.txt";
    }



    class Pair{
        String value;
        String leftValue;
        String rightValue;

        public Pair(String value, String leftValue, String rightValue) {
            this.value = value;
            this.leftValue = leftValue;
            this.rightValue = rightValue;
        }
    }

    char[] pattern;
    HashMap<String, Pair> lookup = new HashMap<>();

    public String taskA(List<String> input){
        for(String line : input){
            if(pattern == null){
                pattern = line.toCharArray();
            }else if(line != null && !line.isEmpty()){
                Pair p = new Pair(line.substring(0,3), line.substring(7,10), line.substring(12, 15));
                lookup.put(p.value, p);
            }
        }

        Pair curPos = lookup.get("AAA");
        int counter = 0;
        while(!curPos.value.equals("ZZZ")){
            if(pattern[counter % pattern.length] == 'L'){
                curPos = lookup.get(curPos.leftValue);
            }else{
                curPos = lookup.get(curPos.rightValue);
            }


            counter++;
        }

        return String.valueOf(counter);
    }

    public String taskB(List<String> input){
        for(String line : input){
            if(pattern == null){
                pattern = line.toCharArray();
            }else if(line != null && !line.isEmpty()){
                Pair p = new Pair(line.substring(0,3), line.substring(7,10), line.substring(12, 15));
                lookup.put(p.value, p);
            }
        }

        List<Pair> startPositions = lookup.entrySet().stream().filter(e -> e.getKey().endsWith("A")).map(Map.Entry::getValue).collect(Collectors.toList());
        List<Long> counters = new ArrayList<>();
        long minCounter = Long.MAX_VALUE;

        for(Pair p : startPositions) {
            Pair curPos = p;
            long counter = 0;
            while (!curPos.value.endsWith("Z")) {
                int index = (int) (counter % pattern.length);
                if (pattern[index] == 'L') {
                    curPos = lookup.get(curPos.leftValue);
                } else {
                    curPos = lookup.get(curPos.rightValue);
                }
                counter++;
            }
            minCounter = Math.min(minCounter, counter);
            counters.add(counter);
        }

        long lcm = counters.stream().reduce((this::getLcm)).get();

        return String.valueOf(lcm);
    }


    private long getLcm(long a, long b)
    {
        return a * (b / gcd(a, b));
    }

    private Long gcd(long a, long b) {
        while (b > 0)
        {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

}
