package twentyfour.d3;

import twentytwo.helper.DailyTask;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Three implements DailyTask {

    public static void main(String[] args) {
        Three day = new Three();
        day.run();
    }

    public String taskA(List<String> input){
        long result = 0;
        String regex = "mul\\((\\d+,\\d+)\\)";
        for(String line : input) {
            List<String[]> pairs = getPairs(line, regex);
            for (String[] p : pairs) {
                result += Long.parseLong(p[0]) * Long.parseLong(p[1]);
            }
        }
        return String.valueOf(result);
    }

    private List<String[]> getPairs(String line, String regex){
        List<String[]> list = new ArrayList<>();
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(line);

        int counter = 0;
        while(matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                System.out.println("Group " + i + ": " + matcher.group(i));
                counter++;
                list.add(matcher.group(i).split(","));
            }
        }

        System.out.println("Found " + counter);

        return list;
    }

    public String taskB(List<String> input){
        int result = 0;

        return String.valueOf(result);
    }
}
