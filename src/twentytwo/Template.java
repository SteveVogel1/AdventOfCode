package twentytwo;

import twentytwo.helper.DailyTask;

import java.util.List;

public class Template implements DailyTask {

    public static void main(String[] args) {
        Template day = new Template();
        day.run();
    }

    public String getFileName() {
        return "src/twentytwo/daySix/input.txt";
    }

    public String taskA(List<String> input){
        return "";
    }

    public String taskB(List<String> input){
        return "";
    }
}
