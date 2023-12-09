package twentytwo.helper.template;

import twentytwo.helper.DailyTask;

import java.util.List;

public class DayX implements DailyTask {

    public static void main(String[] args) {
        DayX dayOne = new DayX();
        dayOne.run();
    }

    public String getFileName() {
        return "src/twentythree/dX/input.txt";
    }


    public String taskA(List<String> input){

        return String.valueOf("");
    }

    public String taskB(List<String> input){

        return String.valueOf("");
    }
}
