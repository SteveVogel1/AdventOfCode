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

        ArrayList<String> system = getSystem(line);

        defragmentation(system);

        result = getResult(system, result);
        return String.valueOf(result);
    }

    public String taskB(List<String> input){
        long result = 0;
        String line = input.getFirst();

        ArrayList<String> system = getSystem(line);
        defragmentationBlock(system);

        result = getResult(system, result);
        return String.valueOf(result);
    }

    private void defragmentation(ArrayList<String> system) {
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
    }

    private void defragmentationBlock(ArrayList<String> system) {
        int leftIndex = 0;
        int rightIndex = system.size() -1;
        while(rightIndex > 0){
            int lengthBlock = 0;
            while(system.get(rightIndex).equals(".")){
                rightIndex--;
            }
            while(rightIndex-lengthBlock > 0 && system.get(rightIndex).equals(system.get(rightIndex-lengthBlock))){
                lengthBlock++;
            }

            while(true){
                int lengthSpace = 1;
                while(!system.get(leftIndex).equals(".")) {
                    leftIndex++;
                }

                while(leftIndex+lengthSpace < rightIndex && system.get(leftIndex+lengthSpace).equals(".")){
                    lengthSpace++;
                }
                if(leftIndex+lengthSpace > rightIndex){
                    break;
                }
                if(lengthSpace >= lengthBlock){
                    for(int s = 0; s < lengthBlock; s++) {
                        system.set(leftIndex + s, system.get(rightIndex -s));
                        system.set(rightIndex -s, ".");
                    }
                    break;
                }
                leftIndex = leftIndex + lengthSpace;
            }

            leftIndex = 0;
            rightIndex -= lengthBlock;
        }
    }

    private ArrayList<String> getSystem(String line) {
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
        return system;
    }

    private long getResult(ArrayList<String> system, long result) {
        for(int i = 0; i < system.size(); i++){
            if(!system.get(i).equals(".")) {
                result += i * Long.parseLong(system.get(i));
            }
        }
        return result;
    }
}