package twentyfour.d9;

import twentytwo.helper.DailyTask;

import java.util.*;

public class Nine implements DailyTask {
    public static void main(String[] args) {
        Nine day = new Nine();
        day.run();
    }

    public String taskA(List<String> input){
        long result = 0;
        String line = input.getFirst();

        boolean isFile = true;
        long index = 0;

        ArrayList<String> system = new ArrayList<>();
        for(String s : line.split("")){
            for(int i = 0; i < Integer.parseInt(s); i++){
                system.add(isFile ? String.valueOf(index) : ".");
            }

            //Next input preparation
            if(isFile){
                index++;
            }
            isFile = !isFile;
        }

        int leftIndex = 0;
        int rightIndex = system.size() -1;
        while(true){
            while(!system.get(leftIndex).equals(".")){
                leftIndex++;
            }
            while(system.get(rightIndex).equals(".")){
                rightIndex--;
            }
            if(leftIndex >= rightIndex){
                break;
            }
            system.set(leftIndex, system.get(rightIndex));
            system.set(rightIndex, ".");
            leftIndex++;
            rightIndex--;
        }

        for(int i = 0; i < system.size(); i++){
            if(!system.get(i).equals(".")) {
                result += i * Long.parseLong(system.get(i));
            }
        }
        return String.valueOf(result);
    }

    public String taskB(List<String> input){
        long result = 0;
        return String.valueOf(result);
    }
}