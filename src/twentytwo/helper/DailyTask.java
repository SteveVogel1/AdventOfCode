package twentytwo.helper;

import javax.sound.midi.Soundbank;
import java.util.List;

public interface DailyTask {
    String taskA(List<String> input);
    String taskB(List<String> input);

    default String getFileName(){
        return "src/" + this.getClass().getPackageName().replace(".", "/") + "/input.txt";
    }

    private List<String> readInput(){
        return new Helper().readFile(getFileName());
    }

    default void run(){
        long startTime = System.currentTimeMillis();
        List<String> input = readInput();
        System.out.println("Task A:");
        System.out.println(taskA(input));
        System.out.println("Task B:");
        System.out.println(taskB(input));
        long endTime = System.currentTimeMillis();

        System.out.println("Runtime: " + (endTime - startTime) + "ms");
    }
    //TODO:
    // eingabe von Beispiel, überprüfung auf richtigkeit und erst dann das andere ausführen
}
