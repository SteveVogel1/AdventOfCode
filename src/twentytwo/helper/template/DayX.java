package twentytwo.helper.template;

import twentytwo.helper.DailyTask;

import java.util.List;

public class DayX implements DailyTask {

    public static void main(String[] args) {
        DayX d = new DayX();
        d.run();
    }


    public String taskA(List<String> input){

        return String.valueOf("");
    }

    public String taskB(List<String> input){

        return String.valueOf("");
    }
}
