package twentytwo.helper;

import java.util.List;

public interface DailyTask {
    String taskA(List<String> input);
    String taskB(List<String> input);

    String getFileName();

    private List<String> readInput(){
        return new Helper().readFile(getFileName());
    }

    default void run(){
        List<String> input = readInput();
        System.out.println("Task A:");
        System.out.println(taskA(input));
        System.out.println("Task B:");
        System.out.println(taskB(input));
    }
}
