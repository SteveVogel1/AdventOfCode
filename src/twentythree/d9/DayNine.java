package twentythree.d9;

import twentytwo.helper.DailyTask;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DayNine implements DailyTask {

    public static void main(String[] args) {
        DayNine dayOne = new DayNine();
        dayOne.run();
    }

    public String taskA(List<String> input){

        int result = 0;
        for(String line : input){
            Integer[] numbers = Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList()).toArray(Integer[]::new);

            int sum = getSumOfLastValues(numbers);
            result += sum;
        }

        return String.valueOf(result);
    }

    private static int getSumOfLastValues(Integer[] numbers) {
        int n = numbers.length;
        int sum = numbers[n-1];
        for (int i = 1; i < n; i++) {
            Integer[] tempLine = new Integer[n - i];
            for (int j = 0; j < n - i; j++) {
                tempLine[j] = numbers[j + 1] - numbers[j];
            }
            numbers = tempLine;
            sum += tempLine[tempLine.length-1];
        }
        return sum;
    }

    public String taskB(List<String> input){
        int result = 0;
        for(String line : input){
            Integer[] numbers = Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList()).toArray(Integer[]::new);
            Collections.reverse(Arrays.asList(numbers));

            int sum = getSumOfLastValues(numbers);
            result += sum;
        }

        return String.valueOf(result);
    }
}
